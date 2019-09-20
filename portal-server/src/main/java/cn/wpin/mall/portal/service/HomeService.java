package cn.wpin.mall.portal.service;

import cn.wpin.mall.client.content.SubjectClient;
import cn.wpin.mall.client.product.HomeClient;
import cn.wpin.mall.client.product.ProductCategoryClient;
import cn.wpin.mall.client.product.ProductClient;
import cn.wpin.mall.client.sale.FlashPromotionClient;
import cn.wpin.mall.client.sale.FlashPromotionSessionClient;
import cn.wpin.mall.client.sale.HomeAdvertiseClient;
import cn.wpin.mall.common.entity.CommonPage;
import cn.wpin.mall.content.entity.Subject;
import cn.wpin.mall.portal.entity.HomeContentResult;
import cn.wpin.mall.portal.entity.HomeFlashPromotion;
import cn.wpin.mall.portal.util.DateUtil;
import cn.wpin.mall.product.dto.FlashPromotionProduct;
import cn.wpin.mall.product.dto.ProductQueryParam;
import cn.wpin.mall.product.entity.Product;
import cn.wpin.mall.product.entity.ProductCategory;
import cn.wpin.mall.sale.entity.FlashPromotion;
import cn.wpin.mall.sale.entity.FlashPromotionSession;
import cn.wpin.mall.sale.entity.HomeAdvertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 首页内容管理Service实现类
 *
 * @author wangpin
 */
@Service
public class HomeService {
    @Autowired
    private HomeAdvertiseClient homeAdvertiseClient;
    @Autowired
    private HomeClient homeClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ProductCategoryClient productCategoryClient;

    @Autowired
    private SubjectClient subjectClient;
    @Autowired
    private FlashPromotionClient flashPromotionClient;
    @Autowired
    private FlashPromotionSessionClient flashPromotionSessionClient;


    public HomeContentResult content() {
        HomeContentResult result = new HomeContentResult();
        //获取首页广告
        result.setAdvertiseList(getHomeAdvertiseList());
        //获取推荐品牌
        result.setBrandList(homeClient.getRecommendBrandList(0, 4));
        //获取秒杀信息
        result.setHomeFlashPromotion(getHomeFlashPromotion());
        //获取新品推荐
        result.setNewProductList(homeClient.getNewProductList(0, 4));
        //获取人气推荐
        result.setHotProductList(homeClient.getHotProductList(0, 4));
        //获取推荐专题
        result.setSubjectList(homeClient.getRecommendSubjectList(0, 4));
        return result;
    }

    public CommonPage<Product> recommendProductList(Integer pageSize, Integer pageNum) {
        // TODO: 2019/9/18 暂时默认推荐所有商品
        return productClient.getList(new ProductQueryParam(), pageSize, pageNum);
    }

    public CommonPage<ProductCategory> getProductCateList(Long parentId) {
        return productCategoryClient.getList(parentId, 10, 0);
    }

    public CommonPage<Subject> getSubjectList(Long cateId, Integer pageSize, Integer pageNum) {
        return subjectClient.getList(cateId, pageSize, pageNum);
    }

    private HomeFlashPromotion getHomeFlashPromotion() {
        HomeFlashPromotion homeFlashPromotion = new HomeFlashPromotion();
        //获取当前秒杀活动
        Date now = new Date();
        FlashPromotion flashPromotion = getFlashPromotion(now);
        if (flashPromotion != null) {
            //获取当前秒杀场次
            FlashPromotionSession flashPromotionSession = getFlashPromotionSession(now);
            if (flashPromotionSession != null) {
                homeFlashPromotion.setStartTime(flashPromotionSession.getStartTime());
                homeFlashPromotion.setEndTime(flashPromotionSession.getEndTime());
                //获取下一个秒杀场次
                FlashPromotionSession nextSession = getNextFlashPromotionSession(homeFlashPromotion.getStartTime());
                if (nextSession != null) {
                    homeFlashPromotion.setNextStartTime(nextSession.getStartTime());
                    homeFlashPromotion.setNextEndTime(nextSession.getEndTime());
                }
                //获取秒杀商品
                List<FlashPromotionProduct> flashProductList = homeClient.getFlashProductList(flashPromotion.getId(), flashPromotionSession.getId());
                homeFlashPromotion.setProductList(flashProductList);
            }
        }
        return homeFlashPromotion;
    }

    /**
     * 获取下一个场次信息
     *
     * @param date
     * @return
     */
    private FlashPromotionSession getNextFlashPromotionSession(Date date) {
        return flashPromotionSessionClient.getNextFlashPromotionSession(date);
    }

    private List<HomeAdvertise> getHomeAdvertiseList() {
        return homeAdvertiseClient.getHomeAdvertiseList();
    }

    /**
     * 根据时间获取秒杀活动
     *
     * @param date
     * @return
     */
    private FlashPromotion getFlashPromotion(Date date) {
        return flashPromotionClient.getFlashPromotion(DateUtil.getDate(date));
    }

    /**
     * 根据时间获取秒杀场次
     *
     * @param date
     * @return
     */
    private FlashPromotionSession getFlashPromotionSession(Date date) {
        return flashPromotionSessionClient.getFlashPromotionSession(DateUtil.getTime(date));
    }
}
