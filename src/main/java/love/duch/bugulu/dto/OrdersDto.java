package love.duch.bugulu.dto;

import love.duch.bugulu.entity.OrderDetail;
import love.duch.bugulu.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
