package com.superwork.apcosplatform.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.app.service.IndexService;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.app.service.AppAcountService;
import com.superwork.apcosplatform.controller.IndexController;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.PSysLogMapper;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.ControlRecordService;
import com.superwork.apcosplatform.service.SysLogService;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.GetWeather;
import com.superwork.apcosplatform.utils.HTTPUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.net.util.IPAddressUtil;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

/**
 * @program: code->AppDataController
 * @description: 获取数据的接口
 * @author: xjj
 * @create: 2019-12-03 16:47
 **/
@Controller
@RequestMapping("appData")
public class AppDataController {
    public static Logger logger = Logger.getLogger(AppDataController.class);

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    AppAcountService appAcountService;

    @Autowired
    IndexService indexService;

    @Autowired
    ControlRecordService controlRecordService;

    @Autowired
    SysLogService sysLogService;

    /**
     * @Description: TODO 通过IP获取天气数据
     * @Param: []
     * @return: SingleResponse<java.util.Map < java.lang.String , java.lang.String>>
     * @Author: xujianjian
     * @Date: 2019/12/3 16:55
     */
    @ApiOperation(value = "通过IP获取天气数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getTodayWeatherByIp.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<Map<String, String>> getTodayWeatherByIp(@RequestParam String cityIp, HttpServletRequest request) {
        SingleResponse<Map<String, String>> response = new SingleResponse<>();
        try {
            if(StringUtils.isEmpty(cityIp)){
                cityIp = HTTPUtils.getIpAddress(request);
                if(cityIp.startsWith("192.") || cityIp.startsWith("127.")){
                    cityIp = "113.204.9.70";//默认重庆
                }
            }
            Map<String, String> todayWeatherByIp = GetWeather.getTodayWeatherByIp(cityIp);
            response.setData(todayWeatherByIp);
        } catch (Exception e) {
            logger.error("通过IP获取天气数据异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 通过城市获取天气数据
     * @Param: []
     * @return: SingleResponse<java.util.Map < java.lang.String , java.lang.String>>
     * @Author: xujianjian
     * @Date: 2019/12/3 16:55
     */
    @ApiOperation(value = "通过城市获取天气数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getTodayWeatherByName.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<Map<String, String>> getTodayWeatherByName(@RequestBody String cityName) {
        SingleResponse<Map<String, String>> response = new SingleResponse<>();
        try {
            Map<String, String> todayWeatherByIp = GetWeather.getTodayWeatherByName(cityName);
            response.setData(todayWeatherByIp);
        } catch (Exception e) {
            logger.error("通过城市获取天气数据异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

//    /**
//     * @Description: TODO 获取本地Token
//     * @Param: []
//     * @return: SingleResponse<java.lang.String>
//     * @Author: xujianjian
//     * @Date: 2019/12/4 9:26
//     */
//    @ApiOperation(value = "控制设备时获取token")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "token", required = true)
//    })
//    @RequestMapping(value = "appGetMyToken.json", method = RequestMethod.GET)
//    @ResponseBody
//    public SingleResponse<String> getMyToken(){
//        SingleResponse<String> response = new SingleResponse<>();
//        try {
//            Map<String, String> info = appAcountService.getInfo();
//            if(StringUtils.isEmpty(info.get("serviceId"))){
//                throw new Exception("请先完善服务信息！");
//            }
//            PSmhDevice pSmhDevice = new PSmhDevice();
//            pSmhDevice.setServiceId(info.get("serviceId"));
//            pSmhDevice.setServiceKey("serviceKey");
//            String myToken = UUID.randomUUID().toString();
//            redisUtil.set(myToken,pSmhDevice);
//            response.setData(myToken);
//        } catch (Exception e) {
//            logger.error("根据账号查询设备异常", e);
//            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
//            response.setMsg(e.getMessage());
//        }
//        return response;
//    }

    /**
     * @Description: TODO  查询设备，执行计划，模式数量
     * @Param: []
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: xujianjian
     * @Date: 2019/12/13 16:39
     */
    @ApiOperation(value = "查询设备，执行计划，模式数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getIndexData.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getIndexData(){
        JSONObject jsonObject = new JSONObject();
        try {
            Map<String, Integer> userDataWithOther = indexService.getUserDataWithOther();
            jsonObject.put("status",200);
            jsonObject.put("msg","查询数据成功！");
            jsonObject.put("data",userDataWithOther);
        } catch (Exception e) {
            logger.error("查询设备，执行计划，模式数量异常", e);
            jsonObject.put("status",HttpStatus.SYSTEM_ERROR.getErrorCode());
            jsonObject.put("msg","查询数据失败！"+e.getMessage());

        }
        return jsonObject;
    }

    /**
     * @Description: TODO 查询我的设备控制情况
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PSbcontrolOriginal>
     * @Author: xujianjian
     * @Date: 2020/1/7 9:12
     */
    @ApiOperation(value = "查询我的设备控制情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listSbControl.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PSbcontrolOriginal> listSbControl(@RequestBody QueryRequest<PSbcontrolOriginal> queryRequest){

        PageResponse<PSbcontrolOriginal> response = new PageResponse<>();
        PSbcontrolOriginal data = queryRequest.getData() == null ? new PSbcontrolOriginal():queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20:queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            data.setControlId(user.getUserId());
            PageInfo<PSbcontrolOriginal> pageInfo = controlRecordService.listSbControl(data, page, limit);
            response.setData(pageInfo.getList());
            response.setTotal(pageInfo.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
            response.setMsg("查询成功！");
        } catch (Exception e) {
            logger.error("查询我的设备控制情况异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询我的模式控制情况
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PSbcontrolOriginal>
     * @Author: xujianjian
     * @Date: 2020/1/7 9:12
     */
    @ApiOperation(value = "查询我的模式控制情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listMsControl.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PMscontrolRecord> listMsControl(@RequestBody QueryRequest<PMscontrolRecord> queryRequest){

        PageResponse<PMscontrolRecord> response = new PageResponse<>();
        PMscontrolRecord data = queryRequest.getData() == null ? new PMscontrolRecord():queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20:queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            data.setControlId(user.getUserId());
            PageInfo<PMscontrolRecord> pageInfo = controlRecordService.listMsControl(data, page, limit);
            response.setData(pageInfo.getList());
            response.setTotal(pageInfo.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
            response.setMsg("查询成功！");
        } catch (Exception e) {
            logger.error("查询我的模式控制情况异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询用户操作记录
     * @Param: [queryRequest]
     * @return: com.superwork.apcosplatform.result.PageResponse<com.superwork.apcosplatform.domain.PSysLog>
     * @Author: xujianjian
     * @Date: 2020/1/11 17:05
     */
    @ApiOperation(value = "查询我的操作记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listMyLog.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PSysLog> listMyLog(@RequestBody QueryRequest<PSysLog> queryRequest){

        PageResponse<PSysLog> response = new PageResponse<>();
        PSysLog data = queryRequest.getData() == null ? new PSysLog():queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20:queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            data.setUsername(user.getUserId());
            PageInfo<PSysLog> pageInfo = sysLogService.listMyLog(data, page, limit);
            response.setData(pageInfo.getList());
            response.setTotal(pageInfo.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
            response.setMsg("查询成功！");
        } catch (Exception e) {
            logger.error("查询我的操作记录异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;
    }





}
