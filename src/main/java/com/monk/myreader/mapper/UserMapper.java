package com.monk.myreader.mapper;

import com.monk.myreader.bean.User;
import com.monk.myreader.bean.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int replacePhone(@Param("phone") String phoneNum, @Param("code") String code);

    int selectPhoneUser(User phoneUser);

    void deleteCode(String phone);

    int hasLogin(String phone);
}