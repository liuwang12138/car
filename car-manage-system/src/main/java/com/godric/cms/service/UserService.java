package com.godric.cms.service;

import com.godric.cms.common.dto.MyInfoDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.dto.UserDTO;
import com.godric.cms.common.po.UserPO;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Godric
 */
public interface UserService {

    /**
     * register
     * @param username username
     * @param password password
     * @param realName realName
     * @param phone phone
     * @return whether register success or not
     */
    ResultMessage<Void> register(String username,
                                 String password,
                                 String realName,
                                 String phone);

    /**
     * login
     * @param username username
     * @param password password
     * @param request request
     * @return user who want to login
     */
    ResultMessage<UserPO> login(String username, String password, HttpServletRequest request);

    /**
     * update password
     * @param oldPassword old password
     * @param newPassword new password
     * @param request request
     * @return whether success or not
     */
    ResultMessage<Void> updatePassword(String oldPassword, String newPassword, HttpServletRequest request);

    /**
     * get my info
     * @param request request
     * @return my info
     */
    ResultMessage<MyInfoDTO> getMyInfo(HttpServletRequest request);

    /**
     * admin get user list
     * @param username username
     * @param startTime register start time
     * @param endTime register end time
     * @param pageNum page number
     * @param pageSize page size
     * @return user list
     */
    ResultMessage<List<UserDTO>> getUserList(String username, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize);

    /**
     * admin delete user by id
     * @param userId user id
     * @return whether delete is success or not
     */
    ResultMessage<Void> deleteById(Integer userId);

    /**
     * get user info by id
     * @param userId user id
     * @return user info
     */
    ResultMessage<UserDTO> getUserInfoById(Integer userId);

    /**
     * update user info
     * @param po user po
     * @return whether update is success or not
     */
    ResultMessage<Void> updateUserInfo(UserPO po);
}
