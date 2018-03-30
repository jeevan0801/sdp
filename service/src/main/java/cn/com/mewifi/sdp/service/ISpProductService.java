package cn.com.mewifi.sdp.service;

import cn.com.mewifi.sdp.bo.SpProduct;

import java.util.List;
import java.util.Map;

public interface ISpProductService {

    <T extends SpProduct> T selectSpProductBySpProductCode(String type,String code);
    List<? extends SpProduct> selectAllSpProducts(String type);
    List<? extends SpProduct> selectSpProductsByCondition(Map<String, Object> queryParams);
    List<? extends SpProduct>  selectSpProductByBaseProductCodeAndLowCost(String type,String baseProductCode);
}