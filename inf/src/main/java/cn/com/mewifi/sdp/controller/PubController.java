package cn.com.mewifi.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.com.mewifi.sdp.service.IPubService;
import cn.com.mewifi.sdp.util.ResultVOUtil;
import cn.com.mewifi.sdp.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 公共类接口
 * description:
 * author: wangjc
 * date: 2017/9/14 19:02
 */
@RestController
@Slf4j
@RequestMapping(value = "/pub")
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
@Api(value = "公共类接口")
public class PubController {
    @Autowired
    IPubService iPubService;
    
    /**
     * 获取流水号
     * @return
     */
    @ApiOperation(value = "获取流水号", notes = "获取流水号")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "modelName", value = "模块编码大写", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "length", value = "长度", required = true, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "preFlag", value = "流水号中是否带模块编码.1:带;0:不带", required = true, dataType = "String", paramType = "query")})
    @GetMapping(value = "/serialNo/")
    public ResultVO getSerialNo(String modelName, int length, String preFlag) {
        // String modelName = SDPTypeEnum.MEMBER.getSpType();
        // int length = Integer.valueOf(PubConstant.SysPropertis.lengthOfSerialNo.getValue());
        // String preFlag = PubConstant.Bool.YES.getCode();
        
        String serialNo = iPubService.getSerialNo(modelName, length, preFlag);
        return ResultVOUtil.success(serialNo);
    }
}
