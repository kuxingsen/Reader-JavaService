package com.monk.myreader.service;

import com.monk.myreader.bean.User;

import java.util.List;

public interface UserService{
    User selectById(Long id);
    String getName(Long id);
    int update(Long id, User user);
    List<User> selectPage(int page,int limit);
    int count();


    String getCode(String phoneNum);
    int loginByPhone(User phoneUser);
    int loginAgain(String phoneNum);
}
