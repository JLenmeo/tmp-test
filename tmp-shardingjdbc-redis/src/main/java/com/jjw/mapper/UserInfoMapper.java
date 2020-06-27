package com.jjw.mapper;

import com.jjw.domain.UserInfo;
import com.jjw.domain.UserInfoCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {

    List<UserInfo> selectByConditon(@Param("condition") UserInfoCondition condition);

    void addUserInfo(@Param("userInfo") UserInfo userInfo);

}
