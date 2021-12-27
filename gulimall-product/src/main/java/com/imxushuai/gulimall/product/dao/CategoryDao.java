package com.imxushuai.gulimall.product.dao;

import com.imxushuai.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
