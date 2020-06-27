package com.jjw.controller;

import com.jjw.domain.UserInfo;
import com.jjw.domain.UserInfoCondition;
import com.jjw.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @RequestMapping("/selectList")
    List<UserInfo> selectList(UserInfoCondition condition){
        return userInfoMapper.selectByConditon(condition);
    }

    @RequestMapping("/addUserInfo")
    public String addUserInfo(UserInfo userInfo){
        userInfoMapper.addUserInfo(userInfo);
        return "成功";
    }

}
