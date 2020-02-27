package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.DepartmentService;
import com.superwork.apcosplatform.utils.ComonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jianjian Xu
 * @create: 2019/10/23 14:40
 * @description:
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    PDepartmentMapper pDepartmentMapper;
    @Autowired
    PDepUserMapper pDepUserMapper;
    @Autowired
    PSysUserMapper pSysUserMapper;

    @Autowired
    POrgUserMapper pOrgUserMapper;
    @Autowired
    POrganizeMapper pOrganizeMapper;
    @Value("${spring.depId}")
    String depId;

    @Value("${spring.hasAllRight}")
    private String hasAllRight;



    @Override
    public PageInfo<PDepartment> listDepartment(PDepartment data, Integer page, Integer limit) {
        PSysUser user = ComonUtils.getUser();
        if(data == null){
            data =    new PDepartment();
        }
        PageHelper.startPage(page,limit);
        data.setCreateUserid(user.getUserId());
        if (hasAllRight.indexOf(user.getUserAccount()) != -1) {
            data.setCompanyId("0");
        }
        List<PDepartment> pDepartments = pDepartmentMapper.listDepartment(data);
        PageInfo<PDepartment> pDepartmentPageInfo = new PageInfo<>(pDepartments);
        return pDepartmentPageInfo;
    }

    @Override
    public void addDepartment(PDepartment data) {
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        data.setCompanyId(bccaServiceInfo.getId().toString());
        pDepartmentMapper.insert(data);
    }

    @Override
    public ResultDO<String> checkName(String data) {
        ResultDO<String> resultDO = new ResultDO<>();
        PDepartmentExample pDepartmentExample = new PDepartmentExample();
        PDepartmentExample.Criteria criteria = pDepartmentExample.createCriteria();
        criteria.andNameEqualTo(data);
        List<PDepartment> pDepartments = pDepartmentMapper.selectByExample(pDepartmentExample);
        if(pDepartments.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("菜单已存在!请换一个");
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> checkNameWith(PDepartment data) {
        ResultDO<String> resultDO = new ResultDO<>();
        PDepartmentExample pDepartmentExample = new PDepartmentExample();
        PDepartmentExample.Criteria criteria = pDepartmentExample.createCriteria();
        criteria.andNameEqualTo(data.getName()).andDepIdNotEqualTo(data.getDepId());
        List<PDepartment> pDepartments = pDepartmentMapper.selectByExample(pDepartmentExample);
        if(pDepartments.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("菜单已存在!请换一个");
        }
        return resultDO;
    }

    @Override
    public void editDepartment(PDepartment data) {
        pDepartmentMapper.updateByPrimaryKeySelective(data);
    }

    @Override
    public  ResultDO<String> delDepartment(PDepartment data) {
        ResultDO<String> resultDO = new ResultDO<>();
        PDepartment pDepartment = pDepartmentMapper.selectByPrimaryKey(data.getDepId());
        if(pDepartment.getSysType().intValue() == 1){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("内置菜单，不能删除！");
            return resultDO;
        }
        PDepUserExample pDepUserExample = new PDepUserExample();
        PDepUserExample.Criteria criteria = pDepUserExample.createCriteria();
        criteria.andDepIdEqualTo(data.getDepId());
        List<PDepUser> pDepUsers = pDepUserMapper.selectByExample(pDepUserExample);
        if(pDepUsers.size()>0){
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("当前菜单下含有人员，请先移除人员！");
            return resultDO;
        }

        pDepartmentMapper.deleteByPrimaryKey(data.getDepId());
        return resultDO;

    }

    @Override
    public List<PSysUser> getUserListByDepId(String data) {
        PSysUser user = ComonUtils.getUser();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        List<PSysUser> pSysUsers = new ArrayList<>();
        if (hasAllRight.indexOf(user.getUserAccount()) != -1) {
            PSysUserExample pSysUserExample = new PSysUserExample();
            PSysUserExample.Criteria criteria1 = pSysUserExample.createCriteria();
            criteria1.andIsdelEqualTo("2");
            pSysUsers = pSysUserMapper.selectByExample(pSysUserExample);
        }else{

            //主账号
            if (user.getUserLevel().equals("1")) {
                PSysUser user1 = new PSysUser();
                user1.setCreatorId(user.getUserId());
                pSysUsers = pSysUserMapper.listMyUser(user1);
            }else{
                //查询当前用户所在部门
                POrgUserExample pOrgUserExample = new POrgUserExample();
                POrgUserExample.Criteria criteria = pOrgUserExample.createCriteria();
                criteria.andUserIdEqualTo(user.getUserId());
                List<POrgUser> pOrgUsers = pOrgUserMapper.selectByExample(pOrgUserExample);
                if(pOrgUsers.size() == 0){
                    pSysUsers.add(user) ;
                }else{
                    POrganize pOrganize = pOrganizeMapper.selectByPrimaryKey(pOrgUsers.get(0).getOrgId());
                    Map<String, Object> map = new HashMap<>();
                    map.put("leves",pOrganize.getLevels());
                    map.put("companyId",bccaServiceInfo.getId().toString());
                    List<PSysUser> pSysUsers1 = pSysUserMapper.selectUserOnOrg(map);
                    pSysUsers.addAll(pSysUsers1);

                }
            }


        }
        PDepUserExample pDepUserExample = new PDepUserExample();
        PDepUserExample.Criteria criteria = pDepUserExample.createCriteria();
        criteria.andDepIdEqualTo(data);
        List<PDepUser> pDepUsers = pDepUserMapper.selectByExample(pDepUserExample);
        for (PSysUser pSysUser : pSysUsers) {
            for (PDepUser pDepUser : pDepUsers) {
                if(pSysUser.getUserId().equals(pDepUser.getUserId())){
                    pSysUser.setChecked(true);
                }
            }
        }
        return pSysUsers;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setUserAndDep(List<Map<String, String>> data, String sdepId) {
        //删除部门下的成员和新增成员所在部门关系
        ArrayList<String> list = new ArrayList<>();
        ArrayList<PDepUser> qlist = new ArrayList<>();
        //如果是空的，直接改为默认部门
        if(data.size()==0){
            PDepUser pDepUser = new PDepUser();
            pDepUser.setDepId(depId);
            PDepUserExample pDepUserExample = new PDepUserExample();
            PDepUserExample.Criteria criteria = pDepUserExample.createCriteria();
            criteria.andDepIdEqualTo(sdepId);
            pDepUserMapper.updateByExampleSelective(pDepUser,pDepUserExample);
        }else{
            //取出选中的人员
            for (Map<String, String> datum : data) {
                list.add(datum.get("value"));
            }
            PDepUserExample pDepUserExample1 = new PDepUserExample();
            PDepUserExample.Criteria criteria2 = pDepUserExample1.createCriteria();
            criteria2.andDepIdEqualTo(sdepId);
            List<PDepUser> pDepUsers = pDepUserMapper.selectByExample(pDepUserExample1);//当前部门下现有用户
            for (PDepUser pDepUser : pDepUsers) {
                boolean boo=true;
                for (String s : list) {
                    if(pDepUser.getUserId().equals(s)){
                        boo=false;//如果相同；不存入
                        break;//结束本次循环
                    }
                }
                if(boo){
                    qlist.add(pDepUser);
                }
            }
            //删除关于人员和部门的数据
            PDepUserExample pDepUserExample = new PDepUserExample();
            PDepUserExample.Criteria criteria = pDepUserExample.createCriteria();
            criteria.andDepIdEqualTo(sdepId);
            PDepUserExample.Criteria criteria1 = pDepUserExample.createCriteria();
            criteria1.andUserIdIn(list);
            pDepUserExample.or(criteria1);
            pDepUserMapper.deleteByExample(pDepUserExample);

            for (String s : list) {
                PDepUser pDepUser = new PDepUser();
                pDepUser.setDepId(sdepId);
                pDepUser.setUserId(s);
                pDepUserMapper.insert(pDepUser);
            }
            for (PDepUser s : qlist) {
                s.setDepId(depId);
                pDepUserMapper.insert(s);
            }

        }

    }
}
