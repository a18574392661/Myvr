package com.bootdo.vrs.interceptors;

import com.alibaba.fastjson.JSON;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.TitleClsDao;
import com.bootdo.vrs.domain.LbimgsDO;
import com.bootdo.vrs.domain.ProDo2;
import com.bootdo.vrs.domain.TitleClsDO;
import com.bootdo.vrs.service.LbimgsService;
import com.bootdo.vrs.service.ProService;
import com.bootdo.vrs.service.TitleClsService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
public class SpringMathQueryRedis  implements ApplicationRunner {


        @Autowired
        private RedisUtil redisUtil;

        @Autowired
        private LbimgsService lbimgsService;

        @Autowired
        private TitleClsService titleClsService;

        @Autowired
        private TitleClsDao titleClsDao;

        //海报首页展示3条排序
        @Autowired
        private ProService proService;



        public void run(ApplicationArguments args) throws Exception {
             Jedis jedis=redisUtil.getJedis();
            //显示到列表的分类

               try {

                    //轮播图
                     List<LbimgsDO> list=lbimgsService.list(null);
                     //标签
                     Map<String,Object> map=new HashedMap();
                     map.put("pid",0);
                     map.put("status",1);
                     //map.put("isfulls",1);
                     List<TitleClsDO> listParent=titleClsDao.titlePrentAll();
               //      List<TitleClsDO> listEnd=listParent;
                //     List<TitleClsDO> listEnd= new ArrayList<>();

                   /* for (int i = 0; i <listParent.size() ; i++) {
                         System.out.println(listParent.get(i));
                         //查询下面的子集
                         map.put("pid",listParent.get(i).getId());
                         List<TitleClsDO> childList=titleClsService.querylist(map);
                         listParent.get(i).setChildTiles(childList);

                         //如果展示到下面
                        if (listParent.get(i).getIsfulls()==1){
                                List<ProDo2> proList= proService.queryTitleLimit(listParent.get(i).getId());
                                listParent.get(i).setProDo2(proList);
                                listEnd.add(listParent.get(i));
                         }
                     }*/
                     //轮播图
                     jedis.set(MessageConstantVrs.MENULIST, JSON.toJSONString(listParent));
                     //标题
                     jedis.set(MessageConstantVrs.LBTALL, JSON.toJSONString(list));
                     //展示到下面的子级
                //     jedis.set(MessageConstantVrs.INDEXCEN, JSON.toJSONString(listEnd));
             }
             catch (Exception e){
                    e.printStackTrace();
             }
             finally {
                    if (jedis!=null)
                        jedis.close();

             }

        }
}
