

package com.zen.smi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class SessionExpiredInterceptor extends HandlerInterceptorAdapter{


    static final Logger logger = Logger.getLogger(SessionExpiredInterceptor.class);
    
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     final HttpSession session = request.getSession(false);
         if ( session == null || session.isNew() ) {
              System.out.println("Session expired, redirect home page");
             logger.info("Session expired, redirect home page");
             request.getSession(true);
             response.sendRedirect("/smi/login");
         } 
        return true;

    }
	
}