package cn.com.mewifi.sdp.handler;

import cn.com.mewifi.core.util.DateUtil;
import cn.com.mewifi.core.util.PinYinUtil;
import cn.com.mewifi.sdp.bo.*;
import cn.com.mewifi.sdp.bo.memberbenefit.MemBaseProduct;
import cn.com.mewifi.sdp.bo.memberbenefit.MemSpInformation;
import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.constant.Result;
import cn.com.mewifi.sdp.service.*;
import cn.com.mewifi.sdp.service.memberbenefit.IMemBaseProductsService;
import cn.com.mewifi.sdp.service.memberbenefit.impl.MemBaseProductsServiceImpl;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.util.SpringUtils;
import cn.com.mewifi.sdp.vo.ResultVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Administrator on 2017-9-18.
 */
@Data
@Component
@Slf4j
public class ProductsHandler {
    @Autowired
    private IBaseProductsService baseProductsService;
    
    @Autowired
    /*******下游产品信息service********/
    private IClientProductsService clientProductsService;
    
    @Autowired
    /*****目录信息service*****/
    private ICatalogService catalogService;
    
    @Autowired
    /*******上游公司service*******/
    private ISpService spService;
    
    @Autowired
    /***客户端信息service****/
    private IClientInfoService clientInfoService;
    
    private static Map<Integer, PubConstant.ProductType> productsTypeMap =
        new HashMap<Integer, PubConstant.ProductType>();
    
    static {
        productsTypeMap.put(1, PubConstant.ProductType.PRODUCT_TYPE_ONE_MONTH);
        productsTypeMap.put(2, PubConstant.ProductType.PRODUCT_TYPE_THREE_MONTH);
        productsTypeMap.put(3, PubConstant.ProductType.PRODUCT_TYPE_SIX_MONTH);
        productsTypeMap.put(4, PubConstant.ProductType.PRODUCT_TYPE_ONE_YEAR);
    }
    
    /***
     * 返回产品的json数组
     * @param type 类型
     * @param queryMap 查询条件
     * @return
     */
    public JSONArray getBaseProducts(String type, Map<String, Object> queryMap) {
        
        List<? extends BaseProduct> productList = null;
        List<Map<String, Object>> indexProducts = null;
        // 1.得到zzwx首页产品
        if (null == queryMap || queryMap.size() == 0) {
            productList = baseProductsService.getProductsByType(type);
        }
        else {
            if (null != queryMap.get("code") && !"".equals(queryMap.get("code").toString().trim())) {
                BaseProduct baseProduct = baseProductsService.getProductByProductCodeAndType(type, queryMap.get("code").toString());
                return getBaseProductDetail(baseProduct, type);
            }
            else {
                
                if (queryMap.get("isindex") != null && PubConstant.Bool.YES.getCode().equals(queryMap.get("isindex"))) {
                    
                    // 1.查询所有在首页显示的产品
                    if (queryMap.get("catalogid") == null) {
                        indexProducts = baseProductsService.getIndexProductsByType(type);
                    }
                    else {
                        // 2.分类在首页显示
                        queryMap.put("type", type);
                        indexProducts = baseProductsService.getIndexProductsByTypeAndCatalog(type,
                            new BigDecimal(queryMap.get("catalogid").toString()));
                    }
                    
                    return getIndexBaseProducts(indexProducts, type);
                }
                else {
                    queryMap.put("type", type);
                    productList = baseProductsService.getProductsByCondition(queryMap);
                }
            }
            
        }
        return getBaseProducts(productList, type);
        
    }
    
    /*****
     * 获取首页数据
      * @param productList 产品列表
     * @param type 类型
     * @return 首页数据的json数组
     */
    private JSONArray getIndexBaseProducts(List<Map<String, Object>> productList, String type) {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> productsJsons = new ArrayList<>();
        
        // BaseProduct product = null;
        Map<String, Object> queryParams = null;
        // 1遍历集合
        if (null != productList) {
            if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
                // 2.根据产品id获取clientProudct的price ,得到上游公司信息
                for (int i = 0; i < productList.size(); i++) {
                    Map<String, Object> productMap = productList.get(i);
                    // 将product转成json
                    JSONObject productJson = JSONObject.parseObject(JSONObject.toJSONString(productMap));
                    queryParams = new HashMap<>();
                    queryParams.put("type", type);
                    queryParams.put("baseproductcode", productMap.get("CODE"));
                    List<? extends ClientProduct> clientProducts =
                        clientProductsService.selectProductByCondition(queryParams);
                    // 替换cost
                    if (null != clientProducts) {
                        productJson.put("COST", clientProducts.get(0).getCost());
                    }
                    /*
                     * //得到上游公司信息 //将公司信息加入到product MemSpInformation spInformation = spService.getSpInfoById(type,
                     * product.getSpId()); if (null != spInformation) { productJson.put("smallLogo",
                     * spInformation.getSmallLogo());
                     * 
                     * } //将product加入到productsJsons中
                     */
                    productsJsons.add(productJson);
                    log.error("------------productsJsons--------------==  " + productsJsons);
                    
                }
            }
            
            return JSONArray.parseArray(JSONArray.toJSONString(productsJsons));
        }
        
        return null;
        
    }
    
    /*****
     * 通过产品去获取详情
     * @param type 产品类型
     * @return 产品详情的json数组
     */
    private JSONArray getBaseProductDetail(BaseProduct baseProduct, String type) {
        if (null ==baseProduct) {
            return null;
        }

        JSONObject jsonObject = new JSONObject();
        List<JSONObject> spJsons = new ArrayList<>();
        // 2遍历map
        // 2.1.通过spId去获取上游公司信息
        if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {

            MemSpInformation spInformation = spService.getSpInfoByIdAndType(type, baseProduct.getSpId());
            if (spInformation == null) {
                return null;
            }
            // 2.2通过上游公司的spId去获得baseProducts
            // 将公司信息转成json格式
            JSONObject spJson = JSONObject.parseObject(JSONObject.toJSONString(spInformation));
            Map<String, Object> productQryMap = new HashMap<>();
            productQryMap.put("spid", spInformation.getId());
            productQryMap.put("catalogid", baseProduct.getCatalogId());
            productQryMap.put("type", type);
            productQryMap.put("unit",baseProduct.getUnit());
            List<? extends BaseProduct> list = baseProductsService.getProductsByCondition(productQryMap);
            if (null == list) {
                return null;
            }
            BaseProduct product = null;
            Map<String, Object> queryParams = null;
            // 2.3遍历集合
            List<JSONObject> memBaseProductJsons = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                product = (MemBaseProduct)list.get(i);
                queryParams = new HashMap<>();
                queryParams.put("type", type);
                queryParams.put("baseproductcode", product.getCode());
                List<? extends ClientProduct> clientProducts =
                        clientProductsService.selectProductByCondition(queryParams);
                // 替换cost
                if (null != clientProducts) {
                    product.setCost(clientProducts.get(0).getCost());
                }
                JSONObject productJson = JSONObject.parseObject(JSONObject.toJSONString(product));
                if (PubConstant.ProductType.PRODUCT_TYPE_ONE_MONTH.getUnit().equals(product.getUnit())) {
                    String spec = getProductSpec(product);

                    productJson.put("SPEC", spec);
                }
                memBaseProductJsons.add(productJson);
            }
            // 将产品信息加入到sp
            spJson.put("list", memBaseProductJsons);
            spJsons.add(spJson);

        }
        if (spJsons.size() > 0) {
            return JSONArray.parseArray(JSONArray.toJSONString(spJsons));
        }

        return null;

    }
    
    /*****
     * 通过产品列表去获取其他相关数据
     * @param productList 产品列表
     * @param type 产品类型
     * @return 产品详情的json数组
     */
    private JSONArray getBaseProducts(List<? extends BaseProduct> productList, String type) {
        if (null == productList) {
            return null;
        }
            JSONArray jsonArray = new JSONArray();
        if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
            BaseProduct product = null;
            Map<String, Object> queryParams = null;

        for (int i = 0; i < productList.size(); i++) {
            product = (MemBaseProduct)productList.get(i);

            queryParams = new HashMap<>();
            queryParams.put("type", type);
            queryParams.put("baseproductcode", product.getCode());
            List<? extends ClientProduct> clientProducts =
                    clientProductsService.selectProductByCondition(queryParams);
            // 替换cost
            if (null != clientProducts) {
                product.setCost(clientProducts.get(0).getCost());
            }
            // todo:添加规格 下次修改将从数据库取值
            String spec = getProductSpec(product);
            JSONObject productJson = JSONObject.parseObject(JSONObject.toJSONString(product));
            productJson.put("SPEC", spec);
            // 添加类型(生活,视频,音乐)
            Catalog catalog =
                    catalogService.selectCatalogByCatalogIdAndType(product.getCatalogId(), product.getType());
            if (catalog != null) {
                productJson.put("catalogName", catalog.getCatalogname());
            }
            jsonArray.add(productJson);
        }
            return jsonArray;
        }
        
       /* // 1.分组(上游spId相同)
        Map<String, List<Object>> spIdMap = productsGroupBySpId(productList, type, "SP_ID");
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> spJsons = new ArrayList<>();
        // 2遍历map
        // 2.1.通过spId去获取上游公司信息
        for (Map.Entry<String, List<Object>> entry : spIdMap.entrySet()) {
            if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
                MemSpInformation spInformation = spService.getSpInfoByIdAndType(type, new BigDecimal(entry.getKey()));
                if (spInformation == null) {
                    continue;
                }
                // 2.2通过上游公司的spId去获得baseProducts
                // 将公司信息转成json格式
                JSONObject spJson = JSONObject.parseObject(JSONObject.toJSONString(spInformation));
                *//*
                 * Map<String, Object> productQryMap = new HashMap<>(); productQryMap.put("spid",
                 * spInformation.getId()); // productQryMap.put("type", type); List<? extends BaseProduct> list =
                 * baseProductsService.getPrdocutsByCondition(productQryMap); if (null == list) { continue; }
                 *//*
                BaseProduct product = null;
                Map<String, Object> queryParams = null;
                // 2.3遍历集合
                List<Object> list = entry.getValue();
                List<JSONObject> memBaseProductJsons = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    product = (MemBaseProduct)list.get(i);
                    
                    queryParams = new HashMap<>();
                    queryParams.put("type", type);
                    queryParams.put("basepid", product.getId());
                    List<? extends ClientProduct> clientProducts =
                        clientProductsService.selectProductByCondition(queryParams);
                    // 替换cost
                    if (null != clientProducts) {
                        product.setCost(clientProducts.get(0).getCost());
                    }
                    // todo:添加规格 下次修改将从数据库取值
                    String spec = getProductSpec(product);
                    JSONObject productJson = JSONObject.parseObject(JSONObject.toJSONString(product));
                    productJson.put("SPEC", spec);
                    // 添加类型(生活,视频,音乐)
                    Catalog catalog =
                        catalogService.selectCatalogByCatalogIdAndType(product.getCatalogId(), product.getType());
                    if (catalog != null) {
                        productJson.put("CATALOGNAME", catalog.getCatalogname());
                    }
                    productJson.remove("UNIT");
                    memBaseProductJsons.add(productJson);
                }
                // 将产品信息加入到sp
                spJson.put("list", memBaseProductJsons);
                spJsons.add(spJson);
            }
            
        }
        if (spJsons.size() > 0) {
            return JSONArray.parseArray(JSONArray.toJSONString(spJsons));
        }*/

        return null;
        
    }
    
    /****
     * 将list以sp分组
     * @param products 产品列表
     * @param type 产品类型
     * @param key 键名
     * @return Map <key,product>
     */
    private Map<String, List<Object>> productsGroupBySpId(List<? extends BaseProduct> products, String type,
        String key) {
        Map<String, List<Object>> productMap = new HashMap<>();
        if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
            List<Object> productList = null;
            MemBaseProduct membaseProduct = null;
            for (int i = 0; i < products.size(); i++) {
                membaseProduct = (MemBaseProduct)products.get(i);
                if (null != membaseProduct.getSpId() && !"".equals(membaseProduct.getSpId())) {
                    
                    if (productMap.get(membaseProduct.getSpId().toString()) == null) {
                        productList = new ArrayList<>();
                    }
                    else {
                        productList = productMap.get(membaseProduct.getSpId().toString());
                    }
                    productList.add(membaseProduct);
                    productMap.put(membaseProduct.getSpId().toString(), productList);
                }

            }
        }
        return productMap;
    }
    
    /****
     * 对用户进行验证
     * @param code 用户code
     * @return
     */
    public ResultVO verifiedUser(String code) {
        // 返回结果
        ResultVO userErr = null;
        List<? extends ClientInformation> accountList = clientInfoService.getByClientInfoCode(code);
        if (accountList == null) {
            userErr = ResultVOUtil.error(String.valueOf(HttpStatus.BAD_REQUEST),
                PubConstant.FindType.USER_NOTFOUND.getDesc());
            return userErr;
        }
        ClientInformation client = accountList.get(0);
        if (client == null || client.getType() == null) {
            userErr = ResultVOUtil.error(String.valueOf(HttpStatus.BAD_REQUEST),
                PubConstant.FindType.USER_TYPE_NOTFOUND.getDesc());
            return userErr;
        }
        userErr = new ResultVO();
        userErr.setData(client.getType());
        return userErr;
    }
    
    /****
     * 将对象转成map
     * @param productList
     * @param type
     * @return
     */
    private List<Map<String, Object>> productListToMap(List<? extends BaseProduct> productList, String type) {
        if (productList == null) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
            for (int i = 0; i < productList.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                MemBaseProduct baseProduct = (MemBaseProduct)productList.get(i);
                /*
                 * BeanMap bean = null; bean = new
                 * org.apache.commons.beanutils.BeanMap((MemBaseProduct)productList.get(i)); if (bean != null) { for
                 * (Object key : bean.keySet()) { map.put(key + "", bean.get(key)); } mapList.add(map); }
                 */
                /*
                 * BeanMap beanMap = BeanMap.create(baseProduct); for (Object key : beanMap.keySet()) { map.put(key+"",
                 * beanMap.get(key)); }
                 */
                Field[] parentFileds = getDeclaredField(baseProduct);
                for (Field field : parentFileds) {
                    field.setAccessible(true);
                    try {
                        map.put(field.getName(), field.get(baseProduct));
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                Field[] declaredFields = baseProduct.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    try {
                        map.put(field.getName(), field.get(baseProduct));
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                mapList.add(map);
            }
        }
        return mapList;
    }
    
    private Field[] getDeclaredField(Object object) {
        Field[] fields = null;
        
        Class<?> clazz = object.getClass();
        clazz = clazz.getSuperclass();
        try {
            fields = clazz.getDeclaredFields();
            return fields;
        }
        catch (Exception e) {
            // 这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
            // 如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            
        }
        
        return null;
    }
    

    
    public ResultVO insertProduct(BaseProduct baseProduct, BaseProduct childBaseProduct, String spName,
        Integer productType) {
        // 定义返回结果
        ResultVO resultVO = null;
        // 1.得到spId
        BigDecimal spId = null;
        // 1.1根据spName去获得spInformation
        Map<String, Object> spQry = new HashMap<>();
        spQry.put("spname", spName);
        List<? extends SpInformation> spInformations = spService.getSpInfosByCondition(spQry);
        // 1.2.1存在,直接赋值
        if (spInformations != null) {
            spId = spInformations.get(0).getId();
        }
        else {
            // 1.2.2不存在,返回
            resultVO = ResultVOUtil.error(String.valueOf(PubConstant.FindType.SPINFO_NOTFOUND.getCode()),
                PubConstant.FindType.SPINFO_NOTFOUND.getDesc());
            return resultVO;
        }
        
        // 判断code是否存在,存在返回
        BaseProduct vaildBaseProduct = baseProductsService.getProductByProductCode(baseProduct.getCode());
        if (vaildBaseProduct != null) {
            resultVO = ResultVOUtil.error(String.valueOf(PubConstant.FindType.PRODUCT_CODE_EXITS.getCode()),
                PubConstant.FindType.PRODUCT_CODE_EXITS.getDesc());
            return resultVO;
        }
        
        // 2.生成unit,tradesize
        // todo:下次修改将从数据库取
        
        PubConstant.ProductType productTypeEnum = productsTypeMap.get(productType);
        if (productTypeEnum != null) {
            baseProduct.setUnit(productTypeEnum.getUnit());
            baseProduct.setTradeSize(productTypeEnum.getTradeSize());
        }
        else {
            baseProduct.setUnit(PubConstant.ProductType.PRODUCT_TYPE_MONEY.getUnit());
            baseProduct.setTradeSize(baseProduct.getPrice());
        }
        // 设置spId,createtime,status
        baseProduct.setSpId(spId);
        baseProduct.setCreateTime(DateUtil.getDefaultDateString());
        baseProduct.setStatus(PubConstant.StatusType.AVAILABLE.getCode());
        // 3.调用插入方法
        int result = baseProductsService.insertBaseProduct(baseProduct);
        if (result > 0) {
            if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(baseProduct.getType())) {
                // 插入memBaseProduct
                MemBaseProduct memBaseProduct = (MemBaseProduct)childBaseProduct;
                memBaseProduct.setMemId(baseProduct.getId());
                // 插入方法
                log.info("ProductsHandler -- insert MemBaseProduct  memBaseProduct== " + memBaseProduct);
                IMemBaseProductsService memBaseProductsService =
                    (MemBaseProductsServiceImpl)SpringUtils.getBean("memBaseProductsServiceImpl");
                int memResult = memBaseProductsService.insertMemBaseProduct(memBaseProduct);
                // memBaseProductsService.insertMemBaseProduct(memBaseProduct);
                if (memResult > 0) {
                    resultVO = ResultVOUtil.success();
                }
                else {
                    resultVO = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
                }
            }
            
        }
        else {
            resultVO = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
        }
        return resultVO;
    }
    
    /*****
     * 删除单个
     * @param productCode 产品code
     * @return
     */
    public ResultVO deleteProductByCode(String productCode) {
        ResultVO rs = null;
        // 先得到产品的id,type
        BaseProduct baseProduct = baseProductsService.getProductByProductCode(productCode);
        if (baseProduct == null) {
            rs = ResultVOUtil.error(String.valueOf(PubConstant.FindType.PRODUCT_NOT_FOUND.getCode()),
                PubConstant.FindType.PRODUCT_NOT_FOUND.getDesc());
            return rs;
        }
        int result = baseProductsService.deleteBaseProductByProductCode(productCode);
        if (result > 0) {
            if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(baseProduct.getType())) {
                IMemBaseProductsService memBaseProductsService =
                    (MemBaseProductsServiceImpl)SpringUtils.getBean("memBaseProductsServiceImpl");
                int memResult = memBaseProductsService.deleteMemBaseProductByProductId(baseProduct.getId());
                if (memResult > 0) {
                    rs = ResultVOUtil.success();
                }
                else {
                    rs = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
                }
            }
        }
        else {
            rs = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
        }
        return rs;
    }
    
    /***
     * 字符串
     * @param codes codes:[]
     * @return
     */
    public ResultVO batchDeleteProductByCodes(String codes) {
        // 返回值
        ResultVO rs = null;
        List<String> list = new ArrayList<>();
        list = Arrays.asList(codes.split(","));
        // 1.解析字符串
       /* JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(codes);
        }
        catch (Exception e) {
            rs = ResultVOUtil.error(PubConstant.dataError.error.getCode(), PubConstant.dataError.error.getDesc());
            return rs;
        }
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("codes"));
        List<String> list = jsonArray.toJavaList(String.class);*/
        List<? extends BaseProduct> baseProducts = baseProductsService.getProductByProductCodes(list);
        if (baseProducts == null) {
            rs = ResultVOUtil.error(String.valueOf(PubConstant.FindType.PRODUCT_NOT_FOUND.getCode()),
                PubConstant.FindType.PRODUCT_NOT_FOUND.getDesc());
            return rs;
        }
        Map<String, List<BigDecimal>> baseProductMapByType = baseProductListGroupByType(baseProducts);
        int result = baseProductsService.batchDeleteBaseProductsByProductCodes(list);
        // 进行根据map的key进行删除
        if (result > 0) {
            // 遍历map
            for (Map.Entry<String, List<BigDecimal>> entry : baseProductMapByType.entrySet()) {
                if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(entry.getKey())) {
                    IMemBaseProductsService baseProductsService =
                        (MemBaseProductsServiceImpl)SpringUtils.getBean("memBaseProductsServiceImpl");
                    int memResult = baseProductsService.batchDeleteMemBaseProductsByProductIds(entry.getValue());
                }
            }
            rs = ResultVOUtil.success();
        }
        else {
            rs = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
        }
        return rs;
    }
    
    private Map<String, List<BigDecimal>> baseProductListGroupByType(List<? extends BaseProduct> baseProducts) {
        // 返回值
        Map<String, List<BigDecimal>> idsByType = new HashMap<>();
        List<BigDecimal> ids = null;
        for (BaseProduct baseProduct : baseProducts) {
            String type = baseProduct.getType();
            if (type != null) {
                if (idsByType.get(type) == null) {
                    ids = new ArrayList<>();
                }
                else {
                    ids = idsByType.get(type);
                }
                ids.add(baseProduct.getId());
            }
            idsByType.put(type, ids);
        }
        return idsByType;
    }
    
    private String getSpInfoCode(String spName) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        spName = PinYinUtil.getPingYin(spName) + "_" + random.nextInt(10, 100);
        while (true) {
            System.err.println("       -------------true        -----");
            List<? extends SpInformation> spInfoBySpInfos = spService.getSpInfoBySpInfoCode(spName);
            if (spInfoBySpInfos == null) {
                return spName;
            }
            else {
                spName = spName + random.nextInt(0, 10);
            }
        }
        
    }
    
    private String getProductCode(String spName, BigDecimal tradeSize) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        spName = PinYinUtil.getPingYin(spName) + tradeSize;
        while (true) {
            System.err.println("       -------------false        -----");
            List<? extends BaseProduct> baseProducts = baseProductsService.getProductByProductCode(spName);
            if (baseProducts == null) {
                return spName;
            }
            else {
                spName = spName + random.nextInt(0, 10);
            }
        }
        
    }
    
    private String getProductSpec(BaseProduct baseProduct) {
        String spec = "";
        if (PubConstant.ProductType.PRODUCT_TYPE_ONE_MONTH.getUnit().equals(baseProduct.getUnit())) {
            if (PubConstant.ProductType.PRODUCT_TYPE_ONE_MONTH.getTradeSize().equals(baseProduct.getTradeSize())) {
                spec = PubConstant.ProductSpec.PRODUCT_SPEC_ONE_MONTH.getSpec();
            }
            else if (PubConstant.ProductType.PRODUCT_TYPE_THREE_MONTH.getTradeSize()
                .equals(baseProduct.getTradeSize())) {
                spec = PubConstant.ProductSpec.PRODUCT_SPEC_THREE_MONTH.getSpec();
            }
            else if (PubConstant.ProductType.PRODUCT_TYPE_SIX_MONTH.getTradeSize().equals(baseProduct.getTradeSize())) {
                spec = PubConstant.ProductSpec.PRODUCT_SPEC_SIX_MONTH.getSpec();
            }
            else if (PubConstant.ProductType.PRODUCT_TYPE_ONE_YEAR.getTradeSize().equals(baseProduct.getTradeSize())) {
                spec = PubConstant.ProductSpec.PRODUCT_SPEC_ONE_YEAR.getSpec();
            }
        }
        else {
            spec = baseProduct.getPrice() + baseProduct.getUnit();
        }
        return spec;
    }
    
    public int insertTest() {
        /*
         * List<BaseProduct> baseProducts = new ArrayList<>(); baseProducts.add(new BaseProduct()); baseProducts.add(new
         * BaseProduct());
         */
        // int result = baseProductsService.batchInsertBaseProducts(baseProducts);
        MemBaseProductsServiceImpl memBaseProductsService =
            (MemBaseProductsServiceImpl)SpringUtils.getBean("memBaseProductsServiceImpl");
        MemBaseProduct memBaseProduct = new MemBaseProduct();
        memBaseProduct.setMemId(new BigDecimal(150));
        int result = memBaseProductsService.insertMemBaseProduct(memBaseProduct);
        return result;
    }

    /***
     * 查询分页
     * @param type 类型
     * @param queryMap
     * @param pageIndex 第几页
     * @param pageSize 每页显示多少
     * @return
     */
    public JSONArray getBaseProductsByPage(String type, Map<String, Object> queryMap, Integer pageIndex, Integer pageSize) {
        Integer totalPage = 1;
        Integer totalCount = null;
        List<? extends BaseProduct> productList = null;
        List<Map<String, Object>> indexProducts = null;
        JSONArray resultArray = null;
        //设置分页
        if(pageIndex!=null && pageSize!=null) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        //查询所有
        if(queryMap==null||queryMap.size()==0){
            productList = baseProductsService.getProductsByType(type);
            totalCount = baseProductsService.getProductsSizeByType(type);

        } else
        //查询首页,分类首页,分类或其他
        if (queryMap.get("isindex") != null && PubConstant.Bool.YES.getCode().equals(queryMap.get("isindex"))) {
            // 分类在首页显示
            if(queryMap.get("catalogid")!=null) {

                queryMap.put("type", type);
                indexProducts = baseProductsService.getIndexProductsByTypeAndCatalog(type,
                        new BigDecimal(queryMap.get("catalogid").toString()));
                totalCount = baseProductsService.getIndexProductsSizeByTypeAndCatalog(type,new BigDecimal(queryMap.get("catalogid").toString()));
                if(null!=pageSize) {
                    totalPage = (totalCount+pageSize-1) / pageSize;
                }
            } else {
                //首页
                indexProducts = baseProductsService.getIndexProductsByType(type);
                totalCount = baseProductsService.getIndexProductsSizeByType(type);
                totalPage = getTotalPage(totalCount,pageSize);
            }
                resultArray = getIndexBaseProducts(indexProducts, type);
            if(null!=resultArray && resultArray.size()>0){
                JSONObject pageInfo =JSONObject.parseObject("{\"TOTALPAGE\":"+totalPage+",\"TOTALCOUNT\":"+totalCount+"}");
                resultArray.add(pageInfo);
            }
            return resultArray;
        }
      else {
            queryMap.put("type",type);
            productList = baseProductsService.getProductsByCondition(queryMap);
            totalCount = baseProductsService.getProductsSizeByCondition(queryMap);
            totalPage = getTotalPage(totalCount, pageSize);
        }
        resultArray = getBaseProducts(productList,type);
        if(null!=resultArray && resultArray.size()>0){
            JSONObject pageInfo =JSONObject.parseObject("{\"TOTALPAGE\":"+totalPage+",\"TOTALCOUNT\":"+totalCount+"}");
            resultArray.add(pageInfo);
        }
        return resultArray;
    }
    private Integer getTotalPage(Integer totalCount,Integer pageSize){
        Integer totalPage = 1;
         if(totalCount > 0 && pageSize !=null){
             totalPage = (totalCount+pageSize-1) / pageSize;
         }
        return totalPage;
    }

    public JSONArray getAllProducts(String type,Integer pageIndex,Integer pageSize){
        return getBaseProductsByPage(type,null,pageIndex,pageSize);
    }

    public JSONArray getProductsByKeyWordsOrCatalogId(String type,String keyWord,BigDecimal catalogId,Integer pageIndex,Integer pageSize){
        //返回结果
        JSONArray resultArray = null;
        Integer totalPage = 1;
        Integer totalCount = null;
        List<? extends BaseProduct> productList = null;
        //设置分页
        if(pageIndex!=null && pageSize!=null) {
            PageHelper.startPage(pageIndex, pageSize);
        }
        productList = baseProductsService.selectProductsByKeyWordAndCatalogId(keyWord,catalogId);
        totalCount = baseProductsService.selectProductsSizeByKeyWordAndCatalogId(keyWord, catalogId);
        resultArray = getBaseProducts(productList,type);
        if(null!=resultArray && resultArray.size()>0){
            JSONObject pageInfo =JSONObject.parseObject("{\"TOTALPAGE\":"+totalPage+",\"TOTALCOUNT\":"+totalCount+"}");
            resultArray.add(pageInfo);
        }
        return resultArray;
    }
}
