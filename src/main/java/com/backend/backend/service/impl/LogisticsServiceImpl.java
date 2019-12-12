package com.backend.backend.service.impl;

import com.backend.backend.dao.LogisticsMapper;
import com.backend.backend.enums.DelFlagEnum;
import com.backend.backend.model.entity.Logistics;
import com.backend.backend.service.LogisticsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: goodtimp
 * @Date: 2019/10/1 13:13
 * @description :
 */
@Service
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements LogisticsService {

    final private LogisticsMapper logisticsMapper;

    @Override
    public List<Logistics> getList(Long logId, Integer type, String productName, String code, String phone, Long postId) {
        QueryWrapper<Logistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(logId != null, Logistics::getLogId, logId)
                .eq(type != null, Logistics::getLogId, logId)
                .like(productName != null, Logistics::getProductName, "%" + productName + "%")
                .eq(code != null, Logistics::getLogCode, code)
                .eq(phone != null, Logistics::getPhone, phone)
                .eq(postId != null, Logistics::getPostId, postId)
                .eq(Logistics::getDelFlag, DelFlagEnum.NORMAL.getCode());
        return logisticsMapper.selectList(queryWrapper);
    }

    @Override
    public Logistics saveLogistics(Logistics logistics) {
        logistics.setCreate();
        if (save(logistics)) {
            return logistics;
        }
        return null;
    }

    @Override
    public Logistics updateLogistics(Logistics logistics) {
        logistics.setUpdate();
        if (updateById(logistics)) {
            return logistics;
        }
        return null;
    }

    @Override
    public Logistics deleteLogisticsById(Long id) {
        Logistics logistics = new Logistics();
        logistics.setLogId(id);
        logistics.delete();
        return updateLogistics(logistics);
    }
}
