SelfDefinedComboBox = function(config) {
   var defaultConfig = {
   		queryInFields : true,
		minChars :0,
		forceAll : false,
		typeAhead : false,
		forceSelection : true,
//					reloadBeforeQuery:true,
//			reloadWhenExpand:false,
		resizable : true,
		selectOnFocus : true,
		triggerAction : 'all',
		editable : true
   };

    config = Ext.applyIf(config || {}, defaultConfig);
    SelfDefinedComboBox.superclass.constructor.call(this, config);


}
Ext.extend(SelfDefinedComboBox,Ext.form.ComboBox, {
/**
	 * @desc 通过displayFields属性确定生成多列的combobox 通过customData属性生成store
	 *       生成queryFields属性值
	 */
	onRender : function(ct, position) {
		SelfDefinedComboBox.superclass.onRender.call(this, ct, position);
		if (this.store) { // 自动生成store
			var cd = this.store;
			this.mode = this.mode;
			var cdField = [];
			if (Ext.isArray(cd)) {
				if (Ext.isObject(cd[0])) { // [{},{}] json数组
					for (var i in cd[0]) {
						cdField.push(i);
					}

					this.store = new Ext.data.JsonStore({
								fields : cdField,
								data : cd
							});
				}
				this.displayField = this.displayField
						|| (cdField.length > 1 ? cdField[1] : cdField[0]);

			}
		}
		// if ((this.queryInFields == true) && (this.mode == 'local')) { //
		// 默认在全部列中查询
		if ((this.queryInFields == true)) { // 默认在全部列中查询
			if (this.queryFields.length == 0) {
				this.store.fields.each(function(f) {
							this.queryFields.push(f.name);
						}, this);
			}
		}

	},
	initList : function() {
		var tileName = '';
		Ext.each(this.titles, function(name) {
					tileName += '<td width="10%">' + name + '</td>';
				}, this);
		if ((!this.tpl) && (this.displayFields)) { // 展示多列
			var tplString = "";
			var cls = 'x-combo-list';
			var cbW = this.width || 150;
			var dfLen = this.displayFields.length;
			var w = (cbW / dfLen).toFixed(2);
			var f = this.store.fields;
			Ext.each(this.displayFields, function(name) {
						name = f.containsKey(name) ? name : f.keys[name]; // 列名或列号
						tplString += '<td>{' + name + '}</td>';
					}, this);
			if (!this.titles || this.titles.length == 1) {
				this.tpl = new Ext.XTemplate(
						'<table cellspacing="0" width="100%">',
						'<tbody><tpl for="."><tr class="x-combo-list-item">',
						tplString, '</tr></tpl></tbody></table>');
			} else {
				this.tpl = new Ext.XTemplate(
						'<table cellspacing="0" width="100%">',
						'<thead><tr class="x-combo-list-hd" height="20px">'
								+ tileName + '</tr></thead>',
						'<tbody><tpl for="."><tr class="x-combo-list-item">',
						tplString, '</tr></tpl></tbody></table>');
			}
		}
		SelfDefinedComboBox.superclass.initList.call(this);
	},

	/**
	 * @param {String}
	 *            query 查询参数的值q
	 * @param {Boolean}
	 *            forceAll 如果forceAll为true 显示全部记录,为false时则通过query参数查询
	 */
	// 复制官方代码进行修改的doQuery函数
	doQuery : function(q, forceAll) {
		// 如果点击combobox的下拉按钮forceAll的值为true，如果输入查询字符forceAll的值为undefined
		// 如果点击combobox的下拉按钮q的值为空字符串，如果输入查询字符q的值为所输入的字符
		if (q === undefined || q === null) {
			q = '';
		}
		var qe = {
			query : q,
			forceAll : forceAll,
			combo : this,
			cancel : false
		};
		// 查询之前触发beforequery事件
		if (this.fireEvent('beforequery', qe) === false || qe.cancel) {
			return false;
		}
		q = qe.query;
		forceAll = qe.forceAll;
		if (forceAll === true || (q.length >= this.minChars)) {
			this.lastQuery = q;
			// 客户端过滤
			if (this.mode == 'local') {

				// 点击下拉按钮过滤
				if (forceAll) {
					// 判断过滤之前是否重新加载数据
					if (this.reloadBeforeQuery == true) {
						this.onLoad();
						this.store.reload({
									scope : this,
									callback : function() {
										this.selectedIndex = -1;
										this.onLoad();// 显示下拉框
									}
								});
					} else {
						this.selectedIndex = -1;
						this.store.clearFilter();
						this.onLoad();
					}
				}
				// 输入字符过滤
				else {
					if (this.reloadBeforeQuery == true) {
						
						this.store.reload({
									scope : this,
									callback : function() {
										this.filterData(q);
									}
								});
					} else {
						this.filterData(q);
					}
				}
			} else {
				// 服务器端过滤
				this.store.baseParams[this.queryParam] = q;
				this.oldStoreCount=this.store.getTotalCount();
				this.store.load({
							params : this.getParams(q),
							callback:function(){
//													var curStoreCount=this.getTotalCount();
//													if(curStoreCount!=this.oldStoreCount)
												
							}
						});

				this.expand();
					this.curStart=0;
			}
		}
	},
	filterData : function(q) {
		var fieldsToQuery = [];
		fieldsToQuery = this.store.fields.keys;

		this.selectedIndex = -1;
		this.store.filterBy(function(record, id) {
					for (var index = 0; index < fieldsToQuery.length; index++) {
						var afield = fieldsToQuery[index];
						if (afield != "id") {
							var regExp = new RegExp(q, 'gi');
							var b = regExp.test(record.get(afield));// 检查每个field对应的值
							if (b) {
								return true;
							}
						}
					}
					return false;
				});
		this.onLoad();
	},

	// /**
	// * @property queryInFields boolean
	// * @desc true时 在多字段中查询(doQuery).false则在displayField中查询, 数据是本地时才有效
	// */
	queryInFields : false,
	// /**
	// * @property queryFields Array
	// * @desc 多列查询的字段名,在queryInFields为true下，默认在全部列中查询,数据是本地时才有效 如:
	// [0,name,age,3]
	// */
	queryFields : [],

	oldStoreCount:0,
	enableKeyEvents:true,
	listeners : {

        expand:function(){
        this.curStart=0
        },
        keypress:function(){

        },

		keydown : function(field, e) {
			if(this.pageSize)
			{
				var start=0;
			if (e.getKey() == Ext.EventObject.LEFT) {
                if((this.curStart-this.pageSize>=0))
                {
                this.curStart=this.curStart-this.pageSize;
				this.pageTb.movePrevious();
                }
				}
			if (e.getKey() == Ext.EventObject.RIGHT) {

                if((this.curStart==0&&this.pageSize<this.store.getTotalCount())||(this.curStart+this.pageSize<this.store.getTotalCount()))
                {
                this.curStart=this.curStart+this.pageSize;
				this.pageTb.moveNext();
                }
                   
				}
			}

		},
		keyup:function(){
		}
	},

	curStart:0
	
});


//var demo=new SelfDefinedComboBox(
//   {
//   	allowBlank : false,
//	style : 'background:none repeat scroll 0 0 #E8FFF5;',
//	hiddenName : "repairApply.applyUser",
//	
//	//上面的代码是可选的，下面的代码是必须的
//	fieldLabel:"xxxxxxxx",
//	width:200,
//	
//	mode:"local",
//	store : new Ext.data.JsonStore(
//	{
//		url:hrpStore.Worker.url,
//		fields:hrpStore.Worker.fields
//		,autoLoad:true
//	}),
//	displayField : hrpStore.Worker.displayField,
//	valueField : hrpStore.Worker.valueField,
//	displayFields : hrpStore.Worker.displayFields,
//	titles : hrpStore.Worker.titles,
//	listWidth : hrpStore.Worker.listWidth
//   }
//);



