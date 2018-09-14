package com.yile.micro.system.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.yile.micro.system.bean.AccountBo;

public interface SysWebService {

	/**通过字典分类编码获取字典数据
	 * @param dictionaryCode
	 * @return
	 */
	List<Map<String, String>> getDictionaryDataListByCode(String dictionaryCode);
	
	
	/**通过字典数据名称查询字典数据
	 * @param dictionaryCode
	 * @param dataName
	 * @return
	 */
	List<Map<String, String>> findDictionaryDataByName(String dictionaryCode,String dataName);
	
	
	List<String> getDictionaryDataIds(String dictionaryCode,String dataName);

	/**获取当前登录账号
	 * @return
	 */
	AccountBo getCurrentAccount();

	/**保存文件
	 * @param multipartFile
	 * @param filePath 文件路径，例如pictures、pictures/aaa、pictures/aaa/bbb
	 * @param attachNo 唯一的编号
	 * @throws IOException
	 */
	void saveAttach(MultipartFile multipartFile,String filePath, String attachNo)throws IOException;

	/**通过字典分类编码获取字典数据,将数据封装成map
	 * @param dictionaryCode
	 * @return
	 */
	Map<String, String> getDictionaryDataMap(String dictionaryCode);

	/**通过编码获取参数值
	 * @param paramDataCode
	 * @param defaultValue 查询的数据为空的情况下的默认值
	 * @return
	 */
	String getSysParamData(String paramDataCode, String defaultValue);
}
