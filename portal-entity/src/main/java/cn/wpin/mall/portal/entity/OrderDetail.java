package cn.wpin.mall.portal.entity;



import cn.wpin.mall.order.entity.Order;
import cn.wpin.mall.order.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含订单商品信息的订单详情
 * @author wangpin
 */
@Getter
@Setter
public class OrderDetail extends Order {

    private List<OrderItem> orderItemList;

}
