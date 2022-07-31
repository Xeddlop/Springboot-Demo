package com.example.demo.config;

import com.example.demo.annotation.Token;
import com.example.demo.pojo.User;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

        @Autowired
        private UserService userService;
        @Autowired
        private TokenUtils tokenUtils;
        public static final String USER_KEY = "USER_ID";
        public static final String USER_INFO = "USER_INFO";

        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object handler) throws Exception {
            Token annotation;


            // Check if handler is mapping to a method
            if (!(handler instanceof HandlerMethod handlerMethod)) {
                return true;
            }else {
                annotation = ((HandlerMethod) handler).getMethodAnnotation(Token.class);
            }

            //没有声明需要权限,或者声明不验证权限
            if(annotation == null || !annotation.validate()){
                return true;
            }

            // 有 @LoginUser 注解，需要认证
            // 执行认证
            System.out.println("Interceptor:Catch a request to /dashboard, verifying...");
            String token = request.getHeader("token");  // 从 http 请求头中取出 token
            if (token == null) {
                System.out.println("Lack of token. Request denied");
                return false;
            }
            int userId;
            User user;
            // Verify Token and get user information
            try {
                user = tokenUtils.validationToken(token);
            } catch (Exception e) {
                System.out.println("Token invalid! Please log in and try again");
                return false;
            }

            if (user != null){
            // realUser should contain all information stored in database
            User realUser = userService.findUserByUsername(user.getUsername());
            request.setAttribute("currentUser", realUser);
            System.out.println(realUser+" has been added to request.");
            return true;
            }else {
                return false;
            }
        }
}
