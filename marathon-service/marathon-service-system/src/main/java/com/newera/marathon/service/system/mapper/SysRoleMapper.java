package com.newera.marathon.service.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newera.marathon.dto.system.inquiry.XfaceSysRoleInquiryPageRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysRoleInquiryPageResponseSubDTO;
import com.newera.marathon.service.system.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<XfaceSysRoleInquiryPageResponseSubDTO> querySysRolePage(Page<XfaceSysRoleInquiryPageResponseSubDTO> page,
                                                                 @Param("requestDTO")XfaceSysRoleInquiryPageRequestDTO requestDTO);
}
