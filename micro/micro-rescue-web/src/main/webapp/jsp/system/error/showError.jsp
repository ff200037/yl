<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>出错了!</title>
</head>

<body>
	<%
		Exception ex = (Exception)request.getAttribute("exception");
	%>
	Exception:
		<%=ex.getMessage()%>
	<br>
	<br>
	<%
		ex.printStackTrace(new java.io.PrintWriter(out));
	%>
</body>
</html>
