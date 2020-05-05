package com.godric.cms.controller;

import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.UserPO;
import com.godric.cms.service.CarModelService;
import com.godric.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Godric
 */
@RestController
public class BaseController {

    @Autowired
    UserService userService;

    @Autowired
    CarModelService carModelService;

    protected ResultMessage<UserPO> getLoginUserInfo(HttpServletRequest request) {
        UserPO user = (UserPO) request.getSession().getAttribute("user");
        if (Objects.isNull(user)) {
            return ResultMessage.fail("用户未登录，无法获取用户信息！");
        }
        return ResultMessage.success(user);
    }

    @PostMapping("user/not/login")
    private ResultMessage<Void> userNotLogin() {
        return ResultMessage.refuse("用户未登录，请登陆后操作！");
    }

    @PostMapping("admin/not/login")
    private ResultMessage<Void> adminNotLogin() {
        return ResultMessage.refuse("用户不具有管理员权限，请登录管理员账号操作！");
    }

}
