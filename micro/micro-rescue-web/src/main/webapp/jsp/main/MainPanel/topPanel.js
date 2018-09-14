var exitBtn = new Ext.Button({
	text : "退出",
	iconCls : "sign-out",
	handler : function() {
		Ext.Msg.confirm('提示', '确定要退出吗', function(btn) {
			if (btn != 'yes') {
				return;
			}
			window.location=contextPath + "/system/main/doExit";


		});
	}
});

//var topPanel = new Ext.Panel({
//	border : false,
//	region : "north",
//	items : [ '->', accountName + '，欢迎您!',
//	      	// 'Date:'+Ext.Date.format(new Date(),'Y-m-d'),
//	      	modifyPasswordBtn, exitBtn, {
//	      		xtype : 'displayfield',
//	      		width : 50
//	      	}
//
//	      	]
//
//});
//var topPanel = new Ext.Panel({
//	border : false,
//	region : 'north',
//	//margins : '0 0 5 0',
//	tbar : [ '->', accountName + '，欢迎您!',
//	// 'Date:'+Ext.Date.format(new Date(),'Y-m-d'),
//	modifyPasswordBtn, exitBtn, {
//		xtype : 'displayfield',
//		width : 50
//	}
//
//	]
//});


//var topPanel = new Ext.Panel({
//	region : 'north',
//	html: '<h1 class="x-panel-header">Logo</h1>',
//	border: false,
//	height: 50,
//	margins: '0 0 0 0'
//})
var topPanel_left = new Ext.Panel({
			region : 'center'
			,items:[
					{
					xtype: "box",
					autoEl: {
						tag: "img",
						src: "logo.png",
						cls: "main-logo"
//						,style:"height:60"
					}
				}
			]
//			,frame:true

		});
var topPanel_right = new Ext.Panel({
			region : 'east',
			width : 260,
			tbar : ['->', accountName + '，欢迎您!', modifyPasswordBtn, exitBtn, {
						xtype : 'displayfield',
						width : 50
					}

			]
//			,frame:true
		});		
var topPanel = new Ext.Panel({
			height : 45,
			region : 'north'
			,layout:"border"
			,items:[topPanel_left,topPanel_right]
			,border:false
			,frame:true

		});
//var topPanel = new Ext.Panel({
//			region : 'north'
//			,height : 45
//			,items:[
//					{
//					xtype: "box",
//					autoEl: {
//						tag: "img",
//						src: "logo.png",
//						cls: "main-logo"
//					}
//				}
//			]
//		});		
//		