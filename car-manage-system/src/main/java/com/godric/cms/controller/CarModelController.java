package com.godric.cms.controller;

import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.dto.CarModelDetailDTO;
import com.godric.cms.common.dto.PreOrderInfoDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.UserPO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Godric
 */
@Validated
@RestController
@RequestMapping("carModel")
public class CarModelController extends BaseController {

    /**
     * user get car model list
     * @param modelName model name
     * @param pageNum page number
     * @param pageSize page size
     * @return car model list
     */
    @PostMapping("list")
    public ResultMessage<List<CarModelDTO>> getCarModelList(String modelName,
                                                            Integer minCarLength,
                                                            Integer maxCarLength,
                                                            Integer minCarWidth,
                                                            Integer maxCarWidth,
                                                            Integer minCarHeight,
                                                            Integer maxCarHeight,
                                                            Integer minWheelBase,
                                                            Integer maxWheelBase,
                                                            Integer pageNum,
                                                            Integer pageSize) {
        return carModelService.getCarModelList(modelName, minCarLength, maxCarLength, minCarWidth, maxCarWidth, minCarHeight, maxCarHeight, minWheelBase, maxWheelBase, pageNum, pageSize);
    }

    /**
     * get car model detail
     * @param modelId model id
     * @return car model detail info
     */
    @PostMapping("detail")
    public ResultMessage<CarModelDetailDTO> getCarModelDetail(@NotNull Integer modelId) {
        return carModelService.getCarModelDetail(modelId);
    }

    /**
     * user pre order car model
     * @param carModelId car model id
     * @param request request
     * @return whether pre order is success or not
     */
    @PostMapping("preOrder")
    public ResultMessage<Void> preOrderCarModel(@NotNull Integer carModelId,
                                                @NotNull String fullName,
                                                @NotNull String phone,
                                                @Email String email,
                                                HttpServletRequest request) {
        ResultMessage<UserPO> loginUserInfo = this.getLoginUserInfo(request);
        if (!loginUserInfo.isSuccess()) {
            ResultMessage.fail(loginUserInfo.getMessage());
        }
        Integer userId = loginUserInfo.getData().getId();
        return carModelService.preOrderCarModel(carModelId, userId, fullName, phone, email);

    }

    /**
     * cancel my pre order
     * @param preOrderId pre order id
     * @param request request
     * @return whether cancel is success or not
     */
    @PostMapping("cancelPreOrder")
    public ResultMessage<Void> cancelPreOrder(@NotNull Integer preOrderId,
                                              HttpServletRequest request) {

        ResultMessage<UserPO> loginUserInfo = this.getLoginUserInfo(request);
        if (!loginUserInfo.isSuccess()) {
            ResultMessage.fail(loginUserInfo.getMessage());
        }
        Integer userId = loginUserInfo.getData().getId();

        return carModelService.cancelPreOrder(preOrderId, userId);
    }

    /**
     * get my pre order info list
     * @param pageNum page number
     * @param pageSize page size
     * @param request request
     * @return my pre order info list
     */
    @PostMapping("getMyPreOrderInfo")
    public ResultMessage<List<PreOrderInfoDTO>> getMyPreOrderInfo(Integer pageNum,
                                                                  Integer pageSize,
                                                                  HttpServletRequest request) {

        ResultMessage<UserPO> loginUserInfo = this.getLoginUserInfo(request);
        if (!loginUserInfo.isSuccess()) {
            return ResultMessage.fail(loginUserInfo.getMessage());
        }
        Integer userId = loginUserInfo.getData().getId();

        return carModelService.getPreOrderInfo(userId, null, null, pageNum, pageSize);
    }

    @PostMapping("afterSaleService/insert")
    public ResultMessage<Void> insertAfterSaleService(@NotNull Integer preOrderId,
                                                      @NotNull String fullName,
                                                      @NotNull String phone,
                                                      @Email String email,
                                                      @NotNull String serviceContent) {
        return carModelService.insertAfterSaleService(preOrderId, fullName, phone, email, serviceContent);
    }

}
