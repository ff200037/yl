package com.yile.micro.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class CommonTagForExt6 extends BodyTagSupport {
	private PageContext pageContext;

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}
	public int doStartTag() throws JspException {
		super.doStartTag();
		return 2;
	}
	
	public int doEndTag() throws JspException {
		ServletRequest servletRequest=pageContext.getRequest();
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		try {
			JspWriter out = pageContext.getOut();  
			String returnStr="\n";
//			out.print("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"+returnStr);
////			out.print("<meta charset='UTF-8'>"+returnStr);
//			out.print("<meta http-equiv='pragma' content='no-cache'>"+returnStr);
//			out.print("<meta http-equiv='cache-control' content='no-cache'>"+returnStr);
//			out.print("<meta http-equiv='expires' content='0'>"+returnStr);
			List<String> linkList=getLink();
			for (String item : linkList) {
				out.print("<link rel='stylesheet' type='text/css' href='"+basePath+item+"'/>"+returnStr);
			}
			List<String> scriptList=getScript();
			for (String item : scriptList) {
				out.print("<script type='text/javascript' src='"+basePath+item+"'></script>"+returnStr);
			}
			out.print("<script type='text/javascript'>"+returnStr);
			out.print("//使用自定义标签"+returnStr);
			out.print("var contextPath='"+basePath2+"';"+returnStr);
			out.print("</script>"+returnStr);
			
			StringBuffer url=request.getRequestURL();
			String baseUrl=url.substring(0, url.lastIndexOf("/")+1);
			out.print("<base href='"+baseUrl+"'>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();   
	}

	private List<String> getScript() {
		List<String> scriptList=new ArrayList<String>();
		scriptList.add("resource2/js/ext/ext-all-debug.js");
//		scriptList.add("resource2/js/ext/ext-all.js");
		scriptList.add("resource2/js/ext/classic/locale/locale-zh_CN.js");
//		scriptList.add("resource/js/tool/HtmlPanel.js");
		
		scriptList.add("resource2/js/UDlibFieldForExt6.js");
		scriptList.add("resource2/js/UDlibAjaxForExt6.js");
		scriptList.add("resource2/js/UDlibButtonForExt6.js");
		scriptList.add("resource2/js/UDlibStoreForExt6.js");
		scriptList.add("resource2/js/UDlibUtilForExt6.js");
		scriptList.add("resource2/js/commonCodeForExt6.js");
		
		
		scriptList.add("resource2/js/tool/HtmlPanel.js");
		return scriptList;
	}

	private List<String> getLink() {
		List<String> linkList=new ArrayList<String>();
//		String themeStr="theme-classic";
		String themeStr="theme-gray";
		linkList.add("resource2/js/ext/classic/"+themeStr+"/resources/"+themeStr+"-all.css");
		linkList.add("resource/icons/icons.css");
		linkList.add("resource2/css/custom.css");
		return linkList;
	}
}