package cn.com.mewifi.sdp.service.impl;

import cn.com.mewifi.sdp.bo.Catalog;
import cn.com.mewifi.sdp.dao.CatalogInfoDao;
import cn.com.mewifi.sdp.service.ICatalogService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-14.
 */
@Data
@Service
public class BaseCatalogServiceImpl implements ICatalogService {
    @Autowired
    private CatalogInfoDao catalogInfoDao;
    
    @Override
    public List<? extends Catalog> getCatalogListByType(String type) {
        return (null == catalogInfoDao.selectAllCatalogs(type) || catalogInfoDao.selectAllCatalogs(type).size() == 0)
            ? null
            : catalogInfoDao.selectAllCatalogs(type);
    }
    
    @Override
    public List<? extends Catalog> getCatalogList() {
        return getCatalogListByType(null);
    }
    
    @Override
    public List<? extends Catalog> getCatalogsByCondition(Map<String, Object> queryParams) {
        /*
         * if(null!=queryParams){ if(null== queryParams.get("type")){ queryParams.put("type",SDPTypeEnum.MEMBER_TYPE); }
         * } else { queryParams = new HashMap<>(); queryParams.put("type",SDPTypeEnum.MEMBER_TYPE); }
         */
        List<? extends Catalog> list = catalogInfoDao.selectCatalogsByCondition(queryParams);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends Catalog> getCatalogByCatalogCodeAndType(String type, String code) {
        Map<String, String> queryMaps = new HashMap<>();
        queryMaps.put("type", type);
        queryMaps.put("code", code);
        List<? extends Catalog> list = catalogInfoDao.selectCatalogByCatalogCode(queryMaps);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends Catalog> getCatalogByCatalogCode(String code) {
        return getCatalogByCatalogCodeAndType(null, code);
    }
    
    @Override
    public List<? extends Catalog> getAvailCatalogsByType(String type) {
        List<? extends Catalog> list = catalogInfoDao.selectAvailAbleCatalogs(type);
        return (null == list || list.size() == 0) ? null : list;
    }
    
    @Override
    public List<? extends Catalog> getAvailCatalogs() {
        return getAvailCatalogsByType(null);
    }

    @Override
    public <T extends Catalog> T selectCatalogByCatalogIdAndType(BigDecimal id, String type) {
        Map<String, Object> queryMaps = new HashMap<>();
        queryMaps.put("type", type);
        queryMaps.put("id", id);
        return catalogInfoDao.selectCatalogByCatalogIdAndType(queryMaps);
    }
}
