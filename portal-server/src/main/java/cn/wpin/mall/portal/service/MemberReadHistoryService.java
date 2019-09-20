package cn.wpin.mall.portal.service;


import cn.wpin.mall.client.mongo.MemberReadHistoryClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.mongo.entity.MemberReadHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员浏览记录管理Service实现类
 *
 * @author wangpin
 */
@Service
public class MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryClient memberReadHistoryClient;

    public CommonResult create(MemberReadHistory memberReadHistory) {
        return memberReadHistoryClient.create(memberReadHistory);
    }

    public CommonResult delete(List<String> ids) {
        return memberReadHistoryClient.delete(ids);
    }

    public CommonResult<List<MemberReadHistory>> list(Long memberId) {
        return memberReadHistoryClient.list(memberId);
    }
}
