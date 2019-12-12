package com.backend.backend.service;

import com.backend.backend.model.entity.Logistics;
import com.backend.backend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: goodtimp
 * @Date: 2019/9/24 19:22
 * @description :  用户管理
 */

public interface LogisticsService extends IService<Logistics> {
    /**
     * 通过参数获取物流列表
     *
     * @param logId
     * @param type
     * @param productName
     * @param code
     * @param phone       收货人手机号
     * @param postId      驿站Id
     * @return
     */
    List<Logistics> getList(Long logId, Integer type, String productName, String code, String phone, Long postId);

    /**
     * 新增
     *
     * @param logistics
     * @return
     */
    Logistics saveLogistics(Logistics logistics);

    /**
     * 修改
     *
     * @param logistics
     * @return
     */
    Logistics updateLogistics(Logistics logistics);

    /**
     * 通过Id删除
     *
     * @param id
     * @return
     */
    Logistics deleteLogisticsById(Long id);

}
