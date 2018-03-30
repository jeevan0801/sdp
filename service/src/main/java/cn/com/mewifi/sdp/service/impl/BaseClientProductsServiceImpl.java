package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.ClientProduct;
import cn.com.mewifi.sdp.dao.ClientProductInfoDao;
import cn.com.mewifi.sdp.service.IClientProductsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-15.
 */
@Data
@Service
public class BaseClientProductsServiceImpl implements IClientProductsService {
    @Autowired
    private ClientProductInfoDao clientProductInfoDao;
    
    @Override
    public List<? extends ClientProduct> selectAllClientProductsByType(String type) {
        List<? extends ClientProduct> list = clientProductInfoDao.selectAllClientProducts(type);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends ClientProduct> selectAllClientProducts() {
        return selectAllClientProductsByType(null);
    }
    
    @Override
    public List<? extends ClientProduct> selectAvailableClientProductsByType(String type) {
        List<? extends ClientProduct> list = clientProductInfoDao.selectAvailAbleClientProducts(type);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends ClientProduct> selectAvailableClientProducts() {
        return selectAvailableClientProductsByType(null);
    }
    
    @Override
    public List<? extends ClientProduct> selectProductByCondition(Map<String, Object> queryParams) {
        /*
         * if(null!=queryParams){ if(null== queryParams.get("type")){ queryParams.put("type",type); } } else {
         * queryParams = new HashMap<>(); queryParams.put("type",type); }
         */
        List<? extends ClientProduct> list = clientProductInfoDao.selectClientProductsByCondition(queryParams);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends ClientProduct> selectClientProductByProductCodeAndType(String type, String code) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("code", code);
        queryParams.put("type", type);
        List<? extends ClientProduct> list = clientProductInfoDao.selectClientProductByClientProductCode(queryParams);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public <T extends ClientProduct> T selectClientProductByProductIdAndType(String type, BigDecimal id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        queryParams.put("type", type);
        return clientProductInfoDao.selectClientProductById(queryParams);
    }
    
    @Override
    public <T extends ClientProduct> T selectClientProductByProductId(BigDecimal id) {
        
        return selectClientProductByProductIdAndType(null, id);
    }

    @Override
    public <T extends ClientProduct> T selectClientProductByBaseProductCodeAndClientCode(String baseProductCode, String clientCode) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("baseproductcode", baseProductCode);
        queryParams.put("clientcode", clientCode);
        return clientProductInfoDao.selectClientProductByBaseProductCodeAndClientCode(queryParams);
    }

    @Override
    public List<? extends ClientProduct> selectClientProudctByProductCode(String code) {
        return selectClientProductByProductCodeAndType(null, code);
    }
}
