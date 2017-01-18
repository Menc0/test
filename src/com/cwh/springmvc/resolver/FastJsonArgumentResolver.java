package com.cwh.springmvc.resolver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cwh.springmvc.Annotation.FastJson;

public class FastJsonArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter paramMethodParameter) {
		// 仅对有fastjson注解有效
		return paramMethodParameter.hasParameterAnnotation(FastJson.class);
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

        // 把reqeust的body读取到StringBuilder
        /*BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();

        char[] buf = new char[1024];
        int rd;
        while((rd = reader.read(buf)) != -1){
            sb.append(buf, 0, rd);
        }*/
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
        JSONObject obj= JSONObject.fromObject(str);
        /*// 利用fastjson转换为对应的类型
        if(JSONObjectWrapper.class.isAssignableFrom(paramMethodParameter.getParameterType())){
            return new JSONObjectWrapper(JSON.parseObject(sb.toString()));
        } else {
            return JSON.parseObject(sb.toString(), paramMethodParameter.getParameterType());
        }*/
        return JSONObject.toBean(obj,paramMethodParameter.getParameterType());
    }

}
