package com.godric.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godric.cms.common.dto.PreOrderInfoDTO;
import com.godric.cms.common.po.PreOrderRecordPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Godric
 */
public interface PreOrderRecordDao extends BaseMapper<PreOrderRecordPO> {

    /**
     * get user's pre order info
     * @param userId user id
     * @param startTime start time
     * @param endTime end time
     * @param startNum start number
     * @param pageSize page size
     * @return pre order info list
     */
    List<PreOrderInfoDTO> getPreOrderInfo(@Param("userId") Integer userId,
                                          @Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime,
                                          @Param("startNum") Integer startNum,
                                          @Param("pageSize") Integer pageSize);

    /**
     * count user's pre order info
     * @param userId user id
     * @param startTime start time
     * @param endTime end time
     * @return the count of user's pre order info
     */
    Integer countPreOrderInfo(@Param("userId") Integer userId,
                              @Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);
}
