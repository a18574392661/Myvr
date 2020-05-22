package com.bootdo.vrs.common;

import java.util.HashMap;
import java.util.Map;

public class MessageConstantVrs {

    //定制系统
    public  static  final  Integer PARCLASSDINZHI=1;
    //换色系统
    public  static  final  Integer HUANSEXXITON=2;
    //门窗设计
    public  static  final  Integer MENGCHUANGSHEJI=3;
    //话端画册
    public  static  final  Integer HUACEXITON=4;

    public  static String IMGCLSDEL_ERROR="该分类下面有子分类不能删除!";
    
    //消息提示
    public static final String WARNINGMSG = "分类下存在商品,暂时不能删除!";
    
    //消息提示
    public static final String WARNINGMSGS = "该分类下包含子分类!";

    //消息提示
    public static final String PRO_ERROR = "产品添加失败!";
    //消息提示
    public static final String PRO_EDIT_ERROR = "产品编辑失败!";
    public static final String PRO_ERROR_CODE= "产品编号添加重复!";

    public  static final String VRSH5_PAGE="vrs/manager/house/";

    public   static final String LBTALL="LBTALL";
    public   static final String LBTA="LBTA:";
    public   static final  String MENULIST="MENULIST";
    public   static final  String INDEXCEN="INDEXCEN";
    public  static  final  String LOGVALUE="LOGVALUE";


    public  static final String VRUSERKEE="VRUSERKEE:";
    public  static final String VRUSER_LOGIN_NAM="账号不存在!";
    public  static final String VRUSER_LOGIN_PWD="密码错误!";
    public  static final Integer VRUSERTIME=60*30;

    public static final String ToLoginUrl="/vrs/user/login";

    public static final String GETVR_RID="(70,71,59)";

    public  static  final  String USER_PRO_ERROR="加入云视失败!";
    public  static  final  String USER_PRO_SUCCESS="加入云视成功!";
    public  static  final  String USER_PRO_DISBELE="该产品已加入过,请忽重复加入!";
    public  static  final  String USER_PRO_ERRORVIP="非会员用户只能加入";
    public  static  final  String USER_PRO_NOT_VIP="非会员只能上传免费图!";
    public  static  final  String USER_VR_LOGIN="请先登录!";
    public  static  final  String PRO_COUNT_ERROR="产品没库存无法加入购物车!";
    public  static  final  String CLS_CHILD_PRO="该分类下面有产品图不能删除!";
    public  static  final  String PRO_DEL_ADMIN="管理员已删除此套产品请刷新页面!";
    public  static  final  String  GET_VIPID="2";
    public  static  final  String PRO_SHOP_DISBLEDS="此产品已经在我的购物车!";
    public  static  final  String SETPROSIMG="最多只能设置2张显示推荐图";
    public  static  final  String ADMIN_ERROR="系统配置不能删除!";
    public  static  final  String ERROR="操作失败!";
    public  static  final  String  SHOW_USER="用户的下级";

    /**
     * @return  每页显示的条数
     * @author: wym
     * @date: 2020年4月16日 上午10:23:05
     */
    public static final Integer PAGECOUNT = 9;
    /**
     * @return  分页显示的页码
     * @author: wym
     * @date: 2020年4月16日 上午10:24:58
     */
    public static final Integer PAGENUMS = 5;
    /**
     * @return  将一个list拆分成几个list
     * @author: wym
     * @date: 2020年4月16日 上午10:39:20
     */
    public static final Integer LISTCOUNT = 3;
    public static final String  INDEX_TIS="请刷新页面再点击!";
    
    public static Map<String,Object> getRootMap(){
    	Map<String,Object> map = new HashMap<String,Object>();
    	return map;
    }

}
