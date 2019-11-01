package com.newera.marathon.service.cms;

import com.alibaba.fastjson.JSONObject;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminRoleAdditionRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminRoleModifyRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServiceCmsApplication.class})
//@Transactional//用于数据回滚
@Slf4j
public class RoleTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void testBefore() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void testAfter() {
        log.info("After.........");
    }

    /**
     * 查询所有角色
     * @throws Exception
     */
    @Test
    public void roleInquirySelectTest() throws Exception {
        MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/cms/admin/role/inquiry/select"))
                .andExpect(
                        status().isOk())
                .andReturn();
        log.info("response data：[" + mvcResult.getResponse().getContentAsString() + "]");
    }

    /**
     * 添加角色
     * @throws Exception
     */
    @Test
    public void roleAdditionTest() throws Exception {
        XfaceCmsAdminRoleAdditionRequestDTO requestDTO = new XfaceCmsAdminRoleAdditionRequestDTO();
        requestDTO.setRoleName("mock_test");
        requestDTO.setRemark("mock_test");
        requestDTO.setCreateOperator("wwb");
        MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/role/addition")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSONObject.toJSONString(requestDTO)))
                .andExpect(
                        status().isOk())
                .andReturn();
        log.info("response data：[" + mvcResult.getResponse().getContentAsString() + "]");
    }

    /**
     * 修改角色
     * @throws Exception
     */
    @Test
    public void roleModifyTest() throws Exception {
        XfaceCmsAdminRoleModifyRequestDTO requestDTO = new XfaceCmsAdminRoleModifyRequestDTO();
        requestDTO.setRoleName("mock_test");
        requestDTO.setRemark("mock_test");
        requestDTO.setId(10);
        requestDTO.setModifyOperator("wangwb");
        MvcResult mvcResult = this.mockMvc
                .perform(
                        post("/role/modify")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JSONObject.toJSONString(requestDTO)))
                .andExpect(
                        status().isOk())
                .andReturn();
        log.info("response data：[" + mvcResult.getResponse().getContentAsString() + "]");
    }
}
