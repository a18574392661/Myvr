package com.bootdo.vrs.util;

import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.vrs.dao.HxLogDao;
import com.bootdo.vrs.domain.HxLogDO;
import com.bootdo.vrs.domain.UserHxDO;
import com.bootdo.vrs.service.UserHxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 用户支付成功后 算两级提成的类
 */
@Component
@Configuration
public class UserVerificationUtil {

    @Autowired
    private UserHxService userHxService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private HxLogDao hxLogDao;


    public  BigDecimal  setUserPrice(Long uid, BigDecimal price,String context,String ordercode){
        //先判断是否是会员1 不是的无法核销

        try {
            List<UserHxDO> userHxDOList=userHxService.list(null);
            Long newUid=uid;
            //2级 循环2次
            for (int i = 1; i <=2 ; i++) {
                newUid=getUserRole(newUid);
                //判断上级是不是会员
                UserDO userDO=userDao.get(newUid);
                if (userDO==null||userDO.getIsVip()==0){
                    continue;
                }
                //拿到新饿上级id
                //拿到值
                Map<String,Object> ressultMap=this.getUserRole(newUid,userHxDOList,i);
                ressultMap.put("uid",uid);
                //算出剩余金额
                price=this.saveUserHx(ressultMap,price,i,context,ordercode);
                //算完上一级++
            }


        }
        catch (RuntimeException e){
            e.getMessage();
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return price;
    }

    public Long getUserRole(Long uid){
        return userDao.getPid(uid);
    }

    //根据用户id拿到角色
    public Map<String,Object> getUserRole(Long pid,List<UserHxDO> list,int count){
            BigDecimal myPriceBl=new BigDecimal(0);
            Map<String,Object> ressultMap=new HashMap<String,Object>();

            //拿到角色 只有一个反正
            UserDO userDO=userDao.get(pid);//拿到上级的id
            if (userDO==null){
                throw  new  RuntimeException("上级用户不存在!");
            }

            for (UserHxDO userHxDO : list) {
                    Integer userHxRid=userHxDO.getRid();
                    Long userRid= userDO.getRid();
                    if (userHxRid==Integer.parseInt(userRid+"")){
                        //判断级别
                        if (count==1){
                            myPriceBl=userHxDO.getFirstlevel();
                        }else if (count==2){
                            myPriceBl=userHxDO.getSeconlevel();
                        }
                    }
            }
            ressultMap.put("myPriceBl",myPriceBl);
            ressultMap.put("pid",pid);
            return  ressultMap;
    }

    //拿到用户信息 提成比例算金额
    public  BigDecimal saveUserHx(Map<String,Object> ressultMap,BigDecimal price,Integer i,String context,String ordercode){
            BigDecimal decimalBl=new BigDecimal(100);//比例
            BigDecimal sPrice=new  BigDecimal(0);
            String uid=ressultMap.get("uid")+"";
            String pid=ressultMap.get("pid")+"";
            BigDecimal myPriceBl=new BigDecimal(ressultMap.get("myPriceBl")+"");
            myPriceBl = myPriceBl.divide(decimalBl);
            //  String pid=ressultMap.get("uid")+"  ";
            BigDecimal bighxPrice= price.multiply(myPriceBl);//核销1金额
            sPrice=price.subtract(bighxPrice);
            //表加一条记录1
            HxLogDO hxLogDO=new HxLogDO();
            hxLogDO.setUid(Integer.parseInt(uid));
            hxLogDO.setPid(Integer.parseInt(pid));
            hxLogDO.setRemker(context);
            hxLogDO.setOrdercode(ordercode);
            hxLogDO.setPrice(bighxPrice);
            hxLogDO.setCreatedate(new Date());
            hxLogDO.setStatus(1);
            hxLogDO.setLevel(i);
            hxLogDao.save(hxLogDO);
            return  sPrice;
    }

    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("myPriceBl",33.69);
        BigDecimal b= new UserVerificationUtil().saveUserHx(map,new BigDecimal(100),1,null,null);
        System.out.println(b);

    }

}
