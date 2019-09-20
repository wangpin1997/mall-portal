package cn.wpin.mall.portal.service;

import cn.wpin.mall.client.mongo.MemberAttentionClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.mongo.entity.MemberBrandAttention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员关注Service实现类
 *
 * @author wangpin
 */
@Service
public class MemberAttentionService {
    @Autowired
    private MemberAttentionClient memberAttentionClient;

    public CommonResult add(MemberBrandAttention memberBrandAttention) {
        return memberAttentionClient.add(memberBrandAttention);
    }

    public CommonResult delete(Long memberId, Long brandId) {
        return memberAttentionClient.delete(memberId, brandId);
    }

    public CommonResult<List<MemberBrandAttention>> list(Long memberId) {
        return memberAttentionClient.list(memberId);
    }
}
