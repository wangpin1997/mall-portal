package cn.wpin.mall.portal.service;

import cn.wpin.mall.client.mongo.MemberCollectionClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.mongo.entity.MemberProductCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员收藏Service实现类
 * @author wangpin
 */
@Service
public class MemberCollectionService {
    @Autowired
    private MemberCollectionClient memberCollectionClient;

    public CommonResult addProduct(MemberProductCollection productCollection) {
        return memberCollectionClient.addProduct(productCollection);
    }

    public CommonResult deleteProduct(Long memberId, Long productId) {
        return memberCollectionClient.deleteProduct(memberId,productId);
    }

    public CommonResult<List<MemberProductCollection>> listProduct(Long memberId) {
        return memberCollectionClient.listProduct(memberId);
    }
}
