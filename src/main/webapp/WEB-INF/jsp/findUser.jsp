<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看用户信息</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
	   <table class="table table-hover table-condensed">
        <thead>
           <tr>
             <th>用户id</th>
             <th>用户姓名</th>
             <th>用户密码</th>
            </tr> 
         </thead>
         <tbody>
         <c:forEach items="${list}" var="user">
            <tr>
            	<td>${user.id }</td>
           		<td>${user.username}</td>
            	<td>${user.password }</td>
             </tr>
            </c:forEach>
            </tbody>
         </table>
         <a href="ADD">添加用户</a>
  </body>
</html>
