package com.bootdo.school.util;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.system.domain.UserDO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {



    public String getUserToken(UserDO userDO, HttpServletRequest request) {
        String token = "";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", userDO.getUserId());
        map.put("username", userDO.getUsername());
        //拿到ip地址
        String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();// 从request中获取ip
            if (StringUtils.isBlank(ip)) {
                ip = "127.0.0.1";
            }
            //按照jwt加密

        }

        token = JwtUtil.encode(MessageConstant.JwyKey, map, ip);
        return token;
    }

}
