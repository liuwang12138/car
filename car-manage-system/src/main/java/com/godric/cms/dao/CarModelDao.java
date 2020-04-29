package com.godric.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.po.CarModelPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarModelDao extends BaseMapper<CarModelPO> {

}
