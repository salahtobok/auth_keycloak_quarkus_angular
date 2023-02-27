/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.common.business;

import com.admin.Condition;
import com.admin.FilterModel;
import com.admin.LazyFilterModel;
import org.primefaces.model.SortOrder;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ************************
 *
 * @author @AFCSOFT ****** ************************
 */
public abstract class AbstractDatabaseService<T> implements Serializable {

    private Class<T> entityClass;

    public AbstractDatabaseService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findAllOrderById() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root c = cq.from(entityClass);
        cq.select(c);
        cq.orderBy(cb.asc(c.get("id")));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findAllOrderByAttribut(String attribut) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root c = cq.from(entityClass);
        cq.select(c);
        cq.orderBy(cb.asc(c.get(attribut)));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findBy(T entity, SingularAttribute... attributes) {
        CriteriaQuery cq = findByCriteriaBuilder(entity, "EQUALS", attributes);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findBy(T entity, int first, int max, SingularAttribute... attributes) {
        CriteriaQuery cq = findByCriteriaBuilder(entity, "EQUALS", attributes);
        return getEntityManager().createQuery(cq).setFirstResult(first).setMaxResults(max).getResultList();
    }

    public List<T> findByLike(T entity, SingularAttribute... attributes) {
        CriteriaQuery cq = findByCriteriaBuilder(entity, "LIKE", attributes);
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findByLike(T entity, int first, int max, SingularAttribute... attributes) {
        CriteriaQuery cq = findByCriteriaBuilder(entity, "LIKE", attributes);
        return getEntityManager().createQuery(cq).setFirstResult(first).setMaxResults(max).getResultList();
    }

    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }


    public List<T> findRange(int first, int max, String sortAttribute, SortOrder sortOrder, List<FilterModel> filterConditions) {
        CriteriaQuery cq = findByCriteriaBuilderCondition(sortAttribute, sortOrder, filterConditions, false);
        return getEntityManager().createQuery(cq).setFirstResult(first).setMaxResults(max).getResultList();
    }

    public List<T> findRange(int first, int max, Map<String, SortOrder> sortAttribute, List<FilterModel> filterConditions) {
        CriteriaQuery cq = findByCriteriaBuilderCondition(sortAttribute, null, filterConditions, false);
        return getEntityManager().createQuery(cq).setFirstResult(first).setMaxResults(max).getResultList();
    }

    public List<T> findRange(int first, int max, List<FilterModel> filterConditions) {
        CriteriaQuery cq = this.findByCriteriaBuilderCondition((Object) null, (SortOrder) null, filterConditions, false);
        return this.getEntityManager().createQuery(cq).setFirstResult(first).setMaxResults(max).getResultList();
    }

    public List<T> findRange(List<FilterModel> filterConditions) {
        CriteriaQuery cq = this.findByCriteriaBuilderCondition((Object) null, (SortOrder) null, filterConditions, false);
        return this.getEntityManager().createQuery(cq).getResultList();
    }

    public int count(List<FilterModel> filterConditions) {
        CriteriaQuery<Long> cq;
        cq = findByCriteriaBuilderCondition(null, null, filterConditions, true);
        return getEntityManager().createQuery(cq).getSingleResult().intValue();
    }

    public List<T> search(LazyFilterModel lazyFilterModel) {
        CriteriaQuery cq = findByCriteriaBuilderCondition(lazyFilterModel.getSortAttribute(), lazyFilterModel.getSortOrder(), lazyFilterModel.getFilterModels(), false);
        return getEntityManager().createQuery(cq).setFirstResult(lazyFilterModel.getPage() * lazyFilterModel.getPageSize()).setMaxResults(lazyFilterModel.getPageSize()).getResultList();
    }

    public int count(LazyFilterModel lazyFilterModel) {
        CriteriaQuery<Long> cq;
        cq = findByCriteriaBuilderCondition(null, null, lazyFilterModel.getFilterModels(), true);
        return getEntityManager().createQuery(cq).getSingleResult().intValue();
    }


    private CriteriaQuery findByCriteriaBuilderCondition(Object sortAttribute, SortOrder sortOrder, List<FilterModel> filterConditions, Boolean count) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate filterCondition = cb.conjunction();
        Root<T> entityRoot = cq.from(entityClass);
        for (FilterModel filterModel : filterConditions) {
            Path<String> path;
            if (filterModel.getAttribute() != null) {
                path = entityRoot.get(filterModel.getAttribute());
            } else {
                path = getRealPath(filterModel.getAttributeStr(), entityRoot);
            }
            switch (filterModel.getFilterMatchMode()) {
                case EXACT:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.equal(path, filterModel.getValue()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.equal(path, filterModel.getValue()));
                    }
                    break;
                case EQUALS:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.equal(cb.lower(path), filterModel.getValue().toString().toLowerCase()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.equal(cb.lower(path), filterModel.getValue().toString().toLowerCase()));
                    }
                    break;
                case NOT_EQUALS:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.notEqual(cb.lower(path), filterModel.getValue().toString().toLowerCase()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.notEqual(cb.lower(path), filterModel.getValue().toString().toLowerCase()));
                    }
                    break;
                case CONTAINS:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.like(cb.lower(path), "%" + filterModel.getValue().toString().toLowerCase() + "%"));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.like(cb.lower(path), "%" + filterModel.getValue().toString().toLowerCase() + "%"));
                    }
                    break;
                case STARTS_WITH:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.like(cb.lower(path), filterModel.getValue().toString().toLowerCase() + "%"));
                    } else {

                        filterCondition = cb.or(filterCondition, cb.like(cb.lower(path), filterModel.getValue().toString().toLowerCase() + "%"));
                    }
                    break;
                case ENDS_WITH:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.like(cb.lower(path), "%" + filterModel.getValue().toString().toLowerCase()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.like(cb.lower(path), "%" + filterModel.getValue().toString().toLowerCase()));
                    }
                    break;
                case LESS_THAN:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.lessThan(path, filterModel.getValue().toString()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.lessThan(path, filterModel.getValue().toString()));
                    }
                    break;
                case LESS_THAN_EQUALS:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.lessThanOrEqualTo(path, filterModel.getValue().toString()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.lessThanOrEqualTo(path, filterModel.getValue().toString()));
                    }
                    break;
                case GREATER_THAN:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.greaterThan(path, filterModel.getValue().toString()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.greaterThan(path, filterModel.getValue().toString()));
                    }
                    break;
                case GREATER_THAN_EQUALS:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.greaterThanOrEqualTo(path, filterModel.getValue().toString()));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.greaterThanOrEqualTo(path, filterModel.getValue().toString()));
                    }
                    break;
                case IN:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, path.in(getListFromStringExp(filterModel.getValue())));
                    } else {
                        filterCondition = cb.or(filterCondition, path.in(getListFromStringExp(filterModel.getValue())));
                    }
                    break;
                case IN_DISTINCT:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, path.in(getListFromStringExp(filterModel.getValue())));
                        cq.distinct(true);
                    } else {
                        filterCondition = cb.or(filterCondition, path.in(getListFromStringExp(filterModel.getValue())));
                        cq.distinct(true);
                    }
                    break;
                case NOT_IN:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.not(path.in(getListFromStringExp(filterModel.getValue()))));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.not(path.in(getListFromStringExp(filterModel.getValue()))));
                    }
                    break;
                case IS_NULL:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.isNull(path));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.isNull(path));
                    }
                    break;
                case IS_NOT_NULL:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.isNotNull(path));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.isNotNull(path));
                    }
                    break;
                case IS_EMPTY:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.isEmpty(entityRoot.get(filterModel.getAttributeStr())));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.isEmpty(entityRoot.get(filterModel.getAttribute())));
                    }
                    break;

                case IS_NOT_EMPTY:
                    if (filterModel.getCondition() == Condition.AND) {
                        filterCondition = cb.and(filterCondition, cb.isNotEmpty(entityRoot.get(filterModel.getAttribute())));
                    } else {
                        filterCondition = cb.or(filterCondition, cb.isNotEmpty(entityRoot.get(filterModel.getAttribute())));
                    }
                    break;

            }
        }
         cq.distinct(true);
        if (count) {
//            cq.select(cb.count(entityRoot));
            cq.select(cb.countDistinct(entityRoot));
        }
        if (sortAttribute != null) {
//            filterConditions.forEach(System.out::println);
            if (sortOrder != null && sortAttribute.toString().contains(".") && !filterConditions.stream()
                    .filter(value -> value.getAttribute() != null)
                    .filter(value -> value.getAttribute().equals(sortAttribute.toString()))
                    .filter(value -> value.getValue() != null)
                    .findFirst().isPresent()) {
                //  filterCondition = cb.and(filterCondition, cb.like(getRealPath(sortAttribute.toString(), entityRoot), "%"));               
//                Predicate itsTrue = cb.conjunction();
//                filterCondition = cb.and(filterCondition, cb.isTrue(itsTrue));
//                filterCondition = cb.and(filterCondition, getRealPath(sortAttribute.toString(), entityRoot).isNull());
//                filterCondition = cb.or(filterCondition, cb.equal(cb.literal(1), 1));
            }
            cq.where(filterCondition);
            if (sortOrder == null) {
                cq.orderBy(orderConditions((Map<String, SortOrder>) sortAttribute, cb, entityRoot));
            } else {
                switch (sortOrder) {
                    case ASCENDING:
                        if (sortAttribute instanceof SingularAttribute) {
                            cq.orderBy(cb.asc(entityRoot.get((SingularAttribute) sortAttribute)));
                        } else {
                            cq.orderBy(cb.asc(getRealPath(sortAttribute.toString(), entityRoot)));
                        }
                        break;
                    case DESCENDING:
                        if (sortAttribute instanceof SingularAttribute) {
                            cq.orderBy(cb.desc(entityRoot.get((SingularAttribute) sortAttribute)));
                        } else {
                            cq.orderBy(cb.desc(getRealPath(sortAttribute.toString(), entityRoot)));
                        }
                        break;
                }
            }
        } else {
            cq.where(filterCondition);
        }
        return cq;
    }

    private List<Object> getListFromStringExp(Object exp) {
        if (exp instanceof String) {
            String exp1 = exp.toString().replace(")", "").replace("(", "");
            return Arrays.asList(exp1.split(","));
        } else {
            return (List<Object>) exp;
        }

    }

    private Expression<?> getCastExpression(String searchValue, String typeName, CriteriaBuilder cb) {
        Expression<?> expression = null;
        switch (typeName) {
            case "short":
                expression = cb.literal(Short.parseShort(searchValue));
                break;
            case "byte":
                expression = cb.literal(Byte.parseByte(searchValue));
                break;
            case "int":
                expression = cb.literal(Integer.parseInt(searchValue));
                break;
            case "long":
                expression = cb.literal(Long.parseLong(searchValue));
                break;
            case "float":
                expression = cb.literal(Float.parseFloat(searchValue));
                break;
            case "double":
                expression = cb.literal(Double.parseDouble(searchValue));
                break;
            case "boolean":
                expression = cb.literal(Boolean.parseBoolean(searchValue));
                break;
            default:
                break;
        }
        return expression;
    }

    private List<Order> orderConditions(Map<String, SortOrder> sortOrder, CriteriaBuilder cb, Root<T> myObj) {
        List<Order> OrderList = new java.util.ArrayList<>();
        sortOrder.forEach((key, value) -> {
            switch (value) {
                case ASCENDING:
//                    System.out.println("val.getField() = " + key);
//                    System.out.println("convertSortOrder(val.getOrder()) = " + value);
                    OrderList.add(cb.asc(getRealPath(key, myObj)));
                    break;
                case DESCENDING:
                    OrderList.add(cb.desc(getRealPath(key, myObj)));
                    break;
            }
        });
        return OrderList;
    }

    private Path<String> getRealPath(String filter, Root<T> entityRoot) {
        Path<String> path;
        //System.out.println(filter + "------->>>>>>>>>>>>>>------");
        if (filter.contains(".")) {
            String[] pairs = filter.split("\\.");
            path = entityRoot.get(pairs[0]);
            for (int i = 1; i < pairs.length; i++) {
                String pair = pairs[i];
                path = path.get(pair);
            }
        } else {
            path = entityRoot.get(filter);
        }
        return path;
    }

    public T getMergedEntity(T entity) {
        if (isEntityManaged(entity)) {
            return entity;
        } else {
            return getEntityManager().merge(entity);
        }
    }

    public boolean isEntityManaged(T entity) {
        return getEntityManager().contains(entity);
    }

    public T findWithParents(T entity) {
        return entity;
    }

    private CriteriaQuery findByCriteriaBuilder(T entity, String condition, SingularAttribute... attributes) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        Predicate filterCondition = cb.conjunction();
        EntityGraph<T> graph = getEntityManager().createEntityGraph(entityClass);
        Root<T> myObj = cq.from(entityClass);
        for (SingularAttribute attribute : attributes) {
            graph.addAttributeNodes(attribute);
            Path<String> path = myObj.get(attribute);
            try {
                Field field = entityClass.getDeclaredField(attribute.getName());
                field.setAccessible(true);
                if (condition.equals("EQUALS")) {
                    filterCondition = cb.and(filterCondition, cb.equal(path, field.get(entity)));
                } else {
                    filterCondition = cb.and(filterCondition, cb.like(path, field.get(entity).toString()));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        cq.where(filterCondition);
        return cq;
    }

}
