package com.itheima.reggie.mapper;

import com.itheima.reggie.entity.DishFlavor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author duch
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Mapper
* @createDate 2024-05-08 10:53:06
* @Entity com.itheima.reggie.entity.DishFlavor
*/
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

}




