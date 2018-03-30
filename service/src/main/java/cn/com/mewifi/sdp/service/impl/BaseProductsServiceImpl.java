package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.BaseProduct;
import cn.com.mewifi.sdp.dao.BaseProductsInfoDao;
import cn.com.mewifi.sdp.service.IBaseProductsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-12.
 */
@Data
@Service
@Slf4j
public class BaseProductsServiceImpl implements IBaseProductsService {
    
    /***产品操作类**/
    @Autowired
    private BaseProductsInfoDao baseProductsInfoDao;
    
    @Override
    public List<? extends BaseProduct> getAvailableProductsByType(String type) {
        List<? extends BaseProduct> list = baseProductsInfoDao.selectAvailableProducts(type);
        return (null == list || list.size() == 0) ? null : list;
    }

    @Override
    public int getAvailableProductsSizeByType(String type) {
        return baseProductsInfoDao.selectAllProductsSize(type);
    }

    @Override
    public List<? extends BaseProduct> getAvailableProducts() {
        
        return getAvailableProductsByType(null);
    }

    @Override
    public int getAvailableProductsSize() {
        return baseProductsInfoDao.selectAvailableProductsSize(null);
    }

    @Override
    public List<Map<String, Object>> getIndexProducts() {
        return getIndexProductsByType(null);
    }

    @Override
    public int getIndexProductsSize() {
        return baseProductsInfoDao.selectIndexProductsSize(null);
    }

    @Override
    public List<Map<String, Object>> getIndexProductsByType(String type) {
        List<Map<String, Object>> memBaseProducts = baseProductsInfoDao.selectIndexProducts(type);
        if (null == memBaseProducts || memBaseProducts.size() == 0) {
            return null;
        }
        
        return memBaseProducts;
    }

    @Override
    public int getIndexProductsSizeByType(String type) {
        return baseProductsInfoDao.selectIndexProductsSize(type);
    }

    @Override
    public List<Map<String, Object>> getIndexProductsByTypeAndCatalog(String type, BigDecimal catalogid) {
        Map<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("type", type);
        queryMaps.put("catalogid", catalogid);
        List<Map<String, Object>> memBaseProducts = baseProductsInfoDao.selectIndexProductsByCatalogId(queryMaps);
        if (null == memBaseProducts || memBaseProducts.size() == 0) {
            return null;
        }
        
        return memBaseProducts;
    }

    @Override
    public int getIndexProductsSizeByTypeAndCatalog(String type, BigDecimal catalogid) {
        Map<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("type", type);
        queryMaps.put("catalogid", catalogid);
        return baseProductsInfoDao.selectIndexProductsSizeByCatalogId(queryMaps);
    }

    @Override
    public List<? extends BaseProduct> getProductsByType(String type) {
        List<? extends BaseProduct> list = baseProductsInfoDao.selectAllProducts(type);
        return (null == list || list.size() == 0) ? null : list;
    }

    @Override
    public int getProductsSizeByType(String type) {
        return baseProductsInfoDao.selectAllProductsSize(type);
    }

    @Override
    public List<? extends BaseProduct> getAllProducts() {
        return getProductsByType(null);
    }

    @Override
    public int getProductsSize() {
        return baseProductsInfoDao.selectAllProductsSize(null);
    }

    @Override
    public List<? extends BaseProduct> getProductsByCondition(Map<String, Object> queryParams) {
        /*
         * if(null!=queryParams){ if(null== queryParams.get("type") ||
         * "".equals(queryParams.get("type").toString().trim())){ queryParams.put("type",type); } } else { queryParams =
         * new HashMap<>(); queryParams.put("type",type);
         * 
         * }
         */
        List<? extends BaseProduct> list = baseProductsInfoDao.selectProductsByCondition(queryParams);
        return (null == list || list.size() == 0) ? null : list;
    }

    @Override
    public int getProductsSizeByCondition(Map<String, Object> queryParams) {
        return baseProductsInfoDao.selectProductsSizeByCondition(queryParams);
    }

    @Override
    public <T extends BaseProduct> T getProductByProductCodeAndType(String type, String productCode) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("code", productCode);
        queryMap.put("type", type);
        return baseProductsInfoDao.selectProductByProductCode(queryMap);
    }
    
    @Override
    public <T extends BaseProduct>  T getProductByProductCode(String productCode) {
        
        return getProductByProductCodeAndType(null, productCode);
    }

    @Override
    public List<? extends BaseProduct> getProductByProductCodes(List<String> codes) {
        List<? extends BaseProduct> list = baseProductsInfoDao.selectProductsByProductCodes(codes);
        return (null == list || list.size() == 0) ? null : list;
    }

    @Override
    public int insertBaseProduct(BaseProduct baseProduct) {
        int result = baseProductsInfoDao.insertBaseProduct(baseProduct);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int insertBaseProductSelective(BaseProduct baseProduct) {
        int result = baseProductsInfoDao.insertBaseProductSelective(baseProduct);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int batchInsertBaseProducts(List<BaseProduct> baseProducts) {
        int result = baseProductsInfoDao.batchInsertBaseProducts(baseProducts);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int updateBaseProductByCode(BaseProduct baseProduct) {
        return 0;
    }
    
    @Override
    public int updateBaseProductBySelective(BaseProduct baseProduct) {
        int result = baseProductsInfoDao.updateBaseProductBySelective(baseProduct);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int batchDeleteBaseProductsByProductCodes(List<String> codes) {
        int result = baseProductsInfoDao.batchDeleteBaseProductsByProductCodes(codes);
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public int deleteBaseProductByProductCode(String code) {
        int result = baseProductsInfoDao.deleteBaseProductByProductCode(code);
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public List<? extends BaseProduct> selectProductsByKeyWordAndCatalogId(String keyWord, BigDecimal catalogId) {
        Map<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("keyword", keyWord);
        queryMaps.put("catalogid", catalogId);
        return baseProductsInfoDao.selectProductsByKeyWordAndCatalogId(queryMaps);
    }

    @Override
    public int selectProductsSizeByKeyWordAndCatalogId(String keyWord, BigDecimal catalogId) {
        Map<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("keyword", keyWord);
        queryMaps.put("catalogid", catalogId);
        return baseProductsInfoDao.selectProductsSizeByKeyWordAndCatalogId(queryMaps);
    }


}
