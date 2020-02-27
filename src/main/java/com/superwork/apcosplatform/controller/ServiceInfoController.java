package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.service.ServiceInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PD3orgZc;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: code->ServiceInfoController
 * @description: 第三方服务信息
 * @author: xjj
 * @create: 2019-11-26 10:41
 **/
@Controller
@RequestMapping("serviceInfo")
public class ServiceInfoController {

    public static Logger logger = Logger.getLogger(ServiceInfoController.class);

    @Autowired
    ServiceInfo serviceInfo;

    @Autowired
    RedisUtil redisUtil;

    /**
     * @param * @param queryRequest
     * @return PageResponse<PSbgl>
     * @Description //TODO 查询服务信息
     * @author xjj
     * @date 2019/10/25
     */
    @RequestMapping("getInfo.json")
    @ResponseBody
    public SingleResponse<Map<String, Object>> getInfo() {
        SingleResponse<Map<String, Object>> response = new SingleResponse<>();
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            HashMap<String, Object> map = new HashMap<>();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                map.put("id", null);//ID
                map.put("serviceId", null);//服务ID
                map.put("serviceKey", null);//服务KEY
                map.put("orgName", null);//3D组织名称
                map.put("belongOrgId", null);//3D注册成功后的组织ID
                map.put("address", null);//公司地址
                map.put("companyPhone", null);//公司电话
                map.put("logo", null);//logo
                map.put("companyInfo", null);//备案信息
            } else {
                map.put("id", bccaServiceInfo.getId());//ID
                map.put("serviceId", bccaServiceInfo.getServiceId());//服务ID
                map.put("serviceKey", bccaServiceInfo.getServiceKey());//服务KEY
                map.put("orgName", bccaServiceInfo.getOrgName());//3D组织名称
                map.put("belongOrgId", bccaServiceInfo.getBelongOrgId());//3D注册成功后的组织ID
                map.put("address", bccaServiceInfo.getAddress());//公司地址
                map.put("companyPhone", bccaServiceInfo.getCompanyPhone());//公司电话
                map.put("logo", bccaServiceInfo.getLogo());//logo
                map.put("companyInfo", bccaServiceInfo.getCompanyInfo());//备案信息
            }
            response.setData(map);
        } catch (Exception e) {
            logger.error("获取服务信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param queryRequest
     * @return PageResponse<PSbgl>
     * @Description //TODO 保存服务信息
     * @author xjj
     * @date 2019/10/25
     */
    @RequestMapping("saveInfo.json")
    @ResponseBody
    public SingleResponse<String> saveInfo(@RequestBody Map<String, String> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            serviceInfo.saveInfo(map);
        } catch (Exception e) {
            logger.error("保存服务信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询所有组织信息
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PD3orgZc>
     * @Author: xujianjian
     * @Date: 2020/1/9 9:04
     */
    @RequestMapping("listOrg.json")
    @ResponseBody
    public PageResponse<PD3orgZc> listOrg(@RequestBody QueryRequest<PD3orgZc> queryRequest) {
        PageResponse<PD3orgZc> response = new PageResponse<>();
        PD3orgZc data = queryRequest.getData() == null ? new PD3orgZc() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();

        try {
            PageInfo<PD3orgZc> pd3orgZcPageInfo = serviceInfo.listOrg(data, page, limit);
            response.setData(pd3orgZcPageInfo.getList());
            response.setTotal(pd3orgZcPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("查询所有组织信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 通过组织ID查询用户
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PSysUser>
     * @Author: xujianjian
     * @Date: 2020/1/9 14:53
     */
    @RequestMapping("listUserByOrgId.json")
    @ResponseBody
    public PageResponse<PSysUser> listUserByOrgId(@RequestBody QueryRequest<String> queryRequest) {

        PageResponse<PSysUser> response = new PageResponse<>();
        String data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSysUser> pageInfo = serviceInfo.listUserByOrgId(data, page, limit);
            response.setData(pageInfo.getList());
            response.setTotal(pageInfo.getTotal());
        } catch (Exception e) {
            logger.error("通过组织信息查询用户异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 修改组织名称
     * @Param: [pd3orgZc]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/9 15:17
     */
    @RequestMapping("editOrg.json")
    @ResponseBody
    public SingleResponse<String> editOrg(@RequestBody PD3orgZc pd3orgZc) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            serviceInfo.editOrg(pd3orgZc);
        } catch (Exception e) {
            logger.error("修改组织名字异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 新增组织
     * @Param: [map]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/10 10:22
     */
    @RequestMapping("addOrg.json")
    @ResponseBody
    public SingleResponse<String> addOrg(@RequestBody Map<String, String> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            serviceInfo.addOrg(map);
        } catch (Exception e) {
            logger.error("新增组织异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除组织
     * @Param: [pd3orgZc]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2020/1/11 10:01
     */
    @RequestMapping("delOrg.json")
    @ResponseBody
    public SingleResponse<String> delOrg(@RequestBody PD3orgZc pd3orgZc) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            serviceInfo.delOrg(pd3orgZc);
        } catch (Exception e) {
            logger.error("删除组织异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
