package cn.wpin.mall.portal.service;

import cn.wpin.mall.client.order.OrderClient;
import cn.wpin.mall.client.product.ProductClient;
import cn.wpin.mall.product.dto.ProductParam;
import cn.wpin.mall.product.dto.ProductResult;
import com.google.common.collect.Lists;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购买商品，测试分布式事务
 */
@Service
public class ProductService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderClient orderClient;

    /**
     * 购买商品，库存减一，订单生成，测试分布式事务
     *
     * @return
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    public Object buyProduct(Long productId) {
        ProductResult result = productClient.getUpdateInfo(productId);
        ProductParam productParam = new ProductParam();
        productParam.setStock(productParam.getStock() - 1);
        BeanUtils.copyProperties(result, productId);
        //减库存
        int i = productClient.update(productId, productParam);
        if (i == 1) {
            throw new RuntimeException("打断一下，测试分布式事务");
        }
        //再删除一个订单
        orderClient.delete(Lists.newArrayList(25L));
        return "success";
    }
}
