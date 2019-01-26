package com.basic.structure.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author joker
 * @When
 * @Description 普通二叉树的创建, 分为递归方式创建和非递归方式创建, 为了方便, 这里的数据都采用int
 * @Detail
 * @date 创建时间：2019-01-14 20:37
 */
@Data
public class BinaryTree
{

    @Data
    private class TreeNode
    {
        private Integer data;

        public Integer getData()
        {
            return data;
        }

        @Override
        public String toString()
        {
            return "TreeNode{" +
                    "data=" + data +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }

        private TreeNode leftChild;

        private TreeNode rightChild;
    }


    private TreeNode root;

    private int index;

    public void buildTree(Integer[] arr)
    {
        this.root = loopBuildTree(this.root, arr);
    }


    // 递归创建普通的树
    public TreeNode loopBuildTree(TreeNode node, Integer[] arr)
    {
        if (index >= arr.length || arr[index] == -1)
        {
            this.index++;
            return null;
        }
        node = new TreeNode();
        node.setData(arr[this.index++]);
        node.setLeftChild(loopBuildTree(node.getLeftChild(), arr));
        node.setRightChild(loopBuildTree(node.getRightChild(), arr));
        return node;

    }


    // 非递归创建普通的树
    public void stackBuildTree(Integer[] arr)
    {

        boolean left = true;

        Stack<TreeNode> stack = new Stack<TreeNode>();

        if (arr.length <= 0)
        {
            return;

        }
        this.root = new TreeNode();
        this.root.setData(arr[0]);
        stack.push(this.root);
        for (int i = 1; i < arr.length; i++)
        {

            if (arr[i] == -1)
            {

                if (left)
                {
                    left = false;
                } else if (!stack.empty())
                {
                    stack.pop();
                }

            } else
            {
                TreeNode newNode = new TreeNode();
                newNode.setData(arr[i]);
                if (left)
                {
//                    left=false;  这个不用设置,因为我们是通过-1来判断是否结束的,当然如果数组中不采取-1的方式
                    // 可以通过手动修改达到功能
                    stack.peek().setLeftChild(newNode);
                } else
                {
                    TreeNode popNode = stack.pop();
                    popNode.setRightChild(newNode);
                    left = true;
                }
                stack.push(newNode);
            }

        }
    }


    //BFS(又名) 基于队列,较dfs性能高,但是更耗内存
    // 3个陷阱:  1. queue#push的时候要进行判断是否为空,否则空的也放进去了
    //          2. pop的时候要对是否为空进行判断,否则empty也pop了
    //          3. 第三个陷阱其实是第二个陷阱的衍生,就是判空不能放在for循环里(会造成第一次就结束)
    //             只能放在循环体内部,符合条件退出
    public List<Integer> BFSTree()
    {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

        queue.push(this.root);

        TreeNode temp = null;

        LinkedList<Integer> resultList = new LinkedList<>();

        for (temp = queue.pop(); null != temp; temp = queue.pop())
        {
            // 层级遍历,一层一层打印
            resultList.add(temp.getData());
            if (temp.leftChild != null)
            {
                queue.add(temp.leftChild);
            }
            if (temp.rightChild != null)
            {
                queue.add(temp.rightChild);
            }

            if (queue.isEmpty())
            {
                return resultList;
            }

        }
        return resultList;

    }


    // DFS 基于栈,较BFS内存占用更少,但是性能较之为低
    // 与BFS相比,不同的地方只有两点:
    // 1.bfs使用的是队列 而dfs使用的是栈
    // 2.bfs的结果是一层一层的顺序,而dfs则是一条连线(在test目录下将结果打印配合自己画图更明确)
    public List<Integer> DFSTree()
    {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(this.root);
        TreeNode temp = null;
        for (temp = stack.pop(); null != temp; temp = stack.pop())
        {
            resultList.add(temp.getData());
            if (null != temp.rightChild)
            {
                stack.push(temp.rightChild);
            }
            if (null != temp.leftChild)
            {
                stack.push(temp.leftChild);
            }

            if (stack.isEmpty())
            {
                return resultList;
            }
        }
        return resultList;
    }

    public void preorderTree(TreeNode node, List<Integer> resultList)
    {
        // 根左右
        if (null != node)
        {
            resultList.add(node.data);

            preorderTree(node.leftChild, resultList);
            preorderTree(node.rightChild, resultList);
        }

    }

    // 非递归先序遍历:
    // 先序遍历: 根左右
    // 对于任一结点P：
    // 访问结点P，并将结点P入栈;
    // 将循环将节点的左孩子入栈,如果为空,则退出调整方向为右孩子,然后继续入栈,若右孩子也为空,则会继续出栈
    public List<Integer
            > preorderTreeByStack()
    {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> resultList = new ArrayList<>();
        TreeNode tempNode = this.root;

        // 为什么这里的限定条件还要有栈不为空
        // 原因在于: 1. 若栈不为空则表明内部还有元素没有遍历完成
        // 2. 如果当方向调整为右边之后,而右孩子为空,则需要继续弹到上一个节点
        while (tempNode != null || !stack.isEmpty())
        {
            // 如果右孩子为空之后,这里就不会执行了,既此时10的右孩子为空
            // 则又会弹出,弹回9,9又会对节点判断,发现右边也是为空,则又会弹回4,8之前已经弹出去了
            while (tempNode != null)
            {
                resultList.add(tempNode.data);
                // 根节点入栈
                stack.push(tempNode);
                // 如果左孩子不为空,则左孩子也会入栈
                tempNode = tempNode.leftChild;
            }
             /*
                    1
                  2    3
                4   5 6  7
              8
               \
                9
               /
              10

             */
            // 上述的退出条件是这个根节点没有左孩子了,如上述树中的8节点
            // 因为是先序,根左右,所以我们需要弹出(弹回8)调整方向为右边
            // 因此
            if (!stack.isEmpty())
            {
                tempNode = stack.pop();
                tempNode = tempNode.rightChild;

            }
        }
        return resultList;
    }

    // 中序遍历
    // 左根右
    public void inOrderTree(TreeNode node, List<Integer> resultList)
    {
        if (null != node)
        {
            inOrderTree(node.leftChild, resultList);
            resultList.add(node.data);
            inOrderTree(node.rightChild, resultList);
        }
    }

    /*
        中序遍历:左根右
        中序遍历其实与先序遍历的代码差不多一样
        只有一行不同,因为中序遍历是先左节点,不是根节点,因而我们需要在if代码块中处理数据
                1
           2        3
             \     /
              4   5
             /
            6
        根节点入栈->移到左孩子->判断左孩子是否为空->若不为空继续沿着这个方向
        ->否则退出循环->节点出栈,并且出栈的节点是root节点(对其子节点而言)

        1->2->因为左孩子为空,会执行if代码块中的内容,因此会出栈2,对2中的元素处理->
        调整方向为右->右会进入while循环->4->6->左孩子为空,出栈4,调整发现为右孩子->
        最外层循环不满足不为空,但是满足栈不为空,然后内层循环发现为空不执行,执行if代码块->
        继续出栈1(2已经出栈了)->......
     */
    public List<Integer> inOrderTreeByStack()
    {
        List<Integer> resultList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode tempNode = this.root;
        while (null != tempNode || !stack.isEmpty())
        {
            while (null != tempNode)
            {
                stack.push(tempNode);
                tempNode = tempNode.leftChild;
            }
            if (!stack.isEmpty())
            {
                tempNode = stack.pop();
                resultList.add(tempNode.data);
                tempNode=tempNode.rightChild;
            }
        }
        return resultList;
    }


    @Override
    public String toString()
    {

        return "BinaryTree{" +
                "root=" + root +
                '}';
    }


}
