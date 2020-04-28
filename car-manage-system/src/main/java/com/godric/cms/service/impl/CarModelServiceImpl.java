package com.godric.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.godric.cms.common.dto.CarModelDTO;
import com.godric.cms.common.dto.CarModelDetailDTO;
import com.godric.cms.common.dto.ResultMessage;
import com.godric.cms.common.po.CarImagePO;
import com.godric.cms.common.po.CarModelPO;
import com.godric.cms.common.po.PreOrderRecordPO;
import com.godric.cms.common.po.UserPO;
import com.godric.cms.dao.CarImageDao;
import com.godric.cms.dao.CarModelDao;
import com.godric.cms.dao.PreOrderRecordDao;
import com.godric.cms.service.CarModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    CarImageDao carImageDao;

    @Autowired
    PreOrderRecordDao preOrderRecordDao;

    @Override
    public ResultMessage<List<CarModelDTO>> getCarModelList(String modelName, Integer pageNum, Integer pageSize) {
        Integer startNum = null;
        if (pageNum != null && pageSize != null) {
            startNum = (pageNum - 1) * pageSize;
        }
        List<CarModelDTO> list = carModelDao.getCarModelList(modelName, startNum, pageSize);
        Integer count = carModelDao.countCarModelList(modelName);

        return ResultMessage.success(list, count);
    }

    @Override
    @Transactional
    public ResultMessage<Void> insertCarModel(String modelName, Integer stock, List<String> imageList) {

        QueryWrapper<CarModelPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("model_name", modelName);
        List<CarModelPO> carModelPos = carModelDao.selectList(queryWrapper);
        if (carModelPos != null && !carModelPos.isEmpty()) {
            return ResultMessage.fail("已添加相同车型，请不要重复添加！");
        }

        CarModelPO po = CarModelPO.builder()
                                    .modelName(modelName)
                                    .stock(stock)
                                    .build();
        carModelDao.insert(po);

        int sort = 0;
        for (String imageUrl : imageList) {
            CarImagePO imagePo = CarImagePO.builder()
                                            .imageUrl(imageUrl)
                                            .modelId(po.getId())
                                            .sort(++sort)
                                            .build();
            carImageDao.insert(imagePo);
        }
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

        QueryWrapper<CarImagePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("model_id", modelId);
        queryWrapper.orderByAsc("sort");
        List<CarImagePO> carImagePos = carImageDao.selectList(queryWrapper);

        List<String> imageList = new ArrayList<>();
        carImagePos.forEach(imagePo -> {
            String image = imagePo.getImageUrl();
            imageList.add(image);
        });

        detailDto.setImageList(imageList);
        return ResultMessage.success(detailDto);
    }

    @Override
    @Transactional
    public ResultMessage<Void> preOrderCarModel(Integer carModelId, HttpServletRequest request) {
        UserPO user = (UserPO) request.getSession().getAttribute("user");

        if (Objects.isNull(user)) {
            return ResultMessage.fail("用户未登录！");
        }

        Integer userId = user.getId();
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
}
