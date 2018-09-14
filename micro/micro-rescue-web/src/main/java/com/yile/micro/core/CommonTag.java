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
public class CommonTag extends BodyTagSupport {
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
			out.print("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"+returnStr);
//			out.print("<meta charset='UTF-8'>"+returnStr);
			out.print("<meta http-equiv='pragma' content='no-cache'>"+returnStr);
			out.print("<meta http-equiv='cache-control' content='no-cache'>"+returnStr);
			out.print("<meta http-equiv='expires' content='0'>"+returnStr);
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
//		SysWebService sysWebService=SpringContextUtil.getApplicationContext().getBean(SysWebService.class);
//		String isCompressJsAnCss=sysWebService.getSysParamData(SysParamConstant.IS_COMPRESS_JS_AND_CSS,"0");
//		String isExtDebug=sysWebService.getSysParamData(SysParamConstant.IS_EXT_DEBUG,"0");
		String isCompressJsAnCss="0";
		String isExtDebug="1";
		List<String> scriptList=new ArrayList<String>();
		
		if ("1".equals(isCompressJsAnCss)) {
			scriptList.add("wro/all.js");
		}else {
			if ("1".equals(isExtDebug)) {
				scriptList.add("resource/js/ext/adapter/ext/ext-base-debug.js");
				scriptList.add("resource/js/ext/ext-all-debug.js");
				
			} else {
				scriptList.add("resource/js/ext/adapter/ext/ext-base.js");
				scriptList.add("resource/js/ext/ext-all.js");
			}
			scriptList.add("resource/js/ext/ext-lang-zh_CN.js");
			scriptList.add("resource/js/ext/ux/Spinner.js");//SpinnerField.js依赖Spinner.js
			scriptList.add("resource/js/ext/ux/SpinnerField.js");//DateTimeField.js依赖SpinnerField.js
			scriptList.add("resource/js/ext/ux/DateTimeField.js");
			scriptList.add("resource/js/ext/ux/BufferView.js");
			scriptList.add("resource/js/ext/ux/fileuploadfield/FileUploadField.js");
			scriptList.add("resource/js/tool/pageTool.js");
			scriptList.add("resource/js/tool/HtmlPanel.js");
			scriptList.add("resource/js/tool/TreeNodeCheck.js");
			scriptList.add("resource/js/tool/PinyinFilter.js");
			scriptList.add("jsp/common/commonCode.js");
			scriptList.add("jsp/common/UDlib.js");
		}
		return scriptList;
	}

	private List<String> getLink() {
//		SysWebService sysWebService=SpringContextUtil.getApplicationContext().getBean(SysWebService.class);
//		String isCompressJsAnCss=sysWebService.getSysParamData(SysParamConstant.IS_COMPRESS_JS_AND_CSS,"0");
		String isCompressJsAnCss="0";;
		List<String> linkList=new ArrayList<String>();
		
		if ("1".equals(isCompressJsAnCss)) {
			linkList.add("wro/all.css");
		}else {
			linkList.add("resource/js/ext/resources/css/ext-all.css");
			linkList.add("resource/js/ext/ux/css/Spinner.css");
			linkList.add("resource/css/newfontSize.css");
			linkList.add("resource/icons/icons.css");
			linkList.add("resource/js/ext/ux/fileuploadfield/css/fileuploadfield.css");
		}
		return linkList;
	}
}