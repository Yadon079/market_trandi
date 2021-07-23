package com.cloud.mini.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cloud.mini.member.model.Member;

import lombok.extern.slf4j.Slf4j;

@Component //등록한 빈 가져오기
@Slf4j //로그 확인 
public class SignInCheckInterceptor extends HandlerInterceptorAdapter{
				@Override
				public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception{
				log.debug("preHandle");
				HttpSession session = request.getSession();
				String id = "";
				id = (String) session.getAttribute("user");
				if(id == "") {
					response.sendRedirect("/member/login");
				}
					return super.preHandle(request, response, handler);
				}
}
