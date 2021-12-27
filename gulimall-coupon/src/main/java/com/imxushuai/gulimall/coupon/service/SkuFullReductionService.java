package com.imxushuai.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.to.SkuReductionTo;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo reductionTo);


}

