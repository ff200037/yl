<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.xxx.com/commonTag" prefix="common" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<common:resource></common:resource>
    <title>实车与设备数据处理中心</title>
    		<style type="text/css">
#loading-mask {
	Z-INDEX: 20000;
	LEFT: 0px;
	WIDTH: 100%;
	POSITION: absolute;
	TOP: 0px;
	HEIGHT: 100%;
	BACKGROUND-COLOR: white
}

#loading {
	PADDING-RIGHT: 2px;
	PADDING-LEFT: 2px;
	Z-INDEX: 20001;
	LEFT: 45%;
	PADDING-BOTTOM: 2px;
	PADDING-TOP: 2px;
	POSITION: absolute;
	TOP: 40%;
	HEIGHT: auto
}

#loading IMG {
	MARGIN-BOTTOM: 5px
}

#loading .loading-indicator {
	PADDING-RIGHT: 10px;
	PADDING-LEFT: 10px;
	BACKGROUND: white;
	PADDING-BOTTOM: 10px;
	MARGIN: 0px;
	FONT: 12px 宋体, arial, helvetica;
	COLOR: #555;
	PADDING-TOP: 10px;
	HEIGHT: auto;
	TEXT-ALIGN: center
}

.banner {
	font-family: "宋体";
	font-size: 12px;
	color: 4798D7;
}
</style>
    <script type="text/javascript">
    	var accountName='<%=request.getAttribute("accountName")%>';
    </script>
    <script type="text/javascript" src="TabCloseMenu.js"></script>
	<script type="text/javascript" src="changePasswordWindow.js"></script>
	<script type="text/javascript" src="topPanel.js"></script>
	<script type="text/javascript" src="MainPanel.js"></script>
  </head>
  
  <body>
    
  </body>
</html>
