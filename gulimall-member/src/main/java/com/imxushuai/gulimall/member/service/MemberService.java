package com.imxushuai.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.gulimall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

