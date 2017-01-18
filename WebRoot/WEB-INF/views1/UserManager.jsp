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
    
    <title>用户管理views2</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  	function search(){
	  var name=document.getElementById("username").value;
	  var form = document.forms["userform1"];
		form.action = "/springMVC/user/getUserByName.do?name="+name;
		form.method="post";
		form.submit();
	  
	}
  	function insert(){
  	
  		window.location="/springMVC/user/insert.do";
  	}
  
  </script>
  
  <body>

  
	 <form action="" name="userform1"> 
		姓名：<input type="text" id="username" >
		<input type="button" value="查找" onclick="search()">
	</form>
	<input type="button" name="button1" id = "button1" value="添加用户" onclick="return insert();">
    <table align="left" border="1">
    	<tr>
    		<th>ID</th>
    		<th>用户名</th>
    		<th>密码</th>
    		<th>状态</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach var="u"  items="${userlist}">
    	<tr>
    		<td>${u.id}</td>
    		<td>${u.username }</td>
    		<td>${u.password }</td>
    		<td><c:if test="${u.status==1 }">有效</c:if>
    		<c:if test="${u.status==0 }">无效</c:if></td>
    		<td><a href ='<%=request.getContextPath() %>/user/UpdateUser.do?id=${u.id }'>修改</a>|
    		<a href ='user/DeleteUser.do?id=${u.id }' onclick='return confirm("确认删除？");'>删除</a></td>
    		
    	</tr>
    	
    	</c:forEach>
    	
    </table>
    <br>
    	
    		第
    	<c:forEach begin="1" end="${pageCount}" var="p">
    		<a href="user/userlist.do?currentpage=${p}">${p}</a>
    	</c:forEach>
    		页
    		
    	
    
  </body>
</html>
