package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.PDepartment;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.DepartmentService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jianjian Xu
 * @create: 2019/10/23 14:36
 * @description:
 */
@Controller
@RequestMapping("department")
public class DepartmentController {
    public static Logger logger = Logger.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    /**
     * @param * @param queryRequest
     * @return void
     * @Description //TODO 分页查询菜单信息
     * @author xjj
     * @date 2019/10/23
     */
    @RequestMapping("listDepartment.json")
    @ResponseBody
    public PageResponse<PDepartment> listDepartment(@RequestBody QueryRequest<PDepartment> queryRequest) {
        PageResponse<PDepartment> response = new PageResponse<>();
        PDepartment data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PDepartment> pDepartmentPageInfo = departmentService.listDepartment(data, page, limit);
            response.setData(pDepartmentPageInfo.getList());
            response.setTotal(pDepartmentPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询菜单信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description //TODO 验证菜单名字是否唯一
     * @author xjj
     * @date 2019/10/23
     * @param  * @param queryRequest
     * @return SingleResponse<java.lang.String>
     */
    @RequestMapping("checkName.json")
    @ResponseBody
    public SingleResponse<String> checkName(@RequestBody QueryRequest<String> queryRequest){

        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();

        try {
            ResultDO<String> stringResultDO = departmentService.checkName(data);
            if(!stringResultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("验证菜单名字是否唯一异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }

    /**
     * @Description //TODO 新增菜单
     * @author xjj
     * @date 2019/10/23
     * @param  * @param queryRequest
     * @param request
     * @return SingleResponse<java.lang.String>
     */
    @RequestMapping("addDepartment.json")
    @ResponseBody
    public SingleResponse<String> addDepartment(@RequestBody QueryRequest<PDepartment> queryRequest, HttpServletRequest request) {

        SingleResponse<String> response = new SingleResponse<>();
        PDepartment data = queryRequest.getData();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        data.setCreateTime(new Date());
        data.setCreateUserid(user.getUserId());
        data.setSysType(new BigDecimal(2));//默认自定义部门
        try {
            departmentService.addDepartment(data);
        } catch (Exception e) {
            logger.error("新增菜单异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description //TODO 验证除自己外名字是否唯一
     * @author xjj
     * @date 2019/10/23
     * @param  * @param queryRequest
     * @return SingleResponse<java.lang.String>
     */
    @RequestMapping("checkNameWith.json")
    @ResponseBody
    public SingleResponse<String> checkNameWith(@RequestBody QueryRequest<PDepartment> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        PDepartment data = queryRequest.getData();
        try {
            ResultDO<String> stringResultDO = departmentService.checkNameWith(data);
            if(!stringResultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("验证除自己外名字是否唯一异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description //TODO 编辑菜单
     * @author xjj
     * @date 2019/10/23
     * @param  * @param queryRequest
     * @return SingleResponse<java.lang.String>
     */
    @RequestMapping("editDepartment.json")
    @ResponseBody
    public SingleResponse<String> editDepartment(@RequestBody QueryRequest<PDepartment> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        PDepartment data = queryRequest.getData();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setEditUserid(user.getUserId());
            data.setEditTime(new Date());
            departmentService.editDepartment(data);
        } catch (Exception e) {
            logger.error("编辑菜单异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除菜单
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:53
     */
    @RequestMapping("delDepartment.json")
    @ResponseBody
    public SingleResponse<String> delDepartment(@RequestBody QueryRequest<PDepartment> queryRequest){

        SingleResponse<String> response = new SingleResponse<>();
        PDepartment data = queryRequest.getData();
        try {
            ResultDO<String> stringResultDO = departmentService.delDepartment(data);
            if(!stringResultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("删除菜单异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 根据菜单ID获取当前部门下的人员
     * @Param: [request]
     * @return: ListResponse<PSysUser>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:54
     */
    @RequestMapping("getUserListByDepId.json")
    @ResponseBody
    public ListResponse<PSysUser> getUserListByDepId(HttpServletRequest request){
        ListResponse<PSysUser> response = new ListResponse<>();
        String depId = request.getParameter("depId");
        try {
            List<PSysUser> userListByDepId = departmentService.getUserListByDepId(depId);
            response.setData(userListByDepId);
        } catch (Exception e) {
            logger.error("查询菜单下的用户异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 设置人员到菜单
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:54
     */
    @RequestMapping("setUserAndDep.json")
    @ResponseBody
    public SingleResponse<String>  setUserAndDep(@RequestBody QueryRequest<List<Map<String,String>>> queryRequest){
        SingleResponse<String> response = new SingleResponse<>();
        String depId = queryRequest.getDirection();
        List<Map<String, String>> data = queryRequest.getData();
        try {
            departmentService.setUserAndDep(data,depId);
        } catch (Exception e) {
            logger.error("设置菜单和用户的关系异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }
}
