package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.SpProduct;
import cn.com.mewifi.sdp.dao.SpProductInfoDao;
import cn.com.mewifi.sdp.service.ISpProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * author: Administrator
 * date: 2017-09-27 19:42
 */
@Service
@Data
public class SpProductServiceImpl implements ISpProductService {
    @Autowired
    private SpProductInfoDao spProductInfoDao;
    @Override
    public <T extends SpProduct> T selectSpProductBySpProductCode(String type,String code) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("type",type);
        queryParams.put("code",code);
        return spProductInfoDao.selectSpProductBySpProductCode(queryParams);
    }

    @Override
    public List<? extends SpProduct> selectAllSpProducts(String type) {
        return spProductInfoDao.selectAllSpProducts(type);
    }

    @Override
    public List<? extends SpProduct> selectSpProductsByCondition(Map<String, Object> queryParams) {
        return spProductInfoDao.selectSpProductsByCondition(queryParams);
    }

    @Override
    public List<? extends SpProduct> selectSpProductByBaseProductCodeAndLowCost(String type,String baseProductCode) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("type",type);
        queryParams.put("baseproductcode",baseProductCode);
        return spProductInfoDao.selectSpProductByBaseProductCodeAndLowCost(queryParams);
    }

}
