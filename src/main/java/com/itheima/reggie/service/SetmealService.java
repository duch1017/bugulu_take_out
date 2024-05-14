package com.itheima.reggie.service;

import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 删除套餐，同时删除套餐和菜品对应信息
     * @param ids
     */
    void removeWithDish(List<Long> ids);
    void updateStatus(Integer status,List<Long> ids);

    void updateWithDish(SetmealDto setmealDto);
}

