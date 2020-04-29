package com.godric.cms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godric.cms.common.dto.PreOrderInfoDTO;
import com.godric.cms.common.po.PreOrderRecordPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Godric
 */
public interface PreOrderRecordDao extends BaseMapper<PreOrderRecordPO> {

    /**
     * get user's pre order info
     * @param userId user id
     * @param startNum start number
     * @param pageSize page size
     */
    List<PreOrderInfoDTO> getPreOrderInfo(@Param("userId") Integer userId,
                                          @Param("startNum") Integer startNum,
                                          @Param("pageSize") Integer pageSize);

    /**
     * count user's pre order info
     * @param userId user id
     * @return the count of user's pre order info
     */
    Integer countPreOrderInfo(@Param("userId") Integer userId);
}
