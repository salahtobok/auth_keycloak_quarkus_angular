package com.admin.business;

import com.admin.AuditedEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.Date;

public class AuditTrailListener {

//    @Inject
//    private UserSession userSession;
    private static Log log = LogFactory.getLog(AuditTrailListener.class);

    @PrePersist
    private void beforeCreate(AuditedEntity auditedEntity) {
        auditedEntity.setCreatedBy("eeeeeeee");
        auditedEntity.setCreatedOn(new Date());
        if (auditedEntity.getId() == 0) {
            log.info("[AuditedEntity AUDIT] About to add a auditedEntity");
        } else {
            log.info("[AuditedEntity AUDIT] About to update/delete auditedEntity: " + auditedEntity.getId());
        }
    }
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(AuditedEntity auditedEntity) {
        auditedEntity.setUpdatedOn(new Date());
        if (auditedEntity.getId() == 0) {
            log.info("[AuditedEntity AUDIT] About to add a auditedEntity");
        } else {
            log.info("[AuditedEntity AUDIT] About to update/delete auditedEntity: " + auditedEntity.getId());
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(AuditedEntity auditedEntity) {
        auditedEntity.setUpdatedOn(new Date());
        log.info("[AuditedEntity AUDIT] add/update/delete complete for auditedEntity: " + auditedEntity.getId());
    }

    @PostLoad
    private void afterLoad(AuditedEntity auditedEntity) {
        log.info("[AuditedEntity AUDIT] auditedEntity loaded from database: " + auditedEntity.getId());
    }
}
