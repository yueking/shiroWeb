package com.yueking.core.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "y_permissions",
        indexes = {
                @Index(name = "idx_sys_permissions_permission", columnList = "permission", unique = true)
        }
)
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(max = 100)
    private String permission;
    @Size(max = 100)
    private String description;
    @Basic
    private boolean available;




}
