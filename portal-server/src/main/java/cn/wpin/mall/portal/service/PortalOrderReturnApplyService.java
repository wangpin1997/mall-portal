package cn.wpin.mall.portal.service;


import cn.wpin.mall.client.order.PortalOrderReturnApplyClient;
import cn.wpin.mall.common.entity.CommonResult;
import cn.wpin.mall.order.dto.OrderReturnApplyParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单退货管理Service实现类
 *
 * @author wangpin
 */
@Service
public class PortalOrderReturnApplyService {
    @Autowired
    private PortalOrderReturnApplyClient portalOrderReturnApplyClient;

    public CommonResult create(OrderReturnApplyParam returnApply) {
        return portalOrderReturnApplyClient.create(returnApply);
    }
}
