package com.yile.micro.controller.system.core.interceptor;

import java.util.List;

public class UnprotectedPath {
	private List<String> paths;
	
	private List<String> unprotected;

	public List<String> getPaths() {
		return paths;
	}

	public void setPaths(List<String> paths) {
		this.paths = paths;
	}
	public void setUnprotected(List<String> unprotected) {
		this.unprotected = unprotected;
	}
	public List<String> getUnprotected() {
		return unprotected;
	}
}