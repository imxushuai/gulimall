package com.imxushuai.gulimall.product.service.impl;

import com.imxushuai.gulimall.product.vo.Catalog3Vo;
import com.imxushuai.gulimall.product.vo.Catelog2Vo;
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

    @Override
    public List<CategoryEntity> getLevel1Categories() {
        return baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("cat_level", 1));
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatelogJson() {
        List<CategoryEntity> entityList = baseMapper.selectList(null);
        // 查询所有一级分类
        List<CategoryEntity> level1 = getCategoryEntities(entityList, 0L);
        Map<String, List<Catelog2Vo>> parent_cid = level1.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 拿到每一个一级分类 然后查询他们的二级分类
            List<CategoryEntity> entities = getCategoryEntities(entityList, v.getCatId());
            List<Catelog2Vo> catelog2Vos = null;
            if (entities != null) {
                catelog2Vos = entities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), l2.getName(), l2.getCatId().toString(), null);
                    // 找当前二级分类的三级分类
                    List<CategoryEntity> level3 = getCategoryEntities(entityList, l2.getCatId());
                    // 三级分类有数据的情况下
                    if (level3 != null) {
                        List<Catalog3Vo> catalog3Vos = level3.stream().map(l3 -> new Catalog3Vo(l3.getCatId().toString(), l3.getName(), l2.getCatId().toString())).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(catalog3Vos);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        return parent_cid;
    }

    /**
     * 第一次查询的所有 CategoryEntity 然后根据 parent_cid去这里找
     */
    private List<CategoryEntity> getCategoryEntities(List<CategoryEntity> entityList, Long parent_cid) {

        return entityList.stream().filter(item -> item.getParentCid().equals(parent_cid)).collect(Collectors.toList());
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