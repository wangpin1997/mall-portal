package cn.wpin.mall.portal.service;

import cn.wpin.mall.client.sale.MemberCouponClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.sale.entity.CartPromotionItem;
import cn.wpin.mall.sale.entity.CouponHistory;
import cn.wpin.mall.sale.entity.CouponHistoryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员优惠券管理Service实现类
 *
 * @author wangpin
 */
@Service
public class MemberCouponService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCouponClient memberCouponClient;


    public CommonResult add(Long couponId) {
        return memberCouponClient.add(couponId, memberService.getCurrentMember());
    }


    public CommonResult<List<CouponHistory>> list(Integer useStatus) {
        return memberCouponClient.list(useStatus, memberService.getCurrentMember());
    }

    public CommonResult<List<CouponHistoryDetail>> listCart(List<CartPromotionItem> cartItemList, Integer type) {
        return memberCouponClient.listCart(cartItemList, type, memberService.getCurrentMember());
    }


}
