package com.superwork.apcosplatform.service;

import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PScene;

public interface SceneService {
    PageInfo<PScene> listScene(PScene pScene, Integer page, Integer limit);

    void addScene(PScene pScene);
}
