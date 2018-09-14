//Ext.example.msg('修改操作', '请选择要修改的一项！');

//user-defined
Ext.ns('UD.lib')
UD.lib.requiredType="red";
UD.lib.changeRequiredColor=function (obj) {
	if (UD.lib.requiredType=='red') {
		obj.fieldLabel='<font color=red>'+obj.fieldLabel+'</font>';
	} else {
		obj.style='background:none repeat scroll 0 0 #E8FFF5;';
	}	
}
UD.lib.createTwinTriggerField=function (config) {
	var obj={
			fieldLabel : config.fieldLabel,
			editable : false,
		    triggers: {
		        foo: {
		            cls: 'x-form-clear-trigger',
		            handler:config.onClearTriggerClick
		        },
		        bar: {
		            cls: 'my-bar-trigger',
		            handler:config.onChooseTriggerClick
		        }
		    }			
		};
	if (config.allowBlank==false) {
		obj.allowBlank=false;
		UD.lib.changeRequiredColor(obj);
	}	
	if (config.name) {
		obj.name=config.name;
	}	
	if (config.width) {
		obj.width=config.width;
	}	
	return new Ext.form.field.Text(obj);	
}


//上传文件控件
UD.lib.createFileUploadField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.FileUploadField(config);
}
//文本框控件
UD.lib.createTextField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.TextField(config);
}
//多行文本框控件
UD.lib.createTextArea=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.TextArea(config);
}
//数值控件
UD.lib.createNumberField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.NumberField(config);
}
//日期控件
UD.lib.createDateField=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
	}
	return new Ext.form.DateField(config);
}

UD.lib.createHidden=function(name) {
	var config={};
	if (name) {
		config.name=name;
	}
	return new Ext.form.Hidden(config);
}

UD.lib.createComboBox=function(config) {
	if (config.allowBlank==false) {
		UD.lib.changeRequiredColor(config);
		config.editable=false;
	}else{
		config.editable=true;
	}
	
	config.queryMode='local';
	config.forceSelection=true;
	config.resizable=true;
	config.triggerAction= 'all';
	return new Ext.form.ComboBox(config);	
}

