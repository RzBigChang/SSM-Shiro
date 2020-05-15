<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
  <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
  <script type="text/javascript" src="js/text.js"></script>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
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
  <div>
    <div class="login-box" align="center">
		<form action="login" method="post">
		
		 <div class="form-group">
            <div class="col-xs-4">
                <input type="text" name="username" class="text" value=""  >
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-xs-4">
               <input type="password" name="password" value="" >
            </div>
        </div>
        
        <div class="clear"> </div>
		<div class="btn">
			<input type="submit" value="登录" >
		</div>
		<div class="clear"> </div>
	</form>
	<a class="btn btn-primary" href="/ADD" role="button">还没有账号？点我注册</a>
</div>
</div>
  </body>
</html>
