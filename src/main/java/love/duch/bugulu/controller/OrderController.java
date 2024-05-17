package love.duch.bugulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import love.duch.bugulu.common.Result;
import love.duch.bugulu.entity.OrderDetail;
import love.duch.bugulu.entity.Orders;
import love.duch.bugulu.service.OrderDetailService;
import love.duch.bugulu.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource(name = "ordersServiceImpl")
    private OrdersService ordersService;
@Autowired
private OrderDetailService orderDetailService;
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
    public Result<Page<Orders>> page(Integer page, Integer pageSize) {
        log.info("page = {},pageSize = {}", page, pageSize);

        //分页构造器
        Page<Orders> pageInfo = new Page<Orders>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        //进行分页查询
        ordersService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);

    }

    @GetMapping("/page")
    public Result<Page<Orders>>orderPage(Integer page,Integer pageSize){
        log.info("page = {},pageSize = {}", page, pageSize);

        //分页构造器
        Page<Orders> pageInfo = new Page<Orders>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        //进行分页查询
        ordersService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);
    }

    @PutMapping
    public Result<String> updateStatus(@RequestBody Orders order){
        log.info("order:{}",order);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getId,order.getId());
        ordersService.update(order,queryWrapper);

        return Result.success("状态更新成功");
    }

    //TODO 减少
    @PostMapping("/again")
    public Result<List<OrderDetail>> again(@RequestBody Orders order){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getId,order.getId());
        Orders orders = ordersService.getOne(queryWrapper);
        String orderId = orders.getNumber();
        LambdaQueryWrapper<OrderDetail>detailLambdaQueryWrapper=new LambdaQueryWrapper<>();
        detailLambdaQueryWrapper.eq(OrderDetail::getOrderId,orderId);
        List<OrderDetail> list = orderDetailService.list(detailLambdaQueryWrapper);

        return Result.success(list);
    }
}
