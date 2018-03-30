package cn.com.mewifi.sdp.service.memberbenefit.impl;

import cn.com.mewifi.sdp.bo.memberbenefit.MemBaseProduct;
import cn.com.mewifi.sdp.dao.memberbenefit.MemBaseProductInfoDao;
import cn.com.mewifi.sdp.service.memberbenefit.IMemBaseProductsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * description:
 * author: Administrator
 * date: 2017-9-22 下午 6:24
 */
@Service
@Data
@Slf4j

public class MemBaseProductsServiceImpl implements IMemBaseProductsService {
    @Autowired
    private MemBaseProductInfoDao memBaseProductInfoDao;
    
    @Override
    public int insertMemBaseProduct(MemBaseProduct memBaseProudct) {
        int result = memBaseProductInfoDao.insertMemBaseProduct(memBaseProudct);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int insertMemBaseProductSelective(MemBaseProduct memBaseProudct) {
        int result = memBaseProductInfoDao.insertMemBaseProductSelective(memBaseProudct);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int batchInsertMemBaseProducts(List<MemBaseProduct> memBaseProducts) {
        int result = memBaseProductInfoDao.batchInsertMemBaseProducts(memBaseProducts);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int updateMemBaseProductById(MemBaseProduct memBaseProudct) {
        int result = memBaseProductInfoDao.updateMemBaseProductById(memBaseProudct);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int updateMemBaseProductBySelective(MemBaseProduct memBaseProudct) {
        int result = memBaseProductInfoDao.updateMemBaseProductBySelective(memBaseProudct);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int deleteMemBaseProductByProductId(BigDecimal memId) {
        int result = memBaseProductInfoDao.deleteMemBaseProductByProductId(memId);
        if (result > 0) {
            return result;
        }
        return 0;
    }
    
    @Override
    public int batchDeleteMemBaseProductsByProductIds(List<BigDecimal> memIds) {
        int result = memBaseProductInfoDao.batchDeleteMemBaseProductsByProductIds(memIds);
        if (result > 0) {
            return result;
        }
        return 0;
    }
}
