package com.yile.micro.controller.system.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.yile.micro.controller.system.SysParamConstant;
import com.yile.micro.controller.system.bean.Account;
import com.yile.micro.controller.system.bean.Attach;
import com.yile.micro.controller.system.bean.ParamData;
import com.yile.micro.controller.system.mapper.AttachDao;
import com.yile.micro.controller.system.mapper.DictionaryDataDao;
import com.yile.micro.controller.system.mapper.ParamDataDao;
import com.yile.micro.controller.system.service.MainService;
import com.yile.micro.system.bean.AccountBo;
import com.yile.micro.system.service.SysWebService;
import com.yile.micro.util.CodeUtil;
import com.yile.micro.util.RequestUtil;
import com.yile.micro.util.ResultUtil;

@Service(version = "1.0.0")
public class SysWebServiceImpl implements SysWebService{
	@Autowired
	private DictionaryDataDao dictionaryDataDao;
	@Autowired
	private AttachDao attachDao;
	@Autowired
	private ParamDataDao paramDataDao;
	
	@Autowired
	private MainService mainService;
	
	public List<Map<String, String>> getDictionaryDataListByCode(String dictionaryCode) {
		List<Map<String, String>> list=dictionaryDataDao.getListDataByCode(dictionaryCode);
		return list;
	}
	public List<Map<String, String>> findDictionaryDataByName(
			String dictionaryCode, String dataName) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("dictionaryCode", dictionaryCode);
		paramsMap.put("dataName", dataName);
		List<Map<String, String>> list=dictionaryDataDao.getListDataByName(paramsMap);
		return list;
	}
	public List<String> getDictionaryDataIds(String dictionaryCode,
			String dataName) {
		List<Map<String, String>> list=this.findDictionaryDataByName(dictionaryCode, dataName);
		List<String> ids=new ArrayList<String>();
		for (Map<String, String> item : list) {
			ids.add(item.get("id"));
		}
		return ids;
	}
	public Map<String, String> getDictionaryDataMap(String dictionaryCode) {
		List<Map<String, String>> list=dictionaryDataDao.getListDataByCode(dictionaryCode);
		Map<String, String> dataMap=new HashMap<String, String>();
		for (Map<String, String> item : list) {
			dataMap.put(item.get("id"), item.get("name"));
		}
		return dataMap;
	}
	public AccountBo getCurrentAccount() {
		Account account=mainService.getCurrentAccount();
		if (account==null) {
			return null;
		} else {
			AccountBo accountBo=new AccountBo();
			accountBo.setId(account.getId());
			accountBo.setAccountName(account.getAccountName());
			accountBo.setAccountPassword(account.getAccountPassword());
			accountBo.setLoginTime(account.getLoginTime());
			accountBo.setLastLoginTime(account.getLastLoginTime());
			accountBo.setAccountType(account.getAccountType());
			accountBo.setAccountStatus(account.getAccountStatus());
			accountBo.setCreateDate(account.getCreateDate());
			accountBo.setRemark(account.getRemark());
			return accountBo;
		}
		
	}
	public void saveAttach(MultipartFile multipartFile,String filePath, String attachNo) throws IOException {
		String fileName = multipartFile.getOriginalFilename();
		String extendName=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		extendName=extendName.toLowerCase();//把扩展名转成小写
		
		long fileSize = multipartFile.getSize();
		
		String newFileName = UUID.randomUUID().toString().toUpperCase().replace("-", "")+"."+extendName;
		Attach attach = new Attach();
		
		attach.setAttachNo(attachNo);
		attach.setFileName(fileName);
		attach.setFileSize(fileSize);
		attach.setExtendName(extendName);
		attach.setNewFileName(newFileName);
		attach.setFilePath(filePath);
		
		InputStream inputStream=multipartFile.getInputStream();
		if ("jpg,png,jpeg,gif".contains(extendName)) {
			attach.setContentType("02");
//			inputStream =  ImageUtil.compressImage(inputStream, 360);//压缩图片
		} else if ("doc,docx,xls.xlsx".contains(extendName)){
			attach.setContentType("01");
		}else {
			attach.setContentType("99");
		}
		String fileBasePath=getSysParamData(SysParamConstant.UPLOAD_FILE_BASE_PATH, "upload");
		
		String contextPath =RequestUtil.getServletContext().getRealPath("/");
		File contextFile = new File(contextPath);
		File destFile = new File(contextFile.getParent()+"/"+fileBasePath+"/"+filePath+"/"+newFileName);
		System.out.println("文件路径====="+destFile.getPath());
		//FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
		FileUtils.copyInputStreamToFile(inputStream, destFile);// 复制临时文件到指定目录下
		
		attachDao.saveAttach(attach);
		
	}
	public String getSysParamData(String paramDataCode, String defaultValue) {
		ParamData paramData=paramDataDao.getByCode(paramDataCode);
		if (paramData==null) {
			return defaultValue;
		} else {
			return paramData.getParamDataValue();
		}
	}
	//上传文件例子
	private JSONObject uploadPicDemo(MultipartFile multipartFile) {
		//检验文件大小
		if (true) {
			long fileSize = multipartFile.getSize();
			int fileSizeLimit = 5*1024*1024;//上传文件大小限制,5M
			if(fileSize > fileSizeLimit)
			{
				return ResultUtil.failed("上传的文件大小最多只能5M");
			}
		}
		//检验文件类型
		if (true) {
			String postNameType = "jpg,png,jpeg,gif";
//			postNameType=postNameType+"doc,docx,xls.xlsx";
			
			String fileName = multipartFile.getOriginalFilename();
			String extendName=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			extendName=extendName.toLowerCase();//把扩展名转成小写
			if (!"jpg,png,jpeg,gif".contains(extendName)) {
				return ResultUtil.failed("上传的文件类型不正确，文件类型配置为 "+postNameType);
			}
		}
		
		
		String attachNo=CodeUtil.get16Code();
//		MucaiPic mucaiPic=new MucaiPic();
//		mucaiPic.setAttachNo(attachNo);
//		mucaiPicDao.save(mucaiPic);
//		int id=mucaiPic.getId();
		String flag="hpx";
		
		try {
			saveAttach(multipartFile,"pictures/"+flag,attachNo);
		} catch (IOException e1) {
			e1.printStackTrace();
			return ResultUtil.failed("保存文件失败!");
		}
		//如果保存的是图片文件，显示图片的路径是/system/attach/showPic?attachNo=attachNo
		

		return ResultUtil.success("保存成功!");
	}
}
