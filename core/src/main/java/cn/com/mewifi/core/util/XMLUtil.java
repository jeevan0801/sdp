package cn.com.mewifi.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * description:
 *
 * author: wangjc
 *
 * date: 2017/8/29 13:55
 */
public class XMLUtil {
    
    /**
     * 将xml字符串转为Map
     * 
     * @param xml
     * @return
     * @throws Exception
     */
    private Map<String, String> parseXml(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for (Element chidlren : elements) {
                map.put(chidlren.getName(), chidlren.getText());
            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }
    
}
