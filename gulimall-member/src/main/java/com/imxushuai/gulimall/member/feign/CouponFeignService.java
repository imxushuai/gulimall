package com.imxushuai.gulimall.member.feign;

import com.imxushuai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {
    @GetMapping("coupon/coupon/member/list")
    public R list();
}
