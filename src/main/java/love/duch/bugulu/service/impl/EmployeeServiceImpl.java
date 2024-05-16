package love.duch.bugulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import love.duch.bugulu.entity.Employee;
import love.duch.bugulu.mapper.EmployeeMapper;
import love.duch.bugulu.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
