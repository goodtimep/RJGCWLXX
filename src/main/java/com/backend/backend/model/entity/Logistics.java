package com.backend.backend.model.entity;

import com.backend.backend.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: goodtimp
 * @Date: 2019/12/12 14:15
 * @description :  物流信息
 */
@Data
@TableName("logistics")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "物流信息")
public class Logistics extends BaseEntity {
    @TableId
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long logId;

    private Integer type;

    private String productName;

    private String logCode;
    
    /**
     * 驿站Id，用户Id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long postId;

    private String deliveryAddress;

    /**
     * 发货地址
     */
    private String shipAddress;

    /**
     * 收货人手机号
     */
    private String phone;
}
