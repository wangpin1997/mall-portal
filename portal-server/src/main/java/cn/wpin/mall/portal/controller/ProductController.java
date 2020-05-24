package cn.wpin.mall.portal.controller;

import cn.wpin.mall.portal.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购买商品的controller，用来测试下分布式事务
 *
 * @author wangpin
 */
@Api(tags = "测试订单")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("购买商品")
    @RequestMapping(value = "/byProduct", method = RequestMethod.GET)
    public Object buyProdyct(@RequestParam("productId") Long productId) {
        return productService.buyProduct(productId);
    }

}
