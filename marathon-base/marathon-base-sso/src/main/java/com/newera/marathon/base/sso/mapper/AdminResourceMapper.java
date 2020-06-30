package com.newera.marathon.base.sso.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newera.marathon.base.sso.entity.AdminResource;

import java.util.List;

/**
 * @author MicroBin
 * @description：TODO
 * @date 2020/6/29 9:34 下午
 */
public interface AdminResourceMapper extends BaseMapper<AdminResource>
{

    List<String> queryPermissions(String userName);
}
