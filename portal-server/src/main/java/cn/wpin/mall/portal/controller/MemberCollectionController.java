package cn.wpin.mall.portal.controller;


import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.mongo.entity.MemberProductCollection;
import cn.wpin.mall.portal.service.MemberCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员收藏管理Controller
 *
 * @author wangpin .
 */
@RestController
@Api(tags = "会员收藏管理")
@RequestMapping("/member/collection")
public class MemberCollectionController {

    @Autowired
    private MemberCollectionService memberCollectionService;

    @ApiOperation("添加商品收藏")
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public CommonResult addProduct(@RequestBody MemberProductCollection productCollection) {
        return memberCollectionService.addProduct(productCollection);
    }

    @ApiOperation("删除收藏商品")
    @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
    public CommonResult deleteProduct(Long memberId, Long productId) {
        return memberCollectionService.deleteProduct(memberId, productId);
    }

    @ApiOperation("显示关注列表")
    @RequestMapping(value = "/listProduct/{memberId}", method = RequestMethod.GET)
    public CommonResult<List<MemberProductCollection>> listProduct(@PathVariable Long memberId) {
        return memberCollectionService.listProduct(memberId);
    }
}
