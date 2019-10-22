package com.newera.marathon.service.cms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminRoleInquiryPageRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminRoleInquiryPageResponseSubDTO;
import com.newera.marathon.service.cms.entity.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    List<XfaceCmsAdminRoleInquiryPageResponseSubDTO> queryRolePage(Page<XfaceCmsAdminRoleInquiryPageResponseSubDTO> page,
                                                                      @Param("requestDTO") XfaceCmsAdminRoleInquiryPageRequestDTO requestDTO);
}
