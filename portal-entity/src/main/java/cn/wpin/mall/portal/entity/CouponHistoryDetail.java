package cn.wpin.mall.portal.entity;


import cn.wpin.mall.sale.entity.Coupon;
import cn.wpin.mall.sale.entity.CouponHistory;
import cn.wpin.mall.sale.entity.CouponProductCategoryRelation;
import cn.wpin.mall.sale.entity.CouponProductRelation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 优惠券领取历史详情封装
 *
 * @author wangpin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CouponHistoryDetail extends CouponHistory {
    /**
     * 相关优惠券信息
     */
    private Coupon coupon;
    /**
     * 优惠券关联商品
     */
    private List<CouponProductRelation> productRelationList;
    /**
     * 优惠券关联商品分类
     */
    private List<CouponProductCategoryRelation> categoryRelationList;

}
