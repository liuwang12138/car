package com.godric.cms.service;

import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.dto.CarModelDetailDTO;
import com.godric.cms.common.dto.PreOrderInfoDTO;
import com.godric.cms.common.dto.ResultMessage;

import java.time.LocalDateTime;
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
     * @param mainImageUrl main image url
     * @param detailImageList detail image list
     * @return whether insert success or not
     */
    ResultMessage<Void> insertCarModel(String modelName, Integer stock, String mainImageUrl, List<String> detailImageList);

    /**
     * get car model detail
     * @param modelId model id
     * @return model detail dto
     */
    ResultMessage<CarModelDetailDTO> getCarModelDetail(Integer modelId);

    /**
     * pre order a car model
     * @param carModelId car model id
     * @param userId user id who login
     * @return whether pre order is success or not
     */
    ResultMessage<Void> preOrderCarModel(Integer carModelId, Integer userId);

    /**
     * cancel pre order
     * @param preOrderId pre order id
     * @param userId user id who login
     * @return whether cancel is success or not
     */
    ResultMessage<Void> cancelPreOrder(Integer preOrderId, Integer userId);

    /**
     * get user pre order records
     * @param userId user id
     * @param startTime start time
     * @param endTime end time
     * @param pageNum page number
     * @param pageSize page size
     * @return pre order info
     */
    ResultMessage<List<PreOrderInfoDTO>> getPreOrderInfo(Integer userId, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize);
}
