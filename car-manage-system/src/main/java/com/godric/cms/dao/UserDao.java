package com.godric.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godric.cms.common.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Godric
 */
public interface UserDao extends BaseMapper<UserPO> {
}
