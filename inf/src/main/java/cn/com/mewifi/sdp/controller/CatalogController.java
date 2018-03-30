package cn.com.mewifi.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.mewifi.sdp.constant.PubConstant;
import cn.com.mewifi.sdp.handler.CatalogsHandler;
import cn.com.mewifi.sdp.handler.ProductsHandler;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * description:
 * author: Administrator
 * date: 2017-9-20 下午 7:23
 */
@Data
@RestController
@Slf4j
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
@Api(value = "查询分类接口")
public class CatalogController {
    @Autowired
    /****分类service***/
    private CatalogsHandler catalogsHandler;
    
    @Autowired
    /*****产品逻辑处理***/
    private ProductsHandler productsHandler;
    
    @ApiOperation(value = "查询分类信息", notes = "用于查询分类",hidden = true)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query")})
    @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/catalogs/{catalogCode}")
    public ResultVO getCatalogs(@PathVariable(value = "catalogCode", required = false) String catalogCode,
        @RequestParam(value = "code", required = true) String code, HttpServletRequest request) {
        // 1.根据用户code得到用户信息(type)
        ResultVO verifyInfo = productsHandler.verifiedUser(code);
        if (null == verifyInfo.getData()) {
            return verifyInfo;
        }
        JSONArray jsonStr = null;
        JSONObject jsonObject = null;
        // 2.根据type去获得分类信息
        String type = String.valueOf(verifyInfo.getData());
        if (null != catalogCode) {
            jsonObject = catalogsHandler.getCatalogsByCodeAndType(type, catalogCode);
        }
        else {
            jsonStr = catalogsHandler.getCatalogsByType(type);
        }
        
        // 3.返回结果
        ResultVO rs = null;
        if (null != jsonStr) {
            rs = ResultVOUtil.success(jsonStr);
        }
        else if (null != jsonObject) {
            rs = ResultVOUtil.success(jsonObject);
        }
        else {
            rs = ResultVOUtil.error(String.valueOf(HttpStatus.BAD_REQUEST), PubConstant.NOT_FOUNT_MSG);
        }
        request.setAttribute(PubConstant.TYPE,type);
        request.setAttribute(PubConstant.RESULTVO,rs);
        return rs;
        
    }
    
    @ApiOperation(value = "查询分类信息", notes = "用于查询分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "code", value = "用户code", required = true, dataType = "String", paramType = "query")})
    @GetMapping(value = "/" + PubConstant.LAST_VERSION + "/catalogs")
    public ResultVO getCatalogs(@RequestParam(value = "code", required = true) String code, HttpServletRequest request) {
        return getCatalogs(null, code,request);
        
    }
}
