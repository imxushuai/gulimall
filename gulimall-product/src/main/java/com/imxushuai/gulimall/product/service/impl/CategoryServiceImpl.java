package com.imxushuai.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imxushuai.common.utils.PageUtils;
import com.imxushuai.common.utils.Query;

import com.imxushuai.gulimall.product.dao.CategoryDao;
import com.imxushuai.gulimall.product.entity.CategoryEntity;
import com.imxushuai.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 查询所有的分类
        List<CategoryEntity> categoryList = baseMapper.selectList(null);

        // 递归分类
        List<CategoryEntity> level1List = categoryList.stream()
                .filter(category -> category.getParentCid() == 0)
                .peek(category -> category.setChildren(getChildrenList(category, categoryList)))
                .sorted(Comparator.comparingInt(c -> (c.getSort() == null ? 0 : c.getSort())))
                .collect(Collectors.toList());

        return level1List;
    }

    @Override
    public void removeBatch(List<Long> asList) {
        // TODO 检查菜单, 若被其他分类引用则不允许删除
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long categoryId) {
        List<Long> path = new ArrayList<>();
        List<Long> parentPath = findParentPath(categoryId, path);

        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }

    private List<Long> findParentPath(Long categoryId, List<Long> path) {
        path.add(categoryId);
        CategoryEntity category = this.getById(categoryId);
        if (category.getParentCid() != 0) {
            findParentPath(category.getParentCid(), path);
        }
        return path;
    }

    /**
     * 查询分类的所有子分类
     *
     * @param category     父分类
     * @param categoryList 所有分类列表
     */
    private List<CategoryEntity> getChildrenList(CategoryEntity root, List<CategoryEntity> categoryList) {

        List<CategoryEntity> childrenList = categoryList.stream()
                .filter(category -> category.getParentCid().equals(root.getCatId()))
                .peek(category -> category.setChildren(getChildrenList(category, categoryList)))
                .sorted(Comparator.comparingInt(c -> (c.getSort() == null ? 0 : c.getSort())))
                .collect(Collectors.toList());

        return childrenList;
    }

}