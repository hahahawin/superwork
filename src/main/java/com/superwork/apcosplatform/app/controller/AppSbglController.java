package com.superwork.apcosplatform.app.controller;



import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.aop.Log;
import com.superwork.apcosplatform.app.common.ApiJsonObject;
import com.superwork.apcosplatform.app.common.ApiJsonProperty;
import com.superwork.apcosplatform.app.service.AppSbglService;
import com.superwork.apcosplatform.app.service.IndexService;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.controller.IndexController;
import com.superwork.apcosplatform.result.ListResponse;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;


import com.superwork.apcosplatform.service.AsyncTask;
import com.superwork.apcosplatform.service.SbglService;
import com.superwork.apcosplatform.service.SmhSettingService;


import com.superwork.apcosplatform.utils.ComonUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

/**
 * @program: code->SbglController
 * @description: 设备
 * @author: xjj
 * @create: 2019-11-30 13:49
 **/
@Controller
@RequestMapping("appSbgl")
public class AppSbglController {

    public static Logger logger = Logger.getLogger(AppSbglController.class);
    @Autowired
    SbglService sbglService;
    @Autowired
    AppSbglService appSbglService;
    @Autowired
    SmhSettingService smhSettingService;
    @Autowired
    IndexService indexService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    AsyncTask asyncTask;


    /**
     * @param * @param queryRequest
     * @return PageResponse<PSbgl>
     * @Description //TODO 分页查询设备
     * @author xjj
     * @date 2019/10/25
     */
    @ApiOperation(value = "分页查询设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listsb.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PSbgl> listsb(@RequestBody QueryRequest<PSbgl> queryRequest) {
        PageResponse<PSbgl> response = new PageResponse<>();
        PSbgl data = queryRequest.getData() == null ? new PSbgl() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSbgl> listsb = appSbglService.listsb(data, page, limit);
            response.setData(listsb.getList());
            response.setTotal(listsb.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
        } catch (Exception e) {
            logger.error("分页查询申请的账号（大管家）异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 通过设备类型查询设备控制模板（URL）
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:02
     */
    @ApiOperation(value = "通过设备类型查询设备控制模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "selmodle.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> selmodle(@RequestParam String productCode) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            String selmodle = appSbglService.selmodle(productCode);
            if(StringUtils.isEmpty(selmodle)){
                response.setMsg("无控制模板，此功能暂无法开放！");
            }else{
                response.setMsg("模板地址生成成功！");
            }
            response.setData(selmodle);
        } catch (Exception e) {
            logger.error("通过设备类型查询设备控制模板异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除临时模板页面
     * @Param: [url]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/9 10:05
     */
    @ApiOperation(value = "删除临时模板页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "delMbUrl.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> delMbUrl(@RequestParam String url) {
        SingleResponse<String> response = new SingleResponse<>();
        try {

            String filepath = "/data/nfs2/temporary/";
            File folder = new File(filepath);
            File indexHtml = new File(folder, url);
            if(indexHtml.exists()){
                indexHtml.delete();
                response.setMsg("删除成功！");
            }else{
                response.setMsg("文件不存在！");
            }
        } catch (Exception e) {
            logger.error("删除临时模板页面异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }




    /**
     * @Description: TODO 设备控制
     * @Param: [request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:03
     */
    @Log("设备控制")
    @ApiOperation(value = "设备控制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "sbControl.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> sbControl(@ApiJsonObject(name = "sbControl", value = {
            @ApiJsonProperty(key = "productCode", example = "vV40XS4pXCPo95wosb8", description = "类型编码（必填）"),
            @ApiJsonProperty(key = "serial_num", example = "d09e53beb9634b64b554b7ff8116dd1a", description = "服务KEY（必填）"),
            @ApiJsonProperty(key = "operation", example = "on", description = "操作名（必填）"),
            @ApiJsonProperty(key = "controlParams", example = "{\"p1\":\"1\"}", description = "操作参数（必填）"),
            @ApiJsonProperty(key = "account", example = "1562264", description = "必填")
    }) @RequestBody Map<String, String> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appSbglService.sbControl(map);
            if(!stringResultDO.isSuccess()){
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }else{
                response.setMsg("控制成功！");
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
    @ApiOperation(value = "模式列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listMs.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PSbmogl> listMs(@RequestBody QueryRequest<PSbmogl> queryRequest) {
        PageResponse<PSbmogl> response = new PageResponse<>();
        PSbmogl data = queryRequest.getData() == null ? new PSbmogl() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSbmogl> pSbmoglPageInfo = appSbglService.listMs(data, page, limit);
            response.setData(pSbmoglPageInfo.getList());
            response.setTotal(pSbmoglPageInfo.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
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
    @ApiOperation(value = "添加自定义模式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "addMsxx.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> addJzly(@RequestBody PSbmogl pSbmogl) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appSbglService.addJzly(pSbmogl);
            if(!stringResultDO.isSuccess()){
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }else{
                response.setMsg("添加模式成功！");
            }
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
    @ApiOperation(value = "修改自定义模式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "editMsById.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> editMsById(@RequestBody PSbmogl pSbmogl) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appSbglService.editMsById(pSbmogl);
            if(stringResultDO.isSuccess()){
                response.setMsg("编辑模式成功！");
            }else{
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("修改模式异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
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
    @ApiOperation(value = "删除模式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "delms.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> delms(@RequestBody PSbmogl pSbmogl) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> delms = appSbglService.delms(pSbmogl);
            if(delms.isSuccess()){
                response.setMsg("删除模式成功！");
            }else{
                response.setStatus(501);
                response.setMsg(delms.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("删除模式异常", e);
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
    @ApiOperation(value = "模式控制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "msControl.json", method = RequestMethod.GET)
    @ResponseBody
    @Log("模式控制")
    public SingleResponse<String> msControl(@RequestParam String msId) {
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
        try {
            ResultDO<String> stringResultDO = appSbglService.msControl(msId);

            if(stringResultDO.isSuccess()){
                response.setMsg("控制模式成功！");
                asyncTask.msControlRecord(msId,user.getUserId(),"1",null);
            }else{
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
                asyncTask.msControlRecord(msId,user.getUserId(),"2",stringResultDO.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("模式控制异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
            asyncTask.msControlRecord(msId,user.getUserId(),"2",e.getMessage());
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
    @ApiOperation(value = "根据账号查询设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listsbByAccount.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PSbgl> listsbByAccount(@RequestBody QueryRequest<PSbgl> queryRequest) {
        PageResponse<PSbgl> response = new PageResponse<>();
        PSbgl data = queryRequest.getData() == null ? new PSbgl() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSbgl> listsb = appSbglService.listsb(data, page, limit);
            response.setData(listsb.getList());
            response.setTotal(listsb.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
        } catch (Exception e) {
            logger.error("根据账号查询设备异常", e);
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

    @ApiOperation(value = "分页查询执行计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "listPlan.json", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<PYktZxjh> listPlan(@RequestBody QueryRequest<PYktZxjh> queryRequest) {
        PageResponse<PYktZxjh> response = new PageResponse<>();
        PYktZxjh data = queryRequest.getData() == null ? new PYktZxjh() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUsetOnApp(redisUtil);
            data.setCreatorId(user.getUserId());
            PageInfo<PYktZxjh> pYktZxjhPageInfo = smhSettingService.listPlan(data, page, limit);
            response.setData(pYktZxjhPageInfo.getList());
            response.setTotal(pYktZxjhPageInfo.getTotal());
            response.setPageindex(page);
            response.setPagesize(limit);
            response.setMsg("查询成功！");
        } catch (Exception e) {
            logger.error("分页查询执行计划异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 新增执行计划
     * @Param: [queryRequest, request]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/2 15:28
     */
    @ApiOperation(value = "新增执行计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "addZxjh.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> addZxjh(@RequestBody PYktZxjh pYktZxjh) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appSbglService.addZxjh(pYktZxjh);
            if(stringResultDO.isSuccess()){
                response.setMsg("新增执行计划成功！");
            }else{
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }
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
    @ApiOperation(value = "编辑执行计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "editPlan.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> editPlan(@RequestBody PYktZxjh pYktZxjh) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appSbglService.updateZxjh(pYktZxjh);
            if(stringResultDO.isSuccess()){
                response.setMsg("编辑执行计划成功！");
            }else{
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }
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
    @ApiOperation(value = "删除执行计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "delPlan.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> delPlan(@RequestBody PYktZxjh pYktZxjh) {

        SingleResponse<String> response = new SingleResponse<>();
        try {

            ResultDO<String> stringResultDO =  appSbglService.delZxjh(pYktZxjh);
            if(stringResultDO.isSuccess()){
                response.setMsg("删除执行计划成功！");
            }else{
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
            }

        } catch (Exception e) {
            logger.error("删除执行计划异常", e);
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
    @ApiOperation(value = "新增执行计划进程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "zxjhJcadd.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> zxjhJcadd(@ApiJsonObject(name = "zxjhJcadd", value = {
            @ApiJsonProperty(key = "account", example = "XXXXX", description = "账号（必填）"),
            @ApiJsonProperty(key = "process_name", example = "XXXXX", description = "进程的名称（必填）"),
            @ApiJsonProperty(key = "trigger_conditon_desc", example = "d09e53beb9634b64b554b7ff8116dd1a", description = "条件描述（必填）"),
            @ApiJsonProperty(key = "plan_id", example = "on", description = "执行计划的ID（必填）"),
            @ApiJsonProperty(key = "has_condition", example = "{\"p1\":\"1\"}", description = "是否前置动作，其他进程的id（必填）"),
            @ApiJsonProperty(key = "mode_ids", example = "1562264", description = "模式id字符串，用,隔开"),
            @ApiJsonProperty(key = "delay_trigger_time", example = "1562264", description = "延时时间，单位s"),
            @ApiJsonProperty(key = "excutionConditionList", example = "1562264", description = "触发条件集合"),
            @ApiJsonProperty(key = "excutionTimeConditionList", example = "1562264", description = "时间条件集合"),
            @ApiJsonProperty(key = "pre_trigger_time", example = "1562264", description = "前置时间，单位s"),
            @ApiJsonProperty(key = "mode_desc", example = "1562264", description = "所绑定模式的中文描述，用，隔开"),
    })
                                            @RequestBody Map<String, Object> map) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appSbglService.zxjhJcadd(map);
            if(stringResultDO.isSuccess()){
                response.setMsg("新增执行计划进程成功！");
            }else{
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
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
    @ApiOperation(value = "删除执行计划进程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "deljc.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> deljc(@RequestParam String id, @RequestParam String account) {
        SingleResponse<String> response = new SingleResponse<>();
        try {

            ResultDO<String> stringResultDO =  appSbglService.deljc(id, account);
            if(stringResultDO.isSuccess()){
                response.setMsg("删除执行计划进程成功！");
            }else{
                response.setStatus(501);
                response.setMsg(stringResultDO.getErrorMsg());
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
    @ApiOperation(value = "根据执行计划查询执行计划进程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "zxjhJcList.json", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<ExcutionPlanProcess> zxjhJcList(@RequestBody PYktZxjh pYktZxjh) {
        ListResponse<ExcutionPlanProcess> response = new ListResponse<>();
        try {
            ResultDO<List<ExcutionPlanProcess>> listResultDO = appSbglService.zxjhJcList(pYktZxjh);
            if(listResultDO.isSuccess()){
                response.setData(listResultDO.getData());
                response.setMsg("根据执行计划查询执行计划进程成功！");
            }else{
                response.setStatus(501);
                response.setMsg(listResultDO.getErrorMsg());
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
    @ApiOperation(value = "查询条件类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "selTjlxList.json", method = RequestMethod.GET)
    @ResponseBody
    public ListResponse<PWebPropertyInfo> selTjlxList(@RequestParam String productCode) {
        ListResponse<PWebPropertyInfo> response = new ListResponse<>();
        try {
            Map<String, String> maps = new HashMap<>();
            maps.put("code", productCode);
            List<PWebPropertyInfo> pWebPropertyInfos = smhSettingService.selTjlx(maps);
            response.setData(pWebPropertyInfos);
        } catch (Exception e) {
            logger.error("查询条件类型异常", e);
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
    @ApiOperation(value = "获取账号下的模式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getMs.json", method = RequestMethod.GET)
    @ResponseBody
    public ListResponse<PSbmogl> getMs(@RequestParam String account) {
        ListResponse<PSbmogl> response = new ListResponse<>();
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
     * @Description: TODO 通过设备类型获取模板列表
     * @Param: [queryRequest]
     * @return: ListResponse<SbzclxTemp>
     * @Author: xujianjian
     * @Date: 2019/11/20 16:02
     */
    @ApiOperation(value = "通过设备类型获取模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getMblist.json", method = RequestMethod.GET)
    @ResponseBody
    public ListResponse<SbzclxTemp> getMblist(@RequestParam String productCode) {
        ListResponse<SbzclxTemp> response = new ListResponse<>();
        try {
            ResultDO<List<SbzclxTemp>> mblist = appSbglService.getMblist(productCode);
            if(mblist.isSuccess()){
                response.setData(mblist.getData());
                response.setMsg("通过设备类型获取模板列表成功！");
            }else{
                response.setStatus(501);
                response.setMsg(mblist.getErrorMsg());
            }
        } catch (Exception e) {
            logger.error("通过设备类型获取模板列表异常", e);
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
    @ApiOperation(value = "设置默认模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "setMrmb.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> setMrmb(@RequestParam String id, @RequestParam String mac, @RequestParam String template_type) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            if (!template_type.equals("mobile")) {
                throw new Exception("请选择类型未mobile的模板");
            }
            appSbglService.setMrmb(id, mac);
        } catch (Exception e) {
            logger.error("设置默认模板异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @param * @param queryRequest
     * @return SingleResponse<java.lang.String>
     * @Description //TODO 从大管家同步执行计划，属性，指令，参数
     * @author xjj
     * @date 2019/11/6
     */
    @ApiOperation(value = "从大管家同步执行计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "syncPlan.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<Integer> syncPlan(@RequestParam String productCode) {
        SingleResponse<Integer> response = new SingleResponse<>();
        try {
            ResultDO<Integer> integerResultDO = appSbglService.syncPlan(productCode);
            if(integerResultDO.isSuccess()){
                response.setMsg("同步数量："+integerResultDO.getData());
            }else{
                response.setMsg(integerResultDO.getErrorMsg());
                response.setStatus(501);
            }
        } catch (Exception e) {
            logger.error("从大管家同步执行计划，属性，指令，参数异常", e);
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
    @ApiOperation(value = "同步设备类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "syncSbLx.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<String> syncSbLx() {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            ResultDO<String> stringResultDO = appSbglService.syncSbLx();
            if(stringResultDO.isSuccess()){
                response.setMsg("同步设备类型成功！");
            }else{
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(501);
            }
        } catch (Exception e) {
            logger.error("同步设备类型异常", e);
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
    @ApiOperation(value = "删除设置的模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "delmb.json", method = RequestMethod.POST)
    @ResponseBody
    public SingleResponse<String> delmb(@RequestBody PSbgl pSbgl) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            appSbglService.delmb(pSbgl);
        } catch (Exception e) {
            logger.error("删除设备的设置模板异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 通过产品序列号查询控制次数
     * @Param: [queryRequest]
     * @return: SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/7 17:18
     */
    @ApiOperation(value = "通过产品序列号查询控制次数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true)
    })
    @RequestMapping(value = "getCountBySum.json", method = RequestMethod.GET)
    @ResponseBody
    public SingleResponse<Integer> getCountBySum(@RequestParam String sum){
        SingleResponse<Integer> response = new SingleResponse<>();
        try {
            Integer countBySum = indexService.getCountBySum(sum);
            response.setData(countBySum);
            response.setMsg("查询成功！");
        } catch (Exception e) {
            logger.error("通过产品序列号查询控制次数异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
