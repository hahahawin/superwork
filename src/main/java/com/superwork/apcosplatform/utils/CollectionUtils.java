package com.superwork.apcosplatform.utils;

import com.superwork.apcosplatform.domain.PSysRight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: code->CollectionUtils
 * @description: 权限树
 * @author: xjj
 * @create: 2019-12-23 16:31
 **/

public class CollectionUtils {

    public static List<PSysRight> parseTreeList(List<PSysRight> list){
        List<PSysRight> menus = new ArrayList<>();
        Iterator<PSysRight> iterator = list.iterator();
        while (iterator.hasNext()){
            PSysRight menu = iterator.next();
            //第一级菜单组装
            if("0".equals(menu.getParentId())) {
                menus.add(menu);
                iterator.remove();
            }
        }
        for(PSysRight menu: menus){
            //第二级菜单组装
            List<PSysRight> chlidren = new ArrayList<>();
            for (PSysRight pSysRight : list) {
                if(pSysRight.getParentId().equals(menu.getRightId())){
                    chlidren.add(pSysRight);
                }
                menu.setChlidren(chlidren);
            }
        }
        return menus;
    }
}
