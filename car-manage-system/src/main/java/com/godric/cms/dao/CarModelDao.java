package com.godric.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.po.CarModelPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarModelDao extends BaseMapper<CarModelPO> {

    /**
     * get car model list
     * @param modelName can be null
     * @param startNum start number
     * @param pageSize page size
     * @return car model list
     */
    List<CarModelDTO> getCarModelList(@Param("modelName") String modelName,
                                      @Param("startNum") Integer startNum,
                                      @Param("pageSize") Integer pageSize);

    /**
     * count car model
     * @param modelName can be null
     * @return the size of list
     */
    Integer countCarModelList(@Param("modelName") String modelName);
}
