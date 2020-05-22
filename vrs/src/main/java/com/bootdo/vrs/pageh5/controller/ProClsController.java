package com.bootdo.vrs.pageh5.controller;


import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.annotations.LoginRequired;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.vrs.common.BaseController;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.*;
import com.bootdo.vrs.domain.*;
import com.bootdo.vrs.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/pro/h5")
public class ProClsController extends BaseController {


    @Autowired
    private TitleClsService titleClsService;

    @Autowired
    private TitleClsDao titleClsDao;

    @Autowired
    private ImgClsDao imgClsDao;

   @Autowired
   private ProService proService;

   @Autowired
   private LbimgsService lbimgsService;

   @Autowired
   private ProDao proDao;

   @Autowired
   private  ImgsSytService imgsSytService;

   @Autowired
   private ImgClsService imgClsService;


   @Autowired
   private ImgsSytDao imgsSytDao;



   @Autowired
   private LbimgsDao lbimgsDao;

   @Autowired
   private  PaydetailesDao paydetailesDao;

   @Autowired
   private  PayvipDao payvipDao;

   @Autowired
   private ColorImgclsService colorImgclsService;

   @Autowired
   private RedisUtil redisUtil;


   @Autowired
   private  IpgetCountService ipgetCountService;




    @RequestMapping("/test")
   @ResponseBody
   public void test(){
     Jedis jedis= redisUtil.getJedis();
     jedis.hsetnx("dx","name","a");
     jedis.sadd("list","b");
       System.out.println(jedis.smembers("list"));

   }

    //查询定制标签 下面的1 分类 分类下面的 选择框
    @RequestMapping("/queryCls1Pro")
    @LoginRequired(loginSuccess =false)
    public   String queryCls1Pro(ModelMap map, HttpServletRequest request, String parent, @RequestParam(defaultValue ="1") Integer page){
        map.put("ste",2);
        map.put("maps",saveIndex());
        //根据typeid 查询出 id 根据分类查下面的\
        PageInfo<ProDO> prosList=null;
        String[] cids=null;
        try {
            TitleClsDO queryTypeId = titleClsService.queryTypeId(MessageConstantVrs.PARCLASSDINZHI);
            //查询分类表
            if (StringUtils.isNotBlank(parent)){
                cids=parent.split(",");

            }

            List<ImgClsDO> list=imgClsDao.imgClsPars("0");
            for (ImgClsDO imgClsDO : list) {
                List<ImgClsDO> childImgs=imgClsDao.imgClsPars(imgClsDO.getId()+"");
                for (ImgClsDO childImg : childImgs) {
                    if (cids!=null&&cids.length>0){
                        for (String cid : cids) {
                            if (childImg.getId()==Integer.parseInt(cid)){
                                childImg.setChecked(true);
                                //高亮显示
                                childImg.setHeightColor("red");
                            }
                        }
                    }

                }
                //判断选中
                imgClsDO.setChilds(childImgs);
            }
            prosList=proService.queryTypeProCls(parent,queryTypeId.getId()+"",page);
            map.put("listCls",list);
            //根据条件查询
            map.put("proList",prosList.getList());
            map.put("page",page);
            map.put("total",prosList.getTotal());
            map.put("val",parent);
            Integer nextPage=prosList.getNextPage();
            if (prosList.getNextPage()==0){
                nextPage=prosList.getPages();
            }
            Integer   prePage=prosList.getPrePage();
            if (prePage<1){
                prePage=1;
            }
            map.put("nextPage",nextPage);
            map.put("prePage",prePage);
            map.put("pages",prosList.getPages());
        }
        catch (Exception e){
            e.printStackTrace();

        }

       // return  prosList;
        return  MessageConstantVrs.VRSH5_PAGE+"custom_design";
       }

        @RequestMapping("/proHuance")
        @LoginRequired(loginSuccess = false)
        public  String proHuance(HttpServletRequest request,ModelMap map,String id,String cids,@RequestParam(defaultValue = "1")Integer page){

            ipgetCountService.queryProCount(Integer.parseInt(id));

            map.put("maps",saveIndex());
            String path=MessageConstantVrs.VRSH5_PAGE+"detailed_second";
            //判断
            if (StringUtils.isBlank(id)){
                return "redirect:/vrs/houses/index";
            }
            Integer typeid=titleClsDao.getProTypeidPage(id);
            if (typeid==null){
                return "redirect:/vrs/houses/index";
            }

            //查询分类 多个
            List<ImgClsDO> listCls=new ArrayList<>();
            //只有一个条件选择了
            if (StringUtils.isNotBlank(cids)){
                cids="("+cids+")";
                //查询分类
                listCls= imgClsDao.queryClsCids(cids);
            }
            //详细信息 详细图片分页 缩图分页
            Map<String,Object>      sucessMap= this.searchImgs(id,6,page,1,2);
            PageInfo<ImgclsImgsDO>  pageInforDetail=(PageInfo<ImgclsImgsDO>)sucessMap.get("pageinfDetaSimg");
            PageInfo<ImgsSytDO>     pageinfoImgSty=(PageInfo<ImgsSytDO>)sucessMap.get("imgStys");

            //查询色卡

            PageInfo<ColorImgclsDO> pageInfo=this.getHuanSeColor(id,page);
            map.addAttribute("colorList",pageInfo.getList());
            map.addAttribute("cls",listCls);
            map.addAttribute("proDetas",pageInforDetail);
            map.addAttribute("imgsStysinfo",pageinfoImgSty);
            map.addAttribute("pro",(ProDO)sucessMap.get("proDetail"));

            map.put("id",id);
            map.put("page",page);//回显详细图的页码
            return  path;
    }


    public  PageInfo<ColorImgclsDO> getHuanSeColor(String id,Integer page){
        Map<String,Object> map1=new HashMap<>();
        map1.put("proid",id);
        PageHelper.startPage(page,6);
        List<ColorImgclsDO> list=colorImgclsService.list(map1);
        PageInfo<ColorImgclsDO> pageinfo=new PageInfo<ColorImgclsDO>(list);
        return  pageinfo;
    }


    @RequestMapping("/proDetailed")
    @LoginRequired(loginSuccess = false)
    public  String proDetailed(HttpServletResponse resp, HttpServletRequest request, ModelMap map, String id, String cids, @RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "1")Integer page2){
        ipgetCountService.queryProCount(Integer.parseInt(id));
        // resp.setContentType("image/tiff");
       //resp.setCharacterEncoding("utf-8");
        //resp.setContentType("application/octet-stream; charset=UTF-8");
        //第一个详细图 页码
        //2个 缩图
        map.put("maps",saveIndex());
        String path=MessageConstantVrs.VRSH5_PAGE+"detailed";
        //判断
        if (StringUtils.isBlank(id)){
            return "redirect:/vrs/houses/index";
        }
        Integer typeid=titleClsDao.getProTypeidPage(id);
        if (typeid==null){
            return "redirect:/vrs/houses/index";
        }
        //查询分类 多个
        List<ImgClsDO> listCls=new ArrayList<>();
        //只有一个条件选择了
        if (StringUtils.isNotBlank(cids)){
            cids="("+cids+")";
            //查询分类
            listCls= imgClsDao.queryClsCids(cids);
        }
        //详细信息 详细图片分页 缩图分页
        Map<String,Object>      sucessMap= this.searchImgs(id,2,page,page2,2);
        PageInfo<ImgclsImgsDO>  pageInforDetail=(PageInfo<ImgclsImgsDO>)sucessMap.get("pageinfDetaSimg");
        PageInfo<ImgsSytDO>     pageinfoImgSty=(PageInfo<ImgsSytDO>)sucessMap.get("imgStys");
        map.addAttribute("cls",listCls);
        map.addAttribute("proDetas",pageInforDetail);
        map.addAttribute("imgsStysinfo",pageinfoImgSty);
        map.addAttribute("pro",(ProDO)sucessMap.get("proDetail"));
        map.addAttribute("cids",cids);
        ProDO proDO= (ProDO)sucessMap.get("proDetail");
        map.put("id",id);
        map.put("page",page);//回显详细图的页码
        map.put("page2",page2);//回显详细图的页码
        return  path;
    }


    /*
    门窗设计详细图
     */
    @RequestMapping("/proMengchuang")
   @LoginRequired(loginSuccess = false)
    public  String proMengchuang(HttpServletRequest request,ModelMap map,String id,String cids,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "1")Integer page2){
        ipgetCountService.queryProCount(Integer.parseInt(id));

        //第一个详细图 页码
        //2个 缩图
        map.put("maps",saveIndex());
        String path=MessageConstantVrs.VRSH5_PAGE+"mengchuang";
        //判断
        if (StringUtils.isBlank(id)){
            return "redirect:/vrs/houses/index";
        }
        Integer typeid=titleClsDao.getProTypeidPage(id);
        if (typeid==null){
            return "redirect:/vrs/houses/index";
        }
        //查询分类 多个
        List<ImgClsDO> listCls=new ArrayList<>();
        //详细信息 详细图片分页 缩图分页
        Map<String,Object>      sucessMap= this.searchImgs(id,2,page,page2,2);
        PageInfo<ImgclsImgsDO>  pageInforDetail=(PageInfo<ImgclsImgsDO>)sucessMap.get("pageinfDetaSimg");
        PageInfo<ImgsSytDO>     pageinfoImgSty=(PageInfo<ImgsSytDO>)sucessMap.get("imgStys");

        map.addAttribute("proDetas",pageInforDetail);
        map.addAttribute("imgsStysinfo",pageinfoImgSty);
        map.addAttribute("pro",(ProDO)sucessMap.get("proDetail"));
        map.put("id",id);
        map.put("page",page);//回显详细图的页码
        map.put("page2",page2);//回显详细图的页码
        return  path;
    }


    @RequestMapping("/proHuace")
    @LoginRequired(loginSuccess = false)
    public  String proHuace(HttpServletRequest request,ModelMap map,String id,String cids,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "1")Integer page2){
        ipgetCountService.queryProCount(Integer.parseInt(id));
        //第一个详细图 页码
        //2个 缩图
        map.put("maps",saveIndex());
        String path=MessageConstantVrs.VRSH5_PAGE+"huace";
        //判断
        if (StringUtils.isBlank(id)){
            return "redirect:/vrs/houses/index";
        }
        Integer typeid=titleClsDao.getProTypeidPage(id);
        if (typeid==null){
            return "redirect:/vrs/houses/index";
        }
        //查询分类 多个
        List<ImgClsDO> listCls=new ArrayList<>();
        //详细信息 详细图片分页 缩图分页
        Map<String,Object>      sucessMap= this.searchImgs(id,2,page,page2,2);
        PageInfo<ImgclsImgsDO>  pageInforDetail=(PageInfo<ImgclsImgsDO>)sucessMap.get("pageinfDetaSimg");
        PageInfo<ImgsSytDO>     pageinfoImgSty=(PageInfo<ImgsSytDO>)sucessMap.get("imgStys");

        map.addAttribute("proDetas",pageInforDetail);
        map.addAttribute("imgsStysinfo",pageinfoImgSty);
        map.addAttribute("pro",(ProDO)sucessMap.get("proDetail"));

        map.put("id",id);
        map.put("page",page);//回显详细图的页码
        map.put("page2",page2);//回显详细图的页码
        return  path;
    }





    public Map<String,Object> searchImgs(String proid, Integer size, Integer page,Integer page2,Integer size2){
        Map<String,Object> result=new HashMap<>();
        ProDO proDO=proService.get(Integer.parseInt(proid));
        //获取全景图文件夹
        String qjtfile=proDO.getImg();

        int index=qjtfile.indexOf(".");
        proDO.setQjtfile(qjtfile.substring(qjtfile.lastIndexOf("/")+1,index)+".tiles");
        //查询详细图
        result.put("proDetail",proDO);
        //详细 每页2张兔兔
        result.put("pageinfDetaSimg",proService.queryServiceDetali(Integer.parseInt(proid),page,size)); //显示2条
        //缩图的多张
        PageInfo<ImgsSytDO> pageInfo= imgsSytService.querySyImg(Integer.parseInt(proid),size2,page2);
        result.put("imgStys",pageInfo); //显示2条
        return  result;
    }



    //轮播图
    @GetMapping("/queryLbByid")
    @LoginRequired(loginSuccess = false)
    public  String queryLbByidd(Integer id,ModelMap map){
          map.put("maps",saveIndex());
         LbimgsDO lbimgsDO=lbimgsDao.queryByID(id+"");
          map.put("lb", lbimgsDO);

        return  MessageConstantVrs.VRSH5_PAGE+"second_index";
    }



    //全局搜索 根据1定制设计分类 编号 名称 等
    /*
    SELECT u.`name`,u.src,pro.*,cen.c1id,cls.name  FROM vrs_pro  pro
left join vrs_pro_cen_cls cen
on pro.id=cen.proid
left join vrs_img_cls cls
on cen.c1id=cls.id
left join sys_user u
on pro.uid=u.user_id
WHERE pro.code='1827929718'
GROUP BY pro.id
     */
    @RequestMapping("/quertSearchPro")
    @LoginRequired(loginSuccess = false)
    public  String quertSearchPro(ModelMap map,@RequestParam(defaultValue = "1") Integer page,String name){
        map.put("maps",saveIndex());

        PageInfo<ProDO> pageInfo= proService.quertSearchPro(9,page,name);


        Integer prePage=pageInfo.getPrePage();
        if (prePage<1){
            prePage=1;
        }
        Integer nextPage=pageInfo.getNextPage();
        if (nextPage<=0){
            nextPage=pageInfo.getPages();
        }
        System.out.println(prePage+"//"+nextPage);
        //缩图 分页
        map.put("pros",pageInfo.getList());
        map.put("nextPage",nextPage);
        map.put("prePage",prePage);
        map.put("total",pageInfo.getTotal());
        map.put("pages",pageInfo.getPages());
        map.put("page",page);
        map.put("name",name);

        return MessageConstantVrs.VRSH5_PAGE+"new/search"; //MessageConstantVrs.VRSH5_PAGE+"search";
    }


    //根据1图片id查询类型跳3不同的页面
    @ResponseBody
    @RequestMapping("/queryProType")
    public  Map<String,Object> queryProType(Integer proid){
        start();
        try {
            Integer i=titleClsDao.getProTypeidPage(proid+"");
            if (i==null){
                message(MessageConstantVrs.INDEX_TIS);
                success(false);
            }
            else {
                success(true);
                data(i);
            }
        }
        catch (Exception e){
            success(false);
            message(MessageConstantVrs.INDEX_TIS);
            e.printStackTrace();


        }

        return  end();
    }


    @RequestMapping("/queryVip")
    @LoginRequired(loginSuccess = false)
    public  String queryVip(ModelMap map, HttpServletRequest request){
        Jedis jedis=redisUtil.getJedis();
        String uid=request.getAttribute("uid")+"";
        try {
            map.put("maps",saveIndex());
            map.put("vip",paydetailesDao.get(Integer.parseInt(MessageConstantVrs.GET_VIPID)));
            Map map1=new HashMap();
            map1.put("vipidvipid",MessageConstantVrs.GET_VIPID);
            map1.put("status","1");
            map.put("vipPrices",payvipDao.list(map1));


            //判断用户是否登录
            if (StringUtils.isNotBlank(uid)&&!("null".equals(uid))){
                System.out.println(uid+"////sss");
                String uuid=UUID.randomUUID().toString();
                //保存进去一个值
                System.out.println(uid+"sss");
                jedis.set(uid+":"+uuid,uuid);
                map.put("orderDisble",uuid);
            }

        }
        catch (Exception e){

            e.printStackTrace();

        }
        finally {
            if (jedis!=null)
                jedis.close();
        }


        //生成一个值 防止用户重复提交
      //  String

        return  MessageConstantVrs.VRSH5_PAGE+"vip";
    }


    @GetMapping("/queryColorCarid")
    @ResponseBody
   public ColorImgclsDO queryColorCarid(Long id, Model model){
        ColorImgclsDO colorImgcls = colorImgclsService.get(id);

        //截取全景图路径
        String qjtfile=colorImgcls.getImg();

        int index=qjtfile.indexOf(".");
        colorImgcls.setQjt(qjtfile.substring(qjtfile.lastIndexOf("/")+1,index)+".tiles");
        return colorImgcls;
    }



    public static void main(String[] args) {
        String url="htt/dasda/pssimg.qwe";
        int start=url.lastIndexOf("/");
        int q=url.indexOf(".");
        System.out.println(url.substring(start+1,q));
    }


    @RequestMapping("/to_mt")
    public  String t(){
        return  MessageConstantVrs.VRSH5_PAGE+"html/zc";
    }

//改版

    @RequestMapping("/queryLbAll")
    @ResponseBody
    public  List<LbimgsDO> queryLbAll(){

        return  lbimgsDao.list(null);
    }

    //查询定制设计三级分类
    @ResponseBody
    @RequestMapping("/getThreeImgClsDo")
    public  List<ImgClsDO> getThreeImgClsDo(){

        return imgClsService.getThreeImgClsDo();
    }


    //每个设计对应三条 下一批
    @RequestMapping("/nextTitPro")
    @ResponseBody
    public  PageInfo<ProDO> getTitleLimt(@RequestParam(defaultValue ="1") Integer page,String tid){
        //nextPage
        PageInfo<ProDO> pageInfo= proService.getTitleLimt(page,tid);

        return  pageInfo;
    }

}
