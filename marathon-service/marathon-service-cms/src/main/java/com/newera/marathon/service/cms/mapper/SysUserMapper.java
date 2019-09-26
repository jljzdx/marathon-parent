package com.newera.marathon.service.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserInquiryPageRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserInquiryPageResponseSubDTO;
import com.newera.marathon.service.cms.entity.SysUser;
import com.newera.marathon.service.cms.vo.LeftMenuListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
    List<XfaceSysUserInquiryPageResponseSubDTO> querySysUserPage(@Param("page") Page<XfaceSysUserInquiryPageResponseSubDTO> page,
                                                                 @Param("requestDTO") XfaceSysUserInquiryPageRequestDTO requestDTO);

    List<LeftMenuListVO> queryLeftMenu(String userName);

    List<String> queryPermissions(String userName);
}