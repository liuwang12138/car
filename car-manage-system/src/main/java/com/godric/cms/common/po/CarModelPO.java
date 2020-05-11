package com.godric.cms.common.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Godric
 */
@Data
@Builder
@TableName("car_model")
public class CarModelPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("model_name")
    private String modelName;

    @TableField("stock")
    private Integer stock;

    @TableField("price")
    private BigDecimal price;

    @TableField("main_image_url")
    private String mainImageUrl;

    @TableField("`desc`")
    private String desc;

    @TableField("detail_image_url")
    private String detailImageUrl;

    /**
     * new fields
     */
    @TableField("car_length")
    private Integer carLength;

    @TableField("car_width")
    private Integer carWidth;

    @TableField("car_height")
    private Integer carHeight;

    @TableField("wheel_base")
    private Integer wheelBase;

    @TableField("displacement")
    private Integer displacement;

    @TableField("acceleration_time")
    private BigDecimal accelerationTime;

    @TableField("oil_consumption")
    private BigDecimal oilConsumption;

    @TableField("fuel_tank_capacity")
    private BigDecimal fuelTankCapacity;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Tolerate
    public CarModelPO() {}

}
