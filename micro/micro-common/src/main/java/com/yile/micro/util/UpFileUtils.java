package com.yile.micro.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

/**
 *  Description : 文件操作工具类
 * 
 *  @author:  yuexin.song-32563
 *
 *  History:  2015-10-4 下午2:00:14   yuexin.song-32563   Created.
 *           
 */
public class UpFileUtils {
	
	  /**
	   * Method description : 把一个流复制到一个流中
	   * @param in 输入流
	   * @param out 输出流
	   * @return
	   *
	   */
	  public static long streamCopy(InputStream in, OutputStream out) throws
	      IOException {
		    long writeCount = 0;
		    if ( (in == null) || (out == null)) {
		      return 0;
		    }
		    byte[] buffer = new byte[32 * 1024];
		    int bytesRead = 0;
		    while ( (bytesRead = in.read(buffer)) != -1) {
		      out.write(buffer, 0, bytesRead);
		      writeCount += bytesRead;
		    }
		    return writeCount;
	  }
	  
	  /**
	 * 编译字符串 下载文件使用
	 * @param request
	 * @param tn 文件名称
	 * @return
	 */
	  public static String encodeName(HttpServletRequest request,String tn){
		  boolean flag = request.getHeader("User-Agent").indexOf("like Gecko") > 0;
		  if(request.getHeader("User-Agent").toLowerCase().indexOf("msie") > 0 || flag){
			  try{
				  tn = URLEncoder.encode(tn, "UTF-8");
			  }catch(UnsupportedEncodingException e){
				  e.printStackTrace();
			  }
		  }else{
			  try{
				  tn = new String(tn.getBytes("UTF-8"),"ISO8859-1");
			  }catch(UnsupportedEncodingException e){
				  e.printStackTrace();
			  }
		  }
		  return tn;
	  }
	  
	/**
	 * 下载文件类型
	 */
	private static Map<String, String> fileToTypeMap = new HashMap<String, String>();
	static{
		fileToTypeMap.put(".gif", "image/gif");
		fileToTypeMap.put(".jpg", "application/x-jpg");
		fileToTypeMap.put(".pdf", "application/pdf");
		fileToTypeMap.put(".odc", "text/x-ms-odc");
		fileToTypeMap.put(".doc", "application/msword");
		fileToTypeMap.put(".xls", "application/vnd.ms-excel");
		fileToTypeMap.put(".png", "application/x-png");
		fileToTypeMap.put(".bmp", "application/x-bmp");
		fileToTypeMap.put(".tiff", "image/tiff");
		fileToTypeMap.put(".pcx", "application/x-pcx");
		fileToTypeMap.put(".tga", "application/x-tga");
		fileToTypeMap.put(".svg", "text/xml");
	}
	
	public static void fileContentType(HttpServletResponse response,String fileName){
		String fileType = "";
		if (fileName.indexOf(".") != -1) {
			int pot = fileName.lastIndexOf(".");
			fileType = fileName.substring(pot);
		}
		String fileContentType = fileToTypeMap.get(fileType!=""?fileType.toLowerCase():fileType);
		if (StringUtils.isEmpty(fileContentType)) {
			//默认类型
			response.setContentType("application/octet-stream");
		}else{
			response.setContentType(fileContentType);
		}
	}
}