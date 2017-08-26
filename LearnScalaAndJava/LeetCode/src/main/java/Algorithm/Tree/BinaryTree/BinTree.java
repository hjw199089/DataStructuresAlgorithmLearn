package Algorithm.Tree.BinaryTree;

import java.io.Console;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by hjw on 17/8/26.
 * 二叉树类
 */
public class BinTree {
    BinNodePtr root;

    public BinTree() {
        this.root = null;
    }

    /*==================================
         递归先序遍历构建二叉树
    ==================================*/
    public static BinNodePtr createBinTree() {
        BinNodePtr root = null;

        System.out.println("用'#'符号,作为NULL,结束叶子的输入");
        Scanner scanner = new Scanner(System.in);
        Object input = scanner.nextLine();

        if (!input.toString().equals("#")) {
            //构建节点
            root = new BinNodePtr(input);
            //构建左子树
            root.setLeft(createBinTree());
            //构建右子树
            root.setRight(createBinTree());
        }

        return root;
    }

    /*==================================
    先序遍历
    前序遍历按照“根结点-左孩子-右孩子”的顺序进行访问
    ================================== */
    public static void preOrder(BinNodePtr root) {
        if (root == null)
            return;

        root.visit();
        preOrder(root.left());
        preOrder(root.right());
    }

    /*==================================
    中序遍历
    中序遍历按照“左孩子-根结点-右孩子”的顺序进行访问。
     ==================================*/
    public static void inOrder(BinNodePtr root) {
        if (root == null)
            return;

        inOrder(root.left());
        root.visit();
        inOrder(root.right());
    }

    /*==================================
    后序遍历
    后序遍历按照“左孩子-右孩子-根结点”的顺序进行访问。
     ==================================*/
    public static void postOrder(BinNodePtr root) {
        if (root == null)
            return;

        postOrder(root.left());
        postOrder(root.right());
        root.visit();
    }


    /*==================================
    树的深度
    递归要找共性,相邻点的共性,最小的计算方式
    树的深度原理: 节点的深度 = max(左边深度 + 1, 右子树深度 + 1)
    整体递归
        1
       / \
     2   3
    /\
   4  5
     ==================================*/
    public static int depthOfTree(BinNodePtr root) {
        //递归的结束条件
        if (root == null) return 0;
        return Math.max(depthOfTree(root.left()) + 1, depthOfTree(root.right()) + 1);
    }


    /*==================================
    层次遍历
    二叉树的层次遍历更像是一种广度优先搜索（BFS）。因此二叉树的层次遍历利用队列来完成,
    按照层把元素存起来,边存边打印
    整体上和链表的遍历一样,只不过链表可以用pNext指针记住下一个节点,这里树不能用一个指针记住所以要临时存储
     ==================================*/
    public static void levelOrder(BinNodePtr root){
        if(root == null)
            return;
        Queue<Object> queue = new LinkedList<Object>();
        queue.offer(root);
        while(queue.size()!=0){
            //访问当前节点
            BinNodePtr binNode = (BinNodePtr)queue.poll();
            binNode.visit();
            //将当前节点的孩子节点暂存
            if (binNode.left() != null){
                queue.offer(binNode.left());
            }
            if (binNode.right() != null){
                queue.offer(binNode.right());
            }
        }
    }
    /*==================================
    求叶子节点的个数
    树中的叶子节点的个数 = 左子树中叶子节点的个数 + 右子树中叶子节点的个数
     ==================================*/
    public static int getNumOfLeaf(BinNodePtr root){
        if (root == null)
            return 0;
        //该节点为叶子节点
        if (root.left() == null && root.right() == null){
            return 1;
        }
        //递归整个树的叶子节点个数 = 左子树叶子节点的个数 + 右子树叶子节点的个数
        return  getNumOfLeaf(root.left()) + getNumOfLeaf(root.right());
    }

    /*==================================
    镜像二叉树
    交换二叉树的左右儿子，整体是先序递归
     ==================================*/
    public static void mirroTree(BinNodePtr root){
        if (root == null)
            return;
        //交换孩子节点
        BinNodePtr temp = root.left();
        root.setLeft(root.right());
        root.setRight(temp);
        //递归
        if (root.left() != null)
            mirroTree(root.left());
        if (root.right() != null){
            mirroTree(root.right());
        }
    }


    public static void main(String[] args) {

        //前序遍历构建二叉树,已'#'符号做null节点
        BinNodePtr root = createBinTree();

        System.out.println("前序遍历:");
        preOrder(root);
        System.out.println("中序遍历:");
        inOrder(root);
        System.out.println("后序遍历:");
        postOrder(root);
        System.out.println("层级序遍历:");
        levelOrder(root);

        int depth = depthOfTree(root);
        System.out.println("深度为:\t" + depth);

        int numOfLeaf = getNumOfLeaf(root);
        System.out.println("叶子数:\t" +  numOfLeaf);
    }
}






