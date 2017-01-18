package com.cwh.springmvc.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cwh.springmvc.Annotation.MyErrorView;

public class CommonExceptionResolver implements HandlerExceptionResolver {
	private static final Log log = LogFactory.getLog(CommonExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3) {
		String errorViewJsp = "error";
		log.info(arg3);
		arg3.printStackTrace();
		HandlerMethod method = (HandlerMethod) arg2;
		MyErrorView errorView = method.getMethodAnnotation(MyErrorView.class);
		if(null != errorView&&StringUtils.isNotBlank(errorView.value())){
			errorViewJsp = errorView.value();
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message",arg3.getMessage());
		modelAndView.setViewName(errorViewJsp);
		return modelAndView;
	}

}
