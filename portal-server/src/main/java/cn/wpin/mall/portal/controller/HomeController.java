package cn.wpin.mall.portal.controller;


import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.content.entity.Subject;
import cn.wpin.mall.portal.entity.HomeContentResult;
import cn.wpin.mall.portal.service.HomeService;
import cn.wpin.mall.product.entity.Product;
import cn.wpin.mall.product.entity.ProductCategory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页内容管理Controller
 *
 * @author wangpin
 */
@RestController
@Api(tags = "首页内容管理")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ApiOperation("首页内容页信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public CommonResult<HomeContentResult> content() {
        HomeContentResult contentResult = homeService.content();
        return CommonResult.success(contentResult);
    }

    @ApiOperation("分页获取推荐商品")
    @RequestMapping(value = "/recommendProductList", method = RequestMethod.GET)
    public CommonResult<List<Product>> recommendProductList(@RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return CommonResult.success(homeService.recommendProductList(pageSize, pageNum).getList());
    }

    @ApiOperation("获取首页商品分类")
    @RequestMapping(value = "/productCateList/{parentId}", method = RequestMethod.GET)
    public CommonResult<List<ProductCategory>> getProductCateList(@PathVariable Long parentId) {
        return CommonResult.success(homeService.getProductCateList(parentId).getList());
    }

    @ApiOperation("根据分类获取专题")
    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
    public CommonResult<List<Subject>> getSubjectList(@RequestParam(required = false) Long cateId,
                                                      @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return CommonResult.success(homeService.getSubjectList(cateId, pageSize, pageNum).getList());
    }
}
