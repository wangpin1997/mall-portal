package cn.wpin.mall.portal.controller;


import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.mongo.entity.MemberBrandAttention;
import cn.wpin.mall.portal.service.MemberAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员关注品牌管理Controller
 *
 * @author wangpin
 */
@RestController
@Api(tags = "会员关注品牌管理")
@RequestMapping("/member/attention")
public class MemberAttentionController {

    @Autowired
    private MemberAttentionService memberAttentionService;

    @ApiOperation("添加品牌关注")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody MemberBrandAttention memberBrandAttention) {
        return memberAttentionService.add(memberBrandAttention);
    }

    @ApiOperation("取消关注")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(Long memberId, Long brandId) {
        return memberAttentionService.delete(memberId, brandId);
    }

    @ApiOperation("显示关注列表")
    @RequestMapping(value = "/list/{memberId}", method = RequestMethod.GET)
    public CommonResult<List<MemberBrandAttention>> list(@PathVariable Long memberId) {
        return memberAttentionService.list(memberId);
    }
}
