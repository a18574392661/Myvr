package com.bootdo.pay.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.bootdo.pay.PayConfig;

/**
 * 签名工具类
 * @author 
 * @date 2018年09月3日
 * */
public class SignUtils {

   /**
    * @param characterEncoding 编码格式 utf-8
    * */
   public static String creatSign(String characterEncoding,
         TreeMap<String, String> parameters) {
      StringBuffer sb = new StringBuffer();
      Set es = parameters.entrySet();
      Iterator it = es.iterator();
      while(it.hasNext()) {
         Map.Entry entry = (Map.Entry)it.next();
         String k = (String)entry.getKey();
         Object v = entry.getValue();
         if(null != v && !"".equals(v) 
               && !"sign".equals(k) && !"key".equals(k)) {
            sb.append(k + "=" + v + "&");
         }
      }
      sb.append("key=" + PayConfig.API_KEY);
      String sign = Md5Utils.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
      System.out.println(sign);
      return sign;
   }
}