package com.wang.computermanage.controller;

import com.alibaba.fastjson.JSON;
import com.wang.computermanage.pojo.User;
import com.wang.computermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Date 2019/12/5 0005 17:52
 * @Created by /SurpriseWang
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/queryUser")
    public Boolean queryUser(HttpServletResponse response,String userName , String password) {
        User user = userService.retrieveUser(userName, password);
        if(user.equals(null)){
            Cookie cookie = new Cookie("user", JSON.toJSONString(user));
            response.addCookie(cookie);
            return true;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/createUser")
    public String createUser(String userName , String password) {
        if (userService.retrieveUserName(userName)) {
            User user = userService.createUser(userName, password);
            if(user.equals(null)){
                return JSON.toJSONString(user);
            }
            return null;
        } else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("/updateUserPassword")
    public String updateUserPassword(HttpServletRequest request, String newPassword) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals("user")) {
                String jsonUser = cookie.getValue();
                User user = JSON.parseObject(jsonUser,User.class);
                User resultUser = userService.updateUserPassword(user, newPassword);
                if(resultUser.equals(null)) {
                    return JSON.toJSONString(resultUser);
                }
                return null;
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public Boolean deleteUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals("user")) {
                String jsonUser = cookie.getValue();
                User user = JSON.parseObject(jsonUser,User.class);
                if(userService.deleteUser(user)){
                    return true;
                }
                    return null;
            }
        }
        return null;
    }
}
