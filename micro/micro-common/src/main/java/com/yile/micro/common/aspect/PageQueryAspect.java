package com.yile.micro.common.aspect;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.yile.micro.common.PageBean;
@Aspect
@Component
@Order(3)
public class PageQueryAspect {
	public final static String CURRENT_PAGE = "curPage";
	public final static String PAGE_SIZE = "pageSize";
	@Pointcut("execution(public * com.yile.micro.*.service.*.*Page(..))")
	public void pagelog() {
	}
	/**
	 * 如果在调用mybatis mapper接口方法时，以map为参数类型且key存在curPage和pageSize的，默认执行分页查询
	 * @author 000
	 *
	 */
	@SuppressWarnings("unchecked")
	@Before("pagelog()")
	public void dobefore(JoinPoint point) {
		Object[] args = point.getArgs();
		if(args!=null && args.length > 0) {
			Object object = args[0];
			if(object!=null && object instanceof Map){
				Map<String, Object> parMap = (Map<String, Object>) object;
				Object cp = parMap.get(CURRENT_PAGE);
				Object ps = parMap.get(PAGE_SIZE);
				if(cp!=null && ps!=null){
					try {
						int c = Integer.parseInt(cp.toString());
						int p = Integer.parseInt(ps.toString());
						//在执行查询前，添加limit参数，用作分页查询
						PageHelper.startPage(c,p);
					} catch (NumberFormatException e) {
						// TODO: handle exception
						e.printStackTrace();
						return;
					}
				}
			}
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@AfterReturning(pointcut="pagelog()",returning = "ret")
	public PageBean doafter(JoinPoint point,Object ret) {
		Object[] args = point.getArgs();
		if(args!=null && args.length > 0) {
			Object object = args[0];
			if(object!=null && object instanceof Map){
				Map<String, Object> parMap = (Map<String, Object>) object;
				Object cp = parMap.get(CURRENT_PAGE);
				Object ps = parMap.get(PAGE_SIZE);
				if(cp!=null && ps!=null){
					if(ret instanceof PageBean){
						PageBean pageBean = (PageBean) ret;
						pageBean.setCurrentPage(Integer.parseInt(cp.toString()));
						pageBean.setPageSize(Integer.parseInt(ps.toString()));
						return pageBean;
					}
				}
			}
		}
		return (PageBean) ret;
	}
}
