package com.basic.tree;

import java.util.*;

/**
 * @author joker
 * @When
 * @Description 普通二叉树的创建, 分为递归方式创建和非递归方式创建, 为了方便, 这里的数据都采用int
 * @Detail
 * @date 创建时间：2019-01-14 20:37
 */
public class BinaryTree
{

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

        public TreeNode getLeftChild()
        {
            return leftChild;
        }

        public TreeNode getRightChild()
        {
            return rightChild;
        }

        private TreeNode leftChild;

        private TreeNode rightChild;

        public void setData(Integer data)
        {
            this.data = data;
        }

        public void setLeftChild(TreeNode leftChild)
        {
            this.leftChild = leftChild;
        }

        public void setRightChild(TreeNode rightChild)
        {
            this.rightChild = rightChild;
        }
    }


    private TreeNode root;

    private int index;


    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }


    public TreeNode getRoot()
    {
        return root;
    }


    public void setRoot(TreeNode root)
    {
        this.root = root;
    }

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


    @Override
    public String toString()
    {

        return "BinaryTree{" +
                "root=" + root +
                '}';
    }


}
