package com.yile.micro.controller.system.core.interceptor.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yile.micro.controller.system.core.interceptor.LoginInterceptor;
import com.yile.micro.controller.system.core.interceptor.UnprotectedPath;
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
 
	@Autowired
	private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        // RequestInterceptor()为自己定义的拦截器
    	List<String> patterns=new ArrayList<String>();
    	patterns.add("/system/main/loginPage");
    	patterns.add("/system/main/doLogin");
    	patterns.add("/system/main/doExit");
    	patterns.add("/system/error/**");
    	patterns.add("/resource*/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    	
    }
    @Bean
    public UnprotectedPath setUnprotectedPath()
    {
    	List<String> patterns=new ArrayList<String>();
    	patterns.add("/system/main/mainPage");//进入用户主页面
    	patterns.add("/system/menu/getTreeDataByAccount");///进入主页面后读取左边的菜单数据
    	patterns.add("/system/account/modifyPassword");//修改密码
    	patterns.add("/system/dictionaryData/getListDataByCode");//获取字典数据 
    	patterns.add("/system/attach/showPic");//预览图片
    	
    	List<String> unprotected=new ArrayList<String>();
//    	unprotected.add("/rest/**");
//    	unprotected.add("/services/**");
    	unprotected.add("*.js");
    	unprotected.add("*.css");
    	unprotected.add("*.xml");
    	unprotected.add("*.gif");
    	unprotected.add("*.jpg");
    	unprotected.add("*.jpeg");
    	unprotected.add("*.bmp");
    	unprotected.add("*.png");
    	unprotected.add("*.swf");
    	unprotected.add("*.woff");
    	unprotected.add("*.svg");
    	unprotected.add("*.otf");
    	unprotected.add("*.eot");
    	unprotected.add("*.ttf");
    	
    	UnprotectedPath unprotectedPath=new UnprotectedPath();
    	unprotectedPath.setPaths(patterns);
    	unprotectedPath.setUnprotected(unprotected);
    	return unprotectedPath;
    }
    
}