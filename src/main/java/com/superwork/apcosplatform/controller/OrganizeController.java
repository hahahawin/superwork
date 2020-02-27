package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.OrganizeService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: code->OrganizeController
 * @description:
 * @author: xjj
 * @create: 2020-01-08 14:03
 **/
@Controller
@RequestMapping("organize")
public class OrganizeController {

    public static Logger logger = Logger.getLogger(OrganizeController.class);

    @Autowired
    OrganizeService organizeService;

    /**
     * @Description: TODO 查询部门
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.POrganize>
     * @Author: xujianjian
     * @Date: 2020/1/8 14:10
     */
    @RequestMapping("listOrganize.json")
    @ResponseBody
    public SingleResponse<List<POrganize>> listOrganize() {
        SingleResponse<List<POrganize>> response = new SingleResponse<>();
        try {
            PD3orgZc pD3orgZc = ComonUtils.getBccaServiceInfo();
            if(pD3orgZc == null){
                response.setStatus(100);
                response.setMsg("请先完善组织组织信息！");
                return response;
            }
            List<POrganize> pOrganizes = organizeService.listOrganize(pD3orgZc.getId());
            response.setData(pOrganizes);
        } catch (Exception e) {
            logger.error("分页查询部门信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 新增部门
     * @Param: [pOrganize]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/8 14:21
     */
    @RequestMapping("addOrganize.json")
    @ResponseBody
    public SingleResponse<String> addOrganize(@RequestBody POrganize pOrganize) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pOrganize.setCreateId(user.getUserId());
            pOrganize.setCreateDate(new Date());
            PD3orgZc pD3orgZc = ComonUtils.getBccaServiceInfo();
            if(pD3orgZc == null){
                response.setStatus(100);
                response.setMsg("请先完善组织组织信息！");
                return response;
            }
            pOrganize.setCompanyId(pD3orgZc.getId().toString());
            ResultDO<String> resultDO = organizeService.addOrganize(pOrganize);
            if (!resultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(resultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("新增部门出错异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 修改部门
     * @Param: [pOrganize]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/8 14:21
     */
    @RequestMapping("editOrganize.json")
    @ResponseBody
    public SingleResponse<String> editOrganize(@RequestBody POrganize pOrganize) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pOrganize.setEditId(user.getUserId());
            pOrganize.setEditDate(new Date());
            ResultDO<String> resultDO = organizeService.editOrganize(pOrganize);
            if (!resultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(resultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("编辑部门出错异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除部门
     * @Param: [pOrganize]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/8 14:21
     */
    @RequestMapping("delOrganize.json")
    @ResponseBody
    public SingleResponse<String> delOrganize(@RequestParam String id) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> resultDO = organizeService.delOrganize(id);
            if (!resultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(resultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("删除部门出错异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询当前部门下的人员
     * @Param: [orgId]
     * @return: com.superwork.apcosplatform.result.ListResponse<com.superwork.apcosplatform.domain.PSysUser>
     * @Author: xujianjian
     * @Date: 2020/1/8 14:38
     */
    @RequestMapping("getUserListByOrgID.json")
    @ResponseBody
    public ListResponse<PSysUser> getUserListByOrgID(@RequestParam String orgId) {
        ListResponse<PSysUser> response = new ListResponse<>();
        try {
            List<PSysUser> userListByOrgID = organizeService.getUserListByOrgID(orgId);
            response.setData(userListByOrgID);
        } catch (Exception e) {
            logger.error("查询当前部门下的人员", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 设置人员到部门中
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/8 14:53
     */
    @RequestMapping("setUserAndOrg.json")
    @ResponseBody
    public SingleResponse<String> setUserAndOrg(@RequestBody QueryRequest<List<Map<String, String>>> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        String orgId = queryRequest.getDirection();
        List<Map<String, String>> data = queryRequest.getData();
        try {
            organizeService.setUserAndOrg(data, orgId);
        } catch (Exception e) {
            logger.error("设置菜单和用户的关系异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
