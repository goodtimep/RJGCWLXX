package com.backend.backend.controller;

import com.backend.backend.common.model.ResponseModel;
import com.backend.backend.jwt.JwtUtil;
import com.backend.backend.model.entity.Logistics;
import com.backend.backend.model.entity.User;
import com.backend.backend.service.LogisticsService;
import com.backend.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author: goodtimp
 * @Date: 2019/12/12 14:57
 * @description :
 */
@Api("物流信息")
@RestController
@RequestMapping("/logistics")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class LogisticsController {

    final private LogisticsService logisticsServiceImpl;

    @GetMapping("/getList")
    @ResponseBody
    @ApiOperation(value = "得到列表", notes = "参数：logId, type, productName, code, phone（收件人手机号）, postId（驿站Id）")
    public ResponseModel getList(@RequestParam(required = false) Long logId,
                                 @RequestParam(required = false) Integer type,
                                 @RequestParam(required = false) String productName,
                                 @RequestParam(required = false) String code,
                                 @RequestParam(required = false) String phone,
                                 @RequestParam(required = false) Long postId) {
        List<Logistics> list = logisticsServiceImpl.getList(logId, type, productName, code, phone, postId);
        return ResponseModel.success("data", list);
    }

    @ApiOperation(value = "新增", notes = "新增物流:参数示例：{ type:1(等待揽件 2运输中 3已签收), productName:'产品名称', logCode:'取件码', phone:'1231'（收件人手机号）, " +
            "postId:'123'（驿站Id）,deliveryAddress:'收货地址',ship_address:'发货地址'}")
    @PostMapping("/save")
    @ResponseBody
    public ResponseModel save(@RequestBody Logistics logistics) {
        logistics = logisticsServiceImpl.saveLogistics(logistics);
        if (logistics == null) {
            return ResponseModel.fail("新增失败");
        }
        return ResponseModel.success("新增成功");
    }

    @ApiOperation(value = "更新", notes = "修改:参数示例：{logId:'123'," +
            "          type:1(等待揽件 2运输中 3已签收), productName:'产品名称', logCode:'取件码', phone:'1231'（收件人手机号）, " +
            "      postId:'123'（驿站Id）,deliveryAddress:'收货地址',ship_address:'发货地址'}")
    @PostMapping("/update")
    @ResponseBody
    public ResponseModel update(@RequestBody Logistics logistics) {
        logistics = logisticsServiceImpl.updateLogistics(logistics);
        if (logistics == null) {
            return ResponseModel.fail("新增失败");
        }
        return ResponseModel.success("新增成功");
    }

    @ApiOperation(value = "删除", notes = "修改:参数示例：{id:'123'}")
    @PostMapping("/delete")
    @ResponseBody
    public ResponseModel update(@RequestBody Map<String, String> map) {
        Logistics logistics = logisticsServiceImpl.deleteLogisticsById(Long.parseLong(map.get("id")));
        if (logistics == null) {
            return ResponseModel.fail("删除失败");
        }
        return ResponseModel.success("删除成功");
    }
}
