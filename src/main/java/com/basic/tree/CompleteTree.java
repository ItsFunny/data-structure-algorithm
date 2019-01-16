package com.basic.tree;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author joker
 * @When
 * @Description 完全二叉树
 * @Detail
 * @date 创建时间：2019-01-16 08:57
 */
@Data
public class CompleteTree
{

    private TreeNode root;

    @Data
    private class TreeNode
    {
        private Integer data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(Integer data)
        {
            this.data = data;
        }
    }


    // 构建一颗完全二叉树
    public void buildCompleteBinaryTree(Integer[] arr)
    {

        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        for (Integer integer : arr)
        {
            nodeList.add(new TreeNode(integer));
        }
        Integer length = arr.length << 1;
        for (int i = 0; i < length; i++)
        {
            if (i * 2 < length)
            {
                nodeList.get(i).setLeftChild(nodeList.get(i * 2));
            }
            if (i * 2 + 1 < length)
            {
                nodeList.get(i).setRightChild(nodeList.get(i * 2 + 1));
            }
        }
    }


    public void inIteratorByArray(Integer[] arr, Integer index, List<Integer> resultList)
    {
        if (index < arr.length)
        {
            // ROOT
            resultList.add(index);
            // LEFT
            this.inIteratorByArray(arr, 2 * index, resultList);
            // RIGHT
            this.inIteratorByArray(arr, 2 * index + 1, resultList);
        }
    }

    public List<Integer> BFSTree()
    {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> resultList = new ArrayList<Integer>();
        queue.add(this.root);
        TreeNode temp = null;
        for (temp = queue.pop(); temp != null; temp = queue.pop())
        {
            resultList.add(temp.data);
            if (null != temp.leftChild)
            {
                queue.add(temp.leftChild);
            }
            if (null != temp.rightChild)
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

}
