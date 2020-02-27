package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PScene;
import com.superwork.apcosplatform.mapper.PSceneMapper;
import com.superwork.apcosplatform.service.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: code->SceneServiceImpl
 * @description: 场景
 * @author: xjj
 * @create: 2019-12-11 11:20
 **/
@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    PSceneMapper pSceneMapper;
    @Override
    public PageInfo<PScene> listScene(PScene pScene, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PScene> pScenes = pSceneMapper.listScene(pScene);

        PageInfo<PScene> pScenePageInfo = new PageInfo<>(pScenes);
        return pScenePageInfo;
    }

    @Override
    public void addScene(PScene pScene) {
        pSceneMapper.insert(pScene);
    }
}
