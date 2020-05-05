package com.godric.cms.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Godric
 */
@Data
public class CarModelDTO {

    private Integer id;

    private String modelName;

    private Integer stock;

    private BigDecimal price;

    private String desc;

    private String mainImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;

}
