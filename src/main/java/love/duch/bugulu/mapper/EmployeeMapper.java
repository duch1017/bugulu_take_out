package love.duch.bugulu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import love.duch.bugulu.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
