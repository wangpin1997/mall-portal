package cn.wpin.mall.portal.service;


import cn.wpin.mall.client.order.PortalOrderClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.order.entity.ConfirmOrderResult;
import cn.wpin.mall.order.entity.Order;
import cn.wpin.mall.order.entity.OrderParam;
import cn.wpin.mall.portal.component.CancelOrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 前台订单管理Service
 *
 * @author wangpin
 */
@Service
public class PortalOrderService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.orderId}")
    private String REDIS_KEY_PREFIX_ORDER_ID;

    @Autowired
    private PortalOrderClient portalOrderClient;

    @Autowired
    private CancelOrderSender cancelOrderSender;


    public CommonResult<ConfirmOrderResult> generateConfirmOrder() {
        return portalOrderClient.generateConfirmOrder(memberService.getCurrentMember());
    }

    public CommonResult generateOrder(OrderParam orderParam) {
        Order order = new Order();
        order.setSourceType(1);
        order.setPayType(orderParam.getPayType());
        orderParam.setMember(memberService.getCurrentMember());
        return CommonResult.success(portalOrderClient.generateOrder(orderParam, generateOrderSn(order)));
    }

    public CommonResult paySuccess(Long orderId) {
        return CommonResult.success(portalOrderClient.paySuccess(orderId));
    }

    public CommonResult cancelTimeOutOrder() {
        return CommonResult.success(portalOrderClient.cancelTimeOutOrder());
    }

    public void cancelOrder(Long orderId) {
        portalOrderClient.cancelOrder(orderId);
    }

    public void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间
        long delayTimes = portalOrderClient.sendDelayMessageCancelOrder(orderId);
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

    /**
     * 生成18位订单编号:8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    private String generateOrderSn(Order order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = REDIS_KEY_PREFIX_ORDER_ID + date;
        Long increment = redisService.increment(key, 1);
        sb.append(date);
        sb.append(String.format("%02d", order.getSourceType()));
        sb.append(String.format("%02d", order.getPayType()));
        String incrementStr = increment.toString();
        if (incrementStr.length() <= 6) {
            sb.append(String.format("%06d", increment));
        } else {
            sb.append(incrementStr);
        }
        return sb.toString();
    }

}
