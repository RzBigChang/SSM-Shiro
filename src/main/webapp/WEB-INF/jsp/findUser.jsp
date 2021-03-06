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
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
  <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
  <script type="text/javascript" src="js/text.js"></script>
    <title>查看用户信息</title>
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
             <th>修改</th>
             <th>删除</th>
            </tr> 
         </thead>
         <tbody>
         <c:forEach items="${list}" var="user">
            <tr>
            	<td>${user.id }</td>
           		<td>${user.username}</td>
            	<td>${user.password }</td>
            	<td><a href="RequerId?id=${user.id}">修改</a></td>
            	<td><a href="javascript:shan('+${user.id}+')">删除</a></td>
             </tr>
            </c:forEach>
            </tbody>
         </table>
         <a href="ADD">添加用户</a>
  </body>
</html>
