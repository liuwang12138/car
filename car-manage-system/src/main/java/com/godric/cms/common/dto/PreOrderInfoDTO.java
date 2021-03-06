package com.godric.cms.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Godric
 */
@Data
public class PreOrderInfoDTO {

    private Integer preOrderId;

    private String carModelName;

    private String username;

    private String fullName;

    private String phone;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime preOrderTime;

}
