package com.itheima.reggie.controller;

import com.itheima.reggie.common.Result;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody DishDto dishDto) {
        log.info("{}", dishDto);
        dishService.saveWithFlaavor(dishDto);
        return Result.success("新增菜品成功");
    }
}
