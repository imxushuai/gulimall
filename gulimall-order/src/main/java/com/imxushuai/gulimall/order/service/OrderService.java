package com.imxushuai.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

