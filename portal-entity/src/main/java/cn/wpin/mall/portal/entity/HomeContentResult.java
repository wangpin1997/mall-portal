package cn.wpin.mall.portal.entity;


import cn.wpin.mall.content.entity.Subject;
import cn.wpin.mall.product.entity.Brand;
import cn.wpin.mall.product.entity.Product;
import cn.wpin.mall.sale.entity.HomeAdvertise;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页内容返回信息封装
 * @author wangpin 
 */
@Getter
@Setter
public class HomeContentResult {
    /**
     * 轮播广告
     */
    private List<HomeAdvertise> advertiseList;
    /**
     * 推荐品牌
     */
    private List<Brand> brandList;
    /**
     * 当前秒杀场次
     */
    private HomeFlashPromotion homeFlashPromotion;
    /**
     * 新品推荐
     */
    private List<Product> newProductList;
    /**
     * 人气推荐
     */
    private List<Product> hotProductList;
    /**
     * 推荐专题
     */
    private List<Subject> subjectList;
}
