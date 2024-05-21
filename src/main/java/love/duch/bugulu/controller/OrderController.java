package love.duch.bugulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import love.duch.bugulu.common.Result;
import love.duch.bugulu.entity.OrderDetail;
import love.duch.bugulu.entity.Orders;
import love.duch.bugulu.service.OrderDetailService;
import love.duch.bugulu.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "订单相关接口")
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
    @ApiOperation(value = "用户下单")
    public Result<String> submit(@RequestBody Orders orders) {
        log.info("订单数据：{}", orders);
        ordersService.submit(orders);
        return Result.success("下单成功");
    }

    /**
     * 用户端查看历史订单
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    @ApiOperation(value = "用户端查看历史订单")
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

    /**
     * 查看订单
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "查看订单")
    public Result<Page<Orders>> orderPage(Integer page, Integer pageSize) {
        log.info("page = {},pageSize = {}", page, pageSize);

        //分页构造器
        Page<Orders> pageInfo = new Page<>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        //进行分页查询
        ordersService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 更新订单状态
     *
     * @param order
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新订单状态")
    public Result<String> updateStatus(@RequestBody Orders order) {
        log.info("order:{}", order);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getId, order.getId());
        ordersService.update(order, queryWrapper);

        return Result.success("状态更新成功");
    }

    //TODO 再来一单
    @PostMapping("/again")
    public Result<List<OrderDetail>> again(@RequestBody Orders order) {


        return null;
    }
}
