package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.PDepartment;
import com.superwork.apcosplatform.domain.PSysRight;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.RighrService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jianjian Xu
 * @create: 2019/10/24 11:26
 * @description:
 */
@Controller
@RequestMapping("right")
public class SysRightController {

    public static Logger logger = Logger.getLogger(SysRightController.class);

    @Autowired
    RighrService righrService;

    /**
     * @Description: TODO 添加权限
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:08
     */
    @RequestMapping("addRight.json")
    @ResponseBody
    public SingleResponse<String> addRight(@RequestBody QueryRequest<PSysRight> queryRequest, HttpServletRequest request) {

        SingleResponse<String> response = new SingleResponse<>();
        PSysRight data = queryRequest.getData();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        data.setCreateId(user.getUserId());
        data.setCreateDate(new Date());
        try {
            righrService.addRight(data);
        } catch (Exception e) {
            logger.error("新增权限异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询所有菜单
     * @Param: []
     * @return: SingleResponse<java.util.List < PSysRight>>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:08
     */
    @RequestMapping("loadMenu.json")
    @ResponseBody
    public SingleResponse<List<PSysRight>> loadMenu() {
        SingleResponse<List<PSysRight>> response = new SingleResponse<>();
        try {
            List<PSysRight> pSysRights = righrService.loadAllMenu();
            response.setData(pSysRights);
            response.setCount(pSysRights.size());
        } catch (Exception e) {
            logger.error("查询所有菜单异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param
     * @return com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PSysRight>
     * @Description //TODO  分页查询外接应用
     * @author xjj
     * @date 2020/2/18
     */
    @RequestMapping("listApplication.json")
    @ResponseBody
    public PageResponse<PSysRight> listApplication(@RequestBody QueryRequest<PSysRight> queryRequest) {
        PageResponse<PSysRight> response = new PageResponse<>();
        PSysRight data = queryRequest.getData() == null ? new PSysRight() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSysRight> pSysRightPageInfo = righrService.listApplication(data, page, limit);
            response.setData(pSysRightPageInfo.getList());
            response.setTotal(pSysRightPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询菜单信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }

    /**
     * @Description: TODO 添加应用
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:08
     */
    @RequestMapping("addApplication.json")
    @ResponseBody
    public SingleResponse<String> addApplication(@RequestBody PSysRight pSysRight) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pSysRight.setCreateDate(new Date());
            pSysRight.setCreateId(user.getUserId());
            ResultDO<String> resultDO = righrService.addApplication(pSysRight);
            if(!resultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(resultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("添加应用异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 编辑应用
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:08
     */
    @RequestMapping("editApplication.json")
    @ResponseBody
    public SingleResponse<String> editApplication(@RequestBody PSysRight pSysRight) {
        SingleResponse<String> response = new SingleResponse<>();

        try {
            PSysUser user = ComonUtils.getUser();
            pSysRight.setEditedTime(new Date());
            pSysRight.setEditedId(user.getUserId());
            ResultDO<String> resultDO = righrService.editApplication(pSysRight);
            if(!resultDO.isSuccess()){
                response.setStatus(100);
                response.setMsg(resultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("编辑应用异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除应用
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:08
     */
    @RequestMapping("delApplication.json")
    @ResponseBody
    public SingleResponse<String> delApplication(@RequestParam String rightId) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
           righrService.delApplication(rightId);
        } catch (Exception e) {
            logger.error("删除应用异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }




    /**
     * @Description: TODO 验证菜单名是否重复
     * @Param: [pSysRight]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/23 14:02
     */
    @RequestMapping("checkRightName.json")
    @ResponseBody
    public SingleResponse<String> checkRightName(@RequestBody PSysRight pSysRight) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = righrService.checkRightName(pSysRight);
            if (!stringResultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("验证菜单名是否重复异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 编辑权限
     * @Param: [pSysRight]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/23 14:02
     */
    @RequestMapping("editRight.json")
    @ResponseBody
    public SingleResponse<String> editRight(@RequestBody PSysRight pSysRight) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = righrService.editRight(pSysRight);
            if (!stringResultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("编辑权限异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除权限
     * @Param: [pSysRight]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/23 14:02
     */
    @RequestMapping("delRight.json")
    @ResponseBody
    public SingleResponse<String> delRight(@RequestParam String id) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            righrService.delRight(id);
        } catch (Exception e) {
            logger.error("删除权限异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 通过部门ID查询所有菜单和部门所在菜单
     * @Param: [depId]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author: xujianjian
     * @Date: 2019/12/25 10:13
     */
    @RequestMapping("findRightwithDepId.json")
    @ResponseBody
    public SingleResponse<JSONObject> findRightwithDepId(@RequestParam String depId) {

        SingleResponse<JSONObject> response = new SingleResponse<>();
        try {
            JSONObject rightwithDepId = righrService.findRightwithDepId(depId);
            response.setData(rightwithDepId);
        } catch (Exception e) {
            logger.error("删除权限异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    @RequestMapping("setRightAndDep.json")
    @ResponseBody
    public SingleResponse<String> setRightAndDep(@RequestBody Map<String, Object> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            righrService.setRightAndDep(map);
        } catch (Exception e) {
            logger.error("删除权限异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
