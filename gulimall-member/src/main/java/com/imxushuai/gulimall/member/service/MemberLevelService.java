package com.imxushuai.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

