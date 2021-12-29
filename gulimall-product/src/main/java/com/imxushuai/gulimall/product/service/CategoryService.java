package com.imxushuai.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.product.entity.CategoryEntity;
import com.imxushuai.gulimall.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeBatch(List<Long> asList);

    Long[] findCatelogPath(Long categoryId);

    List<CategoryEntity> getLevel1Categories();

    Map<String, List<Catelog2Vo>> getCatelogJson();
}

