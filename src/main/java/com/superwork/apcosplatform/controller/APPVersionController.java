package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.APPVersionService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Date;
import java.util.List;

/**
 * @program: code->APPVersionController
 * @description: APP版本
 * @author: xjj
 * @create: 2019-12-16 13:36
 **/
@Controller
@RequestMapping("appVersion")
public class APPVersionController {

    public static Logger logger = Logger.getLogger(APPVersionController.class);

    @Autowired
    APPVersionService appVersionService;

    @Value("${serviceIp}")
    private String serviceIp;

    @Autowired
    RedisUtil redisUtil;

    /**
     * @Description: TODO 查询APP发布的版本
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PAppSoftware>
     * @Author: xujianjian
     * @Date: 2019/12/16 13:58
     */
    @RequestMapping("listApp.json")
    @ResponseBody
    public PageResponse<PAppSoftware> listApp(@RequestBody QueryRequest<PAppSoftware> queryRequest) {
        PageResponse<PAppSoftware> response = new PageResponse<>();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        PAppSoftware data = queryRequest.getData();
        try {
            PageInfo<PAppSoftware> pAppSoftwarePageInfo = appVersionService.listApp(data, page, limit);
            response.setTotal(pAppSoftwarePageInfo.getTotal());
            response.setData(pAppSoftwarePageInfo.getList());
        } catch (Exception e) {
            logger.error("查询APP发布的版本异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 新增APP发布
     * @Param: [pAppSoftware]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/14 14:16
     */
    @RequestMapping("addApp.json")
    @ResponseBody
    @Log("发布APP")
    public SingleResponse<String> addApp(@RequestBody PAppSoftware pAppSoftware) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pAppSoftware.setCreateid(user.getUserId());
            pAppSoftware.setCreatedate(new Date());
            appVersionService.pAppSoftware(pAppSoftware);
        } catch (Exception e) {
            logger.error("发布APP异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }

    /**
     * @Description: TODO 编辑APP
     * @Param: [pAppSoftware]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/14 14:16
     */
    @RequestMapping("editApp.json")
    @ResponseBody
    @Log("编辑APP")
    public SingleResponse<String> editApp(@RequestBody PAppSoftware pAppSoftware) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pAppSoftware.setEditid(user.getUserId());
            pAppSoftware.setEditdate(new Date());
            appVersionService.editApp(pAppSoftware);
        } catch (Exception e) {
            logger.error("编辑APP异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询APP下载地址
     * @Param: [type]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/17 15:04
     */
    @RequestMapping("getAppPath.json")
    @ResponseBody
    public SingleResponse<String> getAppPath(@RequestParam String type) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PAppSoftware appPath = appVersionService.getAppPath(type);
            if (appPath == null) {
                throw new Exception("暂未发布!");
            }
            response.setData(serviceIp + "/index/getAppPath.json?id=" + appPath.getId());
        } catch (Exception e) {
            logger.error("查询APP下载地址异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @Description: TODO 新增APP问题栏目
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("addColumn.json")
    @ResponseBody
    public SingleResponse<String> addColumn(@RequestBody PAppColumn pAppColumn) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pAppColumn.setCreateId(user.getUserId());
            pAppColumn.setCreateTime(new Date());
            appVersionService.addColumn(pAppColumn);
        } catch (Exception e) {
            logger.error("新增APP问题栏目异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 编辑APP问题栏目
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("editColumn.json")
    @ResponseBody
    public SingleResponse<String> editColumn(@RequestBody PAppColumn pAppColumn) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pAppColumn.setEditId(user.getUserId());
            pAppColumn.setEditTime(new Date());
            ResultDO<String> stringResultDO = appVersionService.editColumn(pAppColumn);
            if(!stringResultDO.isSuccess()){
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(100);
            }
        } catch (Exception e) {
            logger.error("编辑APP问题栏目异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除APP问题栏目
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("delColumn.json")
    @ResponseBody
    public SingleResponse<String> delColumn(@RequestBody PAppColumn pAppColumn) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            appVersionService.delColumn(pAppColumn);
        } catch (Exception e) {
            logger.error("删除APP问题栏目异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 验证栏目名是否重复
     * @Param: [title]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 16:34
     */
    @RequestMapping("checkName.json")
    @ResponseBody
    public SingleResponse<String> checkName(@RequestBody String title) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appVersionService.checkName(title);
            if (!stringResultDO.isSuccess()) {
                response.setStatus(100);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("验证栏目是否重复异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询所有的栏目
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("listColumn.json")
    @ResponseBody
    public PageResponse<PAppColumn> listColumn(@RequestBody QueryRequest<PAppColumn> queryRequest) {
        PageResponse<PAppColumn> response = new PageResponse<>();
        PAppColumn data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PAppColumn> pAppColumnPageInfo = appVersionService.listColumn(data, page, limit);
            response.setData(pAppColumnPageInfo.getList());
            response.setTotal(pAppColumnPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("查询所有的栏目异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 新增APP常见问题解决方案
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("addCommonPro.json")
    @ResponseBody
    public SingleResponse<String> addCommonPro(@RequestBody PAppCommonpro pAppCommonpro) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pAppCommonpro.setCreateId(user.getUserId());
            pAppCommonpro.setCreateTime(new Date());
            appVersionService.addCommonPro(pAppCommonpro);
        } catch (Exception e) {
            logger.error("新增APP常见问题解决方案", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 编辑APP常见问题解决方案
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("editCommonPro.json")
    @ResponseBody
    public SingleResponse<String> editCommonPro(@RequestBody PAppCommonpro pAppCommonpro) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pAppCommonpro.setEditId(user.getUserId());
            pAppCommonpro.setEditTime(new Date());
            appVersionService.editCommonPro(pAppCommonpro);
        } catch (Exception e) {
            logger.error("编辑APP常见问题解决方案异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除APP常见问题解决方案
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("delCommonPro.json")
    @ResponseBody
    public SingleResponse<String> delCommonPro(@RequestBody PAppCommonpro pAppCommonpro) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            appVersionService.delCommonPro(pAppCommonpro);
        } catch (Exception e) {
            logger.error("删除APP问题栏目异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询所有常见问题解决方案
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("listCommonPro.json")
    @ResponseBody
    public PageResponse<PAppCommonpro> listCommonPro(@RequestBody QueryRequest<PAppCommonpro> queryRequest) {
        PageResponse<PAppCommonpro> response = new PageResponse<>();
        PAppCommonpro data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PAppCommonpro> pAppColumnPageInfo = appVersionService.listCommonPro(data, page, limit);
            response.setData(pAppColumnPageInfo.getList());
            response.setTotal(pAppColumnPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("查询所有常见问题解决方案异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询所有栏目
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("findAllColumn.json")
    @ResponseBody
    public ListResponse<PAppColumn> findAllColumn(){
        ListResponse<PAppColumn> response = new ListResponse<>();
        try {
            List<PAppColumn> allColumn = appVersionService.findAllColumn();
            response.setData(allColumn);
        } catch (Exception e) {
            logger.error("查询所有的栏目异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @Description: TODO 分页查询APP上报问题
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("listAppProblem.json")
    @ResponseBody
    public PageResponse<PAppProblem> listAppProblem(@RequestBody QueryRequest<PAppProblem> queryRequest) {
        PageResponse<PAppProblem> response = new PageResponse<>();
        PAppProblem data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PAppProblem> pAppColumnPageInfo = appVersionService.listAppProblem(data, page, limit);
            response.setData(pAppColumnPageInfo.getList());
            response.setTotal(pAppColumnPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询APP上报问题异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 处理APP上报问题
     * @Param: [pAppProblem]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/19 13:47
     */
    @RequestMapping("editAppProblem.json")
    @ResponseBody
    public SingleResponse<String> editAppProblem(@RequestBody PAppProblem pAppProblem){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            pAppProblem.setReplyId(user.getUserId());
            pAppProblem.setReplyTime(new Date());
            appVersionService.editAppProblem(pAppProblem);
        } catch (Exception e) {
            logger.error("处理APP上报问题异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
