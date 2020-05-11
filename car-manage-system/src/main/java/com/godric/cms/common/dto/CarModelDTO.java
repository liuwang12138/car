package com.godric.cms.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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

    /**
     * new fields
     */

    /**
     * 车长(毫米)
     */
    private Integer carLength;

    /**
     * 车宽(毫米)
     */
    private Integer carWidth;

    /**
     * 车高(毫米)
     */
    private Integer carHeight;

    /**
     * 轴距(毫米)
     */
    private Integer wheelBase;

    /**
     * 排量(毫升)
     */
    private Integer displacement;

    /**
     * 0 - 100公里/小时的加速时间
     */
    private BigDecimal accelerationTime;

    /**
     * 综合耗油量(升/公里)
     */
    private BigDecimal oilConsumption;

    /**
     * 油箱容积(升)
     */
    private BigDecimal fuelTankCapacity;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createTime;

}
