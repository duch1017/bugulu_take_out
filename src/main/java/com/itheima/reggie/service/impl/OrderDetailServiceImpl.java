package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.OrderDetail;
import com.itheima.reggie.service.OrderDetailService;
import com.itheima.reggie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author duch
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2024-05-16 00:57:10
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




