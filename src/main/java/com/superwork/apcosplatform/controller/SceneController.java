package com.superwork.apcosplatform.controller;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.HttpStatus;
import com.superwork.apcosplatform.domain.PScene;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.QueryRequest;
import com.superwork.apcosplatform.result.PageResponse;
import com.superwork.apcosplatform.result.SingleResponse;
import com.superwork.apcosplatform.service.SceneService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @program: code->SceneController
 * @description: 场景
 * @author: xjj
 * @create: 2019-12-05 17:26
 **/
@Controller
@RequestMapping("scene")
public class SceneController {

    public static Logger logger = Logger.getLogger(SceneController.class);

    @Autowired
    SceneService sceneService;

    @RequestMapping("listScene.json")
    @ResponseBody
    public PageResponse<PScene> listScene(@RequestBody QueryRequest<PScene> queryRequest){

        PageResponse<PScene> response = new PageResponse<>();
        PScene pScene = queryRequest.getData()==null?new PScene():queryRequest.getData();
        Integer page = queryRequest.getPage() == null ? 1:queryRequest.getPage();
        Integer limit = queryRequest.getLimit() == null ? 20:queryRequest.getLimit();
        try {
            PSysUser user = ComonUtils.getUser();
            pScene.setCreator(user.getUserId());
            PageInfo<PScene> pScenePageInfo = sceneService.listScene(pScene, page, limit);
            response.setData(pScenePageInfo.getList());
            response.setTotal(pScenePageInfo.getTotal());
        } catch (Exception e) {
            logger.error("分页查询上传的场景异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }

        return response;

    }


    @RequestMapping("addScene.json")
    @ResponseBody
    public SingleResponse<String> addScene(@RequestBody PScene pScene){
        SingleResponse<String> response = new SingleResponse<>();
        PSysUser user = ComonUtils.getUser();
        pScene.setCreator(user.getUserId());
        pScene.setCreatetime(new Date());
        try {
            sceneService.addScene(pScene);
        } catch (Exception e) {
            logger.error("新增场景异常", e);
            response.setStatus(HttpStatus.SYSTEM_ERROR.getErrorCode());
            response.setMsg(e.getMessage());
        }
        return response;
    }


}
