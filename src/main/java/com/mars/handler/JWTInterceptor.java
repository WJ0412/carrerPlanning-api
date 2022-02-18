package com.mars.handler;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mars.utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 放行所有请求,否则跨域注解失效
         */
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }
        Map<String, String> map = new HashMap<>();

        //获取请求头中的token令牌
        String token = request.getHeader("token");
        try {
            JWTUtil.verify(token);//验证令牌


            boolean b = hasRole(request);
            if(!b){
                map.put("code", "403");//设置状态
                map.put("msg", "没有权限");
                String json = new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json);
                return false;
            }


            return true;//放行请求
        } catch (SignatureVerificationException e) {
            map.put("msg", "无效签名!");
        } catch (TokenExpiredException e) {
            map.put("msg", "token过期!");
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "token算法不一致!");
        } catch (Exception e) {
            map.put("msg", "token无效!!");
        }
        map.put("code", "403");//设置状态
        //将 map 转为json  jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }

    /**
     * 验证当前是否有权限
     * @param request
     * @return
     */
    public boolean hasRole(HttpServletRequest request){
        String url = request.getRequestURI();
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtil.getTokenInfo(token);
        String role = tokenInfo.getClaim("role").asString();

        if((url).startsWith("/"+role)){
            return true;
        }
        return false;
    }
}