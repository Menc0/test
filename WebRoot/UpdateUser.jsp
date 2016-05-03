<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
	
  		
  	<form action="user/update.do" method = "post">
  		用户名：<input type="text" name="username" value='${user.username }'><br>
  		密码：<input type="password" name="password" value='<c:out value="${user.password }"></c:out>'><br>
  		状态：<select name="status">
  			<option <c:if test="${user.status } eq 1">selected="selected"</c:if> value="1">有效</option>
  			<option <c:if test="${user.status }==0">selected="selected"</c:if> value="0">无效</option>
  		</select>
  		<input type="hidden" value="${user.id }" name="id">
  		<input type="submit" value="保存">
  	</form>
    
  </body>
</html>
