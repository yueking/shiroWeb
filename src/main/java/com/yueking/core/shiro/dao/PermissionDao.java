package com.yueking.core.shiro.dao;

import com.yueking.core.shiro.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<Permission,Long> {
}
