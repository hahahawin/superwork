package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.service.SbAliasService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: code->SbAliasController
 * @description: 设备别名
 * @author: xjj
 * @create: 2020-01-07 14:01
 **/
@Controller
@RequestMapping("uiTemplate")
public class SbAliasController {

    public static Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    SbAliasService sbAliasService;


    /**
     * @Description: TODO 获取设备别名
     * @Param: [serialNum]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: xujianjian
     * @Date: 2020/1/7 14:40
     */
    @RequestMapping(value = "getRemark", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getRemark(@RequestParam String serialNum){
        JSONObject jsonObject = new JSONObject();
        try {
            String remark = sbAliasService.getRemark(serialNum);
            if(remark == null){
                jsonObject.put("resultCode","2");
                jsonObject.put("resultDesc","未找到设置的别名!");
            }else{
                jsonObject.put("resultCode","1");
                jsonObject.put("resultDesc","查询成功！!");
                jsonObject.put("resultContent",remark);
            }
        } catch (Exception e) {
            logger.error("获取设备别名异常", e);
            jsonObject.put("resultCode","2");
            jsonObject.put("resultDesc",e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 保存设备别名
     * @Param: [jsonObject]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: xujianjian
     * @Date: 2020/1/7 14:50
     */
    @RequestMapping(value = "saveRemark", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject saveRemark(@RequestBody JSONObject jsonObject){
        JSONObject json = new JSONObject();
        try {
            sbAliasService.saveRemark(jsonObject);
            json.put("resultCode","1");
            json.put("resultDesc","保存成功！");
        } catch (Exception e) {
            logger.error("保存设备别名异常", e);
            json.put("resultCode","2");
            json.put("resultDesc",e.getMessage());
        }
        return json;
    }

}
