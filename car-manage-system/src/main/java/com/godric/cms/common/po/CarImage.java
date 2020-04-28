package com.godric.cms.common.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.time.LocalDateTime;

/**
 * @author Godric
 */
@Data
@Builder
@TableName("car_image")
public class CarImage {

    @TableId("id")
    private Integer id;

    @TableField("image_url")
    private String imageUrl;

    @TableField("model_id")
    private Integer modelId;

    @TableField("sort")
    private Integer sort;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Tolerate
    public CarImage() {}

}
