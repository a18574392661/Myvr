package com.bootdo.pay.utils;

//不通用的、返回Bean格式

//以企业付款到零钱为例子~~根据Api会返回的参数，书写一个Bean类型

import java.io.IOException;
import java.io.StringReader;
import java.util.List;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

/**
* 
* 企业 付款到 客户  的 实体类
* @version 1.0 
* @description: 收集企业 支付给客户成功后的返回信息
* @time : 2018-01-16 16:00：00
*/
public class EnterpriceToCustomer {
 /*  <xml>
  <return_code><![CDATA[SUCCESS]]></return_code>
  <return_msg><![CDATA[]]></return_msg>
  <mchid><![CDATA[1488323162]]></mchid>
  <nonce_str><![CDATA[o9fcpfvqow1aks48a2omvayu1ne7c709]]></nonce_str>
  <result_code><![CDATA[SUCCESS]]></result_code>
  <partner_trade_no><![CDATA[xvuct0087w4t1dpr87iqj98w5f71ljae]]></partner_trade_no>
  <payment_no><![CDATA[1000018301201801163213961289]]></payment_no>
  <payment_time><![CDATA[2018-01-16 14:52:16]]></payment_time>
  </xml>
*/

private String return_code;
private String return_msg;
private String mch_id;
private String nonce_str;
private String result_code;
private String partner_trade_no;
private String payment_no;
private String payment_time;

/*
* 支付错误时，返回的代码
*  key是：return_code，值是：SUCCESS
  key是：return_msg，值是：支付失败
  key是：mch_appid，值是：wx49c22ad731b679c3
  key是：mchid，值是：1488323162
  key是：result_code，值是：FAIL
  key是：err_code，值是：AMOUNT_LIMIT
  key是：err_code_des，值是：付款金额超出限制。低于最小金额1.00元或累计超过20000.00元。
* 
*/
private String err_code;
private String err_code_des;

public String getErr_code() {
  return err_code;
}
public void setErr_code(String errCode) {
  err_code = errCode;
}
public String getErr_code_des() {
  return err_code_des;
}
public void setErr_code_des(String errCodeDes) {
  err_code_des = errCodeDes;
}
public String getReturn_code() {
  return return_code;
}
public void setReturn_code(String returnCode) {
  return_code = returnCode;
}
public String getReturn_msg() {
  return return_msg;
}
public void setReturn_msg(String returnMsg) {
  return_msg = returnMsg;
}

public String getMch_id() {
 return mch_id;
}
public void setMch_id(String mch_id) {
 this.mch_id = mch_id;
}
public String getNonce_str() {
  return nonce_str;
}
public void setNonce_str(String nonceStr) {
  nonce_str = nonceStr;
}
public String getResult_code() {
  return result_code;
}
public void setResult_code(String resultCode) {
  result_code = resultCode;
}
public String getPartner_trade_no() {
  return partner_trade_no;
}
public void setPartner_trade_no(String partnerTradeNo) {
  partner_trade_no = partnerTradeNo;
}
public String getPayment_no() {
  return payment_no;
}
public void setPayment_no(String paymentNo) {
  payment_no = paymentNo;
}
public String getPayment_time() {
  return payment_time;
}
public void setPayment_time(String paymentTime) {
  payment_time = paymentTime;
}
@Override
public String toString() {
  return "EnterpriceToCustomer [err_code=" + err_code + ", err_code_des="
          + err_code_des + ", mch_id=" + mch_id + ", nonce_str="
          + nonce_str + ", partner_trade_no=" + partner_trade_no
          + ", payment_no=" + payment_no + ", payment_time="
          + payment_time + ", result_code=" + result_code
          + ", return_code=" + return_code + ", return_msg=" + return_msg
          + "]";
}
 
/** 
  下面是需要通过跟节点，找找到对应的类属性，手动把它set进去。因此API返回的参数不一样。需要写每个返回的Bean。看个人的习惯呗~~我喜欢用bean存储数据的方式
  * 解析企业支付申请 
  * 解析的时候自动去掉CDMA 
  * @param xml 
  */ 
  @SuppressWarnings("unchecked") 
  public static EnterpriceToCustomer parseXmlToMapEnterpriceToCustomer(String xml){ 
          EnterpriceToCustomer enterpriceToCustomer = new EnterpriceToCustomer(); 
          try { 
                  StringReader read = new StringReader(xml); 
                  // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入 
                  InputSource source = new InputSource(read); 
                  // 创建一个新的SAXBuilder 
                  SAXBuilder sb = new SAXBuilder(); 
                  // 通过输入源构造一个Document 
                  Document doc; 
                  doc = (Document) sb.build(source); 

                  Element root = doc.getRootElement();// 指向根节点 
                  List<Element> list = root.getChildren();

                  if(list!=null&&list.size()>0){ 
                  for (Element element : list) { 
                      System.out.println("key是："+element.getName()+"，值是："+element.getText()); 
                      if("return_code".equals(element.getName())){ 
                              enterpriceToCustomer.setReturn_code(element.getText()); 
                          } 

                      if("return_msg".equals(element.getName())){ 
                              enterpriceToCustomer.setReturn_msg(element.getText()); 
                          } 

                      if("mch_id".equals(element.getName())){ 
                          enterpriceToCustomer.setMch_id(element.getText()); 
                      }

                      if("nonce_str".equals(element.getName())){ 
                          enterpriceToCustomer.setNonce_str(element.getText()); 
                      }
                      if("result_code".equals(element.getName())){ 
                          enterpriceToCustomer.setResult_code(element.getText()); 
                      }
                      if("partner_trade_no".equals(element.getName())){ 
                          enterpriceToCustomer.setPartner_trade_no(element.getText()); 
                      }
                      if("payment_no".equals(element.getName())){ 
                          enterpriceToCustomer.setPayment_no(element.getText()); 
                      }
                      if("payment_time".equals(element.getName())){ 
                          enterpriceToCustomer.setPayment_time(element.getText()); 
                      }   
                      //错误的编码
                      /*
                         private String err_code;
                         private String err_code_des;
                       * */
                      if("err_code".equals(element.getName())){ 
                          enterpriceToCustomer.setErr_code(element.getText()); 
                      }
                      if("err_code_des".equals(element.getName())){ 
                          enterpriceToCustomer.setErr_code_des(element.getText()); 
                      }   

                  }
              }

          } catch (JDOMException e) { 
          e.printStackTrace(); 
          } catch (IOException e) { 
          e.printStackTrace(); 
          }catch (Exception e) { 
          e.printStackTrace(); 
          } 

          return enterpriceToCustomer; 
      }
}