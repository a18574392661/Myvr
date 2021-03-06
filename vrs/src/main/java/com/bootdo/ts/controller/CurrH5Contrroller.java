package com.bootdo.ts.controller;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.ts.dao.CurrDao;
import com.bootdo.ts.dao.CurrlogDao;
import com.bootdo.ts.dao.UCidsDao;
import com.bootdo.ts.dao.ZwDao;
import com.bootdo.ts.domain.CurrDO;
import com.bootdo.ts.domain.CurrlogDO;
import com.bootdo.ts.domain.UCidsDO;
import com.bootdo.ts.domain.ZwDO;
import com.bootdo.ts.service.CurrlogService;
import com.bootdo.ts.util.DatesUtil;
import com.sun.javafx.geom.PickRay;
import com.sun.tools.internal.ws.processor.model.Model;
import org.redisson.misc.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/h5/curr")
public class CurrH5Contrroller extends BaseController {


    private    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private CurrlogDao currlogDao;

    @Autowired
    private UCidsDao uCidsDao;

    @Autowired
    private CurrlogService currlogService;

    @Autowired
    private ZwDao zwDao;


    @Autowired
    private CurrDao currDao;

    @Autowired
    private DatesUtil datesUtil;


    @RequestMapping("/toshowcCnames")
    public  String toshowcCnames(ModelMap map){


        return "ts/h5/new2/chooes";
    }

    //第三次改
    //查询课程组
    @RequestMapping("/showcCnames")

    public  String showcCnames(ModelMap map){
        Map pars=new HashMap();
        pars.put("status","1");
       List<CurrlogDO> list=currDao.list(pars);
        List<Map<String,Object>> list2=new ArrayList<>();

        if (list==null||list.size()<=0){
            return "ts/h5/new2/chooes";
        }
        int count=list.size()/2;
        for (int i = 1; i <=count ; i++) {
            int start=(i*2)-1;
            List<CurrlogDO> list3=new ArrayList<>();
            list3.add(list.get(start-1));
            list3.add(list.get(start));
            Map<String,Object> map1=new LinkedHashMap<>();

            map1.put("list",list3);
            list2.add(map1);
        }
        if (list.size()%2!=0){
            Map<String,Object> map1=new LinkedHashMap<>();
            List<CurrlogDO> list3=new ArrayList<>();
            list3.add(list.get(list.size()-1));
            map1.put("list",list3);
            list2.add(map1);
        }

        map.put("mapSucess",list2);
        System.out.println(list2);
        return "ts/h5/new2/chooes";
    }






    //默认本周的课程
    //上一周 下一周 下下周等.....
    @RequestMapping("/to_cnameDays")
    public String to_cnameDays(String date, String cid, ModelMap map, String sta){

        try {

            List list=null;
            if (StringUtils.isBlank(date)&&StringUtils.isBlank(sta)){
                date=sdf.format(new Date());
                list=dates(null);

            }
            else {
                //判断 传的日期的下一周 还是上一周
                //得到这个日期的周一日期
                String oneDate=datesUtil.geyDayOne(date);
                String resullteDs=datesUtil.getLastTimeInterval(oneDate,sta);//2020-05-25,2020-05-31
                String[] dayAnds=resullteDs.split(",");
                date=dayAnds[0];
                //或者这周的星期 
                list=dates(date);

            }



                List<Map<String,Object>> result=new ArrayList<>();
            //查询可以选择的日期


            for (int i = 0; i <list.size(); i++) {
                Map<String,Object> ms=new LinkedHashMap<>();
                String bs=list.get(i)+"";
                String[] sz=bs.split(" ");
                String rq=sz[0];
                String[] mondays=rq.split("-");
                ms.put("rq",mondays[1]+"-"+mondays[2]);
                ms.put("day",datesUtil.getDay(sz[1]));
                System.out.println(sz[1]);
                result.add(ms);
            }
            //查询这个礼拜 对应的1每节课1 对应的日期
            List<Map<String,Object>> resuList=currlogService.queryDayRq(list,cid);
            map.put("tlist",result);
            map.put("resuList",resuList);
            CurrDO currDO=currDao.get(Integer.parseInt(cid+""));
            map.put("cname",currDO.getCname());
            map.put("dates",date);
            map.put("kid",cid);
            map.put("year",date.substring(0,4)+"年");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    


        return "ts/h5/new2/index";

    }


    public static void main(String[] args) {
        System.out.println("monS".equalsIgnoreCase("MOn"));


    }

    public static String getTimeInterval(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EE");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);
        return imptimeBegin + "," + imptimeEnd;
    }


    @RequestMapping("/to_yuyue")
    public  String to_yuyue(){
        return "ts/h5/yuyue";
    }


    @RequestMapping("/to_xz")
    public  String to_xz(ModelMap map,String name,String tel){
        List<Map<String,Object>> result=new ArrayList<>();
        //查询可以选择的日期
        List list=dates(null);

        for (int i = 0; i <list.size(); i++) {
            Map<String,Object> ms=new LinkedHashMap<>();
            String bs=list.get(i)+"";
           String[] sz=bs.split(" ");
            System.out.println(sz[0]+"//"+sz[1]);
            ms.put("rq",sz[0]);
            ms.put("day",sz[1]);
            result.add(ms);
        }

        //查询这个礼拜 对应的1每节课1 对应的日期
       List<Map<String,Object>> resuList=currlogService.queryDayRq(list,null);
        map.put("name",name);
        map.put("tel",tel);
        map.put("tlist",result);
        map.put("resuList",resuList);
        return "ts/h5/new/index";
    }

    //确定了预约1页面
    @RequestMapping("/yuyue")
    public  String yuyue(ModelMap model,String name,String tel,String cid,String zid)

    {
       model.put("name",name);
        model.put("tel",tel);
      //  model.put("cid",cid);
        model.put("zid",zid);
       CurrlogDO currDO=currlogDao.getCurrCnames(cid);
       if (currDO==null){
           //区域与页面
           return "redirect:/h5/curr/to_xz?name="+name+"&tel="+tel;
       }
       currDO.setCname(currDO.getJname()+":"+currDO.getCname());
       model.put("cur",currDO);
        return "ts/h5/new/yuyue";
    }




    @ResponseBody
    @RequestMapping("/geyisfullYy")
    public  R geyisfullYy(String cid){

        try {
            currlogService.queryKcCount(cid);
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return  R.error(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return  R.error("选择失败请刷新再试!");
        }

        return  R.ok();
    }
    //查询本周7天
    private  List dates(String date) {

        List aa=new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            if (date!=null){
                calendar.setTime(sdf.parse(date));
            }

            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
                }
                for (int i = 0; i < 7; i++) {
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd EE");
                aa.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DATE, 1);
                  }
        }
        catch (Exception e){
                e.printStackTrace();
        }

        return aa;
}



    //根据日期分组 占时不需要
    @ResponseBody
    @RequestMapping("/getCulls")
    public List<Map<String,Object>> getCulls(){
        List<Map<String,Object>> list=new ArrayList<>();
        List<CurrlogDO> list1=currlogDao.currAllLogs();
        for (CurrlogDO currlogDO : list1) {
            Map<String,Object> map=new HashMap<>();
            map.put("text",currlogDO.getDaydate());
            map.put("value",currlogDO.getDaydate());
            list.add(map);
        }


        return  list;
    }


    //根据日期联动查询课程
    @RequestMapping("/searchKc")
    @ResponseBody
    public  List<Map<String,Object>> searchKc(String dates){
        List<Map<String,Object>> results=new ArrayList<>();
        List<CurrlogDO> list=currlogDao.currAllLogsKc(dates);
        for (CurrlogDO currlogDO : list) {
            Map<String,Object> dx=new HashMap<>();
            dx.put("text",currlogDO.getJname()+":"+currlogDO.getCname());
            dx.put("value",currlogDO.getId());
            results.add(dx);
        }

        return results;
    }

    //确定预约
    @RequestMapping("/qdYuyue")
    @ResponseBody
    public  R searchKc(String cid,String zid,String tel,String uname) {

        try {

            //先判断是否已经占用了
            if (StringUtils.isBlank(cid)|| StringUtils.isBlank(tel) || StringUtils.isBlank(uname)) {
                return R.error("请选择完整信息!");
            }

            synchronized(CurrH5Contrroller.class){
                int c = uCidsDao.queryKzw(cid);
                if (c > 0) {
                    return R.error("当前课程已经被预定!");
                }
                //添加
                UCidsDO uCidsDO = new UCidsDO();
                uCidsDO.setCid(Long.parseLong(cid));
                uCidsDO.setCreatedate(new Date());
                uCidsDO.setStatus(1);
                uCidsDO.setTel(tel);
                uCidsDO.setUname(uname);
                uCidsDO.setZid(Long.parseLong(zid));
                int sus=uCidsDao.save(uCidsDO);
                if (sus>0){
                    return  R.ok("预约成功!");
                }
            }

        }
        catch (Exception e){

            e.printStackTrace();
        }

        return R.error("预定失败!");
    }

    //查询当前课程对应的座位
    @RequestMapping("/showzw")
    @ResponseBody
    public  List<ZwDO> showzw(String cid) {
        List<ZwDO> list=null;
        try {

            list=zwDao.queryDqZw(cid);

        }
        catch (Exception e){

            e.printStackTrace();
        }

       return  list;
    }





}
