package com.base.utils;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class JwtUtil {

    /**
     * 后去需要将strs字符串数组修改为当前登录对象，替换登录对象中的成员属性
     * @param strs
     */
    public static String createToken(String[] strs){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,30);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("uid", strs[0])
                .withClaim("username", strs[1])
                .withClaim("role", strs[2])
                .withClaim("permission", strs[3]);
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(strs[4]));
    }

    public static DecodedJWT verifyToken(String token,String password){
        JWTVerifier build = JWT.require(Algorithm.HMAC256(password)).build();
        return build.verify(token);
    }

}
