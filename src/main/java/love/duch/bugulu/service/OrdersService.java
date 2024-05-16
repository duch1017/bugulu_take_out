package love.duch.bugulu.service;

import love.duch.bugulu.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author duch
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2024-05-16 00:57:10
*/
public interface OrdersService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    void submit(Orders orders);
}
