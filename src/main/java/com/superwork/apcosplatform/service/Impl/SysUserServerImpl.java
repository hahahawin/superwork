package com.superwork.apcosplatform.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.superwork.apcosplatform.common.MD5;
import com.superwork.apcosplatform.common.redis.RedisUtil;
import com.superwork.apcosplatform.domain.*;
import com.superwork.apcosplatform.mapper.*;
import com.superwork.apcosplatform.utils.ComonUtils;
import com.superwork.apcosplatform.result.ResultDO;
import com.superwork.apcosplatform.service.SysUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Jianjian Xu
 * @create: 2019/10/21 14:47
 * @description:
 */
@Service
public class SysUserServerImpl implements SysUserServer {

    @Autowired
    PSysUserMapper sysUserMapper;

    @Autowired
    PDepUserMapper pDepUserMapper;

    @Autowired
    PZzUserMapper pZzUserMapper;

    @Autowired
    PD3orgZcMapper pd3orgZcMapper;


    @Autowired
    PRoleUserMapper pRoleUserMapper;

    @Autowired
    PSmhSettingMapper pSmhSettingMapper;

    @Autowired
    POrgUserMapper pOrgUserMapper;

    @Autowired
    POrganizeMapper pOrganizeMapper;

    @Value("${spring.depId}")
    String depId;

    @Value("${spring.hasAllRight}")
    private String hasAllRight;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public ResultDO<String> findPhone(String cellphoneNo) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysUserExample sysUserExample = new PSysUserExample();
        PSysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andCellphoneNoEqualTo(cellphoneNo);
        List<PSysUser> pSysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (pSysUsers.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("电话号码已存在，请换一个！");
        }
        return resultDO;
    }

    @Override
    public ResultDO<String> checkUserAccount(String userAccount) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysUserExample sysUserExample = new PSysUserExample();
        PSysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andUserAccountEqualTo(userAccount);
        List<PSysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("登陆账号已存在，请换一个！");
        }
        return resultDO;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(PSysUser sysUser) {
        //增加用户
        sysUser.setUserLevel("1");
        sysUserMapper.insertSelective(sysUser);
        //设置默认菜单
        PDepUser pDepUser = new PDepUser();
        pDepUser.setUserId(sysUser.getUserId());
        pDepUser.setDepId(depId);
        pDepUserMapper.insert(pDepUser);
    }

    @Override
    public ResultDO<Map<String, Object>> login(PSysUser data) {
        ResultDO<Map<String, Object>> resultDao = new ResultDO<>();
        PSysUser pSysUsers = sysUserMapper.login(data);
        HashMap<String, Object> map = new HashMap<>();
        if (pSysUsers == null) {
            resultDao.setSuccess(false);
            resultDao.setErrorMsg("账号或密码错误！");
            return resultDao;
        } else {
            //判断是否禁用
            if (pSysUsers.getEnable().intValue() == 1) {
                resultDao.setSuccess(false);
                resultDao.setErrorMsg("当前账号已被禁用！");
                return resultDao;
            } else if ("2".equals(pSysUsers.getIsdel())) {
                //查询组织情况
                //TODO 服务信息和组织挂钩
                PD3orgZc pd3orgZc = pd3orgZcMapper.selectServiceInfo(pSysUsers.getUserId());
                if (pd3orgZc != null && pd3orgZc.getEffective().equals("2")) {
                    resultDao.setSuccess(false);
                    resultDao.setErrorMsg("当前组织已被禁用！");
                    return resultDao;
                }
                map.put("bccaService", pd3orgZc);
                map.put("user", pSysUsers);
            } else {
                resultDao.setSuccess(false);
                resultDao.setErrorMsg("当前用户不存在");
                return resultDao;
            }
        }
        resultDao.setData(map);
        return resultDao;
    }

    @Override
    public PSysUser getUserInfo(String userId) {
        PSysUser pSysUser = sysUserMapper.selectByPrimaryKey(userId);
        return pSysUser;
    }

    @Override
    public PSysUser editUserInfo(PSysUser data) throws Exception {
        if (data.getEnable() != null) {
            if (data.getEnable().toString().equals("1")) {
                PSysUser user = sysUserMapper.selectByPrimaryKey(data.getUserId());
                if (user.getUserAccount().equals("admin")) {
                    throw new Exception("admin账号不能禁用");
                }
            }
        }
        PSysUser user = ComonUtils.getUser();
//        if (user == null) {
//            user = ComonUtils.getUsetOnApp(redisUtil);
//        }
//        if (hasAllRight.indexOf(user.getUserAccount()) == -1) {
//            if (user.getUserLevel().equals("2")) {//次账号
//                if (!user.getUserId().equals(data.getUserId())) {
//                    throw new Exception("你不是主账号或此账号不属于你！");
//                }
//            }
//        }
        sysUserMapper.updateByPrimaryKeySelective(data);
        PSysUser user1 = sysUserMapper.selectByPrimaryKey(data.getUserId());
        //清除用户权限
        redisUtil.del(data.getUserId() + ":jurisdiction");
        return user1;
    }

    @Override
    public PageInfo<PSysUser> listUser(PSysUser data, Integer page, Integer limit) {
        PSysUser user = ComonUtils.getUser();
        if (data == null) {
            data = new PSysUser();
        }
        PageHelper.startPage(page, limit);
        if (hasAllRight.indexOf(user.getUserAccount()) == -1) {
            List<PSysUser> pSysUsers = new ArrayList<>();
            //主账号
            if(user.getUserLevel().equals("1")){
                data.setCreatorId(user.getUserId());
                pSysUsers = sysUserMapper.listMyUser(data);
            }else{
                PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
                //查询当前用户所在部门
                POrgUserExample pOrgUserExample = new POrgUserExample();
                POrgUserExample.Criteria criteria = pOrgUserExample.createCriteria();
                criteria.andUserIdEqualTo(user.getUserId());
                List<POrgUser> pOrgUsers = pOrgUserMapper.selectByExample(pOrgUserExample);
                if(pOrgUsers.size() == 0){
                    //当前用户不属于任何部门
                    pSysUsers.add(user);
                }else{
                    POrganize pOrganize = pOrganizeMapper.selectByPrimaryKey(pOrgUsers.get(0).getOrgId());
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("leves",pOrganize.getLevels());
                    map.put("companyId",bccaServiceInfo.getId().toString());
                    map.put("user",data);
                    pSysUsers = sysUserMapper.selectUserOnOrg(map);
                }
            }
            PageInfo<PSysUser> pSysUserPageInfo = new PageInfo<>(pSysUsers);
            return pSysUserPageInfo;
        } else {
            List<PSysUser> pSysUsers = sysUserMapper.listUser(data);
            PageInfo<PSysUser> pSysUserPageInfo = new PageInfo<>(pSysUsers);
            return pSysUserPageInfo;
        }
    }

    @Override
    public ResultDO<String> getCodeWhenPassword(String data) {
        ResultDO<String> resultDO = new ResultDO<>();
        PSysUserExample sysUserExample = new PSysUserExample();
        PSysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andCellphoneNoEqualTo(data);
        List<PSysUser> pSysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (pSysUsers.size() == 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("号码不存在！");
        }
        return resultDO;

    }

    @Override
    public ResultDO<String> getBackPassword(PSysUser data){
        ResultDO<String> resultDO = new ResultDO<>();
        PSysUserExample pSysUserExample = new PSysUserExample();
        PSysUserExample.Criteria criteria = pSysUserExample.createCriteria();
        criteria.andUserAccountEqualTo(data.getCellphoneNo()).andUserPasswordEqualTo(data.getUserPassword());
        List<PSysUser> pSysUsers = sysUserMapper.selectByExample(pSysUserExample);
        if (pSysUsers.size() > 0) {
            resultDO.setSuccess(false);
            resultDO.setErrorMsg("新旧密码不能一致！");
            return resultDO;
        }
        pSysUserExample.clear();
        PSysUserExample.Criteria criteria1 = pSysUserExample.createCriteria();
        criteria1.andUserAccountEqualTo(data.getCellphoneNo());
        PSysUser user = new PSysUser();
        user.setUserPassword(data.getUserPassword());
        sysUserMapper.updateByExampleSelective(user, pSysUserExample);
        return resultDO;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delUser(PSysUser data) throws Exception {



        PSysUser user1 = sysUserMapper.selectByPrimaryKey(data.getUserId());
        if (user1.getUserAccount().equals("admin")) {
            throw new Exception("admin账号不能操作");
        }
//        PSysUser user = ComonUtils.getUser();
//        if (hasAllRight.indexOf(user.getUserAccount()) == -1) {
//            if (user.getUserLevel().equals("2")) {//次账号
//                if (!user.getUserId().equals(data.getUserId())) {
//                    throw new Exception("你不是主账号或此账号不属于你！");
//                }
//            }
//        }
        sysUserMapper.deleteByPrimaryKey(data.getUserId());

        //删除人员与部门的关系
        PDepUserExample pDepUserExample = new PDepUserExample();
        PDepUserExample.Criteria criteria = pDepUserExample.createCriteria();
        criteria.andUserIdEqualTo(data.getUserId());
        pDepUserMapper.deleteByExample(pDepUserExample);

        //删除用户与组织的关系
        PZzUserExample pZzUserExample = new PZzUserExample();
        PZzUserExample.Criteria criteria1 = pZzUserExample.createCriteria();
        criteria1.andUserIdEqualTo(data.getUserId());
        pZzUserMapper.deleteByExample(pZzUserExample);

        //用户与角色的关系
        PRoleUserExample pRoleUserExample = new PRoleUserExample();
        PRoleUserExample.Criteria criteria2 = pRoleUserExample.createCriteria();
        criteria2.andUserIdEqualTo(data.getUserId());
        pRoleUserMapper.deleteByExample(pRoleUserExample);

        //删除用户所申请的BCCA账号
        PSmhSettingExample pSmhSettingExample = new PSmhSettingExample();
        PSmhSettingExample.Criteria criteria3 = pSmhSettingExample.createCriteria();
        criteria3.andCreatorIdEqualTo(data.getUserId());
        pSmhSettingMapper.deleteByExample(pSmhSettingExample);
        //清除用户的权限
        redisUtil.del(data.getUserId() + ":jurisdiction");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(PSysUser data) throws Exception {
        PSysUser user = ComonUtils.getUser();
        PD3orgZc bccaServiceInfo = ComonUtils.getBccaServiceInfo();
        if (bccaServiceInfo == null || StringUtils.isEmpty(bccaServiceInfo.getServiceId())) {
            throw new Exception("请完善组织服务信息！");
        }
        BigDecimal id = bccaServiceInfo.getId();//组织ID
        String s = UUID.randomUUID().toString();
        String s1 = s.replaceAll("-", "");
        data.setUserId(s1);
        data.setEnable(new BigDecimal(2));
        data.setUserType(user.getUserType());//和创建者类型相同
        data.setIsdel("2");//未删除
        data.setUserLevel("2");//子账号
        data.setCreatedTime(new Date());
        data.setCreatorId(user.getUserId());
        MD5 md5 = new MD5();
        data.setUserPassword(md5.getMD5ofStr(data.getUserPassword()));
        sysUserMapper.insertSelective(data);


        //默认菜单
//        PDepUser pDepUser = new PDepUser();
//        pDepUser.setUserId(data.getUserId());
//        pDepUser.setDepId(depId);
//        pDepUserMapper.insert(pDepUser);
        //组织
        PZzUser pZzUser = new PZzUser();
        pZzUser.setUserId(data.getUserId());
        pZzUser.setZzId(id.toString());
        pZzUserMapper.insert(pZzUser);

    }

    @Override
    public String getUserHead(String userId) {
        PSysUser user = sysUserMapper.selectByPrimaryKey(userId);
        return user.getHeadportrarit();
    }


}
