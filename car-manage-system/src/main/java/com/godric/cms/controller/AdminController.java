package com.godric.cms.controller;

import com.godric.cms.common.constants.CmsConstants;
import com.godric.cms.common.dto.PreOrderInfoDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.dto.UserDTO;
import com.godric.cms.common.po.UserPO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Godric
 */
@Validated
@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {

    /**
     * admin login
     * @param username username
     * @param password password
     * @param request don't need
     * @return whether login is success or not
     */
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

    /**
     * get user list
     * @param username username, can be null
     * @param startTime select start time, can  be null
     * @param endTime select end time, can be null,
     * @param pageNum page number
     * @param pageSize page size
     * @return user list
     */
    @PostMapping("user/list")
    public ResultMessage<List<UserDTO>> getUserList(String username,
                                                    LocalDateTime startTime,
                                                    LocalDateTime endTime,
                                                    Integer pageNum,
                                                    Integer pageSize) {
        return userService.getUserList(username, startTime, endTime, pageNum, pageSize);
    }

    /**
     * admin insert user info
     * @param username username, can't be blank
     * @param password password, can't be blank
     * @param realName user real name, can't be blank
     * @param phone user's phone number, can't be blank
     * @return whether insert success or not
     */
    @PostMapping("user/insert")
    public ResultMessage<Void> insertUser(@NotBlank String username,
                                          @NotBlank String password,
                                          @NotBlank String realName,
                                          @NotBlank String phone) {
        return userService.register(username, password, realName, phone);
    }

    /**
     * admin delete user info
     * @param userId user's id who want to delete, can't be null
     * @return whether delete option is success or not
     */
    @PostMapping("user/delete")
    public ResultMessage<Void> delUserById(@NotNull Integer userId) {
        return userService.deleteById(userId);
    }

    /**
     * admin get user info by id
     * @param userId user id
     * @return user info
     */
    @PostMapping("user/get/by/id")
    public ResultMessage<UserDTO> getUserInfoById(@NotNull Integer userId) {
        return userService.getUserInfoById(userId);
    }

    /**
     * admin update user info
     * @param po user po
     * @return whether update is success or not
     */
    @PostMapping("user/update/info")
    public ResultMessage<Void> updateUserInfo(@RequestBody @Validated UserPO po) {
        return userService.updateUserInfo(po);
    }


    /**
     * admin insert car model info
     * @param modelName model name, can't be blank
     * @param stock stock number, can't be null, must greater or equals to 1
     * @param mainImageUrl main image url, can't be blank
     * @param detailImageList detail image list
     * @return whether insert is success or not
     */
    @PostMapping("insert")
    public ResultMessage<Void> insertCarModel(@NotBlank String modelName,
                                              @NotNull @Min(1) Integer stock,
                                              @NotBlank String mainImageUrl,
                                              @NotEmpty List<String> detailImageList) {
        return carModelService.insertCarModel(modelName, stock, mainImageUrl, detailImageList);
    }


    @PostMapping("order/record/list")
    public ResultMessage<List<PreOrderInfoDTO>> getOrderRecordList(LocalDateTime startTime,
                                                                   LocalDateTime endTime,
                                                                   Integer pageNum,
                                                                   Integer pageSize) {
        return carModelService.getPreOrderInfo(null, startTime, endTime, pageNum, pageSize);
    }


}
