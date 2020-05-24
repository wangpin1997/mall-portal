package cn.wpin.mall.portal.service;


import cn.wpin.mall.client.order.CartItemClient;
import cn.wpin.mall.client.product.PortalProductClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.order.entity.CartItem;
import cn.wpin.mall.product.entity.CartProduct;
import cn.wpin.mall.sale.entity.CartPromotionItem;
import cn.wpin.mall.user.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车管理Service实现类
 *
 * @author wangpin
 */
@Service
public class CartItemService {

    @Autowired
    private CartItemClient cartItemClient;
    @Autowired
    private PortalProductClient portalProductClient;
    @Autowired
    private MemberService memberService;

    public CommonResult add(CartItem cartItem) {
        Member member=memberService.getCurrentMember();
        return cartItemClient.add(cartItem,member.getId(),member.getNickname() );
    }


    public CommonResult<List<CartItem>> list() {
        return cartItemClient.list(memberService.getCurrentMember());
    }

    public List<CartPromotionItem> listPromotion() {
        return cartItemClient.listPromotion(memberService.getCurrentMember().getId());
    }

    public CommonResult updateQuantity(Long id, Integer quantity) {
        return cartItemClient.updateQuantity(id, quantity, memberService.getCurrentMember());
    }

    public CommonResult delete(List<Long> ids) {
        return cartItemClient.delete(ids, memberService.getCurrentMember());
    }

    public CartProduct getCartProduct(Long productId) {
        return portalProductClient.getCartProduct(productId);
    }

    public CommonResult updateAttr(CartItem cartItem) {
        Member member=memberService.getCurrentMember();
        return cartItemClient.updateAttr(cartItem, member.getId(),member.getNickname());
    }

    public CommonResult clear() {
        return cartItemClient.clear(memberService.getCurrentMember());
    }
}
