package com.godric.cms.controller;

import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.dto.CarModelDetailDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Godric
 */
@Validated
@RestController
@RequestMapping("carModel")
public class CarModelController {

    @Autowired
    public CarModelService carModelService;

    @PostMapping("insert")
    public ResultMessage<Void> insertCarModel(@NotBlank String modelName,
                                              @NotNull @Min(1) Integer stock,
                                              @NotEmpty List<String> imageList) {
        return carModelService.insertCarModel(modelName, stock, imageList);
    }

    @PostMapping("list")
    public ResultMessage<List<CarModelDTO>> getCarModelList(String modelName,
                                                            Integer pageNum,
                                                            Integer pageSize) {
        return carModelService.getCarModelList(modelName, pageNum, pageSize);
    }

    @PostMapping("detail")
    public ResultMessage<CarModelDetailDTO> getCarModelDetail(@NotNull Integer modelId) {
        return carModelService.getCarModelDetail(modelId);
    }

    @PostMapping("preOrderCarModel")
    public ResultMessage<Void> preOrderCarModel(@NotNull Integer carModelId,
                                                HttpServletRequest request) {
        return carModelService.preOrderCarModel(carModelId, request);
    }



}
