package com.imxushuai.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveProductAttr(List<ProductAttrValueEntity> collect);


    List<ProductAttrValueEntity> baseAttrlistforspu(Long spuId);


    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);


    List<ProductAttrValueEntity> baseAttrListForSpu(Long spuId);
}

