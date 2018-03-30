package cn.com.mewifi.sdp.dao;

import cn.com.mewifi.sdp.bo.SpProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface SpProductInfoDao {

    <T extends SpProduct> T selectSpProductBySpProductCode(Map<String,Object> queryParams);
    List<? extends SpProduct>  selectSpProductByBaseProductCodeAndLowCost(Map<String,Object> queryParams);
    List<? extends SpProduct> selectAllSpProducts(String type);
    List<? extends SpProduct> selectSpProductsByCondition(Map<String,Object> queryParams);

}