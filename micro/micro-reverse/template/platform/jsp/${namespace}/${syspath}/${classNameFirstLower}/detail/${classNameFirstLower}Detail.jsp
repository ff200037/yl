<#include "/common/include/macro.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.xxx.com/commonTag" prefix="common" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<common:resource></common:resource>
    <script type="text/javascript">
    	//解码
    	var jsonParams=Ext.urlDecode('<%=request.getQueryString()%>'); 
    </script>  	
	<script type="text/javascript" src="${classNameLower }Detail.js"></script>
  </head>
  
  <body>
    
  </body>
</html>
