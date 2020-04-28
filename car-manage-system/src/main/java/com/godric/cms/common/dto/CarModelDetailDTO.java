package com.godric.cms.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Godric
 */
@Data
public class CarModelDetailDTO {

    private String modelName;

    private Integer stock;

    private List<String> imageList;

}
