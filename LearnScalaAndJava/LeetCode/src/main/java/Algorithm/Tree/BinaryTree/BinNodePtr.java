package Algorithm.Tree.BinaryTree;

/**
 * Created by hjw on 17/8/26.
 */
/*
二叉树链式节点
 */
public class BinNodePtr implements BinNode {
    //节点对象
    private Object element;
    //左子树指针
    private BinNodePtr left;
    //右子树指针
    private BinNodePtr right;

    /*
    构造
     */
    public BinNodePtr(){left = right = null;}

    public BinNodePtr(Object val){
        left = right = null;
        element = val;
    }

    public BinNodePtr(Object element, BinNodePtr left, BinNodePtr right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
    /*
    实现
     */
    public Object element() {
        return element;
    }

    public Object setElement(Object v) {
        return element = v;
    }

    public BinNodePtr left() {
        return this.left;
    }

    public Object setLeft(BinNode left) {
        return this.left = (BinNodePtr) left;
    }

    public BinNodePtr right() {
        return this.right;
    }

    public Object setRight(BinNode right) {
        return this.right = (BinNodePtr) right;
    }

    public boolean isLeaf() {
        return  this.left == null && this.right == null;
    }

    //访问打印该节点
    public void visit(){
        System.out.println(this.element);
    }

    @Override
    public String toString() {
        return "BinNodePtr{" +
                "element=" + element +
                '}';
    }
}
