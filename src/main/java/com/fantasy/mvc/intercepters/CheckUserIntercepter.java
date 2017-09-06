package com.fantasy.mvc.intercepters;

import com.fantasy.mvc.objects.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Djelu on 06.09.2017.
 */

public class CheckUserIntercepter extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(request.getRequestURI().contains("check-user")){
//            User user = (User) request.getAttribute("user");
            User user = (User) modelAndView.getModel().get("user");
            if(user == null || !user.isAdmin()){
                response.sendRedirect(request.getContextPath() + "/failed");
            }
        }
    }
}
