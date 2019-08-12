package com.newera.marathon.service.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.service.system.entity.SysUserRole;
import com.newera.marathon.service.system.mapper.SysRoleMapper;
import com.newera.marathon.service.system.mapper.SysUserRoleMapper;
import com.newera.marathon.service.system.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public void doSysUserRoleBatchAddition(List<SysUserRole> sysUserRoles) {
        saveBatch(sysUserRoles);
    }
}
