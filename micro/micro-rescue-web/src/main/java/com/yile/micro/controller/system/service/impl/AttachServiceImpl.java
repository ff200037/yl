package com.yile.micro.controller.system.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yile.micro.controller.system.bean.Attach;
import com.yile.micro.controller.system.mapper.AttachDao;
import com.yile.micro.controller.system.service.AttachService;
import com.yile.micro.util.UpFileUtils;

@Service
public class AttachServiceImpl implements AttachService{
	
	@Autowired
	private AttachDao attachDao;
	public void downloadAttach(HttpServletRequest request,OutputStream outputStream, String attachNo) throws IOException {
		Attach attach=attachDao.getAttach(attachNo);
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		File contextFile = new File(contextPath);
		File destFile = new File(contextFile.getParent()+"/upload/"+attach.getFilePath()+"/"+attach.getNewFileName());
		InputStream inputStream = new FileInputStream(destFile);
		
		UpFileUtils.streamCopy(inputStream, outputStream);
	}
}
