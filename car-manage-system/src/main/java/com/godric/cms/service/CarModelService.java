package com.godric.cms.service;

import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.dto.CarModelDetailDTO;
import com.godric.cms.common.dto.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Godric
 */

public interface CarModelService {

    /**
     * get car model list
     * @param modelName can be null
     * @param pageNum page num
     * @param pageSize page size
     * @return car model list
     */
    ResultMessage<List<CarModelDTO>> getCarModelList(String modelName, Integer pageNum, Integer pageSize);

    /**
     * insert car model info
     * @param modelName model name
     * @param stock stock
     * @param imageList imageList
     * @return whether insert success or not
     */
    ResultMessage<Void> insertCarModel(String modelName, Integer stock, List<String> imageList);

    /**
     * get car model detail
     * @param modelId model id
     * @return model detail dto
     */
    ResultMessage<CarModelDetailDTO> getCarModelDetail(Integer modelId);

    /**
     * pre order a car model
     * @param carModelId car model id
     * @param request request
     * @return whether pre order is success or not
     */
    ResultMessage<Void> preOrderCarModel(Integer carModelId, HttpServletRequest request);
}
