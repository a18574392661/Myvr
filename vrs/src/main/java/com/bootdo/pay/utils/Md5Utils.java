package com.bootdo.pay.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Utils {

	 private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

    private static byte[] md5(String s)
    {
        MessageDigest algorithm;
        try
        {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes("UTF-8"));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        }
        catch (Exception e)
        {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static final String toHex(byte hash[])
    {
        if (hash == null)
        {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++)
        {
            if ((hash[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s)
    {
        try
        {
            return new String(toHex(md5(s)).getBytes("UTF-8"), "UTF-8");
        }
        catch (Exception e)
        {
            log.error("not supported charset...{}", e);
            return s;
        }
    }
	
    public  static String calc(String ss){//MD5加密算法
    	String s = ss == null ? "":ss;//如果为空，则返回""
    	char hexDigists[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d',
    			'e','f'};//字典
    
    	try {
    		byte[] strTemp =s.getBytes();//获取二进制
			MessageDigest mdTemp =MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);//执行加密
			byte[] md = mdTemp.digest();//加密结果
			int j = md.length;//结果长度
			char str[] = new char[j*2];//字符数组
			int k = 0;
			for (int i = 0; i < j; i++) { //将二进制加密结果转化为字符
				byte byte0 = md[i];
				str[k++] = hexDigists[byte0 >>> 4 &0xf];
				str[k++] = hexDigists[byte0 & 0xf];
		
			}
			return new String(str);//输出加密后的字符
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
    	
    }
    
   public static String byteArrayToHexString(byte b[]) {
      StringBuffer resultSb = new StringBuffer();
      for (int i = 0; i < b.length; i++)
         resultSb.append(byteToHexString(b[i]));

      return resultSb.toString();
   }

   private static String byteToHexString(byte b) {
      int n = b;
      if (n < 0)
         n += 256;
      int d1 = n / 16;
      int d2 = n % 16;
      return hexDigits[d1] + hexDigits[d2];
   }

   public static String MD5Encode(String origin, String charsetname) {
      String resultString = null;
      try {
         resultString = new String(origin);
         MessageDigest md = MessageDigest.getInstance("MD5");
         if (charsetname == null || "".equals(charsetname))
            resultString = byteArrayToHexString(md.digest(resultString
                  .getBytes()));
         else
            resultString = byteArrayToHexString(md.digest(resultString
                  .getBytes(charsetname)));
      } catch (Exception exception) {
      }
      return resultString;
   }

   private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
         "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

}
