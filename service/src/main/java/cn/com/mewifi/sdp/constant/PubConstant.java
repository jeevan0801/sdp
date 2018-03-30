package cn.com.mewifi.sdp.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

/**
 * 静态变量
 * description:
 * author: wangjc
 * date: 2017/9/12 10:37
 */
public class PubConstant {
    public static final String LAST_VERSION = "v1";
    
    public static final String NOT_FOUNT_MSG = "未找到相关信息!";
    
    /**type***/
    public static final String TYPE = "type";
    
    /**type***/
    public static final String RESULTVO = "resultvo";
    
    public enum SysPropertis {
        lengthOfSerialNo("30", "流水号默认长度");
        
        SysPropertis(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }
        
        @Getter
        private String value;
        
        @Getter
        private String desc;
    }
    
    /**
    * 标识布尔值
    */
    public enum Bool {
        YES("1", "是"), NO("0", "否");
        
        @Getter
        private String code;
        
        @Getter
        private String desc;
        
        Bool(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
    
    /**
     * 系统模块
     */
    public enum ModuleType {
        SMS("SMS", "短信发送模块");
        
        @Getter
        private String code;
        
        @Getter
        private String name;
        
        ModuleType(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }
    
    /**
     * 消息发送类型
     */
    public enum MessageType {
        SMS("1", "SMS"), EMAIL("2", "EMAIL");
        
        @Getter
        private String code;
        
        @Getter
        private String name;
        
        MessageType(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }
    
    /*****
     * 数据查找类型
     */
    public enum FindType {
        USER_NOTFOUND(HttpStatus.BAD_REQUEST.value(), "用户信息未找到!"), USER_TYPE_NOTFOUND(HttpStatus.BAD_REQUEST.value(),
            "用户所属类型未找到!"), SPINFO_NOTFOUND(HttpStatus.BAD_REQUEST.value(), "公司信息未找到!"), PRODUCT_CODE_EXITS(
                HttpStatus.NOT_ACCEPTABLE.value(),
                "productCode已存在,请更换"), PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "未找到,请确定productCode是否正确");
        @Getter
        private int code;
        
        @Getter
        private String desc;
        
        FindType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
    
    /****
     * 版本号
     */
    public enum VersionInfo {
        
        LASTVERSION("V1", "接口最新版本");
        @Getter
        private String msg;
        
        @Getter
        private String version;
        
        VersionInfo(String version, String msg) {
            this.version = version;
            this.msg = msg;
        }
    }
    
    public enum StatusType {
        AVAILABLE("1", "状态可用");
        @Getter
        private String code;
        
        @Getter
        private String desc;
        
        StatusType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
    
    public enum dataError {
        error("-1", "数据解析错误,请检查数据");
        @Getter
        private String code;
        
        @Getter
        private String desc;
        
        dataError(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
    
    /****
     * 产品类型
     */
    public enum ProductType {
        PRODUCT_TYPE_ONE_MONTH(new BigDecimal("1"), "月"), PRODUCT_TYPE_THREE_MONTH(new BigDecimal("3"),
            "月"), PRODUCT_TYPE_SIX_MONTH(new BigDecimal("6"), "月"), PRODUCT_TYPE_ONE_YEAR(new BigDecimal("12"),
                "月"), PRODUCT_TYPE_MONEY(new BigDecimal("0"), "元"), PRODUCT_TYPE_NUMBER(new BigDecimal("13"), "个");
        
        @Getter
        private BigDecimal tradeSize;
        
        @Getter
        private String unit;
        
        ProductType(BigDecimal tradeSize, String unit) {
            this.tradeSize = tradeSize;
            this.unit = unit;
        }
        
    }
    
    /****
     * 产品规格
     */
    public enum ProductSpec {
        PRODUCT_SPEC_ONE_MONTH(new BigDecimal("1"), "月卡"), PRODUCT_SPEC_THREE_MONTH(new BigDecimal("3"),
            "季卡"), PRODUCT_SPEC_SIX_MONTH(new BigDecimal("6"),
                "半年卡"), PRODUCT_SPEC_ONE_YEAR(new BigDecimal("12"), "年卡");
        
        @Getter
        private BigDecimal tradeSize;
        
        @Getter
        private String spec;
        
        ProductSpec(BigDecimal tradeSize, String spec) {
            this.tradeSize = tradeSize;
            this.spec = spec;
        }
    }
    
    /****
     * zzwx平台类型
     */
    public enum SdpType {
        SDP_TYPE_MEMBER("mem", "会员权益平台");
        @Getter
        private String type;
        
        @Getter
        private String desc;
        
        SdpType(String type, String desc) {
            this.type = type;
            this.desc = desc;
        }
    }

    /****
     * 文件路径
     */
    public enum FilePath{
        PRODUCT_IMG_PATH("/static/img/","产品背景图片路径");
        @Getter
        private String url;

        @Getter
        private String desc;

        FilePath(String url, String desc) {
            this.url = url;
            this.desc = desc;
        }
    }

    /****
     * http协议
     */
    public enum ServerSchema{
        HTTP_SCHEMA("http://","http地址的协议"),
        HTTPS_SCHEMA("https://","http地址的协议");
        @Getter
        private String schema;

        @Getter
        private String desc;

        ServerSchema(String schema, String desc) {
            this.schema = schema;
            this.desc = desc;
        }
    }
    /****
     * 流水号enum  modelName 模块名,length,流水号长度
     */
    public enum SerialNo{
        MEMBER_ORDERNO("MEM_ORDER",25);

        @Getter
        private String modelName;
        @Getter
        private Integer length;

        SerialNo(String modelName,Integer length){
            this.modelName = modelName;
            this.length = length;
        }

    }

    /*****
     *
     */
    public enum PreFlag{
        HAVE("1","流水号中带模块名"),
        NO("0","流水号中不带模块名");

        @Getter
        private String isHas;
        @Getter
        private String desc;

        PreFlag(String isHas,String desc){
            this.isHas = isHas;
            this.desc = desc;
        }

    }

}
