package com.itheima.reggie.controller;

import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Resource(name = "dishServiceImpl")
    private DishService dishService;
    @Resource(name = "dishFlavorServiceImpl")
    private DishFlavorService dishFlavorService;
}
