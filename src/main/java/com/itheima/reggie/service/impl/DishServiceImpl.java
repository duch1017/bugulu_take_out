package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.mapper.DishMapper;
import org.springframework.stereotype.Service;

/**
* @author duch
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2024-05-08 00:02:00
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

}




