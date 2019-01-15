package com.basic.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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

        System.out.println(this.index);
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


    // TODO
    // 非递归创建普通的树
    public void queueBuildTree(Integer[] arr)
    {
        int index = 0;
        Stack<TreeNode> stack = new Stack<>();






    }
    public void insertNode(Integer data)
    {}



    @Override
    public String toString()
    {

        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        Integer[] arr = {1, 3, 5, 10, -1, 23, 14};
//        tree.buildTree(arr);
        tree.queueBuildTree(arr);
        System.out.println(tree);
    }

}
