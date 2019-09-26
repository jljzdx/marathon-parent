package com.newera.marathon.service.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newera.marathon.service.cms.entity.SysRole;
import com.newera.marathon.service.cms.entity.SysUserRole;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<SysRole> queryRoleListByUserId(Integer userId);

    @Delete("delete from t_sys_user_role where user_id=#{userId}")
    void deleteByUserId(Integer userId);
}
