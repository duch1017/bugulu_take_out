package love.duch.bugulu.service;

import love.duch.bugulu.dto.DishDto;
import love.duch.bugulu.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

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
