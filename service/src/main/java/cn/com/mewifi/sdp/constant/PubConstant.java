package cn.com.mewifi.sdp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 静态变量
 * description:
 * author: wangjc
 * date: 2017/9/12 10:37
 */
public class PubConstant {

    public enum SysPropertis{
        lengthOfSerialNo("30","流水号默认长度");

        SysPropertis(String value,String desc) {
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
}
