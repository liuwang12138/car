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

    @PostMapping("register")
    public ResultMessage<Void> register(@NotBlank String username,
                                        @NotBlank String password,
                                        @NotBlank String realName,
                                        @NotBlank String phone) {
        return userService.register(username, password, realName, phone);
    }

    @PostMapping("login")
    public ResultMessage<UserPO> login(@NotBlank String username,
                                       @NotBlank String password,
                                       HttpServletRequest request) {
        return userService.login(username, password, request);
    }

    @PostMapping("password/update")
    public ResultMessage<Void> updatePassword(@NotBlank String oldPassword,
                                              @NotBlank String newPassword,
                                              HttpServletRequest request) {

        return userService.updatePassword(oldPassword, newPassword, request);
    }

    @PostMapping("my/info")
    public ResultMessage<MyInfoDTO> getMyInfo(HttpServletRequest request) {
        return userService.getMyInfo(request);
    }
}
