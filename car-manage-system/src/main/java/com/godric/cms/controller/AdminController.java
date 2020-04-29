package com.godric.cms.controller;

import com.godric.cms.common.CmsConstants;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.dto.UserDTO;
import com.godric.cms.common.po.UserPO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Godric
 */
@Validated
@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {

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

    @PostMapping("user/list")
    public ResultMessage<List<UserDTO>> getUserList(String username,
                                                    LocalDateTime startTime,
                                                    LocalDateTime endTime,
                                                    Integer pageNum,
                                                    Integer pageSize) {
        return userService.getUserList(username, startTime, endTime, pageNum, pageSize);
    }

    @PostMapping("user/insert")
    public ResultMessage<Void> insertUser(@NotBlank String username,
                                          @NotBlank String password,
                                          @NotBlank String realName,
                                          @NotBlank String phone) {
        return userService.register(username, password, realName, phone);
    }

    @PostMapping("user/delete")
    public ResultMessage<Void> delUserById(@NotNull Integer userId) {
        return userService.deleteById(userId);
    }



}
