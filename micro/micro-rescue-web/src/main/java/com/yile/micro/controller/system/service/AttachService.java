package com.yile.micro.controller.system.service;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

public interface AttachService {

	void downloadAttach(HttpServletRequest request, OutputStream out, String attachNo) throws IOException;

}
