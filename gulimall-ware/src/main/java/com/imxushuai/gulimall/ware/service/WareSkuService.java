package com.imxushuai.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.to.es.SkuHasStockVo;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * εεεΊε­
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);


    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
}

