package com.test.tree;

import com.basic.tree.CompleteTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author joker
 * @When
 * @Description
 * @Detail
 * @date 创建时间：2019-01-16 09:37
 */
public class CompleteTreeTest
{

    /*
        the tree shall be like this:
                1
               / \
              2   3
             /\   /\
            4  5  6 7
           /\  /
          8 9 10

     */
    private Integer[] arr = new Integer[]{1,2,4,8,-1,-1,9,5,10,-1,-1,-1,3,6,-1,-1,7};


    @Test
    public void testIteratorByArrayAndBFS()
    {
        CompleteTree completeTree = new CompleteTree();
        completeTree.buildCompleteBinaryTree(arr);
        List<Integer> resultList = new ArrayList<Integer>();
        completeTree.inIteratorByArray(arr, 0, resultList);
        List<Integer> bfsResultList = completeTree.BFSTree();
        assert bfsResultList.equals(resultList);
    }

}
