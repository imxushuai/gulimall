package com.imxushuai.gulimall.order.dao;

import com.imxushuai.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 *
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
