package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.PSbgl;
import com.superwork.apcosplatform.domain.PSbmogl;
import com.superwork.apcosplatform.domain.PSysUser;
import com.superwork.apcosplatform.domain.PYktMsgngx;
import com.superwork.apcosplatform.mapper.PSbglMapper;
import com.superwork.apcosplatform.mapper.PSbmoglMapper;
import com.superwork.apcosplatform.mapper.PYktMsgngxMapper;
import com.superwork.apcosplatform.service.SbglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Jianjian Xu
 * @create: 2019/10/25 11:38
 * @description:
 */
@Service
public class SbglServiceImpl implements SbglService {

    @Autowired
    PSbglMapper pSbglMapper;

    @Autowired
    PYktMsgngxMapper pYktMsgngxMapper;

    @Autowired
    PSbmoglMapper pSbmoglMapper;

    /**
     * @Description //TODO 删除上报信息
     * @author xjj
     * @date 2019/10/25
     * @param  * @param sbgl
     * @return void
     */
    @Override
    public void delete(PSbgl sbgl) {
        pSbglMapper.deleteByPrimaryKey(sbgl.getId());
    }

    @Override
    public PageInfo<PSbgl> listsb(PSbgl data, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        if(data == null){
            data = new PSbgl();
        }
        List<PSbgl> listsb = pSbglMapper.listsb(data);
        PageInfo<PSbgl> objectPageInfo = new PageInfo<>(listsb);
        return objectPageInfo;
    }

    @Override
    public List<PYktMsgngx> listmlBymsId(String data) {
        List<PYktMsgngx> pYktMsgngxes = pYktMsgngxMapper.listmlBymsId(data);
        return pYktMsgngxes;
    }

    @Override
    public PageInfo<PSbmogl> listmsBysum(String sum, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<PSbmogl> pSbmogls = pSbmoglMapper.listmsBysum(sum);
        PageInfo<PSbmogl> pSbmoglPageInfo = new PageInfo<>(pSbmogls);
        return pSbmoglPageInfo;
    }

    @Override
    public void editSb(PSbgl pSbgl) {
        pSbglMapper.updateByPrimaryKeySelective(pSbgl);
    }


}
