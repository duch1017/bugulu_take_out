package com.itheima.reggie.service;

import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duch
 * @description 针对表【dish(菜品管理)】的数据库操作Service
 * @createDate 2024-05-08 00:02:00
 */
public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);
    DishDto getByIdWithFlavor(Long id);


    void updateWithFlavor(DishDto dishDto);

    void updateStatus(Integer status, List<Long> ids);

    void removeByIdList(List<Long> ids);
}
