package com.godric.cms.controller;

import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.UserPO;
import com.godric.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Godric
 */

public class BaseController {

    @Autowired
    UserService userService;


    protected ResultMessage<UserPO> getLoginUserInfo(HttpServletRequest request) {
        UserPO user = (UserPO) request.getSession().getAttribute("user");
        if (Objects.isNull(user)) {
            return ResultMessage.fail("用户未登录，无法获取用户信息！");
        }
        return ResultMessage.success(user);
    }

}
