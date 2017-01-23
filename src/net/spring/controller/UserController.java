package net.spring.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




//import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.spring.common.Pagesize;
import net.spring.model.User;
import net.spring.service.UserService;
import net.spring.util.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cwh.springmvc.Annotation.Json2Bean;
import com.cwh.springmvc.Annotation.MyErrorView;


/**
 * 
 * @author  cwh
  ___                  _
/\  _ `\   _       _  /\ \                       
\ \ \/\_\ /\ \    /\ \\ \ \___        
 \ \ \/_/_\ \ \  _\ \ \\ \  _ `\  
  \ \ \L\ \\ \ \/ _` \ \\ \ \ \ \ 
   \ \____/ \ \_ /_ \_ / \ \_\ \_\
    \/___/   \/_/  \/_/   \/_/\/_/
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	//登录
	 @RequestMapping("/login")
	 public String login(){
	 	 return "Login";
	  }
	  @RequestMapping("/loginuser")
	  public String login(@RequestParam("username") String username,@RequestParam("password") String password,HttpServletRequest request){
	 	 //RequestParam  就会将username参数的值付给username。当然，如果请求参数名称和形参名称保持一致，则不需要这种写法
		  User user=userservice.getUserByName(username);
		  if(user!=null){
		  String password2=userservice.getUserByName(username).getPassword();
		  //System.out.println("========"+password2);
		  if(password.equals(password2)){
	 		 //System.out.println("登录成功！");
	 		//HttpSession httpSession = null;
			//request.getSession().setMaxInactiveInterval(120);//120秒  
	 		request.getSession().setAttribute("user", username);
			//httpSession.setAttribute("user",username);
	 		 return "redirect:userlist.do?currentpage=0";
	 	 }else{
	 	 System.out.println(username+","+password);
	 	 System.out.println("登录失败");
	 	 return "redirect:/user/login.do";}
		  }
		  return "redirect:/user/login.do";
	  }
	//用户列表
	@RequestMapping("/userlist")
	@MyErrorView("error1")
	public ModelAndView userlist(@RequestParam("currentpage")int currentpage,HttpSession session) throws Exception{
		 List<User> userList = new ArrayList<User>();
		 int pagesize = Pagesize.PAGESIZE;
		 int pageCount = 0;
		 pageCount = PageUtil.PageCount("user_tbl");
		 
		 session.setAttribute("pageCount", pageCount);//传递分页数目
		 userList = userservice.searchAllUser(currentpage,pagesize);
		 //if(1 ==1)throw new Exception("this is a commonExceptionResolver");
		//返回ModelAndView对象包含view名称和传递的对象
		return new ModelAndView("UserManager","userlist",userList);
	
	}
	//添加用户
	@RequestMapping("/insert")
	public String insert(){
		return "InsertUser";
	}
	@RequestMapping("/insertuser")
	public String insertuser(@ModelAttribute("user") User user){
		boolean bool = userservice.insertUser(user);
		if(bool){
			System.out.println("添加成功！");
			
			//返回重定向
			return "redirect:userlist.do?currentpage=0";
			
		}else{
			System.out.println("添加失败！");
			return "fail";
		}
	}
	//修改用户信息
	@RequestMapping("/UpdateUser")
	 public ModelAndView updateuser(@RequestParam("id") int id){
		 System.out.println("udateuser!"+id);
		 User user = new User();
		 user = userservice.searchUser(id);
		return new ModelAndView("UpdateUser", "user",user);
		 
	 }
	@RequestMapping("/update")
	public String update(@ModelAttribute("user") User user){
		System.out.println("username:"+user.getUsername());
		boolean bool = userservice.updateUser(user);
		if(bool){
			System.out.println("修改成功！");
			return "redirect:/user/userlist.do?currentpage=0";
		}else{
			return "fail";
		}
		
	}
	//删除用户信息
	@RequestMapping("/DeleteUser")
	public String deleteuser(@RequestParam("id") int id){
		boolean bool = userservice.deleteUser(id);
		if(bool){
			System.out.println("删除成功！");
			return "redirect:/user/userlist.do?currentpage=0";
		}else{
			System.out.println("删除失败！");
			return "fail";
		}
	}
	@RequestMapping("/getUserByName/{name}")
	public @ResponseBody User getUserByName(@PathVariable("name")String name, HttpServletRequest request) throws IOException{
		//@Json2Bean User userform,
		//System.out.println(userform);
		//name = userform.getUsername();
		User user= userservice.getUserByName(name);
		
		request.setAttribute("userlist",user);
		
		return user;
	}
	
}
