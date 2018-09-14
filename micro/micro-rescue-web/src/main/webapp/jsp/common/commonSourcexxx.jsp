<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/js/ext/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/css/newfontSize.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>resource/icons/icons.css"/>
<script src="<%=basePath%>resource/js/ext/adapter/ext/ext-base.js"></script>
<script src="<%=basePath%>resource/js/ext/ext-all.js"></script>
<!-- debug版
<script src="<%=basePath%>resource/js/ext/adapter/ext/ext-base-debug.js"></script>
<script src="<%=basePath%>resource/js/ext/ext-all-debug.js"></script>
 -->
<script src="<%=basePath%>resource/js/ext/ext-lang-zh_CN.js"></script>

<script src="<%=basePath%>resource/js/ext/ux/BufferView.js"></script>
<script src="<%=basePath%>resource/js/ext/ux/ProgressBarPager.js"></script>
<script src="<%=basePath%>resource/js/tool/pageTool.js"></script>
<script src="<%=basePath%>resource/js/tool/HtmlPanel.js"></script>
<script src="<%=basePath%>resource/js/tool/TreeNodeCheck.js"></script>
<script src="<%=basePath%>resource/js/tool/PinyinFilter.js"></script>

 <!-- 
<link  rel="stylesheet" type="text/css" href="<%=basePath%>wro/all.css"/>
<script src="<%=basePath%>wro/all.js"></script>
 -->

<script src="<%=basePath%>jsp/common/commonCode.js"></script>
<script src="<%=basePath%>jsp/common/UDlib.js"></script>

<script type="text/javascript">
	var contextPath='<%=basePath2%>';
	//Ext.BLANK_IMAGE_URL = contextPath + '/resource/js/ext/resources/images/default/s.gif';
	//Ext.SSL_SECURE_URL= contextPath + '/resource/js/ext/resources/images/default/s.gif';

</script>

