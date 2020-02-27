package com.superwork.apcosplatform.controller;


import com.alibaba.fastjson.JSONObject;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.service.SysUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @program: code->FileUpload
 * @description: 上传文件
 * @author: xjj
 * @create: 2019-12-10 15:01
 **/
@Controller
@RequestMapping("upload")
public class FileUpload {


    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SysUserServer sysUserServer;
    @Value("${upload.filePath}")
    String filePath;



    @RequestMapping("uploadApp")
//    @Log("上传文件")
    @ResponseBody
    public JSONObject uploadApp(@RequestParam(value = "file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        JSONObject jsonObject = new JSONObject();
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher  m =p.matcher(fileName);
        if(m.find()){
            jsonObject.put("status", 103);
            jsonObject.put("msg", "文件名含中文，请修改后再上传！");
            return jsonObject;
        }
        if (ext.equals("apk")) {
            File targetFile = new File(new File(filePath).getAbsolutePath() + "/app/" + fileName);
            if (targetFile.exists()) {
                jsonObject.put("status", 102);
                jsonObject.put("msg", "文件已存在，请确认或者修改文件名！");
                return jsonObject;
            } else {
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdir();
                }
                try {
                    targetFile.createNewFile();
                    file.transferTo(targetFile);
                    jsonObject.put("status", 200);
                    jsonObject.put("msg", "上传成功！");
                    jsonObject.put("data", filePath + "/app/" + fileName);
                    jsonObject.put("fileName",fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                    jsonObject.put("status", 500);
                    jsonObject.put("msg", e.getMessage());
                }
            }
        } else {
            jsonObject.put("status", 101);
            jsonObject.put("msg", "格式不正确！格式为：.apk");
            return jsonObject;
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 上传图片，返回路径
     * @Param: [file]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: xujianjian
     * @Date: 2019/12/18 14:02
     */
    @RequestMapping("uploadPhoto")
//    @Log("上传文件")
    @ResponseBody
    public JSONObject uploadPhoto(@RequestParam(value = "file") MultipartFile file,@RequestParam("type") String  type) {
        JSONObject jsonObject = new JSONObject();
       if(file==null){
           jsonObject.put("status", 103);
           jsonObject.put("msg", "未发现上传文件");
           return jsonObject;
       }
        //判断文件大小
        if (file.getSize() > 1024 * 1024 * 10) {
            jsonObject.put("status", 101);
            jsonObject.put("msg", "单个文件不要超过10M");
            return jsonObject;
        }
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        if (ext.equals(".jpg") || ext.equals(".png") || ext.equals(".gif") || ext.equals(".jpeg") || ext.equals(".bmp")) {
            String reFileName = UUID.randomUUID().toString();
            reFileName = reFileName.replaceAll("-","");
            File targetFile = null;
            String data="";
            if("portrait".equals(type)){
                targetFile = new File(new File(filePath).getAbsolutePath() + "/portrait/" + reFileName + ext);
                data = filePath + "/portrait/" + reFileName + ext;
            }else if("problem".equals(type)){
                targetFile = new File(new File(filePath).getAbsolutePath() + "/problem/" + reFileName + ext);
                data = filePath + "/problem/" + reFileName + ext;
            }else{
                targetFile = new File(new File(filePath).getAbsolutePath() + "/logo/" + reFileName + ext);
                data = filePath + "/logo/" + reFileName + ext;
            }
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdir();
            }
            try {
                targetFile.createNewFile();
                file.transferTo(targetFile);
                jsonObject.put("status", 200);
                jsonObject.put("msg", "上传成功！");
                jsonObject.put("data", data);
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("status", 500);
                jsonObject.put("msg", e.getMessage());
            }
        } else {
            jsonObject.put("status", 102);
            jsonObject.put("msg", "格式不正确！");
            return jsonObject;
        }
        return jsonObject;
    }


}
