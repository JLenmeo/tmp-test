package com.jjw.controller;

import com.jjw.aop.RateLimiter;
import com.jjw.domain.UserInfo;
import com.jjw.domain.UserInfoCondition;
import com.jjw.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息Controller
 */
@RestController
public class UserInfoController {

    private Map<String,UserInfo> userInfoMap = new HashMap<>();

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

    @RequestMapping("/addMap")
    public String addMap(UserInfo userInfo){
        userInfoMap.put(String.valueOf(userInfo.getId()),userInfo);
        return "成功";
    }

    @RequestMapping("/selectMap")
    @Cacheable("userInfo")
    public UserInfo selectMap(Integer id){
        System.out.println("ID：" + id + "，无缓存，需要查询");
        return userInfoMap.get(String.valueOf(id));
    }

    @RequestMapping("/deleteMap")
    @CacheEvict("userInfo")
    public String deleteMap(Integer id){
        userInfoMap.remove(String.valueOf(id));
        return "成功";
    }

    @RequestMapping("/rateLimit")
    @RateLimiter(key = "test-limit",limit = 3L,expire = 30L)
    public String rateLimitTest(){

        return "成功";

    }

}
