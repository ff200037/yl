<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.xxx.com/commonTag" prefix="common" %>
<!DOCTYPE html>
<html>
  <head>test jsp
  	<common:ext6></common:ext6>
    <script type="text/javascript">
  	//解码
	var jsonParams=Ext.urlDecode('<%=request.getQueryString()%>'); 
   </script>
  <!--  <script type="text/javascript" src="test.js"></script> -->
<!--   <script type="text/javascript" src="..//addCouponData//addCouponData.js"></script>
 -->  <script type="text/javascript" src="couponManage.js"></script>
  </head>
  
  <body>
    	hello world!
  </body>
</html>
