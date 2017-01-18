package com.cwh.springmvc.resolver;

import java.io.BufferedInputStream;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cwh.springmvc.Annotation.Json2Bean;

public class Json2BeanArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter paramMethodParameter) {
		// 仅对有fastjson注解有效
		return paramMethodParameter.hasParameterAnnotation(Json2Bean.class);
	}

	@Override
	public Object resolveArgument(MethodParameter paramMethodParameter,
			ModelAndViewContainer paramModelAndViewContainer,
			NativeWebRequest paramNativeWebRequest,
			WebDataBinderFactory paramWebDataBinderFactory) throws Exception {
		HttpServletRequest request = paramNativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // content-type不是json的不处理
        if (!request.getContentType().contains("application/json")) {
            return null;
        }
        StringBuffer str = new StringBuffer(); 
		try {
			   BufferedInputStream in = new BufferedInputStream(request.getInputStream());
			      int i;
			      char c;
			      while ((i=in.read())!=-1) {
			      c=(char)i;
			      str.append(c);
			      }
			     }catch (Exception ex) {
			   ex.printStackTrace();
			   }
        JSONObject obj= JSONObject.fromObject(str.toString());
        return JSONObject.toBean(obj,paramMethodParameter.getParameterType());
    }

}
