package com.yile.micro.controller.system.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yile.micro.controller.system.service.AttachService;

@Controller
@RequestMapping("/system/attach")
public class AttachController {
	@Autowired
	private AttachService attachService;
	
	@RequestMapping(value = "/showPic")
	public void showPic( String attachNo,HttpServletRequest request, HttpServletResponse response) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			attachService.downloadAttach(request,out,attachNo);
		} catch (Throwable t) {
			t.printStackTrace();
		}finally{
				try {
					if(out!=null){
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}
