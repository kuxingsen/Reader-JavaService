package com.monk.myreader.service.impl;

import com.github.pagehelper.PageHelper;
import com.monk.myreader.bean.User;
import com.monk.myreader.bean.UserExample;
import com.monk.myreader.mapper.UserMapper;
import com.monk.myreader.service.UserService;
import com.monk.myreader.utils.PhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :kuexun
 * @description : todo
 * @create : 2020/4/16 0:29
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User selectById(Long id) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        List<User> list = userMapper.selectByExample(example);
        return list.size()>0?list.get(0):null;
    }

    @Override
    public String getName(Long id) {
        User user = selectById(id);
        return user.getName();
    }

    @Override
    public int update(Long id, User user) {
        return 0;
    }

    @Override
    public List<User> selectPage(int page, int limit) {
        UserExample example = new UserExample();
        PageHelper.startPage(page-1, limit);
        return userMapper.selectByExample(example);
    }

    @Override
    public int count() {
        UserExample example = new UserExample();
        return userMapper.countByExample(example);
    }

    @Override
    public String getCode(String phoneNum) {
        //根据时间生成六位数校验码
        String code = PhoneUtil.get6Code();
        System.out.println(code);
        //将手机号和校验码保存到数据库
        int isSuccess = userMapper.replacePhone(phoneNum,code);//无则插入，有则更新
        //返回校验码
        if(isSuccess > 0){
            return code;
        }
        return null;
    }

    @Override
    public int loginByPhone(User phoneUser) {
        //验证登录
        int i = userMapper.selectPhoneUser(phoneUser);
        if(i != 0){
            //删除数据库中的验证码
            userMapper.deleteCode(phoneUser.getPhone());
            return 1;
        }
        return 0;
    }

    @Override
    public int loginAgain(String phoneNum) {
        return userMapper.hasLogin(phoneNum);
    }
}
