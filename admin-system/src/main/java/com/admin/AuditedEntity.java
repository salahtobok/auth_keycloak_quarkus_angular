
package com.admin;

import com.admin.business.AuditTrailListener;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/***************************
 ***** AFCSOFT *****
 ***************************/

@Getter
@Setter
@MappedSuperclass
//@ExcludeDefaultListeners
@EntityListeners(AuditTrailListener.class)
public abstract class AuditedEntity extends PanacheEntity implements Serializable {


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_by",updatable = false)
    private String CreatedBy;


    public Long getId() {
        return id;
    }
}
