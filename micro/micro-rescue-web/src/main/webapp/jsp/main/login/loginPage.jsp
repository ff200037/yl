<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.xxx.com/commonTag" prefix="common"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<common:resource></common:resource>
<title>登录页面</title>
<script type="text/javascript" src="login2.js"></script>
<style>
body {
	padding: 0;
	margin: 0;
}

#bg {
	width: 100%;
	height: 100%;
	position: absolute;
	z-index: -1;
}
#loginpanel_demo {
	background: #fff;
	position: absolute;
	left: 40%;
	top: 40%;
}
</style>
</head>

<body>
	<img id="bg" src="loginPic.png" />
	<div id="loginpanel"></div>
</body>
</html>
