/*var store =UD.lib.createPagingJsonStore(
{
	url : contextPath+ "/coupon/coupon/getCouponType",
	fields : [
		 {name:'id'}
		,{name:'cpnName'}
		,{name:'cpnType'}
		,{name:'cpnMarks'}
		,{name:'cpnNum'}
		,{name:'areaRange'}
		,{name:'custRange'}
		,{name:'createTime'}
		,{name:'cpnStatus'}
		,{name:'alrdyGetNum'}
		
	]
});
*/

Ext.require(
		'Ext.tab.*'
);
Ext.onReady(
		function(){
			Ext.create(
					'Ext.tab.Panel',
					{
						renderTo:Ext.getBody(),
						activeTab:0,
						width:1000,
						height:450,
						plain:true,
						defaults:{
							autoScoll:true
						},
						items:[
						       {
						    	   id:'tab1',
						    	   title:'Tabs-1',
						    	   html:'这是一个普通的tab'
						       },{
						    	   title:'tab-2',
						    	   contentEl:'tab2'
						       },{
						    	   id:'tab2',
						    	   title:'ajax Tab',
						    	   autoLoad:{
						    		   url:'tabAction',
						    		   params:{
						    			   data:'从客户端传入的参数'
						    		   },
						    		   method:'GET'
						    	   }
						       
						       },{
						    	   id:'tab3',
						    	   title:'优惠券信息', //事件tab
						    	   listeners:{
						    		   activate:function(tab){
						    			   //alert(tab.title + ': activate事件触发。1');
						    		   },
						    		   
						    	   },
						    	   html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="'+'couponManage.jsp?id='+ jsonParams.id +'"> </iframe>',
						    	   autoLoad:false
						       },{
						    	   id:'tab4',
						    	   title:'不可用的tab',
						    	   disabled: true
						       }
						]
					}
			);
		}
);