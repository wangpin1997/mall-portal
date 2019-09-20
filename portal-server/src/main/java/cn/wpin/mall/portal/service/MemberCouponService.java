package cn.wpin.mall.portal.service;

import cn.wpin.mall.client.sale.MemberCouponClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.portal.entity.CartPromotionItem;
import cn.wpin.mall.portal.entity.CouponHistoryDetail;

import cn.wpin.mall.sale.entity.Coupon;
import cn.wpin.mall.sale.entity.CouponHistory;
import cn.wpin.mall.sale.entity.CouponProductCategoryRelation;
import cn.wpin.mall.sale.entity.CouponProductRelation;
import cn.wpin.mall.sale.example.CouponHistoryExample;
import cn.wpin.mall.user.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员优惠券管理Service实现类
 * @author wangpin 
 */
@Service
public class MemberCouponService{

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCouponClient memberCouponClient;


    public CommonResult add(Long couponId) {
        return memberCouponClient.add(couponId, memberService.getCurrentMember());
    }


    public CommonResult<List<CouponHistory>> list(Integer useStatus) {
        return memberCouponClient.list(useStatus,memberService.getCurrentMember());
    }

    public CommonResult<List<CouponHistoryDetail>> listCart(List<CartPromotionItem> cartItemList, Integer type) {
       return memberCouponClient.listCart(cartItemList,type,memberService.getCurrentMember());
    }


}
