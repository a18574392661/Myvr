package com.bootdo.vrs.controller;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.pay.utils.PayCommonUtil;
import com.bootdo.vrs.domain.PayorderLogDO;
import com.bootdo.vrs.domain.UserDO;
import com.bootdo.vrs.service.PayorderLogService;
import com.bootdo.vrs.service.UserAllotService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-26 10:25:17
 */
 
@Controller
@RequestMapping("/vrs/payorderLog")
public class PayorderLogController extends BaseController {
	@Autowired
	private PayorderLogService payorderLogService;


	@Autowired
	private UserAllotService userService;

	
	@GetMapping()
	@RequiresPermissions("vrs:payorderLog:payorderLog")
	String PayorderLog(ModelMap map)
	{
		List<UserDO> userList=userService.list(null);
		map.put("ulist",userList);

		return "vrs/payorderLog/payorderLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:payorderLog:payorderLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (getUserId()!=1 && getUserId()!=138){
			params.put("uid",getUserId());
		}
        Query query = new Query(params);

		List<PayorderLogDO> payorderLogList = payorderLogService.list(query);
		int total = payorderLogService.count(query);
		PageUtils pageUtils = new PageUtils(payorderLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:payorderLog:add")
	String add(){
	    return "vrs/payorderLog/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:payorderLog:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PayorderLogDO payorderLog = payorderLogService.get(id);
		model.addAttribute("payorderLog", payorderLog);
	    return "vrs/payorderLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:payorderLog:add")
	public R save( PayorderLogDO payorderLog){
		if(payorderLogService.save(payorderLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:payorderLog:edit")
	public R update( PayorderLogDO payorderLog){
		payorderLogService.update(payorderLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:payorderLog:remove")
	public R remove( Long id){
		if(payorderLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:payorderLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		payorderLogService.batchRemove(ids);
		return R.ok();
	}


	public static void main(String[] args) {
		try {

			String bw="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx66b8b5da82c63493]]></appid><mch_id><![CDATA[1590586091]]></mch_id><nonce_str><![CDATA[hNDmaITkspfwcwVW]]></nonce_str><sign><![CDATA[CBD3F8FED7C99661B0BDEC5C0238473B]]></sign><result_code><![CDATA[SUCCESS]]></result_code><openid><![CDATA[owkJ9wkRnOANs3qfT99s1F1SFGd8]]></openid><is_subscribe><![CDATA[N]]></is_subscribe><trade_type><![CDATA[NATIVE]]></trade_type><bank_type><![CDATA[OTHERS]]></bank_type><total_fee>1</total_fee><fee_type><![CDATA[CNY]]></fee_type><transaction_id><![CDATA[4200000530202005110349712860]]></transaction_id><out_trade_no><![CDATA[2667046088231626]]></out_trade_no><attach><![CDATA[]]></attach><time_end><![CDATA[20200511144447]]></time_end><trade_state><![CDATA[SUCCESS]]></trade_state><cash_fee>1</cash_fee><trade_state_desc><![CDATA[支付成功]]></trade_state_desc><cash_fee_type><![CDATA[CNY]]></cash_fee_type></xml>";
			Map<String, String> map = PayCommonUtil.xmlToMap(bw);
			System.out.println(JSON.toJSONString(map));
		}
		catch (Exception e){
			e.printStackTrace();
		}

	}

}


