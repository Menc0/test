package net.spring.controller;

import java.util.ArrayList;
import java.util.List;

import net.spring.dao.UserDAO;
import net.spring.dao.impl.IUserDAO;
import net.spring.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HelloWorldController {
	
	UserDAO dao = new IUserDAO();
 @RequestMapping("/hello")
 public String helloWorld() {
	
	

  String message = "Hello World, Spring 3.0!";
  System.out.println(message);
  //return new ModelAndView("hello", "message", message);
  return "hello";
 }
 @RequestMapping("/hello2")
 public ModelAndView helloWorld1() {

  String message = "Hello World, Spring 3.0!";
  System.out.println(message);
  return new ModelAndView("hello", "message", message);
 }

 
 
 
 
 
 
 
 

 
}
