package love.duch.bugulu.controller;

import love.duch.bugulu.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Resource(name = "orderDetailServiceImpl")
    private OrderDetailService orderDetailService;


}
