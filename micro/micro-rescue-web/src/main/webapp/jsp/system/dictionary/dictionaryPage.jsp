<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.xxx.com/commonTag" prefix="common" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<common:resource></common:resource>
  	<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/js/ext/ux/treegrid/treegrid.css"/>
	<script src="<%=basePath%>resource/js/ext/ux/treegrid/TreeGridSorter.js"></script>
	<script src="<%=basePath%>resource/js/ext/ux/treegrid/TreeGridColumnResizer.js"></script>
	<script src="<%=basePath%>resource/js/ext/ux/treegrid/TreeGridNodeUI.js"></script>
	<script src="<%=basePath%>resource/js/ext/ux/treegrid/TreeGridLoader.js"></script>
	<script src="<%=basePath%>resource/js/ext/ux/treegrid/TreeGridColumns.js"></script>
	<script src="<%=basePath%>resource/js/ext/ux/treegrid/TreeGrid.js"></script>
	
	<script src="<%=basePath%>resource/js/tool/PinyinFilter.js"></script>
  	
  	
	<script type="text/javascript" src="leftTree/addDicWindow.js"></script>
	<script type="text/javascript" src="leftTree/treeCode.js"></script>
	<script type="text/javascript" src="dictionaryData.js"></script>
  </head>
  
  <body>
    
  </body>
</html>
