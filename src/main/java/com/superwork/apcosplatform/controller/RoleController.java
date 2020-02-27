package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.PRole;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: code->RoleController
 * @description: 角色（同一角色下数据共享）
 * @author: xjj
 * @create: 2019-12-26 15:05
 **/
@Controller
@RequestMapping("role")
public class RoleController {

    public static Logger logger = Logger.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    /**
     * @Description: TODO 分页查询所在组织下的角色
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PRole>
     * @Author: xujianjian
     * @Date: 2019/12/26 15:24
     */
    @RequestMapping("listRole.json")
    @ResponseBody
    public PageResponse<PRole> listRole(@RequestBody QueryRequest<PRole> queryRequest){

        PageResponse<PRole> response = new PageResponse<>();
        PRole data =  queryRequest.getData() == null ? new PRole():queryRequest.getData();
        Integer page = queryRequest.getPage() == null? 1:queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null? 20:queryRequest.getLimit();
        try {
            PageInfo<PRole> pRolePageInfo = roleService.listRole(data, page, limit);
            response.setData(pRolePageInfo.getList());
            response.setTotal(pRolePageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询角色异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 新增角色
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PRole>
     * @Author: xujianjian
     * @Date: 2019/12/26 15:24
     */
    @RequestMapping("addRole.json")
    @ResponseBody
    public SingleResponse<String> addRole(@RequestBody PRole pRole){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = roleService.addRole(pRole);
            if(!stringResultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("新增角色异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @Description: TODO 编辑角色
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PRole>
     * @Author: xujianjian
     * @Date: 2019/12/26 15:24
     */
    @RequestMapping("editRole.json")
    @ResponseBody
    public SingleResponse<String> editRole(@RequestBody PRole pRole){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = roleService.editRole(pRole);
            if(!stringResultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("编辑角色异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除角色
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PRole>
     * @Author: xujianjian
     * @Date: 2019/12/26 15:24
     */
    @RequestMapping("delRole.json")
    @ResponseBody
    public SingleResponse<String> delRole(@RequestBody PRole pRole){
        SingleResponse<String> response = new SingleResponse<>();
        try {
          roleService.delRole(pRole);
        } catch (Exception e) {
            logger.error("删除角色异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 角色与用户的关系
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PRole>
     * @Author: xujianjian
     * @Date: 2019/12/26 15:24
     */
    @RequestMapping("listUserByRoleId.json")
    @ResponseBody
    public SingleResponse<Map<String, Object>> listUserByRoleId(@RequestParam String roleId){
        SingleResponse<Map<String, Object>> response = new SingleResponse<>();
        try {
            Map<String, Object> map = roleService.listUserByRoleId(roleId);
            response.setData(map);
        } catch (Exception e) {
            logger.error("角色与用户的关系异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @Description: TODO 设置角色与用户的关系
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PRole>
     * @Author: xujianjian
     * @Date: 2019/12/26 15:24
     */
    @RequestMapping("setUserAndRole.json")
    @ResponseBody
    public SingleResponse<String> setUserAndRole(@RequestBody QueryRequest<List<Map<String,String>>> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        List<Map<String, String>> data = queryRequest.getData();
        String roleId = queryRequest.getDirection();
        try {
            roleService.setUserAndRole(data,roleId);
        } catch (Exception e) {
            logger.error("设置角色与用户的关系异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }



}
