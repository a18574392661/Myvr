package com.bootdo.pay;

import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.Format;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.pay.utils.UUID;
import com.bootdo.vrs.dao.PayorderLogDao;
import com.bootdo.vrs.domain.PayorderLogDO;
import com.bootdo.vrs.service.PayorderLogService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.xerces.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.OrderCodeUtils;
import com.bootdo.pay.utils.ClientCustomSSL;
import com.bootdo.pay.utils.EnterpriceToCustomer;
import com.bootdo.pay.utils.HttpClientCustomSSL;
import com.bootdo.pay.utils.HttpUtil;
import com.bootdo.pay.utils.PayCommonUtil;
import com.bootdo.pay.utils.RSAUtil;
import com.bootdo.pay.utils.SignUtils;
import com.bootdo.pay.utils.XMLParser;
import com.bootdo.pay.utils.XMLUtil;


@Controller
@RequestMapping("/api/wxpay")
public class WXPayController  extends BaseController{

	@Autowired
	private PayorderLogService payorderLogService;
 
	private static final Logger logger = LoggerFactory.getLogger(WXPayController.class);
	private Boolean paySuccessFlg;//充值成功回调标识
    /**
	 * 获取用户实际ip
	 * @param request
	 * @return
	 */
	 public String getIpAddr(HttpServletRequest request){  
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        e.printStackTrace();  
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
	  }

	 


		//微信支付，生成二维码
		@RequestMapping(value = "payImg")
		public void payImg(HttpServletResponse response,HttpServletRequest request) throws Exception, IOException {
			int defaultWidthAndHeight = 200;
			String context=request.getParameter("context");
			//调用自己业务逻辑处理
		//	OrderVM orderVM = orderRecordService.addOrder(addOrderRecordPM);
			//调用支付类进行支付
		//	Map map = WeixinPay.getCodeUrl(orderVM);
		//	String urlCode = (String) map.get("code_url");
			//生成二维码
			Map<EncodeHintType, Object> hints = new HashMap<>();
			// 指定纠错等级
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			// 指定编码格式
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 1);
			try {
				BitMatrix bitMatrix = new MultiFormatWriter().encode(context, BarcodeFormat.QR_CODE, defaultWidthAndHeight, defaultWidthAndHeight, hints);
				OutputStream out = response.getOutputStream();
				MatrixToImageWriter.writeToStream(bitMatrix, "png", out);//输出二维码
				out.flush();
				out.close();
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}




	/**
	 * 交易类型	trade_type	是	String(16)	JSAPI	 JSAPI -JSAPI支付   NATIVE -Native支付   APP -APP支付
	 预支付交易会话标识	prepay_id	是	String(64)	wx201410272009395522657a690389285100	微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
	 二维码链接	code_url	否	String(64)	weixin://wxpay/bizpayurl/up?pr=NwY5Mz9&groupid=00	 trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
	 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
	 * @param request
	 * @return Map
	 */

	@RequestMapping("/wxPayNative")
		@ResponseBody
		public SortedMap<Object, Object> orders(HttpServletRequest request,HttpServletResponse response) {
			try {
				String month=request.getParameter("month");
				//拼接统一下单地址参数
				SortedMap<Object, Object> paraMap = new TreeMap<Object, Object>();
				//获取请求ip地址
				String ip = request.getHeader("x-forwarded-for");
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			        ip = request.getHeader("Proxy-Client-IP");
			    }
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
 			        ip = request.getHeader("WL-Proxy-Client-IP");
			    }
			    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			        ip = request.getRemoteAddr();
			    }
			    if(ip.indexOf(",")!=-1){
			    	String[] ips = ip.split(",");
			    	ip = ips[0].trim();
			    }
			    String paternerKey = PayConfig.API_KEY;
			    String ordercode = request.getParameter("ordercode");//OrderCodeUtils.getTenNumberCode();//十位数的订单编号
			    String currTime = PayCommonUtil.getCurrTime();
				String strTime = currTime.substring(8, currTime.length());
				String strRandom = PayCommonUtil.buildRandom(4) + "";
				String nonce_str = strTime + strRandom;
				// 价格 注意：价格的单位是分
				String total_fee = request.getParameter("WIDtotal_fee");//传递的参数,支付金额
				String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
			    String body = "开通云视家会员";
				paraMap.put("appid", PayConfig.APP_ID);  
				paraMap.put("body", body);  
				paraMap.put("mch_id", PayConfig.MCH_ID);  
				paraMap.put("nonce_str", PayCommonUtil.generateNonceStr());  
				//paraMap.put("openid", openId);
				paraMap.put("out_trade_no", ordercode);//订单号
				paraMap.put("spbill_create_ip", ip);  
				paraMap.put("total_fee", order_price);  
				paraMap.put("trade_type", "NATIVE");//"JSAPI");
				//
				paraMap.put("notify_url",PayConfig.NOTIFY_URL_H5);// 此路径是微信服务器调用支付结果通知路径随意写
				paraMap.put("sign_type","MD5");
				String sign = PayCommonUtil.createSign("UTF-8", paraMap, paternerKey);//WXPayUtil.generateSignature(paraMap, paternerKey);
				paraMap.put("sign", sign);
				String xml = PayCommonUtil.getRequestXml(paraMap);
				System.out.println("************************Native支付****************************");
				System.out.println(xml);
				// 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
				String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
 				String xmlStr = HttpUtil.sendPost(unifiedorder_url, xml);//发送post请求"统一下单接口"返回预支付id:prepay_id
				System.out.println(xmlStr);
				//以下内容是返回前端页面的json数据
				String prepay_id = "";//预支付id
				String codeUrl="";
			//	JSONObject json = JSONObject.parseObject(xmlStr);//转成Json格式
				if (xmlStr.indexOf("SUCCESS") != -1) {  
					Map<String, String> map = PayCommonUtil.xmlToMap(xmlStr);  
					prepay_id = (String) map.get("prepay_id");
					System.out.println("---------------------------");
					System.out.println(map);
					codeUrl=(String)map.get("code_url");
				}
				SortedMap<Object, Object> payMap = new TreeMap<Object, Object>();
				payMap.put("appId", PayConfig.APP_ID);  
				payMap.put("timeStamp", PayCommonUtil.getCurrTime()+"");  
				payMap.put("nonceStr", PayCommonUtil.generateNonceStr());  
				payMap.put("signType", "MD5");  
				payMap.put("package", "prepay_id=" + prepay_id);  
				String paySign = PayCommonUtil.createSign("UTF-8", payMap, paternerKey);
				payMap.put("paySign", paySign);
			    payMap.put("codeUrl", codeUrl);
				payMap.put("ordercode", ordercode);
				System.out.println(payMap.toString());
				return payMap;
			} catch (Exception e) {  
				e.printStackTrace();
			}  
			return null;
		}


		@ResponseBody
		@RequestMapping("/tinesPayStatus")
		public boolean tinesPayStatus(String ordercode){
		try {
			PayorderLogDO payorderLogDO=payorderLogService.getPayStatus(ordercode);
			if (payorderLogDO!=null){
				if (payorderLogDO.getStatus()==1){
					return  true;
				}
			}
			//拿到订单号去查询支付状态
			SortedMap<Object, Object> paraMap = new TreeMap<Object, Object>();
			paraMap.put("appid",PayConfig.APP_ID);
			paraMap.put("mch_id",PayConfig.MCH_ID);
			paraMap.put("out_trade_no",ordercode);
			paraMap.put("nonce_str", PayCommonUtil.generateNonceStr());
			String sing=PayCommonUtil.createSign("UTF-8", paraMap, PayConfig.API_KEY);
			paraMap.put("sign",sing);
			String xml = PayCommonUtil.getRequestXml(paraMap);
			//查询支付结果接口
			String xmlStr = HttpUtil.sendPost("https://api.mch.weixin.qq.com/pay/orderquery", xml);
			System.out.println(xmlStr+"//报文");
			Map<String, String> map = PayCommonUtil.xmlToMap(xmlStr);
			String	return_code = (String) map.get("return_code");
			if ("SUCCESS".equals(return_code)){
				String	result_code = (String) map.get("result_code");
				if ("SUCCESS".equals(result_code)) {
					String	trade_state = (String) map.get("trade_state");
					if ("SUCCESS".equals(trade_state)){
						//防止刚好跳过已支付修改了业务逻辑
						PayorderLogDO payorderLogDO1=payorderLogService.getPayStatus(ordercode);
						if (payorderLogDO1!=null){
							if (payorderLogDO1.getStatus()==0){
								String transaction_id=map.get("transaction_id");
								String trade_type=map.get("trade_type");
								//String attach=map.get("attach");
								payorderLogDO.setPayDate(new Date());
								payorderLogDO.setContext(trade_type); //拿到支付一些信息
								payorderLogDO.setTradeno(transaction_id);//流水号
								payorderLogDO.setBw(JSON.toJSONString(map));
								payorderLogService.editPayStatus(payorderLogDO);
								return  true;
							}
						}

					}

				}


			}
		}
		catch (Exception e){
			e.printStackTrace();
		}


			return  false;
		}

	 
	 /**
		 * 微信H5支付
		 *
		 * @param request
		 * @param response
		 * @param model
		 * @throws Exception
		 */
		@RequestMapping("/wxPayH5")
		public void wxPayH5(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", false);
			try {
				// 付款金额，必填
				String total_fee = request.getParameter("WIDtotal_fee");
				// 充值类型：1 积分 2 现金
				//czstyle = Integer.valueOf(request.getParameter("czstyle"));
				// ip地址获取
				String basePath = request.getServerName() + ":" + request.getServerPort();
				// 账号信息
				String appid = PayConfig.APP_ID; // appid
				String mch_id = PayConfig.MCH_ID; // 商业号
				String key = PayConfig.API_KEY; // key
	 
				String currTime = PayCommonUtil.getCurrTime();
				String strTime = currTime.substring(8, currTime.length());
				String strRandom = PayCommonUtil.buildRandom(4) + "";
				String nonce_str = strTime + strRandom;
				// 价格 注意：价格的单位是分
				String order_price = new BigDecimal(total_fee).multiply(new BigDecimal(100)).toString().split("\\.")[0];
				// 自己网站上的订单号
				//RandomGUID id = new RandomGUID();
				String out_trade_no = OrderCodeUtils.getTenNumberCode();//id.toString().substring(0, 32);//TODO 我们自己的订单编号
				// 获取发起电脑 ip
				String spbill_create_ip = HttpUtil.getRealIp(request);
				
				// 回调接口
				String notify_url = PayConfig.NOTIFY_URL_H5;//.replaceAll("localhostUrl", basePath) + getCurrentUser().getId();//回调地址
				// 页面跳转同步通知页面路径
				String trade_type = "MWEB";
	 
				String body = "免单节认购";
				
				// 设置package订单参数
				SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
				packageParams.put("appid", appid);
				packageParams.put("mch_id", mch_id);
				// 生成签名的时候需要你自己设置随机字符串
				packageParams.put("nonce_str", nonce_str);
				packageParams.put("out_trade_no", out_trade_no);
				packageParams.put("total_fee", order_price);
				packageParams.put("spbill_create_ip", spbill_create_ip);
				packageParams.put("notify_url", notify_url);
				packageParams.put("trade_type", trade_type);
				packageParams.put("body", body);
				packageParams.put("scene_info", "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"http://driver.yuemaxc.cn\",\"wap_name\": \"悦马学车\"}}");
				String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
				
				System.out.println("============sign==============" + sign);
				
				packageParams.put("sign", sign);
				String requestXML = PayCommonUtil.getRequestXml(packageParams);
				String resXml = HttpUtil.postData(PayConfig.UFDODER_URL, requestXML);
				

				Integer userId = Integer.parseInt(request.getParameter("userId"));
				Long mdjId = Long.parseLong(request.getParameter("mdjId"));
				
				Map map = XMLUtil.doXMLParse(resXml);
				String urlCode = (String) map.get("code_url");
				//确认支付过后跳的地址,需要经过urlencode处理
				String urlString = URLEncoder.encode("http://driver.yuemaxc.cn/freeorder/paySuccess/"+out_trade_no + "?userId=" + userId + "&mdjId=" + mdjId, "GBK");
				String mweb_url = map.get("mweb_url")+"&redirect_url="+urlString;
				//czflg = true;
				response.sendRedirect(mweb_url);
				//LogUtil.writeMsgToFile("before ----" + czflg);
				result.put("sHtmlText", urlCode);
				result.put("success", true);
			} catch (Exception e) {
				//LogUtil.writeMsgToFile(e);
				result.put("errormsg", e.getMessage());
			}
		}
		
		/**
		 * 执行回调 确认支付后处理事件 例如添加金额到数据库等操作
		 * 
		 * @param request
		 * @param response
		 * @throws Exception
		 */
		@Transactional
		@RequestMapping("/payNotify")
		public synchronized  void weixin_notify(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception {
			System.out.println("进入支付h5回调=====================");
			String xmlMsg = readData(request);
			System.out.println("pay notice---------"+xmlMsg);
			Map params = XMLUtil.doXMLParse(xmlMsg);
//			String appid  = params.get("appid");
//			//商户号
//			String mch_id  = params.get("mch_id");
			String result_code  = params.get("result_code")+"";
//			String openId      = params.get("openid");
//			//交易类型
			String trade_type      = params.get("trade_type")+"";
//			//付款银行
//			String bank_type      = params.get("bank_type");
//			// 总金额
  		   // String total_fee     = params.get("total_fee");
//			//现金支付金额
//			String cash_fee     = params.get("cash_fee");
//			// 微信支付订单号
         		String transaction_id      = params.get("transaction_id")+"";
//			// 商户订单号
			String out_trade_no      =  params.get("out_trade_no") +"";
//			// 支付完成时间，格式为yyyyMMddHHmmss
//			String time_end      = params.get("time_end");
			
			/////////////////////////////以下是附加参数///////////////////////////////////
			String 	attach= params.get("attach")+"";

			//String userid = null;
			try {
				// 过滤空 设置 TreeMap
				SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
				Iterator it = params.keySet().iterator();
				while (it.hasNext()) {
					String parameter = (String) it.next();
					String parameterValue = params.get(parameter)+"";
					String v = "";
					if (null != parameterValue) {
						v = parameterValue.trim();
					}
					System.out.println("value==============="+v);
					packageParams.put(parameter, v);
				}
				// 查看回调参数
				// LogUtil.writeMsgToFile(packageParams.toString());
				String total_fee = new BigDecimal((String) packageParams.get("total_fee")).divide(new BigDecimal(100)).toString();
				//userid = (String) packageParams.get("userid");
				// 账号信息
				String resXml = "";
				// ------------------------------
				// 处理业务开始
				// ------------------------------
				if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
					// 这里是支付成功
					paySuccessFlg = true;
					//System.out.println("支付成功============"+czflg+"czstyle========="+czstyle);
					////////// 执行自己的业务逻辑////////////////
					model.put("sHtmlText", "充值成功");
					try {
						synchronized (paySuccessFlg) {
							if (paySuccessFlg) {
								PayorderLogDO payorderLogDO=payorderLogService.getPayStatus(out_trade_no);
								//payorderLogDO.setMonth(3);
								if (payorderLogDO.getStatus()==0){
									payorderLogDO.setPayDate(new Date());
								
									payorderLogDO.setContext(trade_type); //拿到支付一些信息
									payorderLogDO.setTradeno(transaction_id);
									payorderLogDO.setBw(JSON.toJSONString(params));
									payorderLogService.editPayStatus(payorderLogDO);

								}

								
								paySuccessFlg = false;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					////////// 执行自己的业务逻辑////////////////
					// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
					resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
				} else {
					//修改支付日志状态为支付失败
					//mdjPayLogsService.updateMdjPayLogsByOrdercode(out_trade_no, 3);
					
					model.put("sHtmlText", "充值失败");
					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[充值失败]]></return_msg>" + "</xml> ";
				}
				BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
				out.write(resXml.getBytes());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				//LogUtil.writeMsgToFile(e);
			}
	 
		}
				
		/**
		 * 提现到银行卡
		 * @param encBankAcctNo
		 * @param encBankAcctName
		 * @param bank_code
		 * @param desc
		 * @param amount
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping("/wxToBank")
		public synchronized static EnterpriceToCustomer WXPayToBC(String encBankAcctNo, String encBankAcctName, String bank_code, String desc,
			      String amount) throws Exception {
			   String partner_trade_no = RandomStringUtils.randomAlphanumeric(32);// 生成随机号
			   String nonce_str1 = RandomStringUtils.randomAlphanumeric(32);
			   String mch_id = PayConfig.MCH_ID;// 商户号的id
			   // 定义自己公钥的路径
			   PublicKey pub = RSAUtil.getPublicKey("RSA");
			   String rsa = "RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING";// rsa是微信付款到银行卡要求我们填充的字符串（Java）
			   try {
			      byte[] estr = RSAUtil.encrypt(encBankAcctNo.getBytes(), pub, 2048, 11, rsa);
			      // 对银行账号进行加密
			      encBankAcctNo = Base64.encode(estr);// 并转为base64格式
			      estr = RSAUtil.encrypt(encBankAcctName.getBytes("UTF-8"), pub, 2048, 11, rsa);
			      encBankAcctName = Base64.encode(estr); // 对银行账户名加密并转为base64
			   } catch (UnsupportedEncodingException e1) {
			      e1.printStackTrace();
			   } catch (Exception e1) {
			      e1.printStackTrace();
			   }
			   //根据要传递的参数生成自己的签名
			   TreeMap<String, String> parameters1 = new TreeMap<String, String>();
			   parameters1.put("mch_id", mch_id);// 商户号
			   parameters1.put("partner_trade_no", partner_trade_no);// 商户企业付款单号
			   parameters1.put("nonce_str", nonce_str1);// 随机字符串
			   parameters1.put("enc_bank_no", encBankAcctNo);// 收款方银行卡号
			   parameters1.put("enc_true_name", encBankAcctName);// 收款方用户名
			   parameters1.put("bank_code", bank_code);// 收款方开户行
			   parameters1.put("amount", amount);// 付款金额
			   parameters1.put("desc", desc);// 付款说明
			   // 调用签名方法
			   String sign = SignUtils.creatSign("utf-8", parameters1);
			   // 把签名放到map集合中
			   parameters1.put("sign", sign);// 签名
			   // 将当前的map结合转化成xml格式
			   String reuqestXml = XMLParser.getRequestXml(parameters1);
			   // 发送请求到企业付款到银行的Api。发送请求是一个方法来的POST
			   String wxUrl = "https://api.mch.weixin.qq.com/mmpaysptrans/pay_bank"; // 获取退款的api接口
			   EnterpriceToCustomer enterpriceToCustomer = null;
			   try {
			      // 调用方法发送了
			      String weixinPost = ClientCustomSSL.doRefund(wxUrl, reuqestXml).toString();
			      System.out.println(weixinPost);
			      // 解析返回的xml数据
			      enterpriceToCustomer = EnterpriceToCustomer
			            .parseXmlToMapEnterpriceToCustomer(weixinPost);
			      System.out.println(enterpriceToCustomer);
			      // 根据map中的result_code AND return_code来判断是否成功与失败
			      if ("SUCCESS".equalsIgnoreCase(enterpriceToCustomer.getResult_code())
			            && "SUCCESS".equalsIgnoreCase(enterpriceToCustomer.getReturn_code())) {
			         System.out.println("退款成功！");
			      } else {
			         //退款失败
			         System.err.println(enterpriceToCustomer.getErr_code_des());
			      }

			   } catch (Exception e) {
			      e.printStackTrace();
			   }
			   return enterpriceToCustomer;
			}

			// 生成RSA公钥，格式PKCS#1，需要转成PKCS#8格式 在线转换工具：http://www.ssleye.com/web/pkcs
			public static void getPublicKey() throws Exception {
			   TreeMap<String, String> parameters = new TreeMap<String, String>();
			   String nonce_str = RandomStringUtils.randomAlphanumeric(28);
			   parameters.put("mch_id", PayConfig.MCH_ID);
			   parameters.put("nonce_str", nonce_str);
			   parameters.put("sign_type", "MD5");
			   String sign = SignUtils.creatSign("utf-8", parameters); // 签名
			   // String sign = SignUtils.creatSign(WxSDKConfig.getAppSecret(), parameters); 
			   System.out.println(sign);

			   parameters.put("sign", sign); // 5.0将当前的map结合转化成xml格式
			   String reuqestXml = XMLParser.getRequestXml(parameters);

			   // 带证书请求
			   String xml1 = HttpClientCustomSSL.httpClientResultGetPublicKey(reuqestXml); //
			   String publicKey = XMLParser.Progress_resultParseXml(xml1);
			   System.out.println(publicKey);

			}

		public static String readData(HttpServletRequest request) {
			BufferedReader br = null;
			try {
				StringBuilder result = new StringBuilder();
				br = request.getReader();
				for (String line; (line=br.readLine())!=null;) {
					if (result.length() > 0) {
						result.append("\n");
					}
					result.append(line);
				}
	 
				return result.toString();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}


	public static void main(String[] args) {
		System.out.println("aaa");
		Map s=new HashMap();
		s.put("ss","dsa");
		s.put("ss1","dsa222");
		s.put("s2","dsa3333333");
		System.out.println(JSON.toJSONString(s));
	}
}