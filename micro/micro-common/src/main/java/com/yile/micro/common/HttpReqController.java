package com.yile.micro.common;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yile.micro.util.ResultUtil;
import com.yile.micro.util.SpringContextUtil;
/**
 * http接口统一控制器
 */
@Controller
@RequestMapping("/httpApi")
public class HttpReqController{

    private final static Logger logger = LoggerFactory.getLogger(HttpReqController.class);
    //缓存作用的map
    private final static Map<String, Class<?>> cacheMap = new ConcurrentHashMap<String, Class<?>>();

    @SuppressWarnings("rawtypes")
	@ResponseBody
    @RequestMapping(value = "/{service}/{method}",method = RequestMethod.POST)
    public ResponseEntity api(HttpServletRequest request,@RequestParam Map<String,Object> params,
                      @PathVariable String service,
                      @PathVariable String method) {
    	if(params==null)
    		return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        logger.debug("ip:{}-httpRequest:{}",getIP(request),params);
        try {
        	Object invoke = invoke(params,service,method);
        	logger.debug("callback :"+invoke) ;
        	return new ResponseEntity(invoke,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity(new ResultUtil().failed("调用失败"),HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }
    private Object invoke(Map<String,Object> params,String service,String method)throws Exception{
        Class<?> serviceCla = cacheMap.get(service);
        Object bean = SpringContextUtil.getBean(service);
        Object result = null;
        if (serviceCla == null){
        	if(bean==null){
        		logger.error("service is not correct,service="+service);
        		throw new RuntimeException("service is not correct,service="+service);
        	}
            serviceCla = bean.getClass();
            //设置缓存
            cacheMap.put(service,serviceCla) ;
        }
    	Method targetMethod = serviceCla.getMethod(method,Map.class);
    	if (targetMethod==null){
    		logger.error("method is not correct,method="+method);
    		throw new RuntimeException("method is not correct,method="+method);
    	}
    	Class<?>[] parameterTypes = targetMethod.getParameterTypes();
    	if (parameterTypes.length == 0){
    		//没有参数
    		result = targetMethod.invoke(bean);
    	}else if (parameterTypes.length == 1){
    		result = targetMethod.invoke(bean,params);
    	}else {
    		logger.error("Can only have one parameter");
    		throw new RuntimeException("Can only have one parameter!");
    	}
        
        return result;
    }
  
    /**
     * 获取IP
     * @param request
     * @return
     */
    private String getIP(HttpServletRequest request) {
        if (request == null)
            return null;
        String s = request.getHeader("X-Forwarded-For");
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

            s = request.getHeader("Proxy-Client-IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

            s = request.getHeader("WL-Proxy-Client-IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {
            s = request.getHeader("HTTP_CLIENT_IP");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

            s = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s)) {

            s = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s))
            try {
                s = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException unknownhostexception) {
                return "";
            }
        return s;
    }
}
