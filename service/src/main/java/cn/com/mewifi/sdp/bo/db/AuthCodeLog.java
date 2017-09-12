package cn.com.mewifi.sdp.bo.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 验证码发送日志
 */
public class AuthCodeLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal id;
    private Date logdate;
    private String receiver;
    private String authtype;
    private String authcode;
    private String ischecked;
    private String sender;
    private BigDecimal messageid;
    
    public BigDecimal getId() {
        return id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public Date getLogdate() {
        return logdate;
    }
    
    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }
    
    public String getReceiver() {
        return receiver;
    }
    
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }
    
    public String getAuthtype() {
        return authtype;
    }
    
    public void setAuthtype(String authtype) {
        this.authtype = authtype == null ? null : authtype.trim();
    }
    
    public String getAuthcode() {
        return authcode;
    }
    
    public void setAuthcode(String authcode) {
        this.authcode = authcode == null ? null : authcode.trim();
    }
    
    public String getIschecked() {
        return ischecked;
    }
    
    public void setIschecked(String ischecked) {
        this.ischecked = ischecked == null ? null : ischecked.trim();
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }
    
    public BigDecimal getMessageid() {
        return messageid;
    }
    
    public void setMessageid(BigDecimal messageid) {
        this.messageid = messageid;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", logdate=").append(logdate);
        sb.append(", receiver=").append(receiver);
        sb.append(", authtype=").append(authtype);
        sb.append(", authcode=").append(authcode);
        sb.append(", ischecked=").append(ischecked);
        sb.append(", sender=").append(sender);
        sb.append(", messageid=").append(messageid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}