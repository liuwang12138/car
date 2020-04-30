package com.godric.cms.controller;

import com.godric.cms.common.dto.MyInfoDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.UserPO;
import com.godric.cms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @author Godric
 */

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * user register
     * @param username username, can't be blank
     * @param password password, can't be blank
     * @param realName real name, can't be blank
     * @param phone phone, can't be blank
     * @return whether register is success or not
     */
    @PostMapping("register")
    public ResultMessage<Void> register(@NotBlank String username,
                                        @NotBlank String password,
                                        @NotBlank String realName,
                                        @NotBlank String phone) {
        return userService.register(username, password, realName, phone);
    }

    /**
     * user login
     * @param username username, can't be blank
     * @param password password, can't be blank
     * @param request request
     * @return whether login is success or not, also with user info
     */
    @PostMapping("login")
    public ResultMessage<UserPO> login(@NotBlank String username,
                                       @NotBlank String password,
                                       HttpServletRequest request) {
        return userService.login(username, password, request);
    }

    /**
     * user update my password, need to login before
     * @param oldPassword old password
     * @param newPassword new password
     * @param request request
     * @return whether update is success or not
     */
    @PostMapping("password/update")
    public ResultMessage<Void> updatePassword(@NotBlank String oldPassword,
                                              @NotBlank String newPassword,
                                              HttpServletRequest request) {

        return userService.updatePassword(oldPassword, newPassword, request);
    }

    /**
     * get my info, user need to login before
     * @param request request
     * @return my info, include username, real name and phone
     */
    @PostMapping("my/info")
    public ResultMessage<MyInfoDTO> getMyInfo(HttpServletRequest request) {
        return userService.getMyInfo(request);
    }
}
