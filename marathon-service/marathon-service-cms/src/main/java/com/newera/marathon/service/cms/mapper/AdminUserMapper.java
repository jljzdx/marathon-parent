package com.newera.marathon.service.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminUserInquiryPageRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminUserInquiryPageResponseSubDTO;
import com.newera.marathon.service.cms.entity.AdminUser;
import com.newera.marathon.service.cms.vo.LeftMenuListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminUserMapper extends BaseMapper<AdminUser> {
    List<XfaceCmsAdminUserInquiryPageResponseSubDTO> queryUserPage(@Param("page") Page<XfaceCmsAdminUserInquiryPageResponseSubDTO> page,
                                                                      @Param("requestDTO") XfaceCmsAdminUserInquiryPageRequestDTO requestDTO);

    List<LeftMenuListVO> queryLeftMenu(String userName);

    List<String> queryPermissions(String userName);
}
