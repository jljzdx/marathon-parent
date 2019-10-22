package com.newera.marathon.service.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.service.cms.entity.AdminUserRole;

import java.util.List;

public interface AdminUserRoleService extends IService<AdminUserRole> {

    void doUserRoleBatchAddition(List<AdminUserRole> userRoles);

}
