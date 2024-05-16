package love.duch.bugulu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.duch.bugulu.common.CustomException;
import love.duch.bugulu.entity.Category;
import love.duch.bugulu.entity.Dish;
import love.duch.bugulu.entity.Setmeal;
import love.duch.bugulu.mapper.CategoryMapper;
import love.duch.bugulu.service.CategoryService;
import love.duch.bugulu.service.DishService;
import love.duch.bugulu.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
//    @Autowired
    @Resource(name = "dishServiceImpl")
    private DishService dishService;
//    @Autowired
    @Resource(name = "setmealServiceImpl")
    private SetmealService setmealService;

    /**
     * 根据id删除分类
     *
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        //查询当前套餐是否关联菜品
        if (count1 > 0) {
            //已经关联菜品
            throw new CustomException("当前套餐关联了菜品，不能删除");
        }

        //查询当前分类是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0) {
            //已经关联套餐
            throw new CustomException("当前套餐关联了套餐，不能删除");
        }

        //正常删除
        super.removeById(id);
    }
}
