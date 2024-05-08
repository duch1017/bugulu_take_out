package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.mapper.DishMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duch
 * @description 针对表【dish(菜品管理)】的数据库操作Service实现
 * @createDate 2024-05-08 00:02:00
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
        implements DishService {
    @Resource(name = "dishFlavorServiceImpl")
    private DishFlavorService dishFlavorService;
    @Resource(name = "dishServiceImpl")
    private DishService dishService;

    /**
     * 新增菜品，同时保存对应口味信息
     *
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithFlaavor(DishDto dishDto) {
        this.save(dishDto);

        Long dishId = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map(item -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

}




