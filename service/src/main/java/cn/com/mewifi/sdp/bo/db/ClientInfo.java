package cn.com.mewifi.sdp.bo.db;

import lombok.Data;

import java.util.Date;

/**
 * 下游信息
 * description:
 * todo:
 * 1. 客户端的管理系统密码/ftp密码考虑放到扩展表中, clientInfo中只存放基本信息, 便于以后扩展
 *
 * author: wangjc
 * date: 2017/9/11 10:18
 */
@Data
public class ClientInfo {

    private String userId; //分配给下游的id
    private String userName; // 下游名称
    private String key;  // 分配给下游的key; 重要, 不能泄露
    private String serverIps; // 下游用于请求接口的服务器IP, 不在此列的ip请求接口将被禁用;
    private String status; //状态;
    private Date createTime; //创建时间
    private Date expireTime; //失效时间
    private String operator;// 创建者


}
