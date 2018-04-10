<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html class="login-alone">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body style="text-align: center;margin-top: 168px;">
	<h1>Links</h1>
	<div class="table_box">
		<p>${user.userName} 欢迎来到Links的世界！</p>
		<br>
		<a href="${ctx }/logout">退出</a>
	</div>
</body>
</html>