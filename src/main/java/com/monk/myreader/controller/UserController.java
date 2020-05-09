package com.monk.myreader.controller;

import com.monk.myreader.bean.User;
import com.monk.myreader.dto.Query;
import com.monk.myreader.dto.Result;
import com.monk.myreader.service.UserService;
import com.monk.myreader.utils.PhoneUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api("用户接口")
@Slf4j
@RestController
@RequestMapping("user")
public class UserController{
    @Autowired
    UserService userService;
    /**
     * 获取验证码
     * @param phoneNum 手机号
     * @return 失败（false）或成功（true）+验证码（data[0]）
     */
    @GetMapping(value = "getCode")
    public Result getCode(@RequestParam("phone") String phoneNum){
        System.out.println(phoneNum);
        String msg;
        //校验手机号格式
        if(PhoneUtil.isPhone(phoneNum)){
            //获取验证码
            String code = userService.getCode(phoneNum);
            if(code != null){
                return Result.SUCCESS(new ArrayList<String>(){{add(code);}});//使用代码块的方式初始化List
            }else {
                msg="无法获取验证码";
            }
        }else {
            msg="不是正确的手机号格式";
            System.out.println(phoneNum);
        }
        return Result.FAIL(msg);
    }

    /**
     * 用户登录
     * @param phoneUser 需要phoneNum 手机号，code 验证码
     * @return 失败（false）或成功（true）
     */
    @PostMapping("login")
    public Result loginByPhone(User phoneUser, HttpSession session){
        System.out.println(phoneUser);
        String msg;
        //校验手机号和验证码格式
        if(PhoneUtil.isPhone(phoneUser.getPhone())){
            //查看是否匹配
            int r = userService.loginByPhone(phoneUser);
            if(r > 0){
                session.setAttribute("userPhone",phoneUser.getPhone());
                return Result.SUCCESS("成功");
            }else {
                msg="验证码错误";
            }
        }else {
            msg="不是正确的手机号或验证码格式";
        }
        return Result.FAIL(msg);
    }

    /**
     * 免登陆直接进入（需要用户曾经登录过且数据库中没有验证码）
     * @param phoneNum 手机号码
     * @return 失败（false）或成功（true）
     */
    @GetMapping("loginAgain")
    public Result loginAgain(String phoneNum, HttpSession session){
        String msg="不是正确的手机号或未登录";
        //校验手机号和验证码格式
        if(PhoneUtil.isPhone(phoneNum)){
            //查看是否匹配
            int r = userService.loginAgain(phoneNum);
            if(r > 0){
                session.setAttribute("userPhone",phoneNum);
                return Result.SUCCESS("成功");
            }
        }
        return Result.FAIL(msg);
    }


    @PostMapping(value = "list")
    public Result listSearch(@RequestBody Query query){
        List<User> list = userService.selectPage(query.getPage(),query.getLimit());
        if(list != null){
            return Result.SUCCESS(list,userService.count());
        }
        return Result.FAIL("失败");
    }

}
