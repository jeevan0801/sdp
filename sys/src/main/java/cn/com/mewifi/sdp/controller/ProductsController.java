package cn.com.mewifi.sdp.controller;

import cn.com.mewifi.core.util.FileUtil;
import cn.com.mewifi.sdp.bo.BaseProduct;
import cn.com.mewifi.sdp.bo.memberbenefit.MemBaseProduct;
import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.constant.Result;
import cn.com.mewifi.sdp.handler.ProductsHandler;
import cn.com.mewifi.sdp.service.IClientInfoService;
import cn.com.mewifi.sdp.service.ISpService;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultPageVO;
import cn.com.mewifi.sdp.vo.ResultVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-12.
 */
@Data
@RestController
@Slf4j
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
@Api(value = "产品接口")
public class ProductsController {
    
    @Autowired
    /***客户端信息service****/
    private IClientInfoService clientInfoService;
    
    @Autowired
    /****上游公司service***/
    private ISpService spService;
    
    @Autowired
    /*****产品逻辑处理***/
    private ProductsHandler productsHandler;
    
    @ApiOperation(value = "查询首页产品", notes = "用于查询产品", hidden = true)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query")})
    @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/products/{productCode}/catalog/{catalogId}")
    public ResultVO getProductList(@PathVariable(value = "productCode", required = false) String productCode,
        @PathVariable(value = "catalogId", required = false) BigDecimal catalogId,
        @RequestParam(value = "code", required = true) String code,
        @RequestParam(value = "isIndex", required = false) String isIndex, HttpServletRequest request) {
        log.info("进入ProductsController getProductList方法------");
        // 1.根据用户code得到用户信息(type)
        ResultVO verifyInfo = productsHandler.verifiedUser(code);
        if (null == verifyInfo.getData()) {
            return verifyInfo;
        }
        // 查询map
        Map<String, Object> queryMap = new HashMap<>();
        if (catalogId != null) {
            queryMap.put("catalogid", catalogId);
        }
        if (productCode != null) {
            queryMap.put("code", productCode);
        }
        if (isIndex != null) {
            queryMap.put("isindex", isIndex);
        }
        // 2.根据type和queryMap去获得首页数据
        String type = String.valueOf(verifyInfo.getData());
        log.info(
            "ProductsController--调用ProductsHander  getBaseProducts方法 参数:type ==  " + type + " queryMap ==" + queryMap);
        JSONArray jsonStr = productsHandler.getBaseProducts(type, queryMap);
        log.info("ProductsController--调用ProductsHander  getBaseProducts结果 jsonStr ==" + jsonStr);
        // 3.返回结果
        ResultVO rs = null;
        if (null != jsonStr) {
            rs = ResultVOUtil.success(jsonStr);
        }
        else {
            rs = ResultVOUtil.error(String.valueOf(HttpStatus.BAD_REQUEST), PubConstant.NOT_FOUNT_MSG);
        }
        request.setAttribute(PubConstant.TYPE, type);
        request.setAttribute(PubConstant.RESULTVO, rs);
        log.info("ProductsController--getProductList 结束返回结果 rs ==" + rs);
        return rs;
        
    }
    
    public ResultVO getProductList(String productCode, BigDecimal catalogId, String code, HttpServletRequest request) {
        return getProductList(productCode, catalogId, code, null, request);
    }
    
    @ApiOperation(value = "查询分类产品", notes = "用于查询分类下的产品 isIndex:1时查询首页的")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示多少条", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "isIndex", value = "1:查询分类下的首页产品,不填查询分类的所有产品", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "catalogId", value = "分类id", required = true, dataType = "String", paramType = "path")})
    @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/products/catalog/{catalogId}")
    public ResultPageVO getProductListByCatalogId(
        @PathVariable(value = "catalogId", required = true) BigDecimal catalogId,
        @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
        @RequestParam(value = "pageSize", required = false) Integer pageSize,
        @RequestParam(value = "code", required = true) String code,
        @RequestParam(value = "isIndex", required = false) String isIndex, HttpServletRequest request) {
        log.info("进入ProductsController--getProductListByCatalogId方法 ");
        return getProductListByPage(catalogId, code, isIndex, request, pageIndex, pageSize);
    }
    
    @ApiOperation(value = "查询产品", notes = "用于查询产品详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "productCode", value = "产品code", required = true, dataType = "String", paramType = "path")})
    @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/products/{productCode}")
    public ResultVO getProductListByProductCode(
        @PathVariable(value = "productCode", required = true) String productCode,
        @RequestParam(value = "code", required = true) String code, HttpServletRequest request) {
        log.info("进入ProductsController--getProductListByProductCode方法 ");
        ResultVO rs = getProductList(productCode, null, code, request);
        // 将jsonArray转成jsonObject
        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(rs.getData()));
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        rs.setData(jsonObject);
        return rs;
    }
    
    /*
     * @ApiOperation(value = "查询产品", notes = "isIndex:1时查询首页的产品", hidden = true)
     * 
     * @ApiImplicitParams({
     * 
     * @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query"),
     * 
     * @ApiImplicitParam(name = "isIndex", value = "1:查询首页产品,不填查询所有产品", required = false, dataType = "String", paramType
     * = "query")})
     * 
     * @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/products") public ResultVO
     * getAllProductList(@RequestParam(value = "code", required = true) String code,
     * 
     * @RequestParam(value = "isIndex", required = false) String isIndex, HttpServletRequest request) {
     * log.info("进入ProductsController--getAllProductList方法 "); return getProductList(null, null, code, isIndex,
     * request); }
     */
    
    @ApiOperation(value = "查询产品", notes = "isIndex:1时查询首页的产品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示多少条", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "isIndex", value = "1:查询首页产品,不填查询所有产品", required = false, dataType = "String", paramType = "query")})
    @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/products")
    public ResultPageVO getAllProductListByPage(@RequestParam(value = "code", required = true) String code,
        @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
        @RequestParam(value = "pageSize", required = false) Integer pageSize,
        @RequestParam(value = "isIndex", required = false) String isIndex, HttpServletRequest request) {
        log.info("进入ProductsController--getAllProductListByPage方法 ");
        return getProductListByPage(null, code, isIndex, request, pageIndex, pageSize);
    }
    
    private ResultPageVO getProductListByPage(BigDecimal catalogId, String code, String isIndex,
        HttpServletRequest request, Integer pageIndex, Integer pageSize) {
        // 1.根据用户code得到用户信息(type)
        ResultPageVO resultPageVO = new ResultPageVO();
        ResultVO verifyInfo = productsHandler.verifiedUser(code);
        if (null == verifyInfo.getData()) {
            resultPageVO.setCode(verifyInfo.getCode());
            resultPageVO.setMsg(verifyInfo.getMsg());
            return resultPageVO;
        }
        // 查询map
        Map<String, Object> queryMap = new HashMap<>();
        if (catalogId != null) {
            queryMap.put("catalogid", catalogId);
        }
        if (isIndex != null) {
            queryMap.put("isindex", isIndex);
        }
        // 2.根据type和queryMap去获得首页数据
        String type = String.valueOf(verifyInfo.getData());
        log.info("ProductsController--调用ProductsHander  getBaseProductsByPage方法 参数:type ==  " + type + " queryMap =="
            + queryMap);
        JSONArray jsonArray = productsHandler.getBaseProductsByPage(type, queryMap, pageIndex, pageSize);
        log.info("ProductsController--调用ProductsHander  getBaseProductsByPage结果 jsonStr ==" + jsonArray);
        // 3.返回结果
        // todo:返回方式待修改
        ResultPageVO pageVO = null;
        ResultVO rs = null;
        if (null != jsonArray) {
            JSONObject pageInfo = (JSONObject)jsonArray.get(jsonArray.size() - 1);
            jsonArray.remove(jsonArray.size() - 1);
            rs = ResultVOUtil.success(jsonArray);
            pageVO =
                ResultVOUtil.success(jsonArray, pageInfo.getInteger("TOTALPAGE"), pageInfo.getInteger("TOTALCOUNT"));
        }
        else {
            rs = ResultVOUtil.error(String.valueOf(HttpStatus.BAD_REQUEST), PubConstant.NOT_FOUNT_MSG);
            pageVO = new ResultPageVO();
            pageVO.setCode(rs.getCode());
            pageVO.setMsg(rs.getMsg());
        }
        request.setAttribute(PubConstant.TYPE, type);
        request.setAttribute(PubConstant.RESULTVO, rs);
        log.info("ProductsController--getProductListByPage 结束返回结果 rs ==" + rs);
        return pageVO;
    }
    
    @ApiOperation(value = "新增产品", notes = "用于新增产品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "productName", value = "产品名称", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "spName", value = "产品所属公司名称", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "price", value = "产品面值", required = true, dataType = "BigDecimal", paramType = "query"),
        @ApiImplicitParam(name = "cost", value = "产品折扣价", required = true, dataType = "BigDecimal", paramType = "query"),
        @ApiImplicitParam(name = "type", value = "产品类型:1.会员:mem ", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "catalogId", value = "产品分类:视频:1,音乐:2,生活:3", required = true, dataType = "BigDecimal", paramType = "query"),
        @ApiImplicitParam(name = "productCode", value = "产品code,要求唯一", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "productType", value = "产品类型:月卡:1,季卡:2,半年卡:3,年卡:4,代金券:5", required = true, dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "productDesc", value = "产品描述", required = false, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "picFile", required = false, value = "上传图片", dataType = "file", paramType = "form"),
        @ApiImplicitParam(name = "stock", value = "库存", required = true, dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "isIndex", value = "是否在首页显示", required = false, dataType = "String", paramType = "query")})
    @PostMapping(value = "/" + PubConstant.LAST_VERSION + "/products")
    public ResultVO insertProudct(@RequestParam("productName") String productName,
        @RequestParam("spName") String spName, @RequestParam("price") BigDecimal price,
        @RequestParam("cost") BigDecimal cost, @RequestParam("type") String type,
        @RequestParam("catalogId") BigDecimal catalogId, @RequestParam("productType") int productType,
        @RequestParam("productCode") String productCode,
        @RequestParam(value = "productDesc", required = false) String productDesc,
        @RequestParam(value = "picFile", required = false) MultipartFile file,
        @RequestParam(value = "isIndex", required = false) String isIndex,
        @RequestParam(value = "stock") BigDecimal stock, HttpServletRequest request)
        throws IOException, ServletException {
        log.info("进入ProductsController--insertProudct方法  ");
        log.info("insertproduct params: productName=" + productName + ", spName=" + spName + ", price=" + price
            + ", cost=" + cost + ", type==" + type + ", catalogId=" + catalogId + ", productType==" + productType
            + ",  productCode==" + productCode);
        System.out.println(request.getInputStream());
        // 1.封装参数
        BaseProduct baseProduct = new BaseProduct();
        baseProduct.setProductName(productName);
        baseProduct.setCode(productCode);
        baseProduct.setCost(cost);
        baseProduct.setPrice(price);
        baseProduct.setType(type);
        baseProduct.setProductDesc(productDesc);
        baseProduct.setCatalogId(catalogId);
        baseProduct.setStock(stock);
        BaseProduct childBaseProduct = null;
        if (PubConstant.SdpType.SDP_TYPE_MEMBER.getType().equals(type)) {
            MemBaseProduct memBaseProduct = new MemBaseProduct();
            memBaseProduct.setIsIndex(isIndex);
            if (file == null) {
                ResultVO rs = ResultVOUtil.error(Result.pub_file_isEmpty);
                return rs;
            }
            // 处理上传图片
            String fileName = file.getOriginalFilename();
            String filePath = PubConstant.FilePath.PRODUCT_IMG_PATH.getUrl();
            String savedDir = request.getServletContext().getRealPath(filePath);
            ;
            // 得到本地ip及端口
            InetAddress localHost = InetAddress.getLocalHost();
            int port = request.getServerPort();
            String fileUrl = "";
            String serverName = request.getContextPath();
            if (port == 80) {
                fileUrl = PubConstant.ServerSchema.HTTP_SCHEMA.getSchema() + localHost.getHostName();
            }
            else {
                fileUrl = PubConstant.ServerSchema.HTTP_SCHEMA.getSchema() + localHost.getHostName() + ":" + port;
            }
            if (!StringUtils.isEmpty(serverName)) {
                fileUrl = fileUrl + serverName + "/";
            }
            
            log.info("-----------poroductController ------- 上传路径 ----- savedDir ==== " + savedDir);
            try {
                FileUtil.uploadFile(file.getBytes(), savedDir, fileName);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            memBaseProduct.setPicUrl(fileUrl + filePath + fileName);
            childBaseProduct = memBaseProduct;
        }
        
        // 2.调用新增方法
        log.info("调用ProductsHandler--insertProudct方法 参数:baseProduct  ===  " + baseProduct + "childBaseProduct === "
            + childBaseProduct + "spName == " + spName + "productType== " + productType);
        
        // 3.返回结果
        ResultVO rs = productsHandler.insertProduct(baseProduct, childBaseProduct, spName, productType);
        if (rs == null) {
            rs = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
        }
        
        request.setAttribute(PubConstant.TYPE, type);
        
        request.setAttribute(PubConstant.RESULTVO, rs);
        log.info("ProductsController--insertProudct方法结束返回结果 rs ===" + rs);
        return rs;
    }
    
    @ApiOperation(value = "删除产品", notes = "用于删除产品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "productCode", value = "产品code", required = true, dataType = "String", paramType = "path")})
    @DeleteMapping(value = "/" + PubConstant.LAST_VERSION + "/products/{productCode}")
    public ResultVO deleteProudctByProductCode(@PathVariable(value = "productCode", required = true) String productCode,
        HttpServletRequest request) {
        log.info("进入ProductsController--deleteProudctByProductCode方法 ");
        
        // 2.调用删除方法
        log.info("调用ProductsHandler--deleteProudctByProductCode方法 参数:productCode ===" + productCode);
        ResultVO rs = null;
        rs = productsHandler.deleteProductByCode(productCode);
        
        // 3.返回结果
        if (rs == null) {
            rs = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
        }
        request.setAttribute(PubConstant.RESULTVO, rs);
        log.info("ProductsController--deleteProudctByProductCode方法结束返回结果 rs ===" + rs);
        return rs;
    }
    
    @ApiOperation(value = "删除产品", notes = "用于删除产品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "batch", value = "产品code的字符串,code之间用逗号隔开:\"a,b,c\"", required = true, dataType = "String", paramType = "query")})
    @DeleteMapping(value = "/" + PubConstant.LAST_VERSION + "/products")
    @Scope("prototype")
    public ResultVO batchDeleteProudctByProductCodes(@RequestParam("batch") String codes, HttpServletRequest request) {
        log.info("进入ProductsController--deleteProudctByProductCodes方法 ");
        
        // 2.调用删除方法
        log.info("调用ProductsHandler--deleteProudctByProductCodes方法 参数:productCode ===" + codes);
        ResultVO rs = null;
        rs = productsHandler.batchDeleteProductByCodes(codes);
        
        // 3.返回结果
        if (rs == null) {
            rs = ResultVOUtil.error(Result.pub_exception.getCode(), Result.pub_exception.getMsg());
        }
        request.setAttribute(PubConstant.RESULTVO, rs);
        log.info("ProductsController--deleteProudctByProductCode方法结束返回结果 rs ===" + rs);
        return rs;
    }
    
    @ApiOperation(value = "通过关键字查询产品", notes = "查询产品时可以分类联用进行查找")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keyWord", value = "查找的关键字", required = false, dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "catalogId", value = "分类id", required = false, dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "pageIndex", value = "第几页", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "每页显示多少条", required = false, dataType = "Integer", paramType = "query")})
    @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/products/keyWord")
    public ResultPageVO getProductsByKeyWords(@RequestParam(value = "keyWord", required = false) String keyWord,
        @RequestParam(value = "catalogId", required = false) BigDecimal catalogId,
        @RequestParam(value = "code", required = true) String code,
        @RequestParam(value = "pageIndex", required = false) Integer pageIndex,
        @RequestParam(value = "pageSize", required = false) Integer pageSize,HttpServletRequest request) {
        // 1.根据用户code得到用户信息(type)
        ResultPageVO resultPageVO = new ResultPageVO();
        ResultVO verifyInfo = productsHandler.verifiedUser(code);
        if (null == verifyInfo.getData()) {
            resultPageVO.setCode(verifyInfo.getCode());
            resultPageVO.setMsg(verifyInfo.getMsg());
            return resultPageVO;
        }
        // 2.根据type和queryMap去获得首页数据
        String type = String.valueOf(verifyInfo.getData());
        JSONArray resultJsonArray = null;
        if (StringUtils.isEmpty(keyWord) && catalogId == null) {
            log.info("getProductsByKeyWords == keyowrd and catalogId both null --enter getAllProducts()");
            resultJsonArray = productsHandler.getAllProducts(type, pageIndex, pageSize);
        }
        else {
            resultJsonArray =
                productsHandler.getProductsByKeyWordsOrCatalogId(type, keyWord, catalogId, pageIndex, pageSize);
        }
        
        // 3.返回结果
        // todo:返回方式待修改
        ResultPageVO pageVO = null;
        ResultVO rs = null;
        if (null != resultJsonArray) {
            JSONObject pageInfo = (JSONObject)resultJsonArray.get(resultJsonArray.size() - 1);
            resultJsonArray.remove(resultJsonArray.size() - 1);
            rs = ResultVOUtil.success(resultJsonArray);
            pageVO = ResultVOUtil
                .success(resultJsonArray, pageInfo.getInteger("TOTALPAGE"), pageInfo.getInteger("TOTALCOUNT"));
        }
        else {
            rs = ResultVOUtil.error(String.valueOf(HttpStatus.BAD_REQUEST), PubConstant.NOT_FOUNT_MSG);
            pageVO = new ResultPageVO();
            pageVO.setCode(rs.getCode());
            pageVO.setMsg(rs.getMsg());
        }
        request.setAttribute(PubConstant.TYPE, type);
        request.setAttribute(PubConstant.RESULTVO, rs);
        log.info("ProductsController--getProductListByPage 结束返回结果 rs ==" + rs);
        return pageVO;
        
    }
    
    @ApiOperation(value = "插入测试")
    @GetMapping(value = "/insert")
    public ResultVO insertProudct(HttpServletRequest request) {
        log.error(
            "-----------------" + request.getContextPath() + "     " + StringUtils.isEmpty(request.getContextPath()));
        return null;
    }
}
