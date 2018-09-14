package com.yile.micro.util;

import com.alibaba.fastjson.JSONObject;

public class ResultUtil {

	/**返回操作失败信息
	 * @param msg
	 * @return
	 */
	public static JSONObject failed(String msg) {
		JSONObject json = new JSONObject();
	    json.put("success", false);
	    json.put("msg",msg);
		return json;
	}
	/**返回操作成功信息
	 * @param msg
	 * @return
	 */
	public static JSONObject success(String msg) {
		JSONObject json = new JSONObject();
	    json.put("success", true);
	    json.put("msg",msg);
		return json;
	}
	/**返回分页数据
	 * @param count 总的记录数
	 * @param info 分页数据
	 * @return
	 */
	public static JSONObject getPageResult(Long count, Object info) {
		JSONObject allData = new JSONObject();
		allData.put("count", count);
		allData.put("info", info);
		return allData;
		
	}
	/**返回表单用的数据
	 * @param object
	 * @return
	 */
	public static JSONObject formDataJson(Object object) {
		JSONObject json = new JSONObject();
	    json.put("success", true);
	    if (object==null) {
	    	json.put("data", new JSONObject());
		} else {
			json.put("data", object);
		}
	    return json;
	}
}
