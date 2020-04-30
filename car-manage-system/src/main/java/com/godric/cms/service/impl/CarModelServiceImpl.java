package com.godric.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.dto.CarModelDetailDTO;
import com.godric.cms.common.dto.PreOrderInfoDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.CarModelPO;
import com.godric.cms.common.po.PreOrderRecordPO;
import com.godric.cms.common.po.UserPO;
import com.godric.cms.dao.CarModelDao;
import com.godric.cms.dao.PreOrderRecordDao;
import com.godric.cms.service.CarModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Godric
 */
@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    CarModelDao carModelDao;

    @Autowired
    PreOrderRecordDao preOrderRecordDao;

    @Override
    public ResultMessage<List<CarModelDTO>> getCarModelList(String modelName, Integer pageNum, Integer pageSize) {

        IPage<CarModelPO> carModelPage;
        if (pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0) {
            carModelPage = new Page<>(pageNum, pageSize);
        } else {
            carModelPage = new Page<>(1, Integer.MAX_VALUE);
        }
        QueryWrapper<CarModelPO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(modelName)) {
            queryWrapper.like("model_name", modelName);
        }

        IPage<CarModelPO> carModels = carModelDao.selectPage(carModelPage, queryWrapper);
        List<CarModelDTO> list = new ArrayList<>();
        carModels.getRecords().forEach(po -> {
            CarModelDTO carModel = new CarModelDTO();
            BeanUtils.copyProperties(po, carModel);
            list.add(carModel);
        });

        return ResultMessage.success(list, (int)carModels.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMessage<Void> insertCarModel(String modelName, Integer stock, String mainImageUrl, List<String> detailImageList) {

        QueryWrapper<CarModelPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("model_name", modelName);
        List<CarModelPO> carModelPos = carModelDao.selectList(queryWrapper);
        if (carModelPos != null && !carModelPos.isEmpty()) {
            return ResultMessage.fail("已添加相同车型，请不要重复添加！");
        }

        StringBuilder detailImageUrl = new StringBuilder();

        // detail image list must not empty, so get(0) will not make null pointer exception
        detailImageUrl.append(detailImageList.get(0));
        for (int i=1; i<detailImageList.size(); i++) {
            detailImageUrl.append(",").append(detailImageList.get(i));
        }


        CarModelPO po = CarModelPO.builder()
                                    .modelName(modelName)
                                    .stock(stock)
                                    .mainImageUrl(mainImageUrl)
                                    .detailImageUrl(detailImageUrl.toString())
                                    .build();
        carModelDao.insert(po);

        return ResultMessage.success("添加成功！");
    }

    @Override
    public ResultMessage<CarModelDetailDTO> getCarModelDetail(Integer modelId) {
        CarModelDetailDTO detailDto = new CarModelDetailDTO();
        CarModelPO po = carModelDao.selectById(modelId);

        if (Objects.isNull(po)) {
            return ResultMessage.fail("根据modelId = " + modelId + " 没有找到对应的车型信息");
        }

        BeanUtils.copyProperties(po, detailDto);
        String[] split = po.getDetailImageUrl().split(",");
        detailDto.setImageList(Arrays.asList(split));

        return ResultMessage.success(detailDto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMessage<Void> preOrderCarModel(Integer carModelId, Integer userId) {
        QueryWrapper<PreOrderRecordPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_model_id", carModelId);
        queryWrapper.eq("user_id", userId);

        CarModelPO carModelPo = carModelDao.selectById(carModelId);
        if (Objects.isNull(carModelPo)) {
            return ResultMessage.fail("找不到对应的车型！");
        }

        if (carModelPo.getStock() < 1) {
            return ResultMessage.fail("对应车型库存不足，请选择其他车型！");
        }

        List<PreOrderRecordPO> preOrderRecordPos = preOrderRecordDao.selectList(queryWrapper);
        if (preOrderRecordPos != null && !preOrderRecordPos.isEmpty()) {
            return ResultMessage.fail("您已经预购过此车型，不能重复购买！");
        }

        PreOrderRecordPO po = PreOrderRecordPO.builder()
                                              .carModelId(carModelId)
                                              .userId(userId).build();

        preOrderRecordDao.insert(po);

        // 对应车型的库存数量 -1
        carModelPo.setStock(carModelPo.getStock() - 1);
        carModelDao.updateById(carModelPo);

        return ResultMessage.success("预购成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultMessage<Void> cancelPreOrder(Integer preOrderId, Integer userId) {
        PreOrderRecordPO preOrderRecordPo = preOrderRecordDao.selectById(preOrderId);

        if (Objects.isNull(preOrderRecordPo)) {
            return ResultMessage.fail("根据id找不到对应的预定记录！");
        }
        if (!preOrderRecordPo.getUserId().equals(userId)) {
            return ResultMessage.fail("这不是您的预约记录，您无权取消！");
        }

        CarModelPO carModelPo = carModelDao.selectById(preOrderRecordPo.getCarModelId());
        if (Objects.isNull(carModelPo)) {
            return ResultMessage.fail("找不到您预约的车型，取消预约失败！");
        }

        carModelPo.setStock(carModelPo.getStock() + 1);
        carModelDao.updateById(carModelPo);

        preOrderRecordDao.deleteById(preOrderId);
        return ResultMessage.success("取消预约成功！");
    }

    @Override
    public ResultMessage<List<PreOrderInfoDTO>> getPreOrderInfo(Integer userId, LocalDateTime startTime, LocalDateTime endTime, Integer pageNum, Integer pageSize) {
        Integer startNum = null;
        if (pageNum != null && pageNum > 0 && pageSize != null && pageSize > 0) {
            startNum = (pageNum - 1) * pageSize;
        }

        List<PreOrderInfoDTO> preOrderInfoList = preOrderRecordDao.getPreOrderInfo(userId, startTime, endTime, startNum, pageSize);
        Integer count = preOrderRecordDao.countPreOrderInfo(userId, startTime, endTime);
        return ResultMessage.success(preOrderInfoList, count);
    }
}
