package com.godric.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godric.cms.common.dto.AfterSaleServiceDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.AfterSaleServicePO;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Godric
 */
public interface AfterSaleServiceDao extends BaseMapper<AfterSaleServicePO> {

    List<AfterSaleServiceDTO> getAfterSaleServiceList(@Param("startTime") LocalDateTime startTime,
                                                      @Param("endTime") LocalDateTime endTime,
                                                      @Param("startNum") Integer startNum,
                                                      @Param("pageSize") Integer pageSize);

    int countAfterSaleServiceList(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime);
}
