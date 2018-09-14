var sureBtn=UD.lib.createSureBtn(function(){
    var node = treePanel.getSelectionModel().getSelectedNode();
    if (node) {
        if (node.isRoot == true) {
            Ext.MessageBox.alert('提示', "不能选择根节点");
            return;
        } else {
        	parent.setDictionaryName(node.attributes.id, node.attributes.text);
        }

    } else {
        Ext.MessageBox.alert('提示', "请选择一个字典分类");
    }
})
var treePanel = new Ext.tree.TreePanel({
    border: false,
    autoScroll: true,
    loader: new Ext.tree.TreeLoader({
    	dataUrl : contextPath + '/system/dictionary/getTreeData'
    }),
    root: new Ext.tree.AsyncTreeNode(),
    rootVisible: false,
    split: true,
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