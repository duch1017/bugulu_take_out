package com.itheima.reggie.service;

import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author duch
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2024-05-08 00:03:52
*/
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时保存套餐和菜品对应信息
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);
}
