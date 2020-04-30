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

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Godric
 */
@Data
@Builder
@TableName("user")
public class UserPO {

    @NotNull(message = "id不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "username不能为空")
    @TableField("username")
    private String username;

    @NotNull(message = "password")
    @TableField("password")
    private String password;

    @NotNull(message = "realName")
    @TableField("real_name")
    private String realName;

    @NotNull(message = "phone")
    @TableField("phone")
    private String phone;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Tolerate
    public UserPO() {}

}
