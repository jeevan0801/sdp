package cn.com.mewifi.sdp.handler;

import cn.com.mewifi.sdp.bo.Catalog;
import cn.com.mewifi.sdp.bo.memberbenefit.MemCatalog;
import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.service.ICatalogService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description:
 * author: Administrator
 * date: 2017-9-20 下午 7:33
 */
@Data
@Component
@Slf4j
public class CatalogsHandler {
    @Autowired
    /***目录service****/
    private ICatalogService catalogService;
    
    /***
     * 查找所有type类型的分类
     * @param type 类型
     * @return
     */
    public JSONArray getCatalogsByType(String type) {
        List<? extends Catalog> catalogList = catalogService.getCatalogList();
        if (catalogList == null) {
            return null;
        }
        // 将catalogList转成jsonArray
        if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
            JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(catalogList));
            return jsonArray;
        }
        return null;
        
    }
    
    /***
     * 根据code和type查找catalog
     * @param type 类型
     * @param code catalogCode
     * @return
     */
    public JSONObject getCatalogsByCodeAndType(String type, String code) {
        
        List<? extends Catalog> catalogList = catalogService.getCatalogByCatalogCode(code);
        if (catalogList == null) {
            return null;
        }
        if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
            MemCatalog catalog = (MemCatalog)catalogList.get(0);
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(catalog));
            return jsonObject;
        }
        return null;
    }
}
