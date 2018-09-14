var sureBtn=UD.lib.createSureBtn(function(){
    var selectedNode = treePanel.getSelectionModel().getSelection();
    if (selectedNode.length==1) {
        if (selectedNode[0].get("id") == "root") {
            Ext.MessageBox.alert('提示', "不能选择根节点");
            return;
        } 
        if (selectedNode[0].get("is_folder") == "T") {
        	Ext.MessageBox.alert('提示', "不能选择权限分类");
        	return;
        } 
        if (selectedNode[0].get("permissionType") == "notPage") {
        	Ext.MessageBox.alert('提示', '只能选择权限类型是"页面"的权限');
        	return;
        } 
        parent.setPermission(selectedNode[0].get("id"), selectedNode[0].get("text"), selectedNode[0].get("permission_path"));
    } else {
       Ext.MessageBox.alert('提示', "请选择一个权限");
    }
})
var treeStore = Ext.create('Ext.data.TreeStore', {
    proxy: {
        type: 'ajax',
        url: contextPath + '/system/permission/getTreeData?isOnlyShowPagePermission=1&expanded=0'
    }
});

var treePanel= new Ext.tree.TreePanel(
{
	store: treeStore
	,rootVisible : false
    ,buttonAlign: "center"
    ,buttons: [sureBtn]	
}
);
Ext.onReady(function() {
    new Ext.Viewport({
        layout: "fit",
        items: [treePanel]
    });
});