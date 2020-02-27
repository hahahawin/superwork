package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.D3HttpClient;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.D3Service;
import com.superwork.apcosplatform.service.Impl.SmhSettingServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: beone-platform
 * @description:
 * @author: xjj
 * @create: 2019-11-07 11:37
 **/
@Controller
@RequestMapping("d3")
public class D3Controller {
    public static Logger logger = Logger.getLogger(D3Controller.class);

    @Value("${cd_api_url}")
    private String cd_api_url;//成都平台
    @Value("${back_url}")
    private String back_url;//成都平台回调地址
    @Value("${d3url}")
    private String d3url;
    @Value("${dgj_api_url}")
    private String dgj_api_url;


    @Autowired
    D3Service d3Service;

    /**
     * @Description: TODO 获取元件
     * @Param: []
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/7 0007
     */
    @RequestMapping("sbyjlist.json")
    @ResponseBody
    public SingleResponse<Map<String, Object>> sbyjlist(@RequestBody Map<String, String> map1) {
        SingleResponse<Map<String, Object>> response = new SingleResponse<>();
        PSysUser user = ComonUtils.getUser();
        Map<String, Object> map = new HashMap<>();
        try {
            PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
            if (dInfo == null ||StringUtils.isEmpty(dInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String service_id = dInfo.getServiceId();
            String dToken = D3HttpClient.get3DToken(d3url, service_id, user);
            map1.put("user_token", dToken);
            String s = JSONObject.toJSONString(map1);
            JSONObject obj = JSONObject.parseObject(s);
            String yjData = D3HttpClient.getYJData(d3url, obj);
            JSONObject object = JSONObject.parseObject(yjData);//totalRecords总条数
            map.put("list", object.get("list").toString());
            map.put("totalRecords", object.get("totalRecords").toString());
            response.setData(map);
        } catch (Exception e) {
            logger.error("查詢3D原件数据异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询元件类型
     * @Param: []
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/7 0007
     */
    @RequestMapping("getsbyjlx.json")
    @ResponseBody
    public SingleResponse<String> getYJLXInfo() {
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser user = ComonUtils.getUser();
        try {
            PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
            if (dInfo == null ||StringUtils.isEmpty(dInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String service_id = dInfo.getServiceId();
            String dToken = D3HttpClient.get3DToken(d3url, service_id, user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user_token", dToken);
            String yjlxInfo = D3HttpClient.getYJLXInfo(d3url, jsonObject);
            response.setData(yjlxInfo);
            response.setToken(d3url);//临时储存
        } catch (Exception e) {
            logger.error("查询原件类型异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询我的设备
     * @Param: []
     * @return: ListResponse<PSbzcLx>
     * @Author: xujianjian
     * @Date: 2019/11/7 0007
     */
    @RequestMapping("listMySb.json")
    @ResponseBody
    public ListResponse<PSbgl> listMySb(@RequestBody QueryRequest<PSbgl> queryRequest) {
        PSbgl data = queryRequest.getData();
        String yj_id = queryRequest.getDirection();
        ListResponse<PSbgl> response = new ListResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setCreatorId(user.getUserId());
            List<PSbgl> pSbgls = d3Service.listMySb(data, yj_id);
            response.setData(pSbgls);
        } catch (Exception e) {
            logger.error("查询我的设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 绑定设备到元件
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    @RequestMapping("bongsb.json")
    @ResponseBody
    public SingleResponse<String> bongsb(@RequestBody QueryRequest<List<PSbgl>> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        String yj_id = queryRequest.getDirection();
        List<PSbgl> data = queryRequest.getData();
        try {
            String bongsb = d3Service.bongsb(data, yj_id);
            response.setData(bongsb);
        } catch (Exception e) {
            logger.error("绑定设备到元件异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 获取3D工作空间
     * @Param: []
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    @RequestMapping("getWorkSpaceUrl.json")
    @ResponseBody
    public SingleResponse<String> getWorkSpaceUrl() {
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser user = ComonUtils.getUser();
        String d3url1 = d3url + "/otheAPI/openWorkstation?user_token=";
        try {
            PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
            if (dInfo == null || StringUtils.isEmpty(dInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String service_id = dInfo.getServiceId();
            String dToken = D3HttpClient.get3DToken(d3url, service_id, user);
            d3url1 += dToken;
            response.setData(d3url1);
        } catch (Exception e) {
            logger.error("获取3D工作空间异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 注册或修改3D账号信息
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:50
     */
    @RequestMapping("registerOrg.json")
    @ResponseBody
    public SingleResponse<String> registerOrg(@RequestBody QueryRequest<PD3orgZc> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        PD3orgZc data = queryRequest.getData();
        try {
            d3Service.registerOrg(data);
        } catch (Exception e) {
            logger.error("注册组织机构异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 获取3D在线工作路径
     * @Param: []
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/11 0011
     */
    @RequestMapping("getWorkOnLineURL.json")
    @ResponseBody
    public SingleResponse<String> getWorkOnLineURL() {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
            if (dInfo == null || StringUtils.isEmpty(dInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String service_id = dInfo.getServiceId();
            PSysUser user = ComonUtils.getUser();
            String dToken = D3HttpClient.get3DToken(d3url, service_id, user);
            String onLineurl = d3url + "/otheAPI/openDesign1?user_id=" + user.getUserId() + "&user_token=" + dToken;
            response.setData(onLineurl);
        } catch (Exception e) {
            logger.error("获取在线设计的地址异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO  根据mac查询我的设备
     * @Param: [queryRequest]
     * @return: PageResponse<PSbgl>
     * @Author: xujianjian
     * @Date: 2019/11/11 0011
     */
    @RequestMapping("listMysb3d.json")
    @ResponseBody
    public PageResponse<PSbgl> listMysb3d(@RequestBody QueryRequest<List<String>> queryRequest) {
        PageResponse<PSbgl> response = new PageResponse<>();
        PSysUser user = ComonUtils.getUser();
        List<String> device_mac = queryRequest.getData();
        PSbgl pSbgl = new PSbgl();
        pSbgl.setMacList(device_mac);
        pSbgl.setCreatorId(user.getUserId());
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSbgl> pSbglPageInfo = d3Service.listMysb3d(pSbgl, page, limit);
            response.setData(pSbglPageInfo.getList());
            response.setTotal(pSbglPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("查询我的设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

//    /**
//     * @Description: TODO 3D平台添加模式
//     * @Param: [queryRequest]
//     * @return: SingleResponse<java.lang.String>
//     * @Author: xujianjian
//     * @Date: 2019/11/20 15:50
//     */
//    @RequestMapping("add3DPlan.json")
//    @ResponseBody
//    public SingleResponse<String> add3DPlan(@RequestBody QueryRequest<Map<String, Object>> queryRequest) {
//
//        SingleResponse<String> response = new SingleResponse<>();
//        Map<String, Object> data = queryRequest.getData();
//        String mszh_mc = data.get("mszh_mc").toString();
//        String msmls = data.get("msmls").toString();//命令
//        Map<String, Object> plan_data = (Map<String, Object>) data.get("plan_data");
//        try {
//            d3Service.add3DPlan(mszh_mc, msmls, plan_data);
//        } catch (Exception e) {
//            logger.error("添加3D方案异常", e);
//            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
//            response.setMsg(e.getMessage());
//        }
//        return response;
//    }



    /**
     * @Description: TODO 通过设备MAC查询设备控制模板
     * @Param: [queryRequest]
     * @return: SingleResponse<PSbzcLx>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:51
     */
    @PostMapping("selmodleWithSum.json")
    @ResponseBody
    public SingleResponse<PSbzcLx> selmodleWithSum(@RequestBody QueryRequest<String> queryRequest) {

        SingleResponse<PSbzcLx> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            PSbzcLx sbzcLx = d3Service.selmodleWithSum(data);
            response.setData(sbzcLx);
        } catch (Exception e) {
            logger.error("通过设备类型查询设备控制模板异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 3d模式控制
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/12 0012
     */
    @RequestMapping("msControl.json")
    @ResponseBody
    public SingleResponse<String> msControl(@RequestBody QueryRequest<String> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            PD3orgZc dInfo = ComonUtils.getBccaServiceInfo();
            if (dInfo == null || StringUtils.isEmpty(dInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            PSbmogl pSbmogl = d3Service.selectAcountByMsId(data);
            if (pSbmogl == null) {
                throw new Exception("模式ID不存在！");
            }
            String account = pSbmogl.getAttr2();//云模式账号
            String cd_service_id = dInfo.getServiceId();
            String cd_service_key = dInfo.getServiceKey();
            //如果前台没有传入，从配置文件读取
            String token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, cd_service_id, cd_service_key, back_url, account);
            JSONObject obj2 = new JSONObject();
            obj2.put("id", data);
            JSONObject obj = new JSONObject();
            obj.put("service_id", cd_service_id);
            obj.put("token", token);
            obj.put("processTime", new Date().getTime());
            obj.put("modeConfDO", obj2);
            String result = BCCAClient.sendMode(cd_api_url,obj,account);
            System.out.println("result:" + result);
            JSONObject object = JSONObject.parseObject(result);
            if (object.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, cd_service_id, cd_service_key, back_url, account);
                obj.put("token", token);
                result = BCCAClient.sendMode(cd_api_url,obj,account);
                object = JSONObject.parseObject(result);
            }

            if (!object.get("resultCode").equals("0000")) {
                throw new Exception("模式控制失败！" + JSONObject.parseObject(object.get("resultContent").toString()).get("message"));
            }
        } catch (Exception e) {
            logger.error("3d模式控制异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @Description: TODO 根据模式名称查询模式命令
     * @Param: [queryRequest]
     * @return: ListResponse<PYktMsgngx>
     * @Author: xujianjian
     * @Date: 2019/11/12 0012
     */
    @RequestMapping("getMlByMsName.json")
    @ResponseBody
    public ListResponse<PYktMsgngx> getMlByMsName(@RequestBody QueryRequest<String> queryRequest) {
        ListResponse<PYktMsgngx> response = new ListResponse<>();
        String data = queryRequest.getData();
        try {
            List<PYktMsgngx> mlByMsName = d3Service.getMlByMsName(data);
            response.setData(mlByMsName);
        } catch (Exception e) {
            logger.error("根据模式名称查询模式命令异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

    /**
     * @Description: TODO 验证名字是否重复
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/12 0012
     */
    @RequestMapping("checkName.json")
    @ResponseBody
    public SingleResponse<String> checkName(@RequestBody QueryRequest<String> queryRequest) {
        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            d3Service.checkName(data);
        } catch (Exception e) {
            logger.error("验证名字是否重复异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }
    /**
     * @Description: TODO 获取3D服务信息
     * @Param: []
     * @return: SingleResponse<PD3orgZc>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:51
     */
    @RequestMapping("get3DServiceInfo.json")
    @ResponseBody
    public SingleResponse<PD3orgZc> get3DServiceInfo() {

        SingleResponse<PD3orgZc> response = new SingleResponse<>();
        try {
            PD3orgZc dInfo = d3Service.get3DInfo();
            if (dInfo == null) {
                response.setData(new PD3orgZc());
                return response;
            }
            response.setData(dInfo);
        } catch (Exception e) {
            logger.error("获取3D服务信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
