package com.godric.cms.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Godric
 */
@Data
public class CarModelDTO {

    private Integer id;

    private String modelName;

    private Integer stock;

    private String desc;

    private String mainImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

}
