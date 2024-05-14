package com.itheima.reggie.mapper;

import com.itheima.reggie.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author duch
* @description 针对表【shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2024-05-14 21:27:51
* @Entity com.itheima.reggie.entity.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




