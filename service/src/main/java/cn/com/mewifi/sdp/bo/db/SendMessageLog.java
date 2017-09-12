package cn.com.mewifi.sdp.bo.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 消息发送日志
 */
public class SendMessageLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private BigDecimal id;
    private String messagecode;
    private String sender;
    private String receiver;
    private Date logdate;
    private String content;
    private String messagetype;
    private String sendchannel;
    
    public BigDecimal getId() {
        return id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getMessagecode() {
        return messagecode;
    }
    
    public void setMessagecode(String messagecode) {
        this.messagecode = messagecode == null ? null : messagecode.trim();
    }
    
    public String getSender() {
        return sender;
    }
    
    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }
    
    public String getReceiver() {
        return receiver;
    }
    
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }
    
    public Date getLogdate() {
        return logdate;
    }
    
    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    public String getMessagetype() {
        return messagetype;
    }
    
    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype == null ? null : messagetype.trim();
    }
    
    public String getSendchannel() {
        return sendchannel;
    }
    
    public void setSendchannel(String sendchannel) {
        this.sendchannel = sendchannel == null ? null : sendchannel.trim();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", messagecode=").append(messagecode);
        sb.append(", sender=").append(sender);
        sb.append(", receiver=").append(receiver);
        sb.append(", logdate=").append(logdate);
        sb.append(", content=").append(content);
        sb.append(", messagetype=").append(messagetype);
        sb.append(", sendchannel=").append(sendchannel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}