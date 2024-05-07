package com.itheima.reggie.mapper;

import com.itheima.reggie.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author duch
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2024-05-08 00:02:00
* @Entity com.itheima.reggie.entity.Dish
*/

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




