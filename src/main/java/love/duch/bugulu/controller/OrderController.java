package love.duch.bugulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import love.duch.bugulu.common.Result;
import love.duch.bugulu.entity.Orders;
import love.duch.bugulu.service.OrdersService;
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
}
