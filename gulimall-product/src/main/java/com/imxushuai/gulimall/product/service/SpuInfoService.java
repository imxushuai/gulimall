package com.imxushuai.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.product.entity.SpuInfoEntity;
import com.imxushuai.gulimall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);


    PageUtils queryPageByCondition(Map<String, Object> params);


    SpuInfoEntity getSpuInfoBySkuId(Long skuId);

    void up(Long spuId);
}

