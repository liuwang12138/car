package com.godric.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.godric.cms.common.constants.CmsConstants;
import com.godric.cms.common.dto.MyInfoDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.dto.UserDTO;
import com.godric.cms.common.po.UserPO;
import com.godric.cms.dao.UserDao;
import com.godric.cms.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Godric
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResultMessage<Void> register(String username, String password, String realName, String phone) {

        if (CmsConstants.ADMIN_USERNAME.equals(username)) {
            return ResultMessage.fail("不能以admin作为用户名！");
        }

        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<UserPO>()
                                                .eq("username", username);
        UserPO existUser = userDao.selectOne(queryWrapper);
        if (Objects.nonNull(existUser)) {
            // 之前已经有相同username注册过
            return ResultMessage.fail("用户名已被占用，请更换后重试！");
        }

        UserPO po = UserPO.builder()
                            .username(username)
                            .password(password)
                            .realName(realName)
                            .phone(phone)
                            .build();
        userDao.insert(po);

        return ResultMessage.success("注册成功！");
    }

    @Override
    public ResultMessage<UserPO> login(String username, String password, HttpServletRequest request) {

        if (CmsConstants.ADMIN_USERNAME.equals(username)) {
            return ResultMessage.fail("管理员用户请去往管理员端登陆");
        }

        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<UserPO>()
                                                        .eq("username", username)
                                                        .eq("password", password);
        UserPO user = userDao.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            return ResultMessage.fail("用户名或密码错误！");
        }

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("username", user.getUsername());

        return ResultMessage.success("登陆成功！", user);
    }

    @Override
    public ResultMessage<Void> updatePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        UserPO user = (UserPO) request.getSession().getAttribute("user");
        if (Objects.isNull(user)) {
            return ResultMessage.fail("用户登陆状态错误！");
        }

        if (!oldPassword.equals(user.getPassword())) {
            return ResultMessage.fail("原密码不正确");
        }

        user.setPassword(newPassword);
        userDao.updateById(user);
        return ResultMessage.success("修改成功！");
    }

    @Override
    public ResultMessage<MyInfoDTO> getMyInfo(HttpServletRequest request) {
        UserPO user = (UserPO) request.getSession().getAttribute("user");
        if (Objects.isNull(user)) {
            return ResultMessage.fail("用户登陆状态错误！");
        }

        MyInfoDTO myInfo = new MyInfoDTO();
        BeanUtils.copyProperties(user, myInfo);

        return ResultMessage.success(myInfo);
    }

    @Override
    public ResultMessage<List<UserDTO>> getUserList(String username, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize) {

        IPage<UserPO> userPoPage;
        if (pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0) {
            userPoPage = new Page<>(pageNum, pageSize);
        } else {
            userPoPage = new Page<>(1, Integer.MAX_VALUE);
        }

        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        if (startTime != null) {
            queryWrapper.gt("create_time", startTime);
        }
        if (endTime != null) {
            queryWrapper.lt("create_time", endTime);
        }

        IPage<UserPO> userPos = userDao.selectPage(userPoPage, queryWrapper);

        List<UserDTO> list = new ArrayList<>();
        userPos.getRecords().forEach(po -> {
            UserDTO dto = new UserDTO();

            BeanUtils.copyProperties(po, dto);
            dto.setRegisterTime(po.getCreateTime());

            list.add(dto);
        });

        return ResultMessage.success(list, (int)userPos.getTotal());
    }

    @Override
    public ResultMessage<Void> deleteById(Integer userId) {
        if (userDao.deleteById(userId) > 0) {
            return ResultMessage.success("删除成功");
        }

        return ResultMessage.fail("找不到对应id的用户！");
    }

    @Override
    public ResultMessage<UserDTO> getUserInfoById(Integer userId) {
        UserPO po = userDao.selectById(userId);
        if (Objects.isNull(po)) {
            return ResultMessage.fail("找不到对应id的用户记录");
        }
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(po, dto);
        return ResultMessage.success(dto);
    }

    @Override
    public ResultMessage<Void> updateUserInfo(UserPO po) {
        if (userDao.updateById(po) > 0) {
            return ResultMessage.success("更新成功！");
        }
        return ResultMessage.fail("找不到更新的用户记录！");
    }
}
