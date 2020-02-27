package com.superwork.apcosplatform.app.controller;

import com.superwork.apcosplatform.app.service.AppAcountService;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.controller.IndexController;
import com.superwork.apcosplatform.domain.PD3orgZc;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.utils.D3HttpClient;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: code->AppD3ServiceController
 * @description: 手机端3D服务
 * @author: xjj
 * @create: 2019-12-16 09:35
 **/
@Controller
@RequestMapping("app3Dservice")
public class AppD3ServiceController {


    public static Logger logger = Logger.getLogger(AppD3ServiceController.class);


    @Value("${d3url}")
    private String d3url;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AppAcountService appAcountService;

    /**
     * @Description: TODO 获取3D工作空间
     * @Param: []
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/8 0008
     */
    @ApiOperation(value = "获取3D工作空间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getWorkSpaceUrl.json", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功！"),
            @ApiResponse(code = 501,message = "请先完善服务信息！"),
    })
    @ResponseBody
    public SingleResponse<String> getWorkSpaceUrl() {
        SingleResponse<String> response = new SingleResponse<>();
        String d3url1 = d3url + "/otheAPI/openWorkstation?user_token=";
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            Map<String, String> info = appAcountService.getInfo();
            String service_id = info.get("serviceId");
            if (StringUtils.isEmpty(service_id)) {
                response.setMsg("请先完善服务信息！");
                response.setStatus(501);
                return response;
            }
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
}
