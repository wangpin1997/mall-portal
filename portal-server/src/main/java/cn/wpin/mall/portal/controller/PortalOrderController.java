package cn.wpin.mall.portal.controller;


import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.portal.entity.ConfirmOrderResult;
import cn.wpin.mall.portal.entity.OrderParam;
import cn.wpin.mall.portal.service.PortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理Controller
 * @author wangpin 
 */
@RestController
@Api(tags = "订单管理")
@RequestMapping("/order")
public class PortalOrderController {
    @Autowired
    private PortalOrderService portalOrderService;

    @ApiOperation("根据购物车信息生成确认单信息")
    @RequestMapping(value = "/generateConfirmOrder",method = RequestMethod.POST)
    public CommonResult<ConfirmOrderResult> generateConfirmOrder(){
        return portalOrderService.generateConfirmOrder();
    }

    @ApiOperation("根据购物车信息生成订单")
    @RequestMapping(value = "/generateOrder",method = RequestMethod.POST)
    public Object generateOrder(@RequestBody OrderParam orderParam){
        return portalOrderService.generateOrder(orderParam);
    }
    @ApiOperation("支付成功的回调")
    @RequestMapping(value = "/paySuccess",method = RequestMethod.POST)
    public Object paySuccess(@RequestParam Long orderId){
        return portalOrderService.paySuccess(orderId);
    }

    @ApiOperation("自动取消超时订单")
    @RequestMapping(value = "/cancelTimeOutOrder",method = RequestMethod.POST)
    public Object cancelTimeOutOrder(){
        return portalOrderService.cancelTimeOutOrder();
    }

    @ApiOperation("取消单个超时订单")
    @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
    public CommonResult cancelOrder(Long orderId){
        portalOrderService.sendDelayMessageCancelOrder(orderId);
        return CommonResult.success(null);
    }
}
