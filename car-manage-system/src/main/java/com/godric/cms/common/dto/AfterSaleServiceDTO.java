package com.godric.cms.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Godric
 */
@Data
public class AfterSaleServiceDTO {

    private Integer id;

    private String username;

    private String modelName;

    private String fullName;

    private String phone;

    private String email;

    private String serviceContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
