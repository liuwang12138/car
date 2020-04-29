package com.godric.cms.controller;

import com.godric.cms.common.CmsConstants;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.UserPO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author Godric
 */
@Validated
@RestController
@RequestMapping("admin")
public class AdminController {

    @PostMapping("login")
    public ResultMessage<Void> login(@NotNull String username,
                                     @NotNull String password,
                                     HttpServletRequest request) {

        if (CmsConstants.ADMIN_USERNAME.equals(username) && CmsConstants.ADMIN_PASSWORD.equals(password)) {
            UserPO po = UserPO.builder()
                                .username(username)
                                .password(password)
                                .realName(CmsConstants.ADMIN_USERNAME)
                                .phone(CmsConstants.ADMIN_USERNAME)
                                .build();
            request.getSession().setAttribute("user", po);
            request.getSession().setAttribute("username", CmsConstants.ADMIN_USERNAME);

            return ResultMessage.success("管理员登陆成功！");
        }
        return ResultMessage.fail("用户名或密码错误！");
    }

    

}
