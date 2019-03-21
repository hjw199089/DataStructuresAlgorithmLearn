package Algorithm.Tree.BinaryTree;

import Algorithm.List.List;
import Algorithm.List.ListNode;

import java.util.*;

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
//控制台输入方式
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

    //以先序字符串的方式构建二叉树
    private static class Index {
        int val;

        public Index(int val) {
            this.val = val;
        }
    }

    private static BinNodePtr createTreeWithString(Index index, String[] preOrderArr, int len) {
        BinNodePtr root = null;
        if (len > index.val) {
            if (!preOrderArr[index.val].equals("#")) {
                //构建节点
                root = new BinNodePtr(preOrderArr[index.val++]);
                //构建左子树
                root.setLeft(createTreeWithString(index, preOrderArr, len));
                //构建右子树
                root.setRight(createTreeWithString(index, preOrderArr, len));
            } else {
                index.val++;//跳过#
            }
        }
        return root;
    }

    public static BinNodePtr createBinTreeWithString(String preOrderTree) {
        String[] preOrderArr = preOrderTree.split("\\s+");
        Index index = new Index(0);
        return createTreeWithString(index, preOrderArr, preOrderArr.length);
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
    public static int getDepthOfTree(BinNodePtr root) {
        //递归的结束条件
        if (root == null) return 0;
        return Math.max(getDepthOfTree(root.left()) , getDepthOfTree(root.right()) ) + 1;
    }


    /*==================================
    层次遍历
    二叉树的层次遍历更像是一种广度优先搜索（BFS）。因此二叉树的层次遍历利用队列来完成,
    按照层把元素存起来,边存边打印
    整体上和链表的遍历一样,只不过链表可以用pNext指针记住下一个节点,这里树不能用一个指针记住所以要临时存储
     ==================================*/
    public static void levelOrder(BinNodePtr root) {
        if (root == null)
            return;
        Queue<Object> queue = new LinkedList<Object>();
        queue.offer(root);
        while (queue.size() != 0) {
            //访问当前节点
            BinNodePtr binNode = (BinNodePtr) queue.poll();
            binNode.visit();
            //将当前节点的孩子节点暂存
            if (binNode.left() != null) {
                queue.offer(binNode.left());
            }
            if (binNode.right() != null) {
                queue.offer(binNode.right());
            }
        }
    }

    /*==================================
    求叶子节点的个数
    树中的叶子节点的个数 = 左子树中叶子节点的个数 + 右子树中叶子节点的个数
     ==================================*/
    public static int getNumOfLeaf(BinNodePtr root) {
        if (root == null)
            return 0;
        //该节点为叶子节点
        if (root.left() == null && root.right() == null) {
            return 1;
        }
        //递归整个树的叶子节点个数 = 左子树叶子节点的个数 + 右子树叶子节点的个数
        return getNumOfLeaf(root.left()) + getNumOfLeaf(root.right());
    }

    /*==================================
    镜像二叉树
    交换二叉树的左右儿子，整体是先序递归
     ==================================*/
    public static void mirroTree(BinNodePtr root) {
        if (root == null)
            return;
        //交换孩子节点
        BinNodePtr temp = root.left();
        root.setLeft(root.right());
        root.setRight(temp);
        //递归
        if (root.left() != null)
            mirroTree(root.left());
        if (root.right() != null) {
            mirroTree(root.right());
        }
    }


    /*==================================
    判断一个节点是否在一颗子树中
    先根遍历,找到退出
     ==================================*/
    public static boolean isNodeOfTree(BinNodePtr root, BinNodePtr targetNode) {
        if (root == null || targetNode == null)
            return false;
        if (root == targetNode) {
            return true;
        }
        return isNodeOfTree(root.left(), targetNode) || isNodeOfTree(root.right(), targetNode);
    }

    /*==================================
    是否子树判断
    判断一棵树是否另一棵树的子树
    本质是判断节点相同(所有是递归),
    第一步:先从大树的根节点开始遍历找到一个节点和小树的根节点相同(一个节点在一棵树中)
    第二步:判断节点完全相同(这个所有节点误差的判断递归)
    这里假设数的节点时数字,元素相同即可
     ==================================*/
    private static boolean isChildTreeCmpFun(BinNodePtr root, BinNodePtr childroot) {
        if (childroot == null)
            return true;
        //子树还有节点,大树没有节点了
        if (root == null)
            return false;
        //当前节点元素值相同
        if (!root.element().toString().equals(childroot.element().toString()))
            return false;
        //左右都相同
        return isChildTreeCmpFun(root.left(), childroot.left()) && isChildTreeCmpFun(root.right(), childroot.right());
    }

    public static boolean isChildTree(BinNodePtr root, BinNodePtr childroot) {
        if (childroot == null || root == null)
            return false;

        boolean res = false;
        //找到根节点
        if (root.element().toString().equals(childroot.element().toString())) {
            //递归判断整个小树是大树的子树
            res = isChildTreeCmpFun(root, childroot);
        }
        //在左右子树中寻找相同的根节点
        if (!res) {
            res = isChildTree(root.left(), childroot);
        }
        if (!res) {
            res = isChildTree(root.right(), childroot);
        }

        return res;
    }

    /*==================================
    查找二叉树中两个节点的最低祖先节点（或最近公共父节点等）---递归法
    前提是两个节点都在,否则先用判断节点是否在树中做一轮判断
    (1)如果两个节点都在该节点的左子树,则在左边继续找
    (2)如果两个节点都在该节点的右子树,则在右边继续找
    (3)如果一个在左,一个在右,返回该节点
    伪代码
    比较当前A节点
        若相等返回A节点;
        若没有
            B=递归左边
            C=递归右边
        若左右中只有一个有,返回有的一方B/C;若两边都有返回当前节点A
      可见递归法需要每个节点都做左右子树的比较
     ==================================*/
    public static BinNodePtr getLastCommonParentRecur(BinNodePtr root, int targetA, int targetB) {
        if (root == null)
            return null;
        //如果找到元素,返回该元素
        if (Integer.parseInt(root.element().toString()) == targetA
                || Integer.parseInt(root.element().toString()) == targetB) {
            return root;
        }
        //判断该节点左右子树中的查询情况
        BinNodePtr leftRes = getLastCommonParentRecur(root.left(), targetA, targetB);
        BinNodePtr rightRes = getLastCommonParentRecur(root.right(), targetA, targetB);
        //若分布在左右两边,返回当前节点
        if (leftRes != null && rightRes != null) {
            return root;
        }
        //否则,返回左右中有的一方
        return (leftRes != null ? leftRes : rightRes);
    }
    /*==================================
    到某个节点的路径
    先序遍历,用队列(LinkList存储)
    伪代码
    先序根节点
        若为null,返回res = false
        若相等则,将该节点入栈,返回res = true
        res = 在递归左子树
        若左边没有
            res =  递归右子树
        若右边没有,将该节点在路径中删除
        返回本次查询结果res
    ==================================*/
    public static boolean getPathOfNode(BinNodePtr root, BinNodePtr targetNode, LinkedList<BinNodePtr> path) {
        if (root == null) return false;

        path.offer(root);

        if (root == targetNode) {
            return true;
        }

        boolean res = getPathOfNode(root.left(), targetNode, path);
        if (res == false) {
            res = getPathOfNode(root.right(), targetNode, path);
        }

        if (res == false) {
            path.removeLast();
        }
        return res;
    }

    /*==================================
    查找二叉树中两个节点的最低祖先节点（或最近公共父节点等）---非递归解法
    先找到两个节点的path,从路径中找到最后一个公共的节点的即可
    伪代码:
    第1个节点的path1
    第2个节点的path2
    循环找到最后一个公共的节点
     ==================================*/
    public static BinNodePtr getLastCommonParent(BinNodePtr root, BinNodePtr targetNodeA, BinNodePtr targetNodeB) {
        if (root == null || targetNodeA == null || targetNodeB == null) {
            return null;
        }
        LinkedList<BinNodePtr> pathA = new LinkedList<BinNodePtr>();
        boolean isFindA = getPathOfNode(root, targetNodeA, pathA);

        LinkedList<BinNodePtr> pathB = new LinkedList<BinNodePtr>();
        boolean isFindB = getPathOfNode(root, targetNodeB, pathB);

        BinNodePtr last = null;
        if (isFindB && isFindA) {
            Iterator<BinNodePtr> iterA = pathA.iterator();
            Iterator<BinNodePtr> iterB = pathB.iterator();
            while (iterA.hasNext() && iterB.hasNext()) {
                BinNodePtr temp = iterA.next();
                if (temp == iterB.next())
                    last = temp;
            }
        }
        return last;
    }

    /*==================================
    求任意两节点距离
    (1)找到最低公共节点
    (2)公共节点到第一个节点的距离 + 公共节点到第二个节点的距离
    公共节点到第一个节点的距离递归加和(DFS)
     ==================================*/
    public static int getDistanceOfTwoNode(BinNodePtr startNode, BinNodePtr targetNode) {
        if (startNode == null)
            return -1;
        if (startNode == targetNode)
            return 0;

        int distance = getDistanceOfTwoNode(startNode.left(), targetNode);
        if (distance == -1) {//先根遍历,左边没找到才找右边
            distance = getDistanceOfTwoNode(startNode.right(), targetNode);
        }

        return distance + 1;
    }

    public static int getDistance(BinNodePtr root, BinNodePtr nodeA, BinNodePtr nodeB) {
        if (root == null)
            return -1;
        BinNodePtr LCPNode = getLastCommonParent(root, nodeA, nodeB);
        int distance = -1;
        if (LCPNode != null) {
            int distanceA = getDistanceOfTwoNode(LCPNode, nodeA);
            int distanceB = getDistanceOfTwoNode(LCPNode, nodeA);
            distance = distanceA + distanceB;
        }
        return distance;
    }


    /*==================================
    和值的路径
    从根节点开始找到所有路径，使得路径上的节点值和为某一数值（路径不一定以叶子节点结束）
    伪代码:
    已一个List存储路径
    先根递归遍历
        若经过该节点后和为sum,打印
        遍历左子树
        遍历右子树
        返回上层前减去该节点
     这里有个技巧:sum减法的方式,向下传递,不用指针方式
     ==================================*/
    public static void findAllPathSum(BinNodePtr root, int sum, LinkedList<BinNodePtr> path) {
        if (root == null) return;

        sum -= Integer.parseInt(root.element().toString());
        path.offer(root);
        if (sum <= 0) {
            if (sum == 0) {
                System.out.println(path);
            }
        }
        findAllPathSum(root.left(), sum, path);
        findAllPathSum(root.right(), sum, path);
        //将该节点退出,放返回上一层
        sum += Integer.parseInt(path.removeLast().element().toString());
    }

    /*==================================
     二叉树第k层的节点个数
     第k层的节点个数 = 左子树第K层节点数 + 左子树第K层节点数
     伪代码
     先根遍历
         到第K层,返回1
         return 递归左子树第K层节点数 + 左子树第K层节点数
      ==================================*/
    public static int getLeafNumOfKthLevel(BinNodePtr root, int k) {
        if (root == null) return 0;

        //到第K层
        if (k == 1)
            return 1;

        int leftNum = getLeafNumOfKthLevel(root.left(), k - 1);
        int rightNum = getLeafNumOfKthLevel(root.right(), k - 1);

        return leftNum + rightNum;
    }

    /*==================================
    二叉树前序中序推后序
   前序	[1 2 4 7 3 5 8 9 6]
   中序	[4 7 2 1 8 5 9 3 6]
   后序	[7 4 2 8 9 5 6 3 1]
   总体先序构建
   比如root = 1
   root.left = 由[2 4 7]递归
   root.right = 由[8 5 9 3 6]递归
   递归的过程中按照后续遍历打印
   printPosOrder(arr,start,end)
   伪代码:
   printPosOrder
   if start = end
       打印左孩子
   根据中序的信息将前序数组切分:找到切分点
   切分点左侧数组递归printPosOrder--->会打印出左
   切分点右侧数组递归printPosOrder--->会打印出右
   --->打印出根
     ==================================*/
    public static void printPosOrder(String[] preOrderArr, int sPre, String[] midOrderArr, int sMid, int Len) {
        if (preOrderArr == null || midOrderArr == null) //输入参数检测
            return;

        if (Len == 0) return;

        if (Len == 1) {
            System.out.print(preOrderArr[sPre] + "\t");
            return;
        }
        //切分点查找
        int len = 0;
        for (; preOrderArr[sPre] != midOrderArr[sMid + len]; len++) {
            ;
        }
        //切分点左侧
        printPosOrder(preOrderArr, sPre + 1, midOrderArr, sMid, len); //打印左边
        //切分点右侧
        printPosOrder(preOrderArr, sPre + len + 1, midOrderArr, sMid + len + 1, Len - len - 1);//打印右边
        System.out.print(preOrderArr[sPre] + "\t");
    }
      /*=====================
         二叉树前序中序推后序
        前序	[1 2 4 7 3 5 8 9 6]
        中序	[4 7 2 1 8 5 9 3 6]
        后序	[7 4 2 8 9 5 6 3 1]
       ====================*/

    public static BinNodePtr createTreeWithPreAndMid(String[] preOrderArr, int sPre, String[] midOrderArr, int sMid, int Len) {
        if (preOrderArr == null || midOrderArr == null) //输入参数检测
            return null;

        if (Len == 0) return null;

        if (Len == 1) {
            return new BinNodePtr(preOrderArr[sPre]);
        }
        //切分点查找
        int len = 0;
        for (; preOrderArr[sPre] != midOrderArr[sMid + len]; len++) {
            ;
        }
        BinNodePtr root = new BinNodePtr(preOrderArr[sPre]);
        //切分点左侧
        root.setLeft(createTreeWithPreAndMid(preOrderArr, sPre + 1, midOrderArr, sMid, len)); //打印左边
        //切分点右侧
        root.setRight(createTreeWithPreAndMid(preOrderArr, sPre + len + 1, midOrderArr, sMid + len + 1, Len - len - 1));//打印右边
        return root;
    }

    /*==================================
       二叉树节点最远距离
       最远距离 = max{ (左孩子的深度 + 右孩子深度), 左边最远距离, 右边最远距离}
      int[最远距离] getMaxDistance(BinNodePtr root,int &depth)
      伪代码:
      前序递归
          到null
              depth = 0
              return 0
           int depthLeft, int depthRight
           int leftDist  = getMaxDistance(root.left, &depthLeft); 递归左侧
           int rightDist = getMaxDistance(root.right,&depthRith); 递归右侧
           (*depth) =  max((*depthLeft),(*depthRight)) + 1;
           返回max(leftDist,rightDist,(*depthLeft)+(*depthRight))
     代码类似求深度,深度已引用形式逐层向上带出,返回当前节点的最远距离
    ==================================*/

    private static class Depth {
        private int val;

        public Depth(int val) {
            this.val = val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    private static int getMaxDistanceFun(BinNodePtr root, Depth depth) {
        if (root == null) {
            //更新深度
            depth.setVal(0);
            //返回最远距离0
            return 0;
        }
        Depth depthLeft = new Depth(0);
        Depth depthRight = new Depth(0);
        int leftDist = getMaxDistanceFun(root.left(), depthLeft);
        int rightDist = getMaxDistanceFun(root.right(), depthRight);
        //更新深度
        depth.setVal(Math.max(depthLeft.getVal(), depthRight.getVal()) + 1);
        //返回最远距离
        return Math.max(Math.max(leftDist, rightDist), (depthLeft.getVal() + depthRight.getVal()));
    }

    public static int getMaxDistance(BinNodePtr root) {
        if (root == null) {
            return 0;
        }
        Depth depth = new Depth(0);
        int dist = getMaxDistanceFun(root, depth);
        return dist;
    }

    /*==================================
    判断是否是平衡二叉树-深度法
    原理:每个节点的左深度和右深度间平衡,递归
    伪代码:
    若root为空直接返回
        左边的深度getDepthOfTree
        右边的深度getDepthOfTree
        判断左右平衡
        返回: 递归左 &&  递归右
    从上往下每个节点都要求一遍深度,有冗余
    ==================================*/
    public static boolean isBalanceWithDepth(BinNodePtr root) {
        if (root == null)
            return true;

        int depthLeft = getDepthOfTree(root.left());
        int depthRight = getDepthOfTree(root.right());
        if (Math.abs(depthLeft - depthRight) > 1) {
            return false;
        }

        return isBalanceWithDepth(root.left()) && isBalanceWithDepth(root.left());
    }
    /*==================================
    判断是否是平衡二叉树-直接法
    原理:在计算深度的同时判断当前节点是否平衡,若不平衡退出
    是否平衡 = isBalanceDirect(root,)
    伪代码:
        若节点为null
            返回true,
            depth = 0
        isL = 递归左侧 depthL
        isR = 递归右侧 depthR
        isblance
        若(isL && isR)
            isblance = 判断当前节点是否平衡
            更新深度
        返回isblance
    ==================================*/
    public static boolean isBalanceDirect(BinNodePtr root){
        if (root == null)
            return false;
        int[] depth = {0};
        return isBalanceDirectFun(root,depth);
    }

    private static boolean isBalanceDirectFun(BinNodePtr root, int[] depth) {
        if (root == null) {
            depth[0] = 0;
            return true;
        }
        int[] depthLeft = {0};
        int[] depthRight = {0};
        boolean isbL = isBalanceDirectFun(root.left(), depthLeft);
        boolean isbR = isBalanceDirectFun(root.right(), depthRight);

        boolean isbalance = false;
        if (isbL && isbR) {
            if (Math.abs(depthLeft[0] - depthRight[0]) > 1) {
                return false;
            }
            isbalance = true;
            depth[0] = Math.max(depthLeft[0],depthRight[0]) + 1;
        }
        return isbalance;
    }
    /*==================================
    判定完全二叉树
    判定一棵树是不是完全二叉树的思路是广度遍历该二叉树，当出现NULL值时停止遍历，
    如果此时还有没有遍历到的结点，那么就说明该树非完全二叉树
    按层遍历(层遍历,用中间缓存),当遇到null停此时还有节点在缓存中,即非完全
    伪代码:
        节点不为null,入队列
        while 队列不为空
            出对头,将左右孩子入队列
        while 队列不为空
            接着一直出队,若此时有一个非null的元素,返回false
        返回true
     ==================================*/
    public static boolean isCompleteTree(BinNodePtr root){
        if (root == null)
            return true;
        Queue<BinNodePtr> queue = new LinkedList<BinNodePtr>();
        queue.offer(root);
        while (queue.size() > 0){
            BinNodePtr top = queue.poll();
            if (top == null){
                break;
            }else{
                queue.offer(top.left());
                queue.offer(top.right());
            }
        }

        while (queue.size() > 0){
            if (queue.poll() != null)
                return false;
        }

        return true;
    }
    /*==================================
    求节点数
    先根遍历不为null返
    伪代码:
        若为null,返回0
        sum++
        sum += 递归左边
        sum += 递归右边
        返回sum
     ==================================*/
    public static int getNodeNum(BinNodePtr root){
        if (root == null)
            return 0;
        //int sum = 1;
        //sum += getNodeNum(root.left());
        //sum += getNodeNum(root.right());
        //return sum;
        return 1 + getNodeNum(root.left()) + getNodeNum(root.right());
    }


    /*==================================
    判定满二叉树
    k层节点数2^K -1
    方法1: 判断上面的等式 节点数 == 2^K -1
     ==================================*/
    public static boolean isFullTree(BinNodePtr root){
        if (root == null)
            return false;
        int depth = getDepthOfTree(root);
        int nodeNum = getNodeNum(root);
        return nodeNum == (2^depth - 1);
    }
    /*==================================
    判断是否是二叉查找树
    前序对每一个节点满足 左<当前<右
    ==================================*/
    public static  boolean isBSTV1(BinNodePtr root){
        if (root == null) return true;

        boolean res = true;
        if (root.left() != null){
           if(Integer.parseInt(root.element().toString()) < Integer.parseInt(root.left().element().toString()))
               return false;
            else
               res = isBSTV1(root.left());
        }else if(root.right() != null){
            return false;
        }

        if (!res ){
            if(Integer.parseInt(root.element().toString()) > Integer.parseInt(root.right().element().toString()))
                return false;
            res = isBSTV1(root.left());
        }

        return res;
    }


    /*==================================
    判断是否是二叉查找树
     min < 当前节点 < max
     对于左子树
        MIN  < root.left < root
     对于右子树
        root < root.right < MAX
     ==================================*/
    private static  boolean isBSTFun(BinNodePtr root,int min,int max){
        if (root == null)
            return true;
        int rootVal = Integer.parseInt(root.element().toString());
        if (rootVal < min ||rootVal > max)
            return false;
        return isBSTFun(root.left(),min,rootVal) && isBSTFun(root.right(),rootVal,max);
    }
    public static boolean isBSTV2(BinNodePtr root){
        if (root == null)
            return true;
        int MAX = Integer.MAX_VALUE;
        int MIN = Integer.MIN_VALUE;
        return isBSTFun(root,MIN,MAX);
    }

    /*==================================
    二分查找树转化为排序的循环双链表
    二叉查找树（Binary Search Tree），
    也称有序二叉树（ordered binary tree）,
    排序二叉树（sorted binary tree），
    是指一棵空树或者具有下列性质的二叉树：
        若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
        任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
        任意节点的左、右子树也分别为二叉查找树。
        没有键值相等的节点（no duplicate nodes）
            4
           /\
          2 5
         /\
        1 3
        null<-左-- 1<--左--2 <--左--3<--左--4<--左--5
                    --右-->  --右--> --右--> --右--> --右-->null
      中序遍历
      上一个元素Pre的右指针指向当前
      当前cur的左指针指向上一个元素
      BinNodePtr  BST2SotredList(BinNodePtr cur)
      伪代码:
      BST2SotredListFun(BinNodePtr cur,BinNodePtr pre)
      BST2SotredListFun(root,null)
      整理是中序遍历

      若cur为NULL 返回;
      BST2SotredListFun(root.left,pre)
        cur.left--> pre
        若(pre != null)
            pre.right-->cur
        pre = cur
      BST2SotredListFun(root.right,pre)
     ==================================*/
     public static BinNodePtr  BST2SotredList(BinNodePtr root){
         if (root == null)
             return null;
         BinNodePtr[] pre = {null};
         BST2SotredListFun(root,pre);
         BinNodePtr head = root;
         //找到链表的头
         while(head.left() != null){
             head = head.left();
         }
         return head;
     }
    public static void  BST2SotredListFun(BinNodePtr cur,BinNodePtr[] pre){
        if (cur == null)
            return;
        BST2SotredListFun(cur.left(),pre);
        cur.setLeft(pre[0]);
        if (pre[0] != null)
            pre[0].setRight(cur);
        pre[0] = cur;
        BST2SotredListFun(cur.right(),pre);
    }
    /*==================================
    给定一个升序排列的有序单链表，将其转换为一棵平衡的二叉搜索树。
    比较直观的解法是自顶向下的递归解决，先找到中间节点作为根节点，然后递归左右两部分。
    所有我们需要先找到中间节点，对于单链表来说，必须要遍历一边，可以使用快慢指针加快查找速度。
    BinNodePtr res = sotredList2BST(ListNode  head)
    伪代码:
    若head 或者 head的next为null,返回head
    root = 找到中间元素构建,(slow,fast),返回slow,
    递归左半部分:root.left  = sotredList2BST(head)
    递归右半部分:root.right = sotredList2BST(slow.next)
    返回root
    ================================== */
    public static BinNodePtr sotredList2BSTFun(ListNode head,ListNode tail){
        if (head == null)
            return null;

        ListNode midNode = List.getMidNodeOfList(head,tail);
        BinNodePtr root = null;
        if (midNode != null){
            root  = new BinNodePtr(midNode.getElement());
            root.setLeft(sotredList2BSTFun(head,midNode));
            root.setRight(sotredList2BSTFun(midNode.getNext(),tail));
        }

        return root;
    }

    public static BinNodePtr sotredList2BST(ListNode head){
        if (head == null)
            return null;
        BinNodePtr root = sotredList2BSTFun(head,null);
        return root;
    }
    /*==================================
    求二叉树的宽度
    按层遍历,记录该层的节点数 与 max 比较: max= Math.max(节点数,max)
    getWidthOfTree(BinNodePtr root)
    伪代码:
    总体类似按层遍历
    curLevelNum = 1;max = 1
    nextLevelNum = 0
    队列将元素入队
    当队列不为空
        若curLevelNum != 0 将队列中的元素出队,同时记录nextLevelNum
        若curLevelNum = 0,
            将 max = Math.max(nextLevelNum和max)
            curLevelNum = nextLevelNum
            nextLevelNum = 0
     ==================================*/
    public static int  getWidthOfTree(BinNodePtr root){
        if (root == null)
            return 0;
        int curLevelNum = 1;
        int width = 1;
        int nextLevelNum = 0;
        Queue<BinNodePtr> queue = new LinkedList<BinNodePtr>();
        queue.offer(root);
        while(queue.size() != 0){
            if(curLevelNum == 0){
                width = Math.max(nextLevelNum,width);
                curLevelNum = nextLevelNum;
                nextLevelNum = 0;
            }

            BinNodePtr top = queue.poll();
            curLevelNum--;

            if (top.left() != null){
                queue.offer(top.left());
                nextLevelNum++;
            }
            if (top.right() != null){
                queue.offer(top.right());
                nextLevelNum++;
            }
        }
        return width;
    }
    /*==================================
    二叉树中的最大路径和(全局变量的思量在里面,递归的返回值不一定就是所求值)
                            当前
                            /  \
            (左边)到叶子的最大值     (右边边)到叶子的最大值     全局最大值
    伪代码:
    整体后序遍历
    当前节点此时的全局最大值 = ?
    左边最叶子的最大值 + 当前, 右边最叶子的最大值 + 当前, 左边最叶子的最大值 + 当前 + 右边最叶子的最大值
    返回当前节点到叶子的最大值

    Given a binary tree, find the maximum path sum.
    The path may start and end at any node in the tree.
    For example: Given the below binary tree,

       1
      / \
     2   3
    Return 6.
     ==================================*/
    private static int MaxPathSumFun(BinNodePtr root,int[] globalMax){
        if (root == null)
            return 0;
        int LMaxSum2Leaf = MaxPathSumFun(root.left(),globalMax);
        int RMaxSum2Leaf = MaxPathSumFun(root.right(),globalMax);

        int rootVal = Integer.parseInt(root.element().toString());
        int MaxSum2Leaf  =  Math.max( Math.max( (LMaxSum2Leaf + rootVal) , (RMaxSum2Leaf + rootVal))
                , rootVal);
        int curMax = Math.max(MaxSum2Leaf,LMaxSum2Leaf + RMaxSum2Leaf + rootVal);
        globalMax[0] = Math.max(globalMax[0],curMax);
        return MaxSum2Leaf;
    }

    public static int MaxPathSum(BinNodePtr root){
        if (root == null)
            return 0;
        int[] globalMax = {0};//当做指针
        MaxPathSumFun(root,globalMax);
        return globalMax[0];
    }

    /*==================================
    先序遍历非递归实现
    1) 访问结点 P,并将结点 P 入栈;
    2) 判断结点 P 的左孩子是否为空,若为空,则取栈顶结点并进行出栈操作,并将栈顶结点
    的右孩子置为当前的结点 P,循环至 1);若不为空,则将 P 的左孩子置为当前的结点 P;
    3) 直到 P 为 NULL 并且栈为空,则遍历结束。
    把访问过的储存起来,转到存的元素的右孩子
    Recursive 递归
    Iterative 循环
    伪代码
    整体是先序,访问该节点,存他的左右孩子
     ==================================*/
    public static void preOrderIterative(BinNodePtr root){
        if (root == null)
            return;
        Stack<BinNodePtr> stack = new Stack();

        while(stack.size() > 0 || root != null){
            if (root != null){
                System.out.print(root.element() + "--->");
                stack.add(root);
                root = root.left();//继续前序遍历
            }else{
                root = stack.pop();
                //转到右边去,栈的主要目的就是知道转到哪个元素的后边去
                root = root.right();
            }
        }
        System.out.println("NULL");
    }

    /*==================================
   中序遍历非递归实现
   1) 并将结点 P 入栈;
   2) 判断结点 P 的左孩子是否为空,若为空,则取栈顶结点并进行出栈操作,访问该节点,并将栈顶结点
   的右孩子置为当前的结点 P,循环至 1);若不为空,则将 P 的左孩子置为当前的结点 P;
   3) 直到 P 为 NULL 并且栈为空,则遍历结束。
   把访问过的储存起来,转到存的元素的右孩子
   Recursive 递归
   Iterative 循环
   伪代码
   整体是先序,出栈访问该节点,存他的左右孩子
    ==================================*/
    public static void idOrderIterative(BinNodePtr root){
        if (root == null)
            return;
        Stack<BinNodePtr> stack = new Stack();

        while(stack.size() > 0 || root != null){
            if (root != null){
                stack.add(root);
                root = root.left();//继续前序遍历
            }else{
                root = stack.pop();
                System.out.print(root.element() + "--->");
                //转到右边去,栈的主要目的就是知道转到哪个元素的后边去
                root = root.right();
            }
        }
        System.out.println("NULL");
    }

    /*==================================
   后序遍历非递归实现
   对于后序遍历，首先遍历左子树，然后是右子树，最后才是根节点。
   当遍历到一个节点的时候，
   首先我们将右子树压栈，然后将左子树压栈。
   这里需要注意一下出栈的规则，对于叶子节点来说，直接可以出栈，
   但是对于根节点来说，我们需要一个变量记录上一次出栈的节点
   如果上一次出栈的节点是该根节点的左子树或者右子树，那么该根节点可以出栈，
   否则这个根节点是新访问的节点，将右和左子树分别压栈。
   ==================================*/

    public static void  postOrderIterative(BinNodePtr root){
        if (root == null)
            return;
        Stack<BinNodePtr> stack = new Stack();
        BinNodePtr pre = root;
        stack.add(root);
        while(stack.size() > 0){
            root = stack.peek();
            //若为叶子,直接访问,判断上一次访问的是不是他的叶子
            if (root.isLeaf() || pre == root.left() || pre == root.right()){
                System.out.print(root.element() + "--->");
                pre = stack.pop();
            }else{
                if (root.right() != null){
                    stack.add(root.right());
                }
                if (root.left() != null){
                    stack.add(root.left());
                }
            }
        }
        System.out.println("NULL");
    }

   /*==================================
   后序遍历非递归实现
   第二次访问时才可输出
   ==================================*/
    public static void  postOrderIterativeV2(BinNodePtr root){
        if (root == null)
            return;
        class Node{
            BinNodePtr val;
            boolean isTwiceVisited;

            public Node(BinNodePtr val, boolean isTwiceVisited) {
                this.val = val;
                this.isTwiceVisited = isTwiceVisited;
            }
        }

        Stack<Node> stack = new Stack();

        while(stack.size() > 0 || root != null){
            if (root != null){
                stack.add(new Node(root,false));
                root = root.left();//继续前序遍历
            }else{
                Node node = stack.peek();
                if (node.isTwiceVisited == true){
                    System.out.print(node.val.element() + "--->");
                    stack.pop();
                }else {
                    node.isTwiceVisited = true;
                    //转到右边去,栈的主要目的就是知道转到哪个元素的后边去
                    root = node.val.right();
                }
            }
        }
        System.out.println("NULL");
    }







    public static void main(String[] args) {

        //前序遍历构建二叉树,已'#'符号做null节点
        //BinNodePtr root = createBinTree();
        String preTree = "1 2 4 # # 5 6 # # 7 # # 3 # #";
        BinNodePtr root = createBinTreeWithString(preTree);

        System.out.println("\n前序遍历:");
        preOrder(root);
        System.out.println("\n中序遍历:");
        inOrder(root);
        System.out.println("\n后序遍历:");
        postOrder(root);
        System.out.println("\n层级序遍历:");
        levelOrder(root);

        int depth = getDepthOfTree(root);
        System.out.println("\n深度为:\t" + depth);

        int numOfLeaf = getNumOfLeaf(root);
        System.out.println("叶子数:\t" + numOfLeaf);

        //mirroTree(root);
        //System.out.println("镜像后的前序遍历:");
        //preOrder(root);

        boolean isNodeA = isNodeOfTree(root, root.left().right());
        BinNodePtr otherNode = new BinNodePtr(10);
        boolean isNodeB = isNodeOfTree(root, otherNode);
        System.out.println("是否节点判断:\n" + "\tisNodeA= " + isNodeA + "\tisNodeB= " + isNodeB);

        //树为:1 2 4 # # 5 6 # # 7 # # 3 # #
        BinNodePtr childroot = root.left().right();
        boolean isChTree = isChildTree(root, childroot);
        System.out.println("是否子树:" + isChTree);
        BinNodePtr otherchildroot = createBinTreeWithString("2 6 # # 5 # #");
        isChTree = isChildTree(root, otherchildroot);
        System.out.println("是否子树:" + isChTree);

        preTree = "1 2 4 # # 5 6 # # 7 # # 3 # #";
        root = createBinTreeWithString(preTree);
        System.out.println("递归返回最低公共祖先");
        System.out.println(getLastCommonParentRecur(root, 5, 3));//返回1
        System.out.println(getLastCommonParentRecur(root, 4, 5));//返回2
        System.out.println(getLastCommonParentRecur(root, 4, 2));//返回2

        LinkedList<BinNodePtr> path = new LinkedList<BinNodePtr>();
        boolean isFind = getPathOfNode(root, root.left().right(), path);
        if (isFind) {
            System.out.println("路径为:");
            System.out.println(path.toString());
        }

        System.out.println("非递归返回最低公共祖先:");
        BinNodePtr last = getLastCommonParent(root, root.left().right(), root.right());
        System.out.println(last);

        LinkedList<BinNodePtr> pathOfSum = new LinkedList<BinNodePtr>();
        preTree = "1 2 4 1 # #  # 5 # #  3  2 # 2 # # #";
        root = createBinTreeWithString(preTree);
        System.out.println("从根节点开始找到所有路径，使得路径上的节点值和为某一数值（路径不一定以叶子节点结束）:");
        findAllPathSum(root, 8, pathOfSum);

        int leafNum2thLevel = getLeafNumOfKthLevel(root, 2);
        int leafNum3thLevel = getLeafNumOfKthLevel(root, 3);
        System.out.println("查找第2层和第三层的节点数:");
        System.out.println("leafNum2thLevel:\t" + leafNum2thLevel);
        System.out.println("leafNum3thLevel:\t" + leafNum3thLevel);

        preTree = "1 2 4 1 # #  # 5 # #  3  2 # 2 # # #";
        root = createBinTreeWithString(preTree);
        int dist = getDistanceOfTwoNode(root, root.right().left().right());
        System.out.println("一个节点到另一个节点的距离:\t" + dist);

        preTree = "1 2 4 1 # #  # 5 # #  3  2 # 2 # # #";
        root = createBinTreeWithString(preTree);
        int distOfRand2Node = getDistance(root, root.right().left().right(), root.left().left().left());
        System.out.println("求任意两节点距离:\t" + distOfRand2Node);

        // 前序	[1 2 4 7 3 5 8 9 6]
        // 中序	[4 7 2 1 8 5 9 3 6]
        // 后序	[7 4 2 8 9 5 6 3 1]
        String[] preOrderArr = {"1", "2", "4", "7", "3", "5", "8", "9", "6"};
        String[] midOrderArr = {"4", "7", "2", "1", "8", "5", "9", "3", "6"};
        System.out.println("\n------以前序和中序遍历打印后续遍历------");
        System.out.print("打印后序遍历:");
        printPosOrder(preOrderArr, 0, midOrderArr, 0, preOrderArr.length);

        System.out.println("\n------以前序和中序遍历重建二叉树------");
        root = createTreeWithPreAndMid(preOrderArr, 0, midOrderArr, 0, preOrderArr.length);
        System.out.println("重建树的前序遍历:");
        preOrder(root);
        System.out.println("\n重建树的中序遍历:");
        inOrder(root);
        System.out.println("\n重建树的后序遍历:");
        postOrder(root);

        preTree = "1 2 4 # #  5 # #  3  # #"; //返回3
        preTree = "1 2 4 # # #  3 # #";//返回3
        preTree = "1 2 4 9 10 # # # #  5  6  8  # # # 7 # # 3 # #";//返回6
        root = createBinTreeWithString(preTree);
        int MLD = getMaxDistance(root);
        System.out.println("\n最远距离:\t" + MLD);

        preTree = "1 2 # # 3 # #";//返回ture
        //preTree = "1 2 4 9 10 # # # #  5  6  8  # # # 7 # # 3 # #";//返回false
        root = createBinTreeWithString(preTree);
        boolean isbalance = isBalanceWithDepth(root);
        System.out.println("\n是否平衡(深度法):\t" + isbalance);

        isbalance = isBalanceDirect(root);
        System.out.println("\n是否平衡(直接法):\t" + isbalance);

        preTree = "1 2 # # 3 # #";//返回ture
        preTree = "1 2  4 # #  # 3 # #";//返回ture
        preTree = "1 2  4 # #  5 # # 3 # #";//返回ture
        preTree = "1 2  4 # #  #  3 5 # # #";//返回false
        root = createBinTreeWithString(preTree);
        boolean iscmt = isCompleteTree(root);
        System.out.println("\n是否完全二叉树:\t" + iscmt);


        int nodeNum= getNodeNum(root);
        System.out.println("\n树的节点数:\t" + nodeNum);

        preTree = "1 2 # # 3 # #";//返回ture
        preTree = "1 2  4 # #  # 3 # #";//返回false
        root = createBinTreeWithString(preTree);
        boolean isfull = isFullTree(root);
        System.out.println("\n是否满二叉树:\t" + isfull);

        preTree = "2  1 # # 3 # #";//返回ture
        root = createBinTreeWithString(preTree);
        boolean isbst = isBSTV1(root);
        System.out.println("\n是否搜索二叉树:\t" +isbst);

        isbst = isBSTV2(root);
        System.out.println("\n是否搜索二叉树:\t" +isbst);

        preTree = "4  2 1  # # 3 # # 5 # #";
        root = createBinTreeWithString(preTree);
        BinNodePtr head = BST2SotredList(root);
        BinNodePtr p = head;
        while(p!=null){
            System.out.print(p.element().toString()+"--->");
            p = p.right();
        }
        System.out.print("NULL");


        System.out.println("\n\n------给定一个升序排列的有序单链表，将其转换为一棵平衡的二叉搜索树------");
        //        输入：1 2 3 4 5 6 7 8 9 0
        //        输出：5 2 7 1 3 6 8 4 9
        //
        //        所构造的AVL是：
        //                   5
        //                  /   \
        //                 2     7
        //                / \   / \
        //                1  3  6  8
        //                    \     \
        //                    4     9
        String[] listStr = {"1","2","3","4","5","6","7","8","9"};
        ListNode listHead = List.createNodeWithString(listStr);
        System.out.println("排序链表为:");
        List.printList(listHead);
        root = sotredList2BST(listHead);
        System.out.println("\n层级序遍历:");
        levelOrder(root);

        int width= getWidthOfTree(root);
        System.out.println("\n宽度为:" + width);//4

        preTree = "1 2 # # 3 # #";
        root = createBinTreeWithString(preTree);
        int sum =  MaxPathSum(root);
        System.out.println("\n二叉树中的最大路径和为:" + sum);

        preTree = "1 2  4 # # 5 # # 3 # 6 # #";
        root = createBinTreeWithString(preTree);
        System.out.print("前序遍历:\t");
        preOrderIterative(root);
        System.out.print("中序遍历:\t");
        idOrderIterative(root);

        preTree = "1 2  4  5 # #  #  6 # 7 # # 3 # 8 # #";
        //5--->4--->7--->6--->2--->8--->3--->1--->NULL
        root = createBinTreeWithString(preTree);
        System.out.print("后序遍历:\t");
        postOrderIterative(root);
        System.out.print("后序遍历V2:\t");
        postOrderIterativeV2(root);

    }
}






