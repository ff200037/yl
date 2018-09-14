<#include "/common/include/macro.include"/> 
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.xxx.com/commonTag" prefix="common" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<common:resource></common:resource>
	<script type="text/javascript" src="${classNameLower }SearchForm.js"></script>
	<script type="text/javascript" src="${classNameLower }Manage.js"></script>
  </head>
  
  <body>
    
  </body>
</html>
