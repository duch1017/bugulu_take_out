package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.itheima.reggie.common.Result;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    //    @Autowired
    @Resource(name = "categoryServiceImpl")
    private CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return Result.success("新增分类成功");
    }

}
