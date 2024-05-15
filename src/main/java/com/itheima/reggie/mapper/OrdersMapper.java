package com.itheima.reggie.mapper;

import com.itheima.reggie.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author duch
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2024-05-16 00:57:10
* @Entity com.itheima.reggie.entity.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




