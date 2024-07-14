package member.board.intercepter;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import member.board.dto.MemDto;

public class LoginIntercepter implements HandlerInterceptor {

	public List<String> loginEssential = Arrays.asList("/board/**", "/comm/insert","/ref/**");

	public List<String> loginInessential = Arrays.asList("/board/list/**", "/board/content/**","/ref/list/**", "/ref/content/**");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemDto dto = (MemDto) request.getSession().getAttribute("user");

		if (dto != null && dto.getId() != null) {
			//request.getSession().setAttribute("result", true);
			return true;
		} else {
			request.getSession().setAttribute("result", false);
			response.sendRedirect("/main");
			return false;
		}
	}
}
//	 * @Override public void postHandle(HttpServletRequest request,
//	 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
//	 * throws Exception { // TODO Auto-generated method stub
//	 * HandlerInterceptor.super.postHandle(request, response, handler,
//	 * modelAndView); }
//	 * 
//	 * @Override public void afterCompletion(HttpServletRequest request,
//	 * HttpServletResponse response, Object handler, Exception ex) throws Exception
//	 * { // TODO Auto-generated method stub
//	 * HandlerInterceptor.super.afterCompletion(request, response, handler, ex); }
//	 
