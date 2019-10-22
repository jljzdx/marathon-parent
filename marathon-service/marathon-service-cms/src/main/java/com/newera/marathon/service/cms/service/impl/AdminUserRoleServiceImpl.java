package com.newera.marathon.service.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.service.cms.entity.AdminUserRole;
import com.newera.marathon.service.cms.mapper.AdminUserRoleMapper;
import com.newera.marathon.service.cms.service.AdminUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AdminUserRoleServiceImpl extends ServiceImpl<AdminUserRoleMapper, AdminUserRole> implements AdminUserRoleService {

    @Transactional
    @Override
    public void doUserRoleBatchAddition(List<AdminUserRole> userRoles) {
        saveBatch(userRoles);
    }
}
