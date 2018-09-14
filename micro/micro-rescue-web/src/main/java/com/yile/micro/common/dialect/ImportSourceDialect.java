package com.yile.micro.common.dialect;

//import java.util.HashSet;
//import java.util.Set;
//import org.thymeleaf.dialect.AbstractProcessorDialect;
//import org.thymeleaf.processor.IProcessor;
//import org.thymeleaf.standard.StandardDialect;
//import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
//import org.thymeleaf.templatemode.TemplateMode;
//
//public class ImportSourceDialect extends AbstractProcessorDialect {
//
//	private static final String DIALECT_NAME = "ImportSourceDialect";// 定义方言名称
//
//	public ImportSourceDialect() {
//		// 我们将设置此方言与“方言处理器”优先级相同
//		// 标准方言, 以便处理器执行交错。 import 自定义标签的名称 在html 是这样写的 <import:source/>
//		super(DIALECT_NAME, "source", StandardDialect.PROCESSOR_PRECEDENCE);
//	}
//
//	/*
//	 * 
//	 * 元素处理器：“import”标签。
//	 */
//	public Set<IProcessor> getProcessors(final String dialectPrefix) {
//		Set<IProcessor> processors = new HashSet<IProcessor>();
//		processors.add(new SourceTagProcessor(dialectPrefix));// 添加我们定义的标签
//
//		processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML,
//				dialectPrefix));
//		return processors;
//	}
//}
public class ImportSourceDialect
{
	
}
