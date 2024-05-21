package love.duch.bugulu.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import love.duch.bugulu.common.Result;
import love.duch.bugulu.dto.SetmealDto;
import love.duch.bugulu.entity.Category;
import love.duch.bugulu.entity.Setmeal;
import love.duch.bugulu.entity.SetmealDish;
import love.duch.bugulu.service.CategoryService;
import love.duch.bugulu.service.SetmealDishService;
import love.duch.bugulu.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐管理
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
@Api(tags = "套餐相关接口")
public class SetmealController {

    @Resource(name = "setmealServiceImpl")
    private SetmealService setmealService;
    @Resource(name = "categoryServiceImpl")
    private CategoryService categoryService;
    @Resource(name = "setmealDishServiceImpl")
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增套餐")
    public Result<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("套餐信息: {}", setmealDto);
        setmealService.saveWithDish(setmealDto);

        return Result.success("新增套餐成功");
    }

    /**
     * 套餐分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "套餐分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true),
            @ApiImplicitParam(name = "name", value = "套餐名称", required = false)
    })
    public Result<Page<SetmealDto>> list(int page, int pageSize, String name) {
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, queryWrapper);
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return Result.success(dtoPage);
    }

    /**
     * 删除套餐
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "删除套餐")
    public Result<String> delete(@RequestParam List<Long> ids) {
        log.info("ids:{}", ids);
        setmealService.removeWithDish(ids);
        return Result.success("套餐数据删除成功");
    }

    /**
     * 修改套餐状态
     *
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "修改套餐状态")
    public Result<String> updateStatus(@PathVariable Integer status, @RequestParam List<Long> ids) {
        log.info("status:{},ids:{}", status, ids);
        setmealService.updateStatus(status, ids);
        return Result.success("修改套餐状态成功");
    }

    /**
     * 修改套餐回显
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "修改套餐回显")
    public Result<SetmealDto> page(@PathVariable Long id) {
        log.info("id:{}", id);
        Setmeal setmeal = setmealService.getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, id);
        List<SetmealDish> dishList = setmealDishService.list(queryWrapper);
        setmealDto.setSetmealDishes(dishList);
        return Result.success(setmealDto);
    }

    /**
     * 更新套餐
     *
     * @param setmealDto
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新套餐")
    public Result<String> updateSetmeal(@RequestBody SetmealDto setmealDto) {
        log.info("setmealDto:{}", setmealDto);
        log.info("更新套餐：{}", setmealDto.getName());
        setmealService.updateWithDish(setmealDto);
        return Result.success("更新套餐成功");
    }

    /**
     * 根据条件查询菜品
     *
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "根据条件查询菜品")
    public Result<List<Setmeal>> list(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId())
                .eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus())
                .orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(queryWrapper);
        return Result.success(list);
    }

}
