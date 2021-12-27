package com.imxushuai.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

