package com.godric.cms.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

/**
 * @author Godric
 */

@Data
@Builder
@TableName("after_sale_service")
public class AfterSaleServicePO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer preOrderId;

    private String fullName;

    private String phone;

    private String email;

    private String serviceContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Tolerate
    public AfterSaleServicePO() {}

}
