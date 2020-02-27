package com.superwork.apcosplatform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PReportmessage;
import com.superwork.apcosplatform.domain.PSbgl;
import com.superwork.apcosplatform.domain.PSbzcLx;
import com.superwork.apcosplatform.domain.PSmhSetting;
import com.superwork.apcosplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 外部调用接口，不需要拦截
 */
@Controller
@RequestMapping("/interface")
public class InterFaceAction {
    @Autowired
    SmhSettingService smhSettingService;
    @Autowired
    SbglService sbglService;
    @Autowired
    PReportmessageService pReportmessageService;
    @Autowired
    InterFaceService interFaceService;
    @Autowired
    UIService uIService;

    @Autowired
    AsyncTask asyncTask;



    /**
     * @Description: TODO 数据回调接口
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/20 15:56
     */
    @PostMapping("otherMethod")
    @ResponseBody
    public Map<String, Object> otherMethod(@RequestBody JSONObject map, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("第三方回调URL："+requestURI);
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String code = map.get("code").toString();
            JSONObject data = map.getJSONObject("data");
            System.out.println(code + "==" + data);
            if ("reportMessage".equals(code)) {
                System.out.println("设备信息上报：" + data);
                //data:{"reportMessage":"{r1:01 00}","product_code":"00000146","serial_num":"00158d00019bcabb","account":"C280000002","cmd_id":"1118359254014001154","cmd_name":"报警应答"}
                String product_code = data.get("product_code").toString();
                String serial_num = data.get("serial_num").toString();
                String account = data.get("account").toString();
                String cmd_id = data.get("cmd_id").toString();
                String cmd_name = data.get("cmd_name").toString();
                String reportMessage = data.get("reportMessage").toString();
                PReportmessage message = new PReportmessage();
                message.setProductCode(product_code);
                message.setSerialNum(serial_num);
                message.setAccount(account);
                message.setCmdId(cmd_id);
                message.setCmdName(cmd_name);
                message.setReportmessage(reportMessage);
                message.setCreatedTime(new Date());
                PSmhSetting smhSetting = new PSmhSetting();
                smhSetting.setSmarthomeAccount(account);
                asyncTask.pushReportMsg(message);
                List<PSmhSetting> list = smhSettingService.find(smhSetting);
                if (list.size() > 0) {
                    pReportmessageService.insertMessage(message);
                }
            } else if ("subscription".equals(code)) {
                System.out.println("账户订阅结果：" + data);
//                订阅结果接口
//                date:{"account":"C280001","subscrip_status":"1"}
//                subscrip_status 1 通过  2 未通过
                String account = data.get("account").toString();
                String subscrip_status = data.get("subscrip_status").toString();
                PSmhSetting smhSetting = new PSmhSetting();
                smhSetting.setSmarthomeAccount(account);
                smhSetting.setAttr2(subscrip_status);
                smhSettingService.updateDyjg(smhSetting);
            } else if ("productUpdate".equals(code)) {
                System.out.println("账户设备更新：" + data);
                //账户设备更新
//                date:{"account":"C280001","type":"add|delete","id":""}
                String type = data.get("type").toString();
                String account = data.get("account").toString();
                if (type.equals("delete")) {
                    String id = data.get("id").toString();
                    PSbgl sbgl = new PSbgl();
                    sbgl.setId(id);
                    sbglService.delete(sbgl);
                } else if (type.equals("add")) {
                    //重新同步账户设备
                    //通过账户查询账户信息
                    PSmhSetting smhSetting = new PSmhSetting();
                    smhSetting.setSmarthomeAccount(account);
                    List<PSmhSetting> list = smhSettingService.find(smhSetting);
                    if (list.size() > 0) {
                        smhSettingService.syncSbxx(smhSetting);
                    }
                }
            }
            jsonObject.put("resultCode", "success");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D平台回调查询账号
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/20 12:50
     */
    @PostMapping("/getSmhlist/sbInter/getSmhlist")
    @ResponseBody
    public Map<String, Object> getSmhlist(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String borg_id = map.get("org_id");
            List<PSmhSetting> smhlist = interFaceService.getSmhlist(borg_id);
            ArrayList<Map<String, String>> objects = new ArrayList<>();
            for (PSmhSetting pSmhSetting : smhlist) {
                Map<String, String> map1 = new HashMap<>();
                map1.put("smarthome_account", pSmhSetting.getSmarthomeAccount());
                objects.add(map1);
            }
            jsonObject.put("data", objects);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 根据账号查询设备类型
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/getSbLxlist")
    @ResponseBody
    public Map<String, Object> getSbLxlist(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String borg_id = map.get("org_id");
            String account = map.get("account");
            List<PSbzcLx> sbLxlist = interFaceService.getSbLxlist(borg_id, account);
            jsonObject.put("data", sbLxlist);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 根据账号查询设备
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/getSblist")
    @ResponseBody
    public Map<String, Object> getSblist(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String borg_id = map.get("org_id");
            String account = map.get("account");
            String code = map.get("code");
            String name = map.get("name");
            String num = map.get("num");
            String pageSize = map.get("pageSize");
            String pageNo = map.get("pageNo");

            Map<String, String> map1 = new HashMap<>();
            map1.put("borg_id", borg_id);
            map1.put("account", account);
            map1.put("code", code);
            map1.put("name", name);
            map1.put("num", num);
            map1.put("pageSize", pageSize == null ? "10" : pageSize);
            map1.put("pageNo", pageNo == null ? "1" : pageNo);
            PageInfo<Map<String, String>> sblist = interFaceService.getSblist(map1);
            jsonObject.put("data", sblist.getList());
            jsonObject.put("total", sblist.getTotal());
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 根据账号获取模式
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/getZhms")
    @ResponseBody
    public Map<String, Object> getZhms(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String borg_id = map.get("org_id");
            String account = map.get("account");
            Map<String, String> map1 = new HashMap<>();
            map1.put("borg_id", borg_id);
            map1.put("account", account);
            List<Map<String, String>> zhms = interFaceService.getZhms(map1);
            jsonObject.put("data", zhms);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D添加执行计划
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/addZxjh")
    @ResponseBody
    public Map<String, Object> addZxjh(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String borg_id = map.get("org_id");
            String account = map.get("account");
            String mc = map.get("mc");
            String zt = map.get("zt");
            String user_id = map.get("user_id");
            Map<String, String> map1 = new HashMap<>();
            map1.put("borg_id", borg_id);
            map1.put("account", account);
            map1.put("mc", mc);
            map1.put("zt", zt);
            map1.put("user_id", user_id);
            String s = interFaceService.addZxjh(map1);
            jsonObject.put("yid", s);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "操作成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D编辑执行计划
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/editZxjh")
    @ResponseBody
    public Map<String, Object> editZxjh(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
           interFaceService.editZxjh(map);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }
    /**
     * @Description: TODO 3D删除执行计划
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/delZxjh")
    @ResponseBody
    public Map<String, Object> delZxjh(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
           interFaceService.delZxjh(map);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D执行计划进程添加
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/zxjhJcadd")
    @ResponseBody
    public Map<String, Object> zxjhJcadd(@RequestBody Map<String, Object> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String s = interFaceService.zxjhJcadd(map);
            jsonObject.put("resultCode", 200);
            jsonObject.put("jcid", s);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D执行计划进程编辑
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/zxjhJcedit")
    @ResponseBody
    public Map<String, Object> zxjhJcedit(@RequestBody Map<String, Object> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
             interFaceService.zxjhJcedit(map);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "编辑成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D执行计划进程删除
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/zxjhJcdel")
    @ResponseBody
    public Map<String, Object> zxjhJcdel(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
             interFaceService.zxjhJcdel(map);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D执行计划查询
     * @Param: [map]
     * @return: java.util.Map<java.lang.String , java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/14 10:26
     */
    @PostMapping("/getSmhlist/sbInter/zxjhJcList")
    @ResponseBody
    public Map<String, Object> zxjhJcList(@RequestBody Map<String, String> map) {
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String s = interFaceService.zxjhJcList(map);
            List<Map<String,String>> listObjectSec = JSONArray.parseObject(s,List.class);
            jsonObject.put("resultCode", 200);
            jsonObject.put("data", s);
            jsonObject.put("total", listObjectSec.size());
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "查询成功！");
        } catch (Exception e) {
            jsonObject.put("resultCode", "error");
            jsonObject.put("resultDesc", e.getMessage());
        }
        return jsonObject;
    }

    /**
     * @Description: TODO 3D平台编辑模式
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/11/25 9:05
     */
    @PostMapping("/getSmhlist/sbInter/editMode")
    @ResponseBody
    public Map<String, Object> editMode(@RequestBody JSONObject map){
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            String type = null;
            if(map.containsKey("type")){
                type = map.get("type").toString();
            }
            if(type == null || "".equals(type)){
                jsonObject.put("resultCode", 500);
                jsonObject.put("resultDesc", "操作类型不能为空！");
                return jsonObject ;
            }
            String org_id = null;
            if("add".equals(type) || "edit".equals(type)){
                String sbmogl_name = null;
                if(map.containsKey("sbmogl_name") && map.get("sbmogl_name") != null){
                    sbmogl_name = map.get("sbmogl_name").toString();
                }
                if(sbmogl_name == null || "".equals(sbmogl_name)){
                    jsonObject.put("resultCode", 500);
                    jsonObject.put("resultDesc", "模式名称不能为空！");
                    return jsonObject ;
                }
                String sbmogl_account = null;
                if(map.containsKey("sbmogl_account") && map.get("sbmogl_account") != null){
                    sbmogl_account = map.get("sbmogl_account").toString();
                }
                if(sbmogl_account == null || "".equals(sbmogl_account)){
                    jsonObject.put("resultCode", 500);
                    jsonObject.put("resultDesc", "设备账户不能为空！");
                    return jsonObject ;
                }
                String msmls = null;
                if(map.containsKey("msmls") && map.get("msmls") != null){
                    msmls = map.get("msmls").toString();
                }
                if(msmls == null || "".equals(msmls)){
                    jsonObject.put("resultCode", 500);
                    jsonObject.put("resultDesc", "模式命令不能为空！");
                    return jsonObject ;
                }
                String user_id = null;
                if(map.containsKey("user_id") && map.get("user_id") != null){
                    user_id = map.get("user_id").toString();
                }
                if(user_id == null || "".equals(user_id)){
                    jsonObject.put("resultCode", 500);
                    jsonObject.put("resultDesc", "用户ID不能为空！");
                    return jsonObject ;
                }
            }
            if("del".equals(type) || "edit".equals(type)){
                String sbmogl_id = null;
                if(map.containsKey("sbmogl_id") && map.get("sbmogl_id") != null){
                    sbmogl_id = map.get("sbmogl_id").toString();
                }
                if(sbmogl_id == null || "".equals(sbmogl_id)){
                    jsonObject.put("resultCode", 500);
                    jsonObject.put("resultDesc", "模式ID不能为空！");
                    return jsonObject ;
                }
                String sbmogl_ymsid = null;
                if(map.containsKey("sbmogl_ymsid") && map.get("sbmogl_ymsid") != null){
                    sbmogl_ymsid = map.get("sbmogl_ymsid").toString();
                }
                if(sbmogl_ymsid == null || "".equals(sbmogl_ymsid)){
                    jsonObject.put("resultCode", 500);
                    jsonObject.put("resultDesc", "云模式ID不能为空！");
                    return jsonObject ;
                }
            }

            if(map.containsKey("org_id") && map.get("org_id") != null){
                org_id = map.get("org_id").toString();
            }
            if(org_id == null || "".equals(org_id)){
                jsonObject.put("resultCode", 500);
                jsonObject.put("resultDesc", "组织ID不能为空！");
                return jsonObject ;
            }

            map.put("org_id",org_id);
            Map<String, Object> map1 = interFaceService.addAllMsxx(map);
            if("add".equals(type)){
                jsonObject.put("sbmogl_id",map1.get("sbmogl_id"));
                jsonObject.put("sbmogl_ymsid",map1.get("sbmogl_ymsid"));
            }
            jsonObject.put("resultCode", 200);
            if("add".equals(type)){
                jsonObject.put("resultDesc", "模式添加成功！");
            }else if("edit".equals(type)){
                jsonObject.put("resultDesc", "模式编辑成功！");
            }else if("del".equals(type)){
                jsonObject.put("resultDesc", "模式删除成功！");
            }
        } catch (Exception e) {
            jsonObject.put("resultCode", 500);
            jsonObject.put("resultDesc", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject ;
    }

    /**
     * @Description: TODO 3D查询模式详情
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2020/1/7 11:12
     */
    @PostMapping("/getSmhlist/sbInter/selMsDetail")
    @ResponseBody
    public Map<String,Object> selMsDetail(@RequestBody Map<String,String> map){

        Map<String, Object> jsonObject = new HashMap<>();
        String sbmogl_id = map.get("sbmogl_id");
        try {
            Map<String, Object> stringObjectMap = interFaceService.selMsDetail(sbmogl_id);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "模式查询成功！");
            jsonObject.put("data", stringObjectMap);
        } catch (Exception e) {
            jsonObject.put("resultCode", 500);
            jsonObject.put("resultDesc", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject ;

    }

    /**
     * @Description: TODO 3D通过设备序列号查询控制模板
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/12/30 9:17
     */
    @PostMapping("/getSmhlist/sbInter/getMouldBySerialNum")
    @ResponseBody
    public Map<String,Object> getMouldBySerialNum(@RequestBody Map<String,String> map){

        Map<String, Object> jsonObject = new HashMap<>();
        try {
            Map<String, Object> stringObjectMap = interFaceService.getMouldBySerialNum(map);
            stringObjectMap.put("resultCode", 200);
            stringObjectMap.put("resultDesc", "模式查询成功！");
            return stringObjectMap;
        } catch (Exception e) {
            jsonObject.put("resultCode", 500);
            jsonObject.put("resultDesc", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject ;
    }

    /**
     * @Description: TODO 3D通过组织分页查询设备数据，也可通过设备名称、设备序列号、设备账户、设备类型等搜索查询设备数据。
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/12/30 9:17
     */
    @PostMapping("/getSmhlist/sbInter/getEquipList")
    @ResponseBody
    public Map<String,Object> getEquipList(@RequestBody Map<String,String> map){
        Map<String, Object> jsonObject = new HashMap<>();
        try {
            PageInfo<Map<String, String>> equipList = interFaceService.getEquipList(map);
            jsonObject.put("resultCode", 200);
            jsonObject.put("resultDesc", "模式查询成功！");
            jsonObject.put("total", equipList.getTotal());
            jsonObject.put("rows", equipList.getList());
        } catch (Exception e) {
            jsonObject.put("resultCode", 500);
            jsonObject.put("resultDesc", e.getMessage());
            e.printStackTrace();
        }
        return jsonObject ;
    }

    /**
     * @Description: TODO 3D模式创建
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/12/30 9:17
     */
    @PostMapping("/getSmhlist/sbInter/multiAccountMode")
    @ResponseBody
    public Map<String,Object> multiAccountMode(@RequestBody JSONObject jsonObject){
        Map<String, Object> hashMap = new HashMap<>();
        try {
            String type = jsonObject.getString("type");
            JSONArray mode_data = jsonObject.getJSONArray("mode_data");
            hashMap = interFaceService.multiAccountMode(mode_data);
            if("edit".equals(type)){
                hashMap.put("resultDesc", "编辑成功！");
            }else{
                hashMap.put("resultDesc", "删除成功！");
            }
            hashMap.put("resultCode", 200);

        } catch (Exception e) {
            hashMap.put("resultCode", 500);
            hashMap.put("resultDesc", e.getMessage());
            e.printStackTrace();
        }
        return hashMap ;
    }

    /**
     * @Description: TODO 3D模式查询
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/12/30 9:17
     */
    @PostMapping("/getSmhlist/sbInter/multiModeInfo")
    @ResponseBody
    public Map<String,Object> multiModeInfo(@RequestBody JSONObject jsonObject){
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            List<Object> objects = interFaceService.multiModeInfo(jsonObject);
            hashMap.put("resultCode", 200);
            hashMap.put("data", objects);
            hashMap.put("resultDesc", "查询成功!");
        } catch (Exception e) {
            hashMap.put("resultCode", 500);
            hashMap.put("resultDesc", e.getMessage());
            e.printStackTrace();
        }
        return hashMap ;
    }

    /**
     * @Description: TODO 3D模式控制
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/12/30 9:17
     */
    @PostMapping("/getSmhlist/sbInter/multiModeControl")
    @ResponseBody
    public Map<String,Object> multiModeControl(@RequestBody JSONObject jsonObject){
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
           interFaceService.multiModeControl(jsonObject);
            hashMap.put("resultCode", 200);
            hashMap.put("resultDesc", "控制成功!");
        } catch (Exception e) {
            hashMap.put("resultCode", 500);
            hashMap.put("resultDesc", e.getMessage());
            e.printStackTrace();
        }
        return hashMap ;
    }


    /**
     * @Description: TODO 3D设备控制
     * @Param: [map]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Author: xujianjian
     * @Date: 2019/12/30 9:17
     */
    @PostMapping("/getSmhlist/sbInter/equipControl")
    @ResponseBody
    public Map<String,Object> equipControl(@RequestBody JSONObject jsonObject){
        String account = jsonObject.getString("account");
        String productId = jsonObject.getString("productId");
        String serial_num = jsonObject.getString("serial_num");
        System.out.println(serial_num);
        String operation = jsonObject.getString("operation");
        String controlParams = jsonObject.getString("controlParams");
        JSONObject jsonObject1 = new JSONObject();
        try {
            uIService.kfsbkz(serial_num, productId, operation, controlParams, account);
            jsonObject1.put("resultCode",200);
            jsonObject1.put("resultDesc","控制成功！");
        } catch (Exception e) {
            jsonObject1.put("resultCode",500);
            jsonObject1.put("resultDesc",e.getMessage());
            e.printStackTrace();
        }
        return jsonObject1 ;
    }


}

