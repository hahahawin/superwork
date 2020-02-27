package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.PSysLog;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.service.SysLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jianjian Xu
 * @create: 2019/10/24 9:34
 * @description:
 */
@Controller
@RequestMapping("log")
public class LogController {

    public static Logger logger = Logger.getLogger(LogController.class);

    @Autowired
    SysLogService sysLogService;

    /**
     * @Description: TODO 分页查询日志
     * @Param: [queryRequest]
     * @return: PageResponse<PSysLog>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:57
     */
    @RequestMapping("listLog.json")
    @ResponseBody
    public PageResponse<PSysLog> listLog(@RequestBody QueryRequest<PSysLog> queryRequest){

        PageResponse<PSysLog> response = new PageResponse<>();
        PSysLog data = queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PSysLog> pSysLogPageInfo = sysLogService.listLog(data, page, limit);
            response.setData(pSysLogPageInfo.getList());
            response.setTotal(pSysLogPageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询日志异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

}
