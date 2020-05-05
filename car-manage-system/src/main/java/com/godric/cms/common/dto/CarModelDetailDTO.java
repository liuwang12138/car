package com.godric.cms.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Godric
 */
@Data
public class CarModelDetailDTO {

    private String modelName;

    private Integer stock;

    private BigDecimal price;

    private String desc;

    private String mainImageUrl;

    private List<String> imageList;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;

}
