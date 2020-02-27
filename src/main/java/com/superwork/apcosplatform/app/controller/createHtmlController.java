package com.superwork.apcosplatform.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import freemarker.template.Configuration;

/**
 * @program: code->createHtmlController
 * @description: 创建静态Html文件
 * @author: xjj
 * @create: 2019-12-03 09:31
 **/
@Controller
@RequestMapping("createHtml")
public class createHtmlController {

    @Autowired
    private Configuration configuration;


//    @RequestMapping("getHtml.json")
//    @ResponseBody
//    public SingleResponse<String> createIndexHtml(){
//        SingleResponse<String> response = new SingleResponse<>();
//        try {
//            /**获取输出目标文件输出流------开始*/
//            String filepath = this.getClass().getResource("/").toURI().getPath()+"static/html/";
//            File folder = new File(filepath);
//            //如果文件夹不存在
//            if (!folder.exists()) {
//                folder.mkdir();
//            }
//            String indexFileName = "ytc.html";
//            File indexHtml = new File(folder, indexFileName);
//            //如果html文件不存在
//            if (!indexHtml.exists()) {
//                indexHtml.createNewFile();
//            }
//            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexHtml),"UTF-8"));
//            /**获取输出目标文件输出流------结束*/
//
//            String rec = "<!DOCTYPE html>\n" +
//                    "<html lang=\"en\">\n" +
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\">\n" +
//                    "    <title>Title</title>\n" +
//                    "</head>\n" +
//                    "<body>张三\n" +
//                    "\n" +
//                    "</body>\n" +
//                    "</html>";
//
//            //获取数据
//            Map<String, Object> map = new HashMap<>();
//            map.put("test", rec);
//
//            //获取模板
//            Template template = configuration.getTemplate("content.ftl");
//            //把数据和输出文件信息交给模板得到静态html文件
//            template.process(map,out);
//            out.flush();
//            out.close();
//            response.setData("/html/ytc.html");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
//        return response;
//    }
}
