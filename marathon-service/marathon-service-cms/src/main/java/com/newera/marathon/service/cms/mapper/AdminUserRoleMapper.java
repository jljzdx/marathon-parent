package com.newera.marathon.service.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newera.marathon.service.cms.entity.AdminRole;
import com.newera.marathon.service.cms.entity.AdminUserRole;
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
public interface AdminUserRoleMapper extends BaseMapper<AdminUserRole> {
    List<AdminRole> queryRoleListByUserId(Integer userId);

    @Delete("delete from CMS_ADMIN_USER_ROLE where user_id=#{userId}")
    void deleteByUserId(Integer userId);
}
