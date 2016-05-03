package net.spring.common;


//import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
    
    public void setAllowUrls(String[] allowUrls) {  
        this.allowUrls = allowUrls;  
    }  
	
//	@Override
//	public  void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex)	throws Exception {
//		System.out.println("最后执行！！！一般用于释放资源！！");
//		
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,	ModelAndView modelAndView) throws Exception {
//		System.out.println("Action执行之后，生成视图之前执行！！");
//	}

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("action之前执行！！！");
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");    
       // System.out.println(requestUrl);  
        if(null != allowUrls && allowUrls.length>=1)  
            for(String url : allowUrls) {    
                if(requestUrl.contains(url)) {    
                    return true;    
                }    
            }  
		if(request.getSession().getAttribute("user")==null){
			request.getRequestDispatcher("/Login.jsp").forward(request, response); 
			return false;
		}
		
		return true;  //继续执行action
	}




}
