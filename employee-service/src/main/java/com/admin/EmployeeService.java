package com.admin;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeService {
    private static final Logger LOG = Logger.getLogger(EmployeeService.class);


    @Inject
    EntityManager entityManager;

    public List<Employee> getAllEmployee(){

        LOG.debug("The application start");

        List<Employee> employees = new ArrayList<>();

        employees = entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
        employees.forEach(System.out::println);

        LOG.debug("The application end");

        return employees;
    }
}
