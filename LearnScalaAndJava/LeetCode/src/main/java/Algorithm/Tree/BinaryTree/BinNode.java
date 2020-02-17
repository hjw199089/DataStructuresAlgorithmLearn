package Algorithm.Tree.BinaryTree;

/**
 * Created by hjw on 17/8/26.
 */
/*
二叉树的抽象数据类型,
包含元素的返回与设置,
左右子树的返回与设置
判断是否是叶子节点
 */
public interface BinNode {
    //返回并设置元素
    public Object element();
    public Object setElement(Object v);

    //返回并设置左子树
    public  Object left();
    public  Object setLeft(BinNode left);

    //返回并设置右子树
    public  Object right();
    public  Object setRight(BinNode right);

    //判断是否为叶子节点
    public boolean isLeaf();
}
