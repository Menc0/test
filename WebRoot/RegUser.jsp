<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Reservation Form</title>
<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>
<script type="text/javascript">
	function upload(){
	alert("upload");
	   var name=document.getElementsByName("name").value;
	   var file=document.getElementsByName("file").value;
	    $.get("/springMVC/upload/upload.do?name="+name+"&file="+file,function(data){
			if("success" == data.result){
				alert("删除成功");
				window.location.reload();
			}else{
				alert("删除失败");
			}
		});
	    
	   /*  var form = document.forms["upload"];
	    alert(form);
		form.action = "/user/upload.do";
		form.method="post";
		form.submit(); */
	}
</script>
</head>

<body>
	<form:form method="post" modelAttribute="user2" action="reguser.do">
		<form:errors path="*" cssClass="error" />
		<table>
			<tr>
				<td>Name</td>
				<td><form:input path="username" />
				</td>
				<td><form:errors path="username" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>email</td>
				<td><form:input path="email" />
				</td>
				<td><form:errors path="email" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>Customer Age :</td>
				<td><form:input path="age" /></td>
				<td><form:errors path="age" cssClass="error" /></td>
			</tr>
			<tr>
				<td>birthday</td>
				<td><form:input path="birthday"/></td>
				<td><form:errors path="birthday" cssClass="error"/></td>
			
			</tr>
			<tr>
				<!--<form:form name="upload" action="" enctype="multipart/form-data">
				<input type="text" name="name" />
				<input type="file" name="file" />
				<input type="button" value="上传" onclick="upload()"/>
				</form:form>
				-->
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="注册"/>
				</td>
			</tr>
		</table>
	</form:form>
	
				<form name="upload" action="/springMVC/upload/upload.do" enctype="multipart/form-data">
				<input type="text" name="name" />
				<input type="file" name="file" />
				<input type="submit" value="chang"/>
				<input type="button" value="上传" onclick="javascript:upload()"/>
				</form>
</body>
</html>
  </body>
</html>
