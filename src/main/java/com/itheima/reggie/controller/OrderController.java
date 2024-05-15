package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.Result;
import com.itheima.reggie.entity.Orders;
import com.itheima.reggie.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource(name = "ordersServiceImpl")
    private OrdersService ordersService;

    /**
     * 用户下单
     *
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders) {
        log.info("订单数据：{}", orders);
        ordersService.submit(orders);
        return Result.success("下单成功");
    }


    @GetMapping("/userPage")
    public Result<Page> page(@RequestBody Integer page, @RequestBody Integer pageSize) {

        return null;
    }
}
