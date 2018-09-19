<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.xxx.com/commonTag" prefix="common" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<common:ext6></common:ext6>
    <script type="text/javascript">
    	//解码
    	var jsonParams=Ext.urlDecode('<%=request.getQueryString()%>'); 
    </script>  	
    <%--
	<script type="text/javascript" src="createRescue.js"></script>
	 --%>
	   <script type="text/javascript">
        Ext.onReady(function() {
        	
            var top = new Ext.FormPanel({
                labelAlign: 'top',
                frame:false,
                title: '订单信息',
                bodyStyle:'padding:5px 5px 0',
                width: 500,
                split:true,
                region:'west',
                items: [{
                    layout:'column',
                    items:[{
                        columnWidth:.5,
                        layout: 'form',
                        items: [{
                            xtype:'textfield',
                            fieldLabel: 'First Name',
                            name: 'first',
                            anchor:'95%'
                        }, {
                            xtype:'textfield',
                            fieldLabel: 'Company',
                            name: 'company',
                            anchor:'95%'
                        }]
                    },{
                        columnWidth:.5,
                        layout: 'form',
                        items: [{
                            xtype:'textfield',
                            fieldLabel: 'Last Name',
                            name: 'last',
                            anchor:'95%'
                        },{
                            xtype:'textfield',
                            fieldLabel: 'Email',
                            name: 'email',
                            vtype:'email',
                            anchor:'95%'
                        }]
                    },{
                        columnWidth:1,
                        layout: 'form',
                        items: [{
                        	xtype:'textfield',
                            id:'vvv',
                            fieldLabel:'Biography',
                            height:60,
                            width:400,
                            anchor:'98%'
                        }]
                    }]
                
                }],

                buttons : [ UD.lib.createSaveBtn(saveAction) ]
            });
        	
        	var center = new Ext.Panel({
        		region:'center',
                margins: '5 5 5 0',
                layout:'fit',
        		html : '<iframe src="'+contextPath+'/order/rescue/detailBMap" frameborder="0" marginheight="0" width="100%" height="100%" marginwidth="0" scrolling="auto" id="mapIframe"></iframe>'
        	});
			
            function saveAction() {
            	var theForm = this.findParentByType("form");
            	var basicForm = theForm.getForm(); 
                if (basicForm.isValid()) {
                    UD.lib.doFormSubmit(basicForm, contextPath+ '/order/rescue/save',
                    function(action) {
                        Ext.MessageBox.alert('提示', action.result.msg,
                        function() {
                        	parent.iframeWindow.hide();
                        	parent.grid.getStore().reload();
                        });
                    })
                }	
            }
            
            var viewport = new Ext.Viewport({
                layout:'border',

                items: [top, {
                    region:'center',
                    margins: '5 5 5 0',
                    layout: 'anchor',
                    items:[center]
                }]
            });
            
        });// end
        
        
    </script>
  </head>
  
  <body>
  </body>
</html>
