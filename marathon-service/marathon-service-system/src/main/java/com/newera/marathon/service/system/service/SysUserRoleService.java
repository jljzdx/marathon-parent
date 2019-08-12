package com.newera.marathon.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.service.system.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {

    void doSysUserRoleBatchAddition(List<SysUserRole> sysUserRoles);

}
