DROP TABLE IF EXISTS `car_image`;
CREATE TABLE `car_image`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `image_url` varchar(200) NOT NULL COMMENT '图片url',
  `model_id` int(11) NOT NULL COMMENT '车型id',
  `sort` int(5) NOT NULL COMMENT '排序字段-倒叙排',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `car_model`;
CREATE TABLE `car_model`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `model_name` varchar(50) NOT NULL COMMENT '型号名',
  `stock` int(5) NOT NULL COMMENT '库存',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

DROP TABLE IF EXISTS `pre_order_record`;
CREATE TABLE `pre_order_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `car_model_id` int(11) NOT NULL COMMENT '车型id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `real_name` varchar(50) NOT NULL COMMENT '真实姓名',
  `phone` varchar(50) NOT NULL COMMENT '手机号码',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名唯一'
) ENGINE = InnoDB;
