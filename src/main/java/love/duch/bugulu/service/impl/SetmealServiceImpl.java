package love.duch.bugulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.duch.bugulu.common.CustomException;
import love.duch.bugulu.dto.SetmealDto;
import love.duch.bugulu.entity.Setmeal;
import love.duch.bugulu.entity.SetmealDish;
import love.duch.bugulu.service.SetmealDishService;
import love.duch.bugulu.service.SetmealService;
import love.duch.bugulu.mapper.SetmealMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duch
 * @description 针对表【setmeal(套餐)】的数据库操作Service实现
 * @createDate 2024-05-08 00:03:52
 */
@Slf4j
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
        implements SetmealService {

    @Resource(name = "setmealDishServiceImpl")
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时保存套餐和菜品对应信息
     *
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        List<SetmealDish> dishList = setmealDto.getSetmealDishes();
        dishList = dishList.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(dishList);
    }

    /**
     * 删除套餐，同时删除套餐和菜品对应信息
     *
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids)
                .eq(Setmeal::getStatus, 1);
        int count = this.count(queryWrapper);
        if (count > 0) {
            throw new CustomException("套餐正在售卖中，不能删除");
        }
        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }

    @Override
    public void updateStatus(Integer status, List<Long> ids) {
        Setmeal setmeal = new Setmeal();
        setmeal.setStatus(status);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        this.update(setmeal, queryWrapper);
    }

    @Override
    @Transactional
    public void updateWithDish(SetmealDto setmealDto) {
        this.updateById(setmealDto);
        Long setmealId = setmealDto.getId();
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmealId);


        setmealDishService.remove(queryWrapper);

        List<SetmealDish> dishList = setmealDto.getSetmealDishes();
        dishList = dishList.stream()
                .peek((item) -> item.setSetmealId(setmealDto.getId()))
                .collect(Collectors.toList());

        setmealDishService.saveOrUpdateBatch(dishList);

    }
}




