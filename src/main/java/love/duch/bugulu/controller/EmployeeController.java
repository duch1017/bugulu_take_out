package love.duch.bugulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import love.duch.bugulu.common.Result;
import love.duch.bugulu.entity.Employee;
import love.duch.bugulu.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

@Slf4j
@RestController
@RequestMapping("/employee")
@Api(tags = "员工管理相关接口")
public class EmployeeController {
    /*
    @Autowired
    @Qualifier("employeeServiceImpl")
    */
    @Resource(name = "employeeServiceImpl")
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //md5加密password
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //数据库查询username
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //判断是否查到数据
        if (emp == null) {
            return Result.error("无此账号");
        }

        //比对密码
        if (!emp.getPassword().equals(password)) {
            return Result.error("密码错误");
        }

        //查看员工状态
        if (emp.getStatus() == 0) {
            return Result.error("账号禁用");
        }

        //登录成功，将id放入session
        request.getSession().setAttribute("employee", emp.getId());

        return Result.success(emp);
    }

    /**
     * 员工退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "员工退出")
    public Result<String> logout(HttpServletRequest request) {
        //清理session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return Result.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增员工")
    public Result<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工：{}", employee);
        //设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        //获得当前登录用户的ID
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return Result.success("新赠员工成功");
    }

    /**
     * 员工信息分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "员工信息分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true),
            @ApiImplicitParam(name = "name", value = "套餐名称", required = false)
    })
    public Result<Page> page(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {},name = {}", page, pageSize, name);

//        log.info("线程id为：{}", Thread.currentThread().getId());

        //分页构造器
        Page<Employee> pageInfo = new Page<Employee>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);

        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, queryWrapper);

        return Result.success(pageInfo);
    }

    /**
     * 修改员工数据
     * @param request
     * @param employee
     * @return
     */
    @PutMapping@ApiOperation(value = "修改员工数据")
    public Result<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());

        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(empId);

        employeeService.updateById(employee);
        return Result.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询员工")
    public Result<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询员工");
        Employee emp = employeeService.getById(id);
        if (emp != null) {
            return Result.success(emp);
        }
        return Result.error("没有查询到对应员工信息");
    }
}
