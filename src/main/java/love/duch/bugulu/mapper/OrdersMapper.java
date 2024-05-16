package love.duch.bugulu.mapper;

import love.duch.bugulu.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author duch
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2024-05-16 00:57:10
* @Entity love.duch.bugulu.entity.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




