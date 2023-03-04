package com.admin.business.entity;


import com.admin.AuditedEntity;
import com.admin.DbSchema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "admin_user", schema = DbSchema.ADMINISTRATION_SCHEMA)
public class User extends AuditedEntity implements Serializable {
    private static Log log = LogFactory.getLog(User.class);

    @JsonbTransient
    @Column(name = "user_type", insertable = false, updatable = false)
    protected String userType;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    @JsonbTransient
    private String name;

    @Transient
    private String fullName;
    @NotNull
    @NotBlank
    @JsonbTransient
    @Size(min = 1, max = 100)
    private String firstName;

    @NotNull
    @NotBlank
//    @Pattern(regexp = "^[A-Za-z]+$")
    @JsonbTransient
    @Size(min = 1, max = 100)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "username", unique = true)
    @JsonbTransient
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~.-]+")
    private String username;




    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~.-]+@[a-z0-9-]+(\\.[a-z0-9-]+)*")
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

}
