var sureBtn=UD.lib.createSureBtn(function(){
    var node = treePanel.getSelectionModel().getSelectedNode();
    if (node) {
    	parent.setPermissionFolder(node.attributes.id, node.attributes.text);
    } else {
        Ext.MessageBox.alert('提示', "请选择一个权限分类");
    }
})
var treePanel = new Ext.tree.TreePanel({
    border: false,
    autoScroll: true,
    loader: new Ext.tree.TreeLoader({
        dataUrl: contextPath + '/system/permission/getPermissionFolderTreeData'
    }),
    root: new Ext.tree.AsyncTreeNode(),
    rootVisible: false,
    split: true,
    //			width : 250,
    maxWidth: 300,
    plugins: 'filterBar',
    buttonAlign: "center",
    buttons: [sureBtn]
});

Ext.onReady(function() {
    new Ext.Viewport({
        layout: "fit",
        items: [treePanel]
    });
});