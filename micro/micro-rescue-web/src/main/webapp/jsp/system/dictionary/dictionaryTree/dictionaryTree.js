var sureBtn=UD.lib.createSureBtn(function(){
    var selectedNode = treePanel.getSelectionModel().getSelection();
    if (selectedNode.length==1) {
        if (selectedNode[0].get("id") == "root") {
            Ext.MessageBox.alert('提示', "不能选择根节点");
            return;
        } else {
        	parent.setDictionaryName(selectedNode[0].get("id"), selectedNode[0].get("text"));
        }

    } else {
        Ext.MessageBox.alert('提示', "请选择一个字典分类");
    }
})
var treeStore = Ext.create('Ext.data.TreeStore', {
    proxy: {
        type: 'ajax',
        url: contextPath + '/system/dictionary/getTreeData'
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