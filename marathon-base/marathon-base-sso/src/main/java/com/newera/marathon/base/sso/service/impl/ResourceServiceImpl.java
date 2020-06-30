package com.newera.marathon.base.sso.service.impl;

import com.newera.marathon.base.sso.mapper.AdminResourceMapper;
import com.newera.marathon.base.sso.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService
{
    @Autowired
    private AdminResourceMapper adminResourceMapper;

    @Override
    public List<String> findByUserId(String userName)
    {
        return adminResourceMapper.queryPermissions(userName);
    }
}
