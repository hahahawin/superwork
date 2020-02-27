package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.service.AsyncTask;
import com.superwork.apcosplatform.utils.BCCAClient;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.Impl.SmhSettingServiceImpl;
import com.superwork.apcosplatform.service.SbglService;
import com.superwork.apcosplatform.service.SmhSettingService;
import com.superwork.apcosplatform.utils.HTTPclient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Jianjian Xu
 * @create: 2019/10/25 15:29
 * @description: 设备管理
 */
@Controller
@RequestMapping("sbgl")
public class SbglController {
    public static Logger logger = Logger.getLogger(SbglController.class);


    @Autowired
    SbglService sbglService;

    @Autowired
    SmhSettingService smhSettingService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AsyncTask asyncTask;


    @Value("${cd_api_url}")
    private String cd_api_url;//成都平台api

    @Value("${back_url}")
    private String back_url;//成都平台回调
    @Value("${kf_api_url}")
    private String kf_api_url;//开发者平台api

    @Value("${dgj_api_url}")
    private String dgj_api_url;


    /**
     * @param * @param queryRequest
     * @return PageResponse<PSbgl>
     * @Description //TODO 分页查询设备
     * @author xjj
     * @date 2019/10/25
     */
    @RequestMapping("listsb.json")
    @ResponseBody
    public PageResponse<PSbgl> listsb(@RequestBody QueryRequest<PSbgl> queryRequest) {
        PageResponse<PSbgl> response = new PageResponse<>();
        PSbgl data = queryRequest.getData() == null ? new PSbgl():queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setCreatorId(user.getUserId());
            PageInfo<PSbgl> listsb = sbglService.listsb(data, page, limit);
            response.setData(listsb.getList());
            response.setTotal(listsb.getTotal());
        } catch (Exception e) {
            logger.error("分页查询申请的账号（大管家）异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param request
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 同步设备类型
     * @author xjj
     * @date 2019/10/28
     */
    @RequestMapping("syncSbLx.json")
    @ResponseBody
    public SingleResponse<String> syncSbLx(HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        try {
            PSbzcLx pSbzcLx = new PSbzcLx();
            pSbzcLx.setEditorId(user.getUserId());
            pSbzcLx.setEditedTime(new Date());
            String msg = smhSettingService.syncSbLx(pSbzcLx);
            response.setMsg(msg);
        } catch (Exception e) {
            logger.error("同步设备类型异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param queryRequest
     * @return PageResponse<PSbzcLx>
     * @Description //TODO 分页查询设备类型
     * @author xjj
     * @date 2019/10/28
     */
    @RequestMapping("listsblx.json")
    @ResponseBody
    public PageResponse<PSbzcLx> listsblx(@RequestBody QueryRequest<PSbzcLx> queryRequest) {
        PageResponse<PSbzcLx> response = new PageResponse<>();
        PSbzcLx data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSbzcLx> listsblx = smhSettingService.listsblx(data, page, limit);
            response.setData(listsblx.getList());
            response.setTotal(listsblx.getTotal());
        } catch (Exception e) {
            logger.error("分页查询设备类型异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }


    /**
     * @Description: TODO 通过设备类型获取模板列表
     * @Param: [queryRequest]
     * @return: ListResponse<SbzclxTemp>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:02
     */
    @RequestMapping("getMblist.json")
    @ResponseBody
    public ListResponse<SbzclxTemp> getMblist(@RequestBody QueryRequest<PSbgl> queryRequest) {
        ListResponse<SbzclxTemp> response = new ListResponse<>();
        PSbgl data = queryRequest.getData();
        PSysUser user = ComonUtils.getUser();
        try {
            data.setEditorId(user.getUserId());
            List<SbzclxTemp> mblist = smhSettingService.getMblist(data);
            response.setData(mblist);
        } catch (Exception e) {
            logger.error("通过设备类型获取模板列表异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 通过设备MAC查询设备控制模板
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:02
     */
    @PostMapping("selmodle.json")
    @ResponseBody
    public SingleResponse<String> selmodle(@RequestBody QueryRequest<String> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            String selmodle = smhSettingService.selmodle(data);
            response.setData(selmodle);
        } catch (Exception e) {
            logger.error("通过设备类型查询设备控制模板异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }
    /**
     * @Description: TODO 通过设备MAC查询设备控制模板（APP端）
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:02
     */
    @PostMapping("selUimodle.json")
    @ResponseBody
    public SingleResponse<String> selUimodle(@RequestBody QueryRequest<String> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        String data = queryRequest.getData();
        try {
            String selmodle = smhSettingService.selUimodle(data);
            response.setData(selmodle);
        } catch (Exception e) {
            logger.error("通过设备类型查询设备控制模板（APP端）异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 设备控制（目前已不使用）
     * @Param: [request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:03
     */
//    @Log("设备控制")
    @RequestMapping("sbControl.json")
    @ResponseBody
    public SingleResponse<String> sbControl(HttpServletRequest request) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String service_id = bccaServiceInfo.getServiceId();
            String service_key = bccaServiceInfo.getServiceKey();
            String productCode = request.getParameter("productCode");
            String serial_num = request.getParameter("serial_num");
            String operation = request.getParameter("operation");
            String controlParams = request.getParameter("controlParams");
            String account = request.getParameter("account");
            if (StringUtils.isEmpty(account)) {
                PSbgl accountBySernum = smhSettingService.findAccountBySernum(serial_num);
                if (accountBySernum != null) {
                    account = accountBySernum.getAccount();
                } else {
                    throw new Exception("未找到对应的设备！");
                }
            }

            Object o = redisUtil.get(service_id);
            String token = "";
            if (o == null) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
                redisUtil.set(service_id, token);
            } else {
                token = o.toString();
            }
            JSONObject obj = new JSONObject();
            obj.put("service_id", service_id);
            obj.put("token", token);
            obj.put("processTime", new Date().getTime());
            obj.put("serial_num", serial_num);
            obj.put("operation", operation);
            controlParams = URLEncoder.encode(controlParams, "utf-8");
            obj.put("controlParams", controlParams);
            obj.put("productId", productCode);
            String result = BCCAClient.sendDeviceCmd(cd_api_url, obj, account);
            JSONObject object = JSONObject.parseObject(result);
            if (object.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
                redisUtil.set(service_id, token);
                obj.put("token", token);
                result = BCCAClient.sendDeviceCmd(cd_api_url, obj, account);
                object = JSONObject.parseObject(result);
            }
            System.out.println("result:" + result);
            if (object.get("resultCode").equals("0000")) {
                JSONObject object2 = JSONObject.parseObject(object.get("resultContent").toString());
                if (object2.containsKey("code")) {
                    if (!object2.get("code").equals("0")) {
                        response.setMsg((String) object2.get("message"));
                        response.setStatus(100);
                    }
                } else {
                    response.setMsg("返回信息格式错误，请联系管理员！");
                    response.setStatus(100);
                }

            } else {
                response.setMsg((String) object.get("resultDesc"));
                response.setStatus(100);
            }

        } catch (Exception e) {
            logger.error("控制设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 模式列表查询
     * @Param: [queryRequest, request]
     * @return: PageResponse<PSbmogl>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:03
     */
    @PostMapping("listMs.json")
    @ResponseBody
    public PageResponse<PSbmogl> listMs(@RequestBody QueryRequest<PSbmogl> queryRequest, HttpServletRequest request) {

        PageResponse<PSbmogl> response = new PageResponse<>();
        PSbmogl data = queryRequest.getData() == null ? new PSbmogl() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setCreatorId(user.getUserId());
            PageInfo<PSbmogl> pSbmoglPageInfo = smhSettingService.listMs(data, page, limit);
            response.setData(pSbmoglPageInfo.getList());
            response.setTotal(pSbmoglPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询模式异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 添加自定义模式
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:03
     */
    @PostMapping("addMsxx.json")
    @ResponseBody
    public SingleResponse<String> addJzly(@RequestBody QueryRequest<PSbmogl> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        PSbmogl data = queryRequest.getData();
        try {
            data.setCreatorId(user.getUserId());
            data.setCreatedTime(new Date());
            smhSettingService.addJzly(data);
        } catch (Exception e) {
            logger.error("添加自定义模式异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 编辑模式
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:04
     */
    @RequestMapping("editMsById.json")
    @ResponseBody
    public SingleResponse<String> editMsById(@RequestBody QueryRequest<PSbmogl> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        PSbmogl data = queryRequest.getData();
        data.setCreatorId(user.getUserId());
        data.setCreatedTime(new Date());
        try {
            smhSettingService.editMsById(data);
        } catch (Exception e) {
            logger.error("修改模式异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }


    /**
     * @Description: TODO 模式命令设置
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:04
     */
    @RequestMapping("setMsml.json")
    @ResponseBody
    public SingleResponse<String> setMsml(@RequestBody QueryRequest<List<PYktMsgngx>> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        List<PYktMsgngx> data = queryRequest.getData();
        String msId = queryRequest.getDirection();
        try {
            smhSettingService.setMsml(data, msId, user);
        } catch (Exception e) {
            logger.error("模式命令设置异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param request
     * @return ListResponse<PSmhSetting>
     * @Description //TODO 查询当前登陆用户下所订阅的账号
     * @author xjj
     * @date 2019/10/29
     */
    @RequestMapping("getAccount.json")
    @ResponseBody
    public ListResponse<PSmhSetting> getAccount() {
        ListResponse<PSmhSetting> response = new ListResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            List<PSmhSetting> account = smhSettingService.getAccount(user.getUserId());
            response.setData(account);
        } catch (Exception e) {
            logger.error("查询当前登陆用户下所订阅的账号异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 根据账号查询设备
     * @Param: [queryRequest]
     * @return: PageResponse<PSbgl>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:04
     */
    @RequestMapping("listsbByAccount.json")
    @ResponseBody
    public PageResponse<PSbgl> listsbByAccount(@RequestBody QueryRequest<PSbgl> queryRequest) {
        PageResponse<PSbgl> response = new PageResponse<>();
        PSbgl data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setCreatorId(user.getUserId());
            PageInfo<PSbgl> listsb = sbglService.listsb(data, page, limit);
            response.setData(listsb.getList());
            response.setTotal(listsb.getTotal());
        } catch (Exception e) {
            logger.error("根据账号查询设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 根据模式ID查询命令
     * @Param: [queryRequest]
     * @return: ListResponse<PYktMsgngx>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:05
     */
    @RequestMapping("listmlBymsId.json")
    @ResponseBody
    public ListResponse<PYktMsgngx> listmlBymsId(@RequestBody QueryRequest<String> queryRequest) {

        ListResponse<PYktMsgngx> response = new ListResponse<>();
        String data = queryRequest.getData();
        try {
            List<PYktMsgngx> pYktMsgngxes = sbglService.listmlBymsId(data);
            response.setData(pYktMsgngxes);
        } catch (Exception e) {
            logger.error("根据模式ID查询命令异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 模式控制
     * @author xjj
     * @date 2019/10/30
     */
    @RequestMapping("msControl.json")
    @ResponseBody
    @Log("模式控制")
    public SingleResponse<String> msControl(@RequestParam String msId) {
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser user = ComonUtils.getUser();
        try {
            smhSettingService.msControl(msId);
            asyncTask.msControlRecord(msId,user.getUserId(),"1",null);//成功
        } catch (Exception e) {
            logger.error("模式控制异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
            asyncTask.msControlRecord(msId,user.getUserId(),"2",e.getMessage());//失败
        }
        return response;

    }

    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO  删除模式
     * @author xjj
     * @date 2019/10/30
     */
    @RequestMapping("delms.json")
    @ResponseBody
    public SingleResponse<String> delms(@RequestBody QueryRequest<PSbmogl> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        PSbmogl data = queryRequest.getData();
        try {
            smhSettingService.delms(data);
        } catch (Exception e) {
            logger.error("删除模式异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 查询执行计划
     * @Param: [queryRequest, request]
     * @return: PageResponse<PYktZxjh>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:06
     */
    @RequestMapping("listPlan.json")
    @ResponseBody
    public PageResponse<PYktZxjh> listPlan(@RequestBody QueryRequest<PYktZxjh> queryRequest) {
        PageResponse<PYktZxjh> response = new PageResponse<>();
        PYktZxjh data = queryRequest.getData() == null ? new PYktZxjh() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUser();
            data.setCreatorId(user.getUserId());
            PageInfo<PYktZxjh> pYktZxjhPageInfo = smhSettingService.listPlan(data, page, limit);
            response.setData(pYktZxjhPageInfo.getList());
            response.setTotal(pYktZxjhPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询执行计划异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param *       @param queryRequest
     * @param request
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 新增执行计划
     * @author xjj
     * @date 2019/10/30
     */
    @PostMapping("addZxjh.json")
    @ResponseBody
    public SingleResponse<String> addZxjh(@RequestBody QueryRequest<PYktZxjh> queryRequest, HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        PYktZxjh data = queryRequest.getData();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        data.setCreatorId(user.getUserId());
        data.setCreatedTime(new Date());
        try {
            smhSettingService.addZxjh(data);
        } catch (Exception e) {
            logger.error("新增执行计划异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 编辑执行计划
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:06
     */
    @RequestMapping("editPlan.json")
    @ResponseBody
    public SingleResponse<String> editPlan(@RequestBody QueryRequest<PYktZxjh> queryRequest, HttpServletRequest request) {

        SingleResponse<String> response = new SingleResponse<>();
        PYktZxjh data = queryRequest.getData();
        HttpSession session = request.getSession();
        PSysUser user = (PSysUser) session.getAttribute("user");
        data.setEditorId(user.getUserId());
        data.setEditedTime(new Date());
        try {
            smhSettingService.updateZxjh(data);
        } catch (Exception e) {
            logger.error("修改执行计划异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 删除执行计划
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:06
     */
    @RequestMapping("delPlan.json")
    @ResponseBody
    public SingleResponse<String> delPlan(@RequestBody QueryRequest<PYktZxjh> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        PYktZxjh data = queryRequest.getData();
        try {
            smhSettingService.delZxjh(data);
        } catch (Exception e) {
            logger.error("删除执行计划异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 设置默认模板
     * @Param: [request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:07
     */
    @RequestMapping("setMrmb.json")
    @ResponseBody
    public SingleResponse<String> setMrmb(HttpServletRequest request) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            String id = request.getParameter("id");
            String mac = request.getParameter("mac");
            String template_type = request.getParameter("template_type");
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (StringUtils.isEmpty(bccaServiceInfo.getServiceId()) || StringUtils.isEmpty(bccaServiceInfo.getServiceKey())) {
                response.setStatus(100);
                response.setMsg("请先完善服务接入信息！");
                return response;
            }
            String service_id = bccaServiceInfo.getServiceId();
            String service_key = bccaServiceInfo.getServiceKey();
            String account = null;
            String  token = HTTPclient.getNewAccessToken(dgj_api_url,service_id,service_key);
            String result =BCCAClient.getTempLate(kf_api_url,service_id,token,account,id);
            JSONObject jsonObject2 = JSONObject.parseObject(result);
            if (jsonObject2.get("resultCode").equals("0000")) {
                jsonObject2 = JSONObject.parseObject(jsonObject2.get("resultContent").toString());
                String template_html = jsonObject2.get("template_html").toString();
                if (template_html == null || "".equals(template_html)) {
                    response.setStatus(100);
                    response.setMsg("模板代码为空，默认模板设置失败！");
                    return response;
                }
                PControFormwork pControFormwork = new PControFormwork();
                pControFormwork.setCode(mac);
                pControFormwork.setCreatorId(user.getUserId());
                pControFormwork.setCreatedTime(new Date());
                pControFormwork.setHtmlmodle(template_html);
                if("pc".equals(template_type)){
                    pControFormwork.setAttr1("pc");
                }else{
                    pControFormwork.setAttr1("mobile");
                }
                smhSettingService.setHtmlModel(pControFormwork);

            } else if (jsonObject2.get("resultCode").equals("0101")) {
                token = HTTPclient.getNewAccessToken(dgj_api_url,service_id,service_key);
                result =BCCAClient.getTempLate(kf_api_url,service_id,token,account,id);
                jsonObject2 = JSONObject.parseObject(result);
                if (jsonObject2.get("resultCode").equals("0000")) {
                    jsonObject2 = JSONObject.parseObject(jsonObject2.get("resultContent").toString());
                    String template_html = jsonObject2.get("template_html").toString();
                    if (template_html == null || "".equals(template_html)) {
                        response.setStatus(100);
                        response.setMsg("模板代码为空，默认模板设置失败！");
                        return response;
                    }
                    PControFormwork pControFormwork = new PControFormwork();
                    pControFormwork.setCode(mac);
                    pControFormwork.setCreatorId(user.getUserId());
                    pControFormwork.setCreatedTime(new Date());
                    pControFormwork.setHtmlmodle(template_html);
                    if("pc".equals(template_type)){
                        pControFormwork.setAttr1("pc");
                    }else{
                        pControFormwork.setAttr1("mobile");
                    }
                    smhSettingService.setHtmlModel(pControFormwork);
                } else {
                    response.setStatus(100);
                    response.setMsg(jsonObject2.get("resultDesc").toString());
                }
            } else {
                response.setStatus(100);
                response.setMsg(jsonObject2.get("resultDesc").toString());
            }

        } catch (Exception e) {
            logger.error("设置默认模板异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param map
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 新增执行计划进程
     * @author xjj
     * @date 2019/11/4
     */
    @RequestMapping("zxjhJcadd.json")
    @ResponseBody
    public SingleResponse<String> zxjhJcadd(@RequestBody Map<String, Object> map) {
        SingleResponse<String> response = new SingleResponse<>();
        System.out.println(map);
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String cd_service_id = bccaServiceInfo.getServiceId();
            String cd_service_key = bccaServiceInfo.getServiceKey();
            JSONObject obj = new JSONObject(map);
            System.out.println(obj);
            String account = obj.get("account") == null ? "" : obj.get("account").toString();
            Object o = redisUtil.get(cd_service_id);
            String token = null;
            if (o == null) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, cd_service_id, cd_service_key, back_url, account);
                redisUtil.set(cd_service_id, token);
            } else {
                token = o.toString();
            }
            obj.put("service_id", cd_service_id);
            obj.put("token", token);
            obj.put("processTime", new Date().getTime());
            String result =BCCAClient.addExcutionPlanProcess(cd_api_url,obj,account);
            System.out.println("result:" + result);
            if (result == null) {
                response.setStatus(100);
                response.setMsg("添加执行计划进程接口无返回！");
                return response;
            }
            JSONObject object = JSONObject.parseObject(result);
            if (object.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, cd_service_id, cd_service_key, back_url, account);
                redisUtil.set(cd_service_id, token);
                obj.put("token", token);
                result =BCCAClient.addExcutionPlanProcess(cd_api_url,obj,account);
                object = JSONObject.parseObject(result);
            }
            if (!object.get("resultCode").equals("0000")) {
                response.setStatus(100);
                response.setMsg(object.get("resultDesc").toString());
            }
        } catch (Exception e) {
            logger.error("新增执行计划进程异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 删除执行计划进程
     * @author xjj
     * @date 2019/11/5
     */
    @RequestMapping("deljc.json")
    @ResponseBody
    public SingleResponse<String> deljc(@RequestBody QueryRequest<String> queryRequest) {

        SingleResponse<String> response = new SingleResponse<>();
        String id = queryRequest.getData();
        String account = queryRequest.getDirection();
        System.out.println(account);
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String service_id = bccaServiceInfo.getServiceId();
            String service_key = bccaServiceInfo.getServiceKey();
            Object o = redisUtil.get(service_id);
            String token = "";
            if (o == null) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            } else {
                token = o.toString();
            }
            JSONObject obj = new JSONObject();
            obj.put("service_id", service_id);
            obj.put("token", token);
            obj.put("processTime", new Date().getTime());
            obj.put("id", id);
            String result =  BCCAClient.deleteExcutionPlanProcess(cd_api_url,obj,account);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
                redisUtil.set(service_id, token);
                obj.put("token", token);
                result =  BCCAClient.deleteExcutionPlanProcess(cd_api_url,obj,account);
                jsonObject = JSONObject.parseObject(result);
            }
            if (!jsonObject.get("resultCode").equals("0000")) {
                response.setStatus(100);
                response.setMsg(jsonObject.get("resultContent").toString());
            }
        } catch (Exception e) {
            logger.error("删除执行计划进程异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param queryRequest
     * @return ListResponse<ExcutionPlanProcess>
     * @Description //TODO 根据执行计划查询执行计划进程
     * @author xjj
     * @date 2019/11/4
     */
    @RequestMapping("zxjhJcList.json")
    @ResponseBody
    public ListResponse<ExcutionPlanProcess> zxjhJcList(@RequestBody QueryRequest<PYktZxjh> queryRequest) {

        ListResponse<ExcutionPlanProcess> response = new ListResponse<>();
        PYktZxjh data = queryRequest.getData();
        try {
            String yptjh_id = data.getYptjhId();
            String account = data.getZxjhYptzh();
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String service_id = bccaServiceInfo.getServiceId();
            String service_key = bccaServiceInfo.getServiceKey();
            Object o = redisUtil.get(service_id);
            String token = "";
            if (o == null) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
            } else {
                token = o.toString();
            }
            JSONObject obj = new JSONObject();
            obj.put("service_id", service_id);
            obj.put("token", token);
            obj.put("processTime", new Date().getTime());
            obj.put("plan_id", yptjh_id);
            String result = BCCAClient.queryExcutionPlanProcess(cd_api_url,obj,account);
            System.out.println("result:" + result);
            if (result == null) {
                response.setStatus(100);
                response.setMsg("执行计划进程查询接口无返回！");
                return response;
            }
            JSONObject object = JSONObject.parseObject(result);
            if (object.get("resultCode").equals("0101")) {
                token = BCCAClient.getNewToken(dgj_api_url,cd_api_url, service_id, service_key, back_url, account);
                redisUtil.set(service_id, token);
                obj.put("token", token);
                result = BCCAClient.queryExcutionPlanProcess(cd_api_url,obj,account);
                object = JSONObject.parseObject(result);
            }
            if (object.get("resultCode").equals("0000")) {
                String jclist = object.get("resultContent").toString();
                ArrayList<ExcutionPlanProcess> list = (ArrayList<ExcutionPlanProcess>) JSONArray.parseArray(jclist, ExcutionPlanProcess.class);
                response.setData(list);
            } else {
                response.setStatus(100);
                response.setMsg(object.get("resultDesc").toString());
            }
        } catch (Exception e) {
            logger.error("根据执行计划查询执行计划进程异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 查询条件类型
     * @Param: [map]
     * @return: ListResponse<PWebPropertyInfo>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:07
     */
    @PostMapping("selTjlxList.json")
    @ResponseBody
    public ListResponse<PWebPropertyInfo> selTjlxList(@RequestBody Map<String, String> map) {

        ListResponse<PWebPropertyInfo> response = new ListResponse<>();

        try {
            String account = map.get("account");
            String code = map.get("code");
            if (account == null || account.equals("")) {
                response.setStatus(100);
                response.setMsg("参数为空，请检查传入参数！");
            } else {
                Map<String, String> maps = new HashMap<>();
                maps.put("code", code);
                List<PWebPropertyInfo> pWebPropertyInfos = smhSettingService.selTjlx(maps);
                response.setData(pWebPropertyInfos);
            }
        } catch (Exception e) {
            logger.error("查询条件类型异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 通过条件类型查询触发值
     * @Param: [map]
     * @return: ListResponse<java.util.Map < java.lang.String , java.lang.String>>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:07
     */
    @PostMapping("selCfzByTjlx.json")
    @ResponseBody
    public ListResponse<Map<String, String>> selCfzByTjlx(@RequestBody Map<String, String> map) {

        ListResponse<Map<String, String>> response = new ListResponse<>();
        try {
            String bind_type = map.get("bind_type");
            String property_id = map.get("property_id");
            Map<String, String> maps = new HashMap<>();
            maps.put("property_id", property_id);
            maps.put("bind_type", bind_type);
            List<Map<String, String>> list = smhSettingService.selCfzByTjlx(maps);
            response.setData(list);
        } catch (Exception e) {
            logger.error("通过条件类型查询触发值异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @param * @param queryRequest
     * @return ListResponse<PSbzcLx>
     * @Description //TODO 根据账号查询设备
     * @author xjj
     * @date 2019/11/4
     */
    @RequestMapping("getSbLxlist.json")
    @ResponseBody
    public ListResponse<PSbgl> getSbLxlist(@RequestBody QueryRequest<String> queryRequest) {
        ListResponse<PSbgl> response = new ListResponse<>();
        String data = queryRequest.getData();
        try {
            List<PSbgl> sbLxlist = smhSettingService.getSbLxlist(data);
            response.setData(sbLxlist);
        } catch (Exception e) {
            logger.error("根据账号查询设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param queryRequest
     * @return ListResponse<PSbmogl>
     * @Description //TODO 获取账号下的模式
     * @author xjj
     * @date 2019/11/5
     */
    @RequestMapping("getMs.json")
    @ResponseBody
    public ListResponse<PSbmogl> getMs(@RequestBody QueryRequest<String> queryRequest) {
        ListResponse<PSbmogl> response = new ListResponse<>();
        String account = queryRequest.getData();
        try {
            List<PSbmogl> ms = smhSettingService.getMs(account);
            response.setData(ms);
        } catch (Exception e) {
            logger.error("根据账号查询设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 获取本地Token
     * @Param: []
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/4 9:26
     */
    @RequestMapping("getMyToken.json")
    @ResponseBody
    public SingleResponse<String> getMyToken() {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
                throw new Exception("请先完善服务信息！");
            }
            String myToken = UUID.randomUUID().toString();
            redisUtil.set(myToken, bccaServiceInfo);
            response.setData(myToken);
        } catch (Exception e) {
            logger.error("根据账号查询设备异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除设置的模板
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/7 17:18
     */
    @RequestMapping("delmb.json")
    @ResponseBody
    public SingleResponse<String> delmb(@RequestBody PSbgl pSbgl) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            smhSettingService.delmb(pSbgl);
        } catch (Exception e) {
            logger.error("删除设备的设置模板异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }
    /**
     * @Description: TODO 通过序列号查询模式
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PSbmogl>
     * @Author: xujianjian
     * @Date: 2019/12/27 15:27
     */
    @RequestMapping("listmsBysum.json")
    @ResponseBody
    public PageResponse<PSbmogl> listmsBysum(@RequestBody QueryRequest<String> queryRequest){

        PageResponse<PSbmogl> response = new PageResponse<>();
        try {
            String sum = queryRequest.getData();
            Integer page = queryRequest.getPage() == null ? 1:queryRequest.getPage();
            Integer limit = queryRequest.getLimit() == null ? 20:queryRequest.getLimit();
            PageInfo<PSbmogl> pSbmoglPageInfo = sbglService.listmsBysum(sum, page, limit);
            response.setData(pSbmoglPageInfo.getList());
            response.setTotal(pSbmoglPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("根据设备序列查询设备绑定的模式异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }


    /**
     * @Description: TODO 查询设备注册网关
     * @Param: []
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/17 10:23
     */
    @RequestMapping("wgOnLine.json")
    @ResponseBody
    public SingleResponse<JSONObject> wgOnLine(@RequestBody PSbgl pSbgl){
        SingleResponse<JSONObject> response = new SingleResponse<>();
        try {
           //调用接口
            Map<String, Object> map = smhSettingService.wgOnLine(pSbgl);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",map.get("code").toString());
            jsonObject.put("data",map.get("data"));
            response.setData(jsonObject);
        } catch (Exception e) {
            logger.error("查询网关是否在线异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 设备区域编辑
     * @Param: []
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/17 10:23
     */
    @RequestMapping("editSb.json")
    @ResponseBody
    public SingleResponse<String> editSb(@RequestBody PSbgl pSbgl){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            sbglService.editSb(pSbgl);
        } catch (Exception e) {
            logger.error("设备区域编辑异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
