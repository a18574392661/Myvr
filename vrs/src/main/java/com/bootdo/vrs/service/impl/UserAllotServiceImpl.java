package com.bootdo.vrs.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.HxLogDao;
import com.bootdo.vrs.dao.UserImgclsDao;
import com.bootdo.vrs.dao.UsertAllotDao;
import com.bootdo.vrs.domain.UserDO;
import com.bootdo.vrs.service.PaydetailesService;
import com.bootdo.vrs.service.UserAllotService;
import com.bootdo.vrs.util.DateVrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class UserAllotServiceImpl implements UserAllotService {
	@Autowired
	private UsertAllotDao userDao;

	@Autowired
	private UserDao userDaos;

	@Autowired
	private PaydetailesService paydetailesService;

	@Autowired
	private HxLogDao hxLogDao;

	@Autowired
	UserRoleDao userRoleMapper;


	@Autowired
	private UserImgclsDao userImgclsDao;

	@Autowired
	private  RedisUtil redisUtil;

	@Autowired
	private DateVrUtils dateVrUtils;

	@Override
	public UserDO get(Long userId){
		return userDao.get(userId);
	}
	
	@Override
	public List<UserDO> list(Map<String, Object> map){
		List<UserDO>  list=userDao.list(map);
		for (UserDO userDO : list) {
			if (userDO.getIsvip()!=null&&userDO.getIsvip()==1){
				//算出还多少是时间过期
				Long c=dateVrUtils.getDay(userDO.getVipDate());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ; //使用了默认的格式创建了一个日期格式化对象。
				String sj=dateFormat.format(userDO.getVipDate());
				userDO.setContext("会员到期时间:"+sj+" 剩余:"+c+"天");
			}
		}

		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userDao.count(map);
	}
	
	@Override
	public int save(UserDO user){
		//查询用户名

		int telC=userDaos.queryTel(user.getMobile(),null);
		if (telC>0){
			throw  new RuntimeException(MessageConstant.TEL_ERROR);
		}

		if (false) {
			int emlC = userDaos.queryEmall(user.getEmail(), null);
			if (emlC > 0) {
				throw new RuntimeException(MessageConstant.EMALL_ERROR);
			}

			int userDO = userDaos.queryUserNames(user.getUsername(), null);
			//手机号码
			if (userDO > 0) {
				throw new RuntimeException(MessageConstant.USER_ERROR);

			}
		}
		//不是1会员


		//密码加密
		user.setPassword(MD5Utils.encrypt(user.getName(),user.getPassword()));
	//添加角色
		int c=userDao.save(user);

		saveUserRole(user.getUserId(),Long.parseLong(user.getRid()+""));
		return c;
	}

	public  void saveUserRole(Long uid,Long rid){
		userRoleMapper.removeByUserId(uid);
		List<UserRoleDO> list = new ArrayList<>();
		//一对一
		UserRoleDO ur = new UserRoleDO();
		ur.setUserId(uid);
		ur.setRoleId(rid);
		list.add(ur);


		if (list.size() > 0) {
			userRoleMapper.batchSave(list);
		}

	}

	@Override
	public int update(UserDO user){

		int telC=userDaos.queryTel(user.getMobile(),user.getUserId()+"");
		if (telC>0){
			throw  new RuntimeException(MessageConstant.TEL_ERROR);
		}

		//查询用户名 占时不需要
		if (false) {
			int userDO = userDaos.queryUserNames(user.getUsername(), user.getUserId() + "");
			//手机号码
			if (userDO > 0) {
				throw new RuntimeException(MessageConstant.USER_ERROR);

			}


			int emlC = userDaos.queryEmall(user.getEmail(), user.getUserId() + "");
			if (emlC > 0) {
				throw new RuntimeException(MessageConstant.EMALL_ERROR);
			}
		}

		int c=userDao.update(user);
		saveUserRole(user.getUserId(),Long.parseLong(user.getRid()+""));
		return c;
	}
	
	@Override
	public int remove(Long userId){
		return userDao.remove(userId);
	}
	
	@Override
	public int batchRemove(Long[] userIds){
		return userDao.batchRemove(userIds);
	}

	@Override
	public List<UserDO> saveMyUserName(Map<String, Object> paremts) {
		//查询它的子账号 判断是否是会员
	 	List<UserDO> list=userDao.getUserPro(paremts);
		for (UserDO userDO : list) {
			//查询每个会员的上传了的次数
			int count=userImgclsDao.getUserImgCount(userDO.getUserId());
			userDO.setCount(count);
		}

		return list;
	}

	@Override
	public Tree<UserDO> userTree(Long uid) {
			//查询当前用户
			Tree<UserDO> treePar=new Tree<UserDO>();
			treePar.setText(MessageConstantVrs.SHOW_USER);
			List<UserDO> userDOList=userDao.queryList(uid+"");
			int i=0;
			for (UserDO userDO : userDOList) {
				Tree<UserDO> tree=new Tree<UserDO>();
				tree.setText(userDO.getUsername());
				tree.setId(userDO.getUserId()+"");
				tree.setChecked(false);
				//查询第一级
				List<Integer> uids=userDao.getUids(userDO.getUserId());
				this.saveeTreeDao(tree,uids,1);
				treePar.getChildren().add(tree);
				i++;
			}
			return treePar;
	}



	public  void saveeTreeDao(Tree<UserDO> tree,List<Integer> uids,int count){
		//只查2级
		if (count>2){
			return;
		}

		String ids="";
		if (uids!=null&&uids.size()>0){
			ids+="(";
			for (int i = 0; i <uids.size() ; i++) {
				ids+=uids.get(i);
				if (uids.size()-1>i){
					ids+=",";
				}
			}
			ids+=")";
			//查第一个下级
			List<UserDO> list=userDao.getUsers(ids);
			List<Tree<UserDO>> cs=new ArrayList<>();

			for (UserDO uDo : list) {
				Tree<UserDO> tr1=new Tree<UserDO>();
				tr1.setText(uDo.getUsername());
				tr1.setId(uDo.getUserId()+"");
				tr1.setChecked(false);
				//查询第二级子级
				List<Integer> idss=userDao.getUids(uDo.getUserId());
				if (idss!=null&&idss.size()>0){
					this.saveeTreeDao(tr1, idss,++count);
				}

				cs.add(tr1);
				//遍历下面第二级
			}
			tree.setChildren(cs);
		}

	}

	public  void delRedis(UserDO userDO){
		Jedis jedis= redisUtil.getJedis();

		try {
			String key= MessageConstantVrs.VRUSERKEE + userDO.getUsername() ;
			Set<String> vals=jedis.keys("*"+key+"*");
			if (vals!=null&&vals.size()>0){
				jedis.del(vals.toArray()[0]+"");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
				if (jedis!=null)
					jedis.close();
		}

	}




	//查询用户的总收益
	@Override
	public  Map getUserStatus(long uid){
		Map<String,Object> resusucess=new HashMap<>();
		//总提成
		BigDecimal bigDecimal=hxLogDao.getUserPrice(uid);

		 //是否是会员
		UserDO userDO=userDao.get(uid);
		resusucess.put("price",bigDecimal);
		if (userDO.getIsvip()==1){
			System.out.println(userDO.getVipDate());
			//是vip算出 还剩余过期天数
			resusucess.put("vipstatus",1);
			resusucess.put("day",this.getDay(userDO.getVipDate()));
		}
		else {
			resusucess.put("vipstatus",0);
		}

		return  resusucess;
	}

	@Override
	public long getVipUserStatus(Long uid) {
		long days=11;
		//是否是会员
		UserDO userDO=userDao.get(uid);

		if (userDO.getIsvip()==1){

			days=this.getDay(userDO.getVipDate());
		}
		else{
			days=-1;
		}


		return days;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int editUserVip() {
		List<UserDO> list=userDaos.queryUserVip();
		for (UserDO userDO : list) {

			//if (userDO.getIsvip() == 1) {
				if (userDO.getVipDate() != null) {
					//判断是否过期
					long days = this.getDay(userDO.getVipDate());
					if (days <= 0) {
						//修改用户信息
						userDaos.editVip(userDO);
					}

				}

			}
	//	}

		return 0;
	}


	public   long getDay(Date entTime){
		Calendar calender= Calendar.getInstance();


		Calendar c1=Calendar.getInstance();
		c1.set(calender.get(Calendar.YEAR), calender.get(Calendar.MONTH)+1,calender.get(Calendar.DATE));
		Calendar c2=Calendar.getInstance();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ; //使用了默认的格式创建了一个日期格式化对象。
		 String time = dateFormat.format(entTime); //可以把日期转换转指定格式的字符串
			String[] ds=time.split("-");
			int y=Integer.parseInt(ds[0]);
			int m=Integer.parseInt(ds[1]);
			int d=Integer.parseInt(ds[2]);


		c2.set(y,m,d);
		long t1=c1.getTimeInMillis();
		long t2=c2.getTimeInMillis();
		long days=(t2-t1)/(24*60*60*1000);



		return  days;
	}


	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ; //使用了默认的格式创建了一个日期格式化对象。
		String time = dateFormat.format(new Date()); //可以把日期转换转指定格式的字符串
		System.out.println("当前的系统时间："+ time);

	}




}
