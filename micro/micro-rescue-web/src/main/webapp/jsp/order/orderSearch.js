var orderNo = UD.lib.createTextField({
	fieldLabel : '订单编号',
});
var faultCarId = UD.lib.createTextField({
	fieldLabel : '故障车牌号码',
});
var carUserName = UD.lib.createTextField({
	fieldLabel : '车主姓名',
});
var carUserPhone= UD.lib.createTextField({
	fieldLabel : '车主手机号码',
});
 
var rescueProjectData = UD.lib.createPagingJsonStore(
		{
			url : contextPath+ "/order/getListDataByCode?dictionaryCode=rescue_object",
			fields : [
				 {name:'id'}
				,{name:'name'}
			 ]
		});

var rescueProject = UD.lib.createComboBox({
	fieldLabel : '救援选项',
	valueField : 'id',
	displayField : 'name',
	width : 100,
	store : rescueProjectData
});
var rescueName= UD.lib.createTextField({
	fieldLabel : '救援人员姓名',
});
var rescuePhone= UD.lib.createTextField({
	fieldLabel : '救援人员电话',
});

var order_status = UD.lib.createPagingJsonStore(
		{
			url : contextPath+ "/order/getListDataByCode?dictionaryCode=order_status",
			fields : [
				 {name:'id'}
				,{name:'name'}
			 ]
		});
var orderStatus = UD.lib.createComboBox({
	fieldLabel : '订单状态',
	valueField : 'id',
	displayField : 'name',
	width : 100,
	store :  order_status
});
var orderStartTime = UD.lib.createDateField({
	fieldLabel : "订单开始时间",
	width : 150,
	emptyText : '订单开始时间',
	format : "Y-m-d"//指定日期格式,Y 表示四位数的年，m 表示月，d 表示日
});
var orderEndTime = UD.lib.createDateField({
	fieldLabel : "订单结束时间",
	width : 150,
	emptyText : '订单结束时间',
	format : "Y-m-d"//指定日期格式,Y 表示四位数的年，m 表示月，d 表示日
});

var order_area = UD.lib.createPagingJsonStore(
		{
			url : contextPath+ "/order/getListDataByCode?dictionaryCode=order_area",
			fields : [
				 {name:'id'}
				,{name:'name'}
			 ]
		});
var area = UD.lib.createComboBox({
	fieldLabel : '所属区域',
	valueField : 'id',
	displayField : 'name',
	width : 100,
	store : order_area
});
var searchbtn = UD.lib.createSearchBtn(function()
{
	UD.lib.loadStoreData(
	{
		store:grid.getStore()
		,params:{
			 orderNo : orderNo.getValue()
			,orderStatus : orderStatus.getValue()
			,faultCarId : faultCarId.getValue()
			,carUserName : carUserName.getValue()
			,carUserPhone : carUserPhone.getValue()
			,rescueProject : rescueProject.getValue()
			,rescueName : rescueName.getValue()
            ,rescuePhone : rescuePhone.getValue()
            ,orderStatus : orderStatus.getValue()
            ,orderStartTime : orderStartTime.getValue()
            ,orderEndTime : orderEndTime.getValue()
            ,area : area.getValue()
		}
		,isClearLastParams:false
	}
	);		
}
);
 
var rese = UD.lib.createResetBtn;
var resetbtn =Ext.create("Ext.Button", {
    text: "重置",
    iconCls: "remove",
    handler: function () {
       this.findParentByType("form").getForm().reset();
    },
});
Ext.onReady(function(){
	 
	rescueProjectData.load();
	order_status.load();
	order_area.load();
});
var searchForm = new Ext.FormPanel({
	//layout: "form", //整个大的表单是 form 布局
	labelAlign: "right",//统一的提示信息对齐方式，此处为右对齐
	frame: true,//此处为 true 可以得到更好的效果
	items: [
      { // 行 1
		   layout: "column",  
		   border: 0,
		   defaults: {
			    border: 0,
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.25
		    },
		   items:[
		  { items: [orderNo]}
		 ,{ items: [faultCarId]}
		 ,{ items: [carUserPhone]}
		 ,{ items: [carUserName]}
		 ]
      
      }
      ,{  
	       layout: "column",  
	       border: 0,
	       defaults: {
			    border: 0,
		        layout: 'form'
		        ,xtype: 'container'
		        ,columnWidth:.25
		    },
	       items:[
		  { items: [rescueProject]}
		 ,{ items: [rescueName]}
		 ,{ items: [rescuePhone]}
		 ,{ items: [orderStatus]}
		 ]
       }
      ,{  
	       layout: "column",  
	       border: 0,
	       defaults: {
			     border: 0
		        ,layout: 'form'
		        ,xtype: 'container'
		    },
		   items:[
		  { columnWidth:.25, items: [area]}
		 ,{ columnWidth:.25, items: [orderStartTime]}
		 ,{ columnWidth:.25, items: [orderEndTime]}
		 ,{ columnWidth:.1,  items: [searchbtn]}
		 ,{ columnWidth:.05, items: [resetbtn]}
		   ]
		 } 
	] 
	,region:"north"
});
console.info(searchForm);
