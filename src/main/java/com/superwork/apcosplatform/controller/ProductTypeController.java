package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.ProductTypeService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author Jianjian Xu
 * @create: 2020/2/11 16:20
 * @description: 自定义设备类型
 */
@Controller
@RequestMapping("productType")
public class ProductTypeController {
    public static Logger logger = Logger.getLogger(APPVersionController.class);

    @Autowired
    ProductTypeService productTypeService;

    /**
     * @Description: TODO 查询我的所有设备分类
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("listMyProType.json")
    @ResponseBody
    public PageResponse<PProductType> listMyProType(@RequestBody QueryRequest<PProductType> queryRequest) {
        PageResponse<PProductType> response = new PageResponse<>();
        PProductType data = queryRequest.getData() == null ? new PProductType() : queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1 : queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20 : queryRequest.getLimit();
        try {
            PageInfo<PProductType> pageInfo = productTypeService.listMyProType(data, page, limit);
            response.setData(pageInfo.getList());
            response.setTotal(pageInfo.getTotal());
        } catch (Exception e) {
            logger.error("查询我的所有设备分类异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


    /**
     * @Description: TODO 新增设备类型
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("addProType.json")
    @ResponseBody
    public SingleResponse<String> addProType(@RequestBody PProductType productType) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
            productType.setCreateId(user.getUserId());
            productType.setCreateTime(new Date());
            productType.setCompanyId(bccaServiceInfo.getId().toString());
            ResultDO<String> resultDO = productTypeService.addProType(productType);
            if(!resultDO.isSuccess()){
                response.setMsg(resultDO.getErrorMsg());
                response.setStatus(100);
            }
        } catch (Exception e) {
            logger.error("新增设备类型异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 编辑设备类型
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("editProType.json")
    @ResponseBody
    public SingleResponse<String> editProType(@RequestBody PProductType productType) {

        SingleResponse<String> response = new SingleResponse<>();
        try {
            PSysUser user = ComonUtils.getUser();
            productType.setEditId(user.getUserId());
            productType.setEditTime(new Date());
            ResultDO<String> stringResultDO = productTypeService.editProType(productType);
            if(!stringResultDO.isSuccess()){
                response.setMsg(stringResultDO.getErrorMsg());
                response.setStatus(100);
            }
        } catch (Exception e) {
            logger.error("编辑设备类型异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }

    /**
     * @Description: TODO 删除设备类型
     * @Param: [pAppColumn]
     * @return: com.superwork.apcosplatform.result.SingleResponse<java.lang.String>
     * @Author: xujianjian
     * @Date: 2019/12/18 15:43
     */
    @RequestMapping("delProType.json")
    @ResponseBody
    public SingleResponse<String> delProType(@RequestBody PProductType productType) {
        SingleResponse<String> response = new SingleResponse<>();
        try {
            productTypeService.delProType(productType);
        } catch (Exception e) {
            logger.error("删除设备类型异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }




    }
