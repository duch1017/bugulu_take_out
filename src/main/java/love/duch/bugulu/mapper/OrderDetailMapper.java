package love.duch.bugulu.mapper;

import love.duch.bugulu.entity.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author duch
* @description 针对表【order_detail(订单明细表)】的数据库操作Mapper
* @createDate 2024-05-16 00:57:10
* @Entity love.duch.bugulu.entity.OrderDetail
*/
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}




