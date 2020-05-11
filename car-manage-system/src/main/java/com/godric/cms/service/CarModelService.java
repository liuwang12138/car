package com.godric.cms.service;

import com.godric.cms.common.dto.*;

import java.math.BigDecimal;
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
    ResultMessage<List<CarModelDTO>> getCarModelList(String modelName, Integer minCarLength,
                                                     Integer maxCarLength,
                                                     Integer minCarWidth,
                                                     Integer maxCarWidth,
                                                     Integer minCarHeight,
                                                     Integer maxCarHeight,
                                                     Integer minWheelBase,
                                                     Integer maxWheelBase, Integer pageNum, Integer pageSize);

    /**
     * insert car model info
     * @param modelName model name
     * @param stock stock
     * @param price price
     * @param desc description
     * @param mainImageUrl main image url
     * @param detailImageList detail image list
     * @return whether insert success or not
     */
    ResultMessage<Void> insertCarModel(String modelName, Integer stock, BigDecimal price, String desc, String mainImageUrl, List<String> detailImageList);


    /**
     * insert car model info
     * @param modelName model name
     * @param stock stock
     * @param price price
     * @param desc description
     * @param mainImageUrl main image url
     * @param detailImageList detail image list
     * @return whether insert success or not
     */
    ResultMessage<Void> insertCarModel(String modelName, Integer stock, BigDecimal price, String desc, String mainImageUrl, String detailImageList,
                                       Integer carLength, Integer carWidth, Integer carHeight, Integer wheelBase, Integer displacement, BigDecimal accelerationTime,
                                       BigDecimal oilConsumption, BigDecimal fuelTankCapacity);

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
    ResultMessage<Void> preOrderCarModel(Integer carModelId, Integer userId, String fullName, String phone, String email);

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

    /**
     * delete car model info by id
     * @param carModelId car model id
     * @return whether delete is success or not
     */
    ResultMessage<Void> delCarModelById(Integer carModelId);

    /**
     * update car model by id
     * @param carModelId car model id
     * @param modelName model name
     * @param stock stock
     * @param price price
     * @param desc description
     * @param mainImageUrl main image url
     * @param detailImageList detail image list
     * @return whether update is success or not
     */
    ResultMessage<Void> updateCarModelById(Integer carModelId, String modelName, Integer stock, BigDecimal price, String desc, String mainImageUrl, List<String> detailImageList);

    /**
     * update car model by id
     * @param carModelId car model id
     * @param modelName model name
     * @param stock stock
     * @param price price
     * @param desc description
     * @param mainImageUrl main image url
     * @param detailImageList detail image list
     * @return whether update is success or not
     */
    ResultMessage<Void> updateCarModelById(Integer carModelId, String modelName, Integer stock, BigDecimal price, String desc, String mainImageUrl, String detailImageList);

    ResultMessage<Void> insertAfterSaleService(Integer preOrderId, String fullName, String phone, String email, String serviceContent);

    ResultMessage<List<AfterSaleServiceDTO>> getAfterSaleServiceList(LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize);
}
