/**
 * 给树添加级联选中功能，为树设置属性enableAllCheck则为所有节点添加checkbox；
 *
 * 实现方式：
 * 为TreeNode添加方法：cascadeParent、cascadeChildren；
 * 修改AsyncTreeNode的loadComplete方法使其加载后自动级联级联子结点（cascadeChildren方法）；
 * 修改TreeEventModel的onCheckboxClick方法是的结点checkbox被点击后级联父节点和子节点；
 * （注意：之所以不修改TreeNodeUI类是为了使得执行函数为树结点修改选中状态时不进行级联，譬如
 * 说为所有树节点加载一个选中状态的数组时我们并不希望级联的情况发生）；
 * 为TreePanel添加属性enableAllCheck，
 * 为TreePanel添加loadCheckedNodes、checkAllNodes方法
 *
 * 使用方法：
 * TreePanel
 * enableAllCheck:Boolean 此配置项为true时所有加载的结点都会渲染chechbox；
 * loadCheckedNodes(Array nodes):void 根据结点数组/结点id数组选中结点
 * checkAllNodes(Bolean checked):void 全选或全不选树中所有节点
 * 
 * 1.1改动:
 * 略微修改匿名函数返回值
 * 
 * @author chemzqm@gmail.com
 * @version 1.1 (2010-4-24)
 */    
(function(){
    function cascadeParent(){
        var pn = this.parentNode;
        if (!pn || !Ext.isBoolean(this.attributes.checked)) 
            return;
        if (this.attributes.checked) {//级联选中
            pn.getUI().toggleCheck(true);
        }
        else {//级联未选中
            var b = true;
            Ext.each(pn.childNodes, function(n){
                if (n.getUI().isChecked()){ 
                    return b = false;
                }
                return true;
            });
            if (b) 
                pn.getUI().toggleCheck(false);
        }
        pn.cascadeParent();
    }
    
    function cascadeChildren(){
        var ch = this.attributes.checked;
        if (!Ext.isBoolean(ch)) 
            return;
        Ext.each(this.childNodes, function(n){    
            n.getUI().toggleCheck(ch);
            n.cascadeChildren();
        });
    }
    
    /**
     * 全选或全不选所有结点，必须所有结点都有checked属性
     * @param {Boolean} checked
     */
    function checkAllNodes(checked){
        this.root.attributes.checked = checked;
        this.root.cascadeChildren();
    }
    
    /**
     * 根据nodes数组加载选中节点
     * @param {Object} nodes node或id数组
     */
    function loadCheckedNodes(nodes){
        this.checkAllNodes(false);
        Ext.each(nodes, function(n){
            if (Ext.isString(n)) {
                n = this.getNodeById(n);
            }
            n.getUI().toggleCheck(true);
        }, this);
    }
    /**
     * tree的属性enableAllCheck为true时，所有节点默认渲染未选中的checkbox
     */
    Ext.apply(Ext.tree.TreePanel.prototype, {
        checkAllNodes: checkAllNodes,
        loadCheckedNodes: loadCheckedNodes,
        initComponent: Ext.tree.TreePanel.prototype.initComponent.createInterceptor(function(){
            if (this.enableAllCheck === true) {
                var loader = this.loader;
                loader.baseAttrs = loader.baseAttrs || {};
                loader.baseAttrs['checked'] = false;
            }
        })
    });
    /**
     * 为TreeNode对象添加级联父节点和子节点的方法
     */
    Ext.apply(Ext.tree.TreeNode.prototype, {
        cascadeParent: cascadeParent,
        cascadeChildren: cascadeChildren
    });
    /**
     * 结点加载后级联子节点
     */
    Ext.override(Ext.tree.AsyncTreeNode, {
        loadComplete: Ext.tree.AsyncTreeNode.prototype.loadComplete.createSequence(function(e, node){
            this.cascadeChildren();
        })
    });
    /**
     * Checkbox被点击后级联父节点和子节点
     */
    Ext.override(Ext.tree.TreeEventModel, {
        onCheckboxClick: Ext.tree.TreeEventModel.prototype.onCheckboxClick.createSequence(function(e, node){
            node.cascadeParent();
            node.cascadeChildren();
        })
    });
})();


