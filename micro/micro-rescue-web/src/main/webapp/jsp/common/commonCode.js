	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';
	Ext.grid.RowNumberer.prototype.width=35;
	Ext.grid.ColumnModel.prototype.defaultSortable=true;	
	//禁止所有页面弹出浏览器的右键菜单
	Ext.get(document).on('contextmenu', function(e) {
	    //e.preventDefault();//取消浏览器对此事件的默认操作，否则会弹出浏览器的系统菜单
	});
//————————————————————————————————————————————————————————————————————————————————————————————————————————

Ext.override(Ext.data.Store, {
	listeners : {
		exception:function(dataProxy,type,action,options,response,arg)
		{
			var resJson = Ext.decode(response.responseText);
			//没有配置权限或者该用户没有权限
			if (response.statusText=="Forbidden") {
				Ext.MessageBox.alert('提示', resJson.errorMsg);
			} 
			else if (response.statusText=="Internal Server Error"){
				Ext.MessageBox.alert('提示', "服务器内部错误");
			}
			else if (response.statusText=="communication failure"){
				Ext.MessageBox.alert('提示', "连接不上服务器");
			}
			else if (response.statusText=="transaction aborted"){
				Ext.MessageBox.alert('提示', "请求超时");
			}
			else if (response.statusText=="OK"){
				if (action=="read") {
					Ext.MessageBox.alert('提示', "解析数据出错");
				} else {
					Ext.MessageBox.alert('提示', "其他错误");
				}
			}
			else {
				Ext.MessageBox.alert('提示', "其他错误");
			}
			
		}
	}
});
Ext.override(Ext.tree.TreeLoader, {
	listeners : {
		loadexception:function(curr,node,response)
		{
			var resJson = Ext.decode(response.responseText);
			//没有配置权限或者该用户没有权限
			if (response.statusText=="Forbidden") {
				Ext.MessageBox.alert('提示', resJson.errorMsg);
			} 
			else if (response.statusText=="Internal Server Error"){
				Ext.MessageBox.alert('提示', "服务器内部错误");
			}
			else if (response.statusText=="communication failure"){
				Ext.MessageBox.alert('提示', "连接不上服务器");
			}
			else if (response.statusText=="transaction aborted"){
				Ext.MessageBox.alert('提示', "请求超时");
			}			
			else {
				Ext.MessageBox.alert('提示', "其他错误");
			}			
		}
	}
});


//保留滚动条的代码
Ext.override(Ext.grid.GridView, {
	scrollTop : function() {
		this.scroller.dom.scrollTop = 0;
		this.scroller.dom.scrollLeft = 0;
	},
	scrollToTop : Ext.emptyFn
});
//支持中文排序
Ext.data.Store.prototype.applySort = function() {
    if (this.sortInfo && !this.remoteSort) {
        var s = this.sortInfo, f = s.field;
        var st = this.fields.get(f).sortType;
        var fn = function(r1, r2) {
            var v1 = st(r1.data[f]), v2 = st(r2.data[f]);
            if (typeof(v1) == "string") {
                return v1.localeCompare(v2);
            }
            return v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);
        };
        this.data.sort(s.direction, fn);
        if(this.snapshot && this.snapshot != this.data) {
            this.snapshot.sort(s.direction, fn);
        }
    }
};



//让windows实现全屏的代码
Ext.override(Ext.BoxComponent, {
    openFullScreen: false,
    afterRender: function() {
        Ext.BoxComponent.superclass.afterRender.call(this);
        if (this.resizeEl) {
            this.resizeEl = Ext.get(this.resizeEl);
        }
        if (this.positionEl) {
            this.positionEl = Ext.get(this.positionEl);
        }
        this.boxReady = true;
        Ext.isDefined(this.autoScroll) && this.setAutoScroll(this.autoScroll);
        if (this.openFullScreen) {
            var screenHeight = document.body.clientHeight * 0.98;
            var screenWidth = document.body.clientWidth * 0.98;
            this.setSize(screenWidth, screenHeight);
        } else {
            this.setSize(this.width, this.height);
        }
        if (this.x || this.y) {
            this.setPosition(this.x, this.y);
        } else if (this.pageX || this.pageY) {
            this.setPagePosition(this.pageX, this.pageY);
        }
    }
});
//限制日期的选择范围的检验代码
Ext.apply(Ext.form.VTypes, {
    daterange: function(val, field) {
        var date = field.parseDate(val);
        if (!date) {
            return;
        }
        if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
            var start = Ext.getCmp(field.startDateField);
            start.setMaxValue(date);
            start.validate();
            this.dateRangeMax = date;
        } else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
            var end = Ext.getCmp(field.endDateField);
            end.setMinValue(date);
            end.validate();
            this.dateRangeMin = date;
        }
        /* 
         * Always return true since we're only using this vtype to set
         * the min/max allowed values (these are tested for after the
         * vtype test)
         */
        return true;
    }
});
//使用例子
//var sTime = UD.lib.createDateField({
//	fieldLabel : '开始时间'
//	,format : 'Y-m-d'
//	,name:'sTime'
//	,id : 'startDateId' 
//    ,vtype : 'daterange' 
//    ,endDateField : 'endDateId'
//});
//
//var eTime = UD.lib.createDateField({
//	fieldLabel : '结束时间'
//	,format : 'Y-m-d'
//	,name:'eTime'
//	,id : 'endDateId'  
//    ,vtype : 'daterange'
//    ,startDateField : 'startDateId'
//});

