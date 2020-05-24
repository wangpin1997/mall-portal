package cn.wpin.mall.portal.controller;


import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.order.entity.CartItem;
import cn.wpin.mall.portal.service.CartItemService;
import cn.wpin.mall.product.entity.CartProduct;
import cn.wpin.mall.sale.entity.CartPromotionItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车管理Controller
 *
 * @author wangpin
 */
@RestController
@Api(tags = "购物车管理")
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody CartItem cartItem) {
        return cartItemService.add(cartItem);
    }

    @ApiOperation("获取某个会员的购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<CartItem>> list() {
        return cartItemService.list();
    }

    @ApiOperation("获取某个会员的购物车列表,包括促销信息")
    @RequestMapping(value = "/list/promotion", method = RequestMethod.GET)
    public CommonResult<List<CartPromotionItem>> listPromotion() {
        return CommonResult.success(cartItemService.listPromotion());
    }

    @ApiOperation("修改购物车中某个商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    public CommonResult updateQuantity(@RequestParam Long id,
                                       @RequestParam Integer quantity) {
        return cartItemService.updateQuantity(id, quantity);
    }

    @ApiOperation("获取购物车中某个商品的规格,用于重选规格")
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    public CommonResult<CartProduct> getCartProduct(@PathVariable Long productId) {
        CartProduct cartProduct = cartItemService.getCartProduct(productId);
        return CommonResult.success(cartProduct);
    }

    @ApiOperation("修改购物车中商品的规格")
    @RequestMapping(value = "/update/attr", method = RequestMethod.POST)
    public CommonResult updateAttr(@RequestBody CartItem cartItem) {
        return cartItemService.updateAttr(cartItem);
    }

    @ApiOperation("删除购物车中的某个商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        return cartItemService.delete(ids);
    }

    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public CommonResult clear() {
        return cartItemService.clear();
    }
}
