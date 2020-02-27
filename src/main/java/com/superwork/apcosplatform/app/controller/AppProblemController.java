package com.superwork.apcosplatform.app.controller;

import com.superwork.apcosplatform.app.service.AppProblemService;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.controller.IndexController;
import com.superwork.apcosplatform.domain.PAppColumn;
import com.superwork.apcosplatform.domain.PAppProblem;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.utils.ComonUtils;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @program: code->AppProblemController
 * @description: APP端问题上报
 * @author: xjj
 * @create: 2019-12-18 14:19
 **/
@Controller
@RequestMapping("appProblem")
public class AppProblemController {
    public static Logger logger = Logger.getLogger(AppProblemController.class);
    @Autowired
    AppProblemService appProblemService;
    @Autowired
    RedisUtil redisUtil;


    /**
     * @Description: TODO APP上报异常问题
     * @Param: [pAppProblem]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 14:32
     */
    @ApiOperation(value = "APP上报异常问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "addProblem.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> addProblem(@RequestBody PAppProblem pAppProblem){

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            pAppProblem.setCreateId(user.getUserId());
            pAppProblem.setCreateTime(new Date());
            appProblemService.addProblem(pAppProblem);
            response.setMsg("新增成功！");
        } catch (Exception e) {
            logger.error("APP上报异常问题异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询我的上报问题
     * @Param: []
     * @return: com.superwork.apcosplatform.result.ListResponse<com.superwork.apcosplatform.domain.PAppProblem>
     * @Author: xujianjian
     * @Date: 2019/12/18 14:31
     */
    @ApiOperation(value = "查询我的上报问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listMyProblem.json", method = RequestMethod.GET)
    @ResponseBody
    public ListResponse<PAppProblem> listMyProblem(){

        ListResponse<PAppProblem> response = new ListResponse<>();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            List<PAppProblem> pAppProblems = appProblemService.listMyProblem(user.getUserId());
            response.setData(pAppProblems);
            response.setMsg("查询成功！");
        } catch (Exception e) {
            logger.error("查询我的上报问题异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 查询常见问题
     * @Param: []
     * @return: com.superwork.apcosplatform.result.ListResponse<com.superwork.apcosplatform.domain.PAppColumn>
     * @Author: xujianjian
     * @Date: 2019/12/19 9:19
     */
    @ApiOperation(value = "查询常见问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listCommonProblem.json", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<PAppColumn> listCommonProblem(@RequestBody QueryRequest<String> queryRequest){
        ListResponse<PAppColumn> response = new ListResponse<>();
        String data = queryRequest.getData();
        try {
            List<PAppColumn> pAppColumns = appProblemService.listCommonProblem(data);
            response.setMsg("查询成功！");
            response.setData(pAppColumns);
        } catch (Exception e) {
            logger.error("查询常见问题异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg("查询常见问题失败！"+e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 评价常见问题
     * @Param: []
     * @return: com.superwork.apcosplatform.result.ListResponse<com.superwork.apcosplatform.domain.PAppColumn>
     * @Author: xujianjian
     * @Date: 2019/12/19 9:19
     */
    @ApiOperation(value = "评价常见问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "成功！"),
            @ApiResponse(code = 201,message = "重复评价！"),
            @ApiResponse(code = 202,message = "参数错误！")
    })
    @RequestMapping(value = "commonProYesOrNo.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> commonProYesOrNo(@RequestParam String id,@RequestParam String decision){
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appProblemService.commonProYesOrNo(id, decision);
            if(!stringResultDO.isSuccess()){
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(stringResultDO.getErrorCode());
            }else{
                response.setMsg("评价成功！");
            }
        } catch (Exception e) {
            logger.error("评价常见问题异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg("评价失败！"+e.getMessage());
        }
        return response;


    }




}
