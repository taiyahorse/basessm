package com.base.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.base.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
//        log.info("token：" + token);
//
//        if (token == null) {
//            log.error("token为空");
//        }
//        //TODO 这里要替换真正的密码
//        String password = "";
//        try {
//            JwtUtil.verifyToken(token,password);
//        } catch (SignatureVerificationException e) {
//            log.error("无效签名");
//            return false;
//        } catch (TokenExpiredException e) {
//            log.error("token过期");
//            return false;
//        } catch (AlgorithmMismatchException e) {
//            log.error("token算法不一致");
//            return false;
//        } catch (Exception e) {
//            log.error("token无效");
//            return false;
//        }
        return true;
    }
}
