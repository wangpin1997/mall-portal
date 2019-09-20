package cn.wpin.mall.portal.controller;


import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.mongo.entity.MemberReadHistory;
import cn.wpin.mall.portal.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 *
 * @author wangpin
 */
@RestController
@Api(tags = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {

    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody MemberReadHistory memberReadHistory) {
        return memberReadHistoryService.create(memberReadHistory);
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<String> ids) {
        return memberReadHistoryService.delete(ids);
    }

    @ApiOperation("展示浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MemberReadHistory>> list(Long memberId) {
        return memberReadHistoryService.list(memberId);
    }
}
