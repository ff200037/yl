var sureBtn=UD.lib.createSureBtn(function(){
    var selectedNode = treePanel.getSelectionModel().getSelection();
    if (selectedNode.length==1) {
        parent.setCatName(selectedNode[0].get("id"), selectedNode[0].get("text"));
    } else {
        Ext.MessageBox.alert('提示', "请选择一个参数分类");
    }
})

var treeStore = Ext.create('Ext.data.TreeStore', {
    proxy: {
        type: 'ajax',
        url: contextPath + '/system/paramCat/getTreeData'
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