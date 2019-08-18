package com.newera.marathon.service.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceLoopInquiryResponseSubDTO;
import com.newera.marathon.service.system.entity.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MicroBin
 * @since 2019-08-14
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<XfaceSysResourceLoopInquiryResponseSubDTO> querySysResourceLoop(@Param("parentId") Integer parentId);
}
