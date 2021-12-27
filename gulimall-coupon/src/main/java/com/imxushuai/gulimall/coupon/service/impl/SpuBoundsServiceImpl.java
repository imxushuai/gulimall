package com.imxushuai.gulimall.coupon.service.impl;

import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.common.utils.Query;
import com.imxushuai.gulimall.coupon.dao.SpuBoundsDao;
import com.imxushuai.gulimall.coupon.entity.SpuBoundsEntity;
import com.imxushuai.gulimall.coupon.service.SpuBoundsService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity> implements SpuBoundsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuBoundsEntity> page = this.page(
                new Query<SpuBoundsEntity>().getPage(params),
                new QueryWrapper<SpuBoundsEntity>()
        );

        return new PageUtils(page);
    }

}