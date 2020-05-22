package com.bootdo.vrs.pageh5.controller;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.annotations.LoginRequired;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.vrs.common.BaseController;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.PayorderLogDao;
import com.bootdo.vrs.dao.PayvipDao;
import com.bootdo.vrs.domain.OrderCodeFactory;
import com.bootdo.vrs.domain.PayorderLogDO;
import com.bootdo.vrs.domain.PayvipDO;
import com.bootdo.vrs.service.PayorderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vrs/pay")
public class PayController  extends BaseController {


    @Autowired
    private PayorderLogService payorderLogService;

    @Autowired
    private PayvipDao payvipDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PayorderLogDao payorderLogDao;

    //去支付页面
   @RequestMapping("/to_pay")
   @LoginRequired
    public  String to_pay(HttpServletRequest request, String vid,String sta,String orderDisble,ModelMap map){
       Jedis jedis=redisUtil.getJedis();
       try {
           String uid=request.getAttribute("uid")+"";
           if (StringUtils.isBlank(vid)){
               return "redirect:/vrs/houses/index";
           }

           if (StringUtils.isBlank(uid)){
               //以防万一
               return "redirect:/vrs/houses/index";
           }

           //防止重复提交订单
           String codes=jedis.get(uid+":"+orderDisble);
           if (StringUtils.isNotBlank(codes)&&!("null".equals(codes))&&codes.equals(orderDisble)){
               //添加订单

               //根据选择的日期查询对应出价格 防止页面谋改
               PayvipDO payvipDa=payvipDao.get(Integer.parseInt(vid));
               if (payvipDa==null){
                   return "redirect:/vrs/houses/index";
               }
               //添加订单 未支付
               PayorderLogDO payorderLogDO=new PayorderLogDO();
               String code=OrderCodeFactory.generateNumber(16);
               payorderLogDO.setOrdercode(code);
               payorderLogDO.setCreatedate(new Date());
               payorderLogDO.setMonth(payvipDa.getMonth());
               payorderLogDO.setPaysta(1);
               payorderLogDO.setPrice(payvipDa.getPrice());
               payorderLogDO.setRemark("开通云视家会员:"+payvipDa.getRemker());
               payorderLogDO.setUid(Long.parseLong(uid));
               payorderLogDO.setStatus(0);//未支付

               int c=payorderLogService.save(payorderLogDO);
               map.put("price",payvipDa.getPrice());
               map.put("remker",payorderLogDO.getRemark());
               map.put("code",code);
               map.put("month",payvipDa.getMonth());

               //删除键
               jedis.del(uid+":"+orderDisble);
           }
           else{
               //返回上一次未支付的订单
             PayorderLogDO payorderLogDO= payorderLogDao.getMaxOrder(uid);
               map.put("price",payorderLogDO.getPrice());
               map.put("remker",payorderLogDO.getRemark());
               map.put("code",payorderLogDO.getOrdercode());
               map.put("month",payorderLogDO.getMonth());

           }


       }
       catch (Exception e){
           e.printStackTrace();
           //去错误页面
           return "redirect:/vrs/houses/index";
       }
       finally {
                if (jedis!=null)
                    jedis.close();
       }
       return MessageConstantVrs.VRSH5_PAGE+"html/payment";//去支付页面
   }







    public static void main(String[] args) {
        List<String> list=new ArrayList();
        for (int i = 0; i < 1000; i++) {
            String code=OrderCodeFactory.generateNumber(16);
            if (list.contains(code)==false){
                list.add(code);
            }
        }
        System.out.println( list.size());

        String url="/usr/loca/pro";
        String bus="/usr/loca/pro?id=5&q=6";
        System.out.println(bus.contains(url));
    }


}
