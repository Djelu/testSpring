package com.fantasy.mvc.intercepters;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Djelu on 06.09.2017.
 */
public class TimeIntercepter extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(TimeIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long startTime = Long.valueOf(request.getAttribute("startTime").toString());

        Thread.sleep(2900);

        int totalTime = (int) ((System.currentTimeMillis() - startTime) / 1000);

        modelAndView.addObject("totalTime", totalTime);
        logger.info(handler + ": post handle method, totalTime passed: " + totalTime + "sec");
    }
}
