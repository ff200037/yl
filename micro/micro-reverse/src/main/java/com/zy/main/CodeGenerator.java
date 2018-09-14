package com.zy.main;

import cn.org.rapid_framework.generator.GeneratorFacade;

public class CodeGenerator {

	public static void main(String[] args) {
		try {
			GeneratorFacade g = new GeneratorFacade();
			// 删除生成器的输出目录
			g.deleteOutRootDir();

			// 通过数据库表生成文件,template为模板的根目录
			g.generateByTable("t_mechanism_price","template");
			g.generateByTable("t_order","template");			
			g.generateByTable("t_order_project","template");
			g.generateByTable("t_order_rel_rescues","template");
			g.generateByTable("t_price","template");			
			g.generateByTable("t_price_charge","template");
			g.generateByTable("t_price_charge_detail","template");
			g.generateByTable("t_rescue_order","template");
			
			// 自动搜索数据库中的所有表并生成文件,template为模板的根目录
			//g.generateByAllTable("template");

			// g.generateByClass(Blog.class,"template_clazz");
			
			// 删除生成的文件
			// g.deleteByTable("table_name", "template");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
