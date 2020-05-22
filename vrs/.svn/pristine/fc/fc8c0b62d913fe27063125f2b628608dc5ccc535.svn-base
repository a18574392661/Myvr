package com.bootdo.pay.utils;

import java.io.StringReader;
import java.util.*;



import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;


public class XMLParser {
    
    /**
     * 日志
     * @return
     */
    public static Logger getLogger() {
        Logger logger = LoggerFactory.getLogger("wxpay java sdk");
        return logger;
    }
    

    public static String getRequestXml(TreeMap<String, String> parameters)
          throws Exception {
          StringBuffer sb = new StringBuffer();
          sb.append("<xml>");
          Set es = parameters.entrySet();
          Iterator it = es.iterator();
          while (it.hasNext()) {
          Map.Entry entry = (Map.Entry) it.next();
          String k = (String) entry.getKey();
          String v = (String) entry.getValue();
          if ("mch_id".equalsIgnoreCase(k) || "nonce_str".equalsIgnoreCase(k)
          || "sign".equalsIgnoreCase(k)) {
          sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
          } else {
          sb.append("<" + k + ">" + v + "</" + k + ">");
          }
          }
          sb.append("</xml>");
          return sb.toString();
          }
    
    public static String Progress_resultParseXml(String xml) {
        String publicKey = null;
        try {
            StringReader read = new StringReader(xml);

            InputSource source = new InputSource(read);

            SAXBuilder sb = new SAXBuilder();

            Document doc;
            doc = (Document) sb.build(source);

            Element root = doc.getRootElement();
            List<Element> list = root.getChildren();

            if (list != null && list.size() > 0) {
                for (Element element : list) {
                    if("pub_key".equals(element.getName())){
                        publicKey=element.getText();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return publicKey;
    }

}