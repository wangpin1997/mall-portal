package cn.wpin.mall.portal.controller;


import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.portal.entity.CartPromotionItem;
import cn.wpin.mall.portal.entity.CouponHistoryDetail;
import cn.wpin.mall.portal.service.CartItemService;
import cn.wpin.mall.portal.service.MemberCouponService;
import cn.wpin.mall.sale.entity.CouponHistory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户优惠券管理Controller
 *
 * @author wangpin
 */
@RestController
@Api(tags = "用户优惠券管理")
@RequestMapping("/member/coupon")
public class MemberCouponController {

    @Autowired
    private MemberCouponService memberCouponService;
    @Autowired
    private CartItemService cartItemService;


    @ApiOperation("领取指定优惠券")
    @RequestMapping(value = "/add/{couponId}", method = RequestMethod.POST)
    public CommonResult add(@PathVariable Long couponId) {
        return memberCouponService.add(couponId);
    }

    @ApiOperation("获取用户优惠券列表")
    @ApiImplicitParam(name = "useStatus", value = "优惠券筛选类型:0->未使用；1->已使用；2->已过期",
            allowableValues = "0,1,2", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<CouponHistory>> list(@RequestParam(value = "useStatus", required = false) Integer useStatus) {
        return memberCouponService.list(useStatus);
    }

    @ApiOperation("获取登录会员购物车的相关优惠券")
    @ApiImplicitParam(name = "type", value = "使用可用:0->不可用；1->可用",
            defaultValue = "1", allowableValues = "0,1", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/list/cart/{type}", method = RequestMethod.GET)
    public CommonResult<List<CouponHistoryDetail>> listCart(@PathVariable Integer type) {
        List<CartPromotionItem> cartPromotionItemList = cartItemService.listPromotion();
        return memberCouponService.listCart(cartPromotionItemList, type);
    }
}
