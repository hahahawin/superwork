package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PReportmessage;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.service.PReportmessageService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("reportmessage")
public class PReportmessageController {

    public static Logger logger = Logger.getLogger(PReportmessageController.class);

    @Autowired
    PReportmessageService pReportmessageService;


    /**
     * @Description: TODO 分页查询上报信息
     * @Param: [queryRequest]
     * @return: PageResponse<PReportmessage>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:57
     */
    @RequestMapping("listPReportmessage.json")
    @ResponseBody
    public PageResponse<PReportmessage> listPReportmessage(@RequestBody QueryRequest<PReportmessage> queryRequest){

        PageResponse<PReportmessage> response= new PageResponse<>();
        PReportmessage data = queryRequest.getData() == null ? new PReportmessage():queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1:queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20:queryRequest.getLimit();
        try {
            PageInfo<PReportmessage> pReportmessagePageInfo = pReportmessageService.listPReportmessage(data,page,limit);
            response.setData(pReportmessagePageInfo.getList());
            response.setTotal(pReportmessagePageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询上报信息异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;

    }

}
