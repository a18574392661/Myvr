package com.bootdo.system.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.dao.UserCouponDao;
import com.bootdo.school.domain.UserCouponDO;
import com.bootdo.school.util.GenSerial;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.UserService;
import com.bootdo.system.vo.UserVO;
import com.bootdo.vrs.common.MessageConstantVrs;
import org.apache.commons.lang.ArrayUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

//@CacheConfig(cacheNames = "user")
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;
    @Autowired
    DeptDao deptMapper;
    @Autowired
    private FileService sysFileService;
    @Autowired
    private BootdoConfig bootdoConfig;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserCouponDao userCouponDao;


    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisUtil redisUtil;
    
    @Override
//    @Cacheable(key = "#id")
    public UserDO get(Long id) {
        List<Long> roleIds = userRoleMapper.listRoleId(id);
        UserDO user = userMapper.get(id);
        if (user.getDeptId()!=null) {
        	 user.setDeptName(deptMapper.get(user.getDeptId()).getName());	
		}
       
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public List<UserDO> list(Map<String, Object> map) {

        return userMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userMapper.count(map);
    }

    @Transactional
    @Override
    public int save(UserDO user) {

        if (user.getStatus()==null){
            user.setStatus(1);
        }

            List<Long> roles = user.getRoleIds();
         //   roles.add(user.getRid());
            int count = userMapper.save(user);
            Long userId = user.getUserId();

            userRoleMapper.removeByUserId(userId);
            List<UserRoleDO> list = new ArrayList<>();
            //一对一
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(user.getRid());
            list.add(ur);

        if (roles!=null){
            for (Long roleId : roles) {
                //只可能有一个
                user.setRid(roleId);

                UserRoleDO ur1 = new UserRoleDO();
                ur1.setUserId(userId);
                ur1.setRoleId(roleId);
                list.add(ur1);
            }
        }

        if (list.size() > 0) {
            userRoleMapper.batchSave(list);
        }
        return count;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int update(UserDO user) {
        Jedis jedis=redisUtil.getJedis();
        int r=0;
        //模糊匹配key
        String key= MessageConstant.USER_REDIS + user.getStartName() ;
        Set<String> vals=jedis.keys("*"+key+"*");
        try {
            r = userMapper.update(user);
            Long userId = user.getUserId();
            List<Long> roles = user.getRoleIds();
            userRoleMapper.removeByUserId(userId);

            List<UserRoleDO> list = new ArrayList<>();
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(user.getRid());
            list.add(ur);

            //一定是null的
            if (roles!=null){
                for (Long roleId : roles) {
                    user.setRid(roleId);
                    UserRoleDO ur1 = new UserRoleDO();
                    ur1.setUserId(userId);
                    ur1.setRoleId(roleId);
                    list.add(ur1);
                }

            }

            if (list.size() > 0) {
                userRoleMapper.batchSave(list);
            }

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
        return r;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int remove(Long userId) {
       Jedis jedis=redisUtil.getJedis();
        //模糊匹配key
        String key=null;
        String val=null;
        try {
            userRoleMapper.removeByUserId(userId);
            UserDO user= userMapper.get(userId);
            if (user!=null){
                  key=MessageConstant.USER_REDIS + user.getStartName() ;
                Set<String> likeKeys= jedis.keys("*"+key+"*");

                if (likeKeys!=null&&likeKeys.size()>0){
                    jedis.del(likeKeys.toArray()[0]+"");

                }
            }


        }catch (Exception e){

            e.printStackTrace();
        }finally {
            if (jedis!=null)
                jedis.close();
        }

        return userMapper.remove(userId);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userMapper.list(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
        if (Objects.equals(userVO.getUserDO().getUserId(), userDO.getUserId())) {
            if (Objects.equals(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()), userDO.getPassword())) {
                userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
                return userMapper.update(userDO);
            } else {
                throw new Exception("输入的旧密码有误！");
            }
        } else {
            throw new Exception("你修改的不是你登录的账号！");
        }
    }

    @Override
    public int adminResetPwd(UserVO userVO) throws Exception {

            UserDO userDO = get(userVO.getUserDO().getUserId());
            if ("admin".equals(userDO.getUsername())) {
                throw new Exception("超级管理员的账号不允许直接重置！");
            }
            userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));



        return userMapper.update(userDO);


    }

    @Transactional
    @Override
    public int batchremove(Long[] userIds) {
        int count = userMapper.batchRemove(userIds);
        userRoleMapper.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public Tree<DeptDO> getTree() {
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        List<DeptDO> depts = deptMapper.list(new HashMap<String, Object>(16));
        Long[] pDepts = deptMapper.listParentDept();
        Long[] uDepts = userMapper.listAllDept();
        Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
        for (DeptDO dept : depts) {
            if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
                continue;
            }
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(dept.getDeptId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            trees.add(tree);
        }
        List<UserDO> users = userMapper.list(new HashMap<String, Object>(16));
        for (UserDO user : users) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(user.getUserId().toString());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public int updatePersonal(UserDO userDO) {
        return userMapper.update(userDO);
    }

    @Override
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        //获取图片后缀
        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        String[] str = avatar_data.split(",");
        //获取截取的x坐标
        int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
        //获取截取的y坐标
        int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
        //获取截取的高度
        int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
        //获取截取的宽度
        int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
        //获取旋转的角度
        int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
        try {
            BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
            BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(rotateImage, prefix, out);
            //转换后存入数据库
            byte[] b = out.toByteArray();
            FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            throw new Exception("图片裁剪错误！！");
        }
        Map<String, Object> result = new HashMap<>();
        if (sysFileService.save(sysFile) > 0) {
            UserDO userDO = new UserDO();
            userDO.setUserId(userId);
            userDO.setPicId(sysFile.getId());
            if (userMapper.update(userDO) > 0) {
                result.put("url", sysFile.getUrl());
            }
        }
        return result;
    }

    
    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public UserDO appLogin(UserDO userDO) {
        //防止学员重复添加 记录
        if (userDO.getRid()==62){
            RLock rLock=redissonClient.getLock(userDO.getUsername()+":"+MessageConstant.LOGIN_LOCK);
            try {
                boolean b = rLock.tryLock(10, TimeUnit.SECONDS);
                if (b==false){

                    return  appLogin(userDO);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                rLock.unlock();
            }
        }
		UserDO userSucess=null;
		//先查询用户名是否存在 
		UserDO userDaoName= userMapper.queryUserName(userDO);
		if (userDaoName==null) {
			//判断这个手机号码是否注册过其他的账号 
			/*int telDisbale=userMapper.queryUserNameDisble(userDO.getUsername());
			if (telDisbale>0) {
				//存在重复用户名字 
				throw new RuntimeException(MessageConstant.USERDISBALETEL);
			}*/
			//添加
			//int count=userMapper.save(userDO);
			/*if (count<=0) {
				//添加失败 
				throw new RuntimeException(MessageConstant.APP_USERLOGIN_ERROR);
			}*/
			//id赋值过去了
			//userSucess=userDO;

            throw new RuntimeException(MessageConstant.USER_LOGIN_NAME);
		}
		else {
			//密码判断是否相等
			if (userDaoName.getPassword().equals(userDO.getPassword())) {
				userSucess=userDaoName;
				//添加核销卷
                if (userDO.getRid()==62) {
                    //下查询是否存在
                  int count= userCouponDao.queryUserAndCoupon(userDaoName.getUserId());
                  if (count>0){
                      //避免重复添加
                      return userSucess;
                  }

                    GenSerial genSerial = new GenSerial(0, 0);
                    //添加 一条用户的核销优惠券 后台可以编辑
                    UserCouponDO userCouponDO=new UserCouponDO();
                    userCouponDO.setCreatedate(new Date());
                    userCouponDO.setStatus(MessageConstant.SUCESS_STATUS); //正常
                    userCouponDO.setUid(userSucess.getUserId());
                    userCouponDO.setCode(genSerial.nextId()+"");
                    userCouponDO.setPrice(new BigDecimal(0));
                    int userCouponSave=userCouponDao.save(userCouponDO);
                    if (userCouponSave<=0) {
                        //用户优惠券添加失败
                        throw new RuntimeException(MessageConstant.APP_USERLOGIN_ERROR);
                    }
                }

			}
			else {
				//密码错误 
				throw new RuntimeException(MessageConstant.APP_USERLOGINPWD_ERROR);
			}
			
		}
		
		
		
		return userSucess;
	}
	//vr项目登录
    @Override
    public UserDO vrLogin(UserDO userDO) {
        UserDO userSucess = null;
        //先查询用户名是否存在
        UserDO userDaoName = userMapper.queryVRUserName(userDO.getUsername());
        if (userDaoName == null) {
            throw new RuntimeException(MessageConstantVrs.VRUSER_LOGIN_NAM);
        } else {
            //密码判断是否相等
            if (userDaoName.getPassword().equals(userDO.getPassword())) {
                userSucess = userDaoName;
            } else {
                //密码错误
                throw new RuntimeException(MessageConstantVrs.VRUSER_LOGIN_PWD);
            }

            return userSucess;
        }
    }

}
