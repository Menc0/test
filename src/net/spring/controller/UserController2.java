package net.spring.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import net.spring.model.User2;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user2")
public class UserController2 {
	@RequestMapping("/reguser")
	public String reguser(@Valid @ModelAttribute("user2") User2 user,BindingResult result){
		if(result.hasErrors()){
			return "RegUser";
		}else{
			return "Login";
		}
	}
	@RequestMapping("/reg")
	public ModelAndView reg(@ModelAttribute("user2") User2 user){
		return new ModelAndView("RegUser", "user2", user);
	}
	
	//定义初始化绑定
    @InitBinder   
    public void initBinder(WebDataBinder binder) {   
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        //dateFormat.setLenient(true);//严格匹配日期格式   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  
}
