package com.yile.micro.common.dialect;

//import org.thymeleaf.context.ITemplateContext;
//import org.thymeleaf.model.IModel;
//import org.thymeleaf.model.IModelFactory;
//import org.thymeleaf.model.IProcessableElementTag;
//import org.thymeleaf.processor.element.AbstractElementTagProcessor;
//import org.thymeleaf.processor.element.IElementTagStructureHandler;
//import org.thymeleaf.templatemode.TemplateMode;
//
//public class SourceTagProcessor extends AbstractElementTagProcessor {
//	private static final String TAG_NAME = "bootstrap";// 标签名
//	private static final int PRECEDENCE = 10000;// 优先级
//
//	public SourceTagProcessor(String dialectPrefix) {
//		super(TemplateMode.HTML, // 此处理器将仅应用于HTML模式
//				dialectPrefix, // 要应用于名称的匹配前缀
//				TAG_NAME, // 标签名称：匹配此名称的特定标签
//				true, // 将标签前缀应用于标签名称
//				null, // 无属性名称：将通过标签名称匹配
//				false, // 没有要应用于属性名称的前缀
//				PRECEDENCE); // 优先(内部方言自己的优先)
//
//	}
//
//	/**
//	 * context 页面上下文 tag 标签
//	 */
//	@Override
//	protected void doProcess(final ITemplateContext context,
//			final IProcessableElementTag tag,
//			final IElementTagStructureHandler structureHandler) {
//		final IModelFactory modelFactory = context.getModelFactory();
//		final IModel model = modelFactory.createModel();
//		model.add(modelFactory.createOpenElementTag("link href='bootstrap/css/bootstrap.css' rel='stylesheet'"));
//		model.add(modelFactory.createCloseElementTag("link"));
//		model.add(modelFactory.createOpenElementTag("script src='js/jquery-2.0.1.min.js'"));
//		model.add(modelFactory.createCloseElementTag("script"));
//		model.add(modelFactory.createOpenElementTag("script src='bootstrap/js/bootstrap.min.js'"));
//		model.add(modelFactory.createCloseElementTag("script"));
//
//		/*
//		 * 指示引擎用指定的模型替换整个元素。
//		 */
//		structureHandler.replaceWith(model, false);
//
//	}
//}

public class SourceTagProcessor
{
	
}
