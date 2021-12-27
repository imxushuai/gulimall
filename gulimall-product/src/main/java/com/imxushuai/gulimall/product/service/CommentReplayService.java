package com.imxushuai.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

