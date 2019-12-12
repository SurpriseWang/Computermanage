package com.wang.computermanage.service.impl;

import com.wang.computermanage.mapper.UserMapper;
import com.wang.computermanage.pojo.User;
import com.wang.computermanage.pojo.UserExample;
import com.wang.computermanage.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @Description TODO
 * @Date 2019/12/9 0009 14:05
 * @Created by /SurpriseWang
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User retrieveUser(String userName, String password) {
        try {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserNameEqualTo(userName)
                    .andUserPasswordEqualTo(password).andIsDeleteEqualTo("false");
            List<User> users = userMapper.selectByExample(userExample);
            return users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean retrieveUserName(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName).andIsDeleteEqualTo("false");
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User createUser(String userName, String password) {
        try {
            User user = new User(userName, password,
                    "localhost:8080/headPortrait/01.jpg",
                    new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),
                    "false"
            );
            userMapper.insertSelective(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteUser(User user) {
        try {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserNameEqualTo(user.getUserName())
                    .andUserPasswordEqualTo(user.getUserPassword()).andIsDeleteEqualTo("false");
            List<User> users = userMapper.selectByExample(userExample);
            if (users.size() != 0) {
                User modelUser = new User();
                modelUser.setModifyTime(new Date(System.currentTimeMillis()));
                modelUser.setIsDelete("true");
                userMapper.updateByExample(modelUser, userExample);
                return true;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User updateUserPassword(User user, String newPassword) {
        try {
            user.setUserPassword(newPassword);
            int resultValue = userMapper.updateByPrimaryKey(user);
            if (resultValue != 0) {
                return user;
            }
             return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User updateUserHeadPortrait(User user, String newHeadPortrait) {
        try {
            user.setUserPassword(newHeadPortrait);
            int resultValue = userMapper.updateByPrimaryKey(user);
            if (resultValue != 0) {
                return user;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Test
    public void test() {
        System.out.println(Boolean.valueOf("false"));
        System.out.println(System.currentTimeMillis());
    }
}
