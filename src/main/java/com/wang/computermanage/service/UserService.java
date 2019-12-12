package com.wang.computermanage.service;

import com.wang.computermanage.pojo.User;

/**
 * @Description TODO
 * @Date 2019/12/9 0009 14:04
 * @Created by /SurpriseWang
 */
public interface UserService {
    public User retrieveUser(String userName , String password);
    public Boolean retrieveUserName(String userName);
    public User createUser(String userName , String password);
    public Boolean deleteUser(User user);
    public User updateUserPassword(User user, String newPassword);
    public User updateUserHeadPortrait(User user, String newHeadPortrait);
}
