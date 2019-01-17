package com.basic.sort;

import lombok.Data;

/**
 * @author joker
 * @When
 * @Description 堆排序
 * @Detail
 * @date 创建时间：2019-01-17 22:01
 */

/*
    总结:
    注意点: 1.无论是建堆还是排序,都是从大的往前推,因而是i-- ,而不是i++
           2.

 */
public class HeapSort
{

    public static void main(String[] args)
    {

    }

    // 堆排序:
    // 分为三个步骤: 建堆,排序 (其实排序就是重新建堆的过程,因为构建的是堆,只需要将首位移到最后,剩余的继续建堆即可)
    // 堆排序是建立在完全二叉树上的,堆又分为最大堆和最小堆,如果我们想升序的话则需要构建的是最大堆,并且最大值在第一位
    public void heapSort(Integer[] arr)
    {
        // 建堆
        for (int i = (arr.length >> 1) - 1; i >= 0; i--)
        {
            // 为什么我们要从父节点的上限开始,原因在于当我们从后往前的时候,子节点所处的树较父节点肯定是小的
            // 也就可以省去很多无用的操作 ,想象一下就行,当根节点与某个子节点发生了变化之后,子节点需要重新排序
            // 这时候的树肯定是较当这个节点为父节点时候的子节点的树要大的
            buildHeap(arr, i, arr.length - 1);
        }
        // 当最大堆构建完毕之后,我们只需要不断的将最大的移动到最后然后重新建堆即可
        // 并且因为最大的一直都是在0位,所以我们只需要从后往前更换元素即可
        for (int i = arr.length - 1; i >= 0; i--)
        {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            sort(arr, 0, i);
        }

    }

    // 建堆有2种方式,第一种是通过递归建堆的方式,如下:
    public void buildHeap(Integer[] arr, Integer index, Integer limit)
    {
        // leftChild index
        Integer leftChildIndex = (index << 1) + 1;
        // rightChind index
        Integer rightChindIndex = (index << 1) + 2;
        Integer maxIndex = index;
        if (leftChildIndex < limit && arr[leftChildIndex] > arr[maxIndex])
        {
            maxIndex = leftChildIndex;
        }
        if (rightChindIndex < limit && arr[rightChindIndex] > arr[maxIndex])
        {
            maxIndex = rightChindIndex;
        }
        // 如果根节点不是最小值则交换位置
        if (maxIndex == index)
        {
            return;
        }
        Integer temp = arr[maxIndex];
        arr[maxIndex] = arr[index];
        arr[index] = temp;
        // 因为我们是对整个堆进行排序,所以当更换了值之后,所在的树也基本上变了,所以我们需要重新建堆
        // 既子节点的树很可能是发生了变化
        // 这里就是可以优化的地方,既然变更的只是子节点,大可抽出成为一个for循环实现
        buildHeap(arr, maxIndex, limit);
    }


    public void sort(Integer[] arr, Integer index, Integer limit)
    {
        buildHeap(arr, index, limit);
    }


}
