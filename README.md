# TODO

标签（空格分隔）： TODO

---

- `WIP` 8中排序算法的实现       **升序的时候要假设比较的值都是大于它的**

    -   **直接插入排序 (稳定)** **:将一个数据插入到已经排序的数组中(分为无序区和有序区)**
        -   时间复杂度: O(n^2) 因为有2层for循环:                                                   一个O(n)找元素,一个O(n)找位置
        -   空间复杂度: O(1) 因为是在原先的数组上操作的  
        -   核心要点:   从下标处往前进行遍历
    ```
        插入排序的核心就是假设要插入的元素的下标index 0-index 都是有序的(升序)
         所以需要从后往前判断,因为是升序,所以要找到的是坐标是小于他的,而右边是大于他的,因而判断条件是大于
         如果条件成立需要将这个值往后移动(不用担心插入的值,因为开始就会保存)
         当内层循环跳出的时候也就意味着,下标所在的值是小于temp值的,而我们需要在这index+1处插入
         因为之前的数都已经往后移动了
    ```
   -    **冒泡排序(稳定)**
        -   时间复杂度: O(n^2) 2层for循环
                        一个O(n)用于遍历查找,一个用于匹配
        -   空间复杂度: O(1) 只在原先的数组中操作
        -   核心要点: 
        ```
         就是2层for循环,对前后进行比较
        ```
              
    -  希尔排序: **稳定**
    **在原先简单排序的基础,对原先数组通过stride步长分成多块,对每块做简单插入**
        -   时间复杂度: O(n^2) 
    ```
        // 希尔排序是直接插入算法的优化:
        // 将一个数组分成多块,对每块进行插入排序
        // 直接插入排序其实就是步长为1的希尔排序
    ```
    -   **简单选择排序(稳定)**
        -   时间复杂度: O(n^2)  2层for循环,                                                         1层用于下标遍历,1层用于判断匹配
        -   空间复杂度: O(1)    没有申请新的空间
        -   核心思路:   既数组下标与元素是强匹配的:
                        0号存放最小,1号存放次小的,所以需要遍历匹配的
    ```
    简单选择排序就是暴力遍历: 2层for循环 将最小的放在0号,次小的放在1号,也就是说需要从0到length进行遍历
    public static void simpleSelectionSort(Integer[] arr)
    {
        // 简单选择排序的核心就是0号放的是最小的元素,和1号放的是次小             的元素,意味着需要暴力遍历
        for (int i = 0; i < arr.length; i++)
        {

            int min = arr[i];
            int pos = i;

            for (int j = i + 1; j < arr.length; j++)
            {

                if (arr[j] < min)
                {
                    min = arr[j];   //将最小的这个给min
                    pos = j;        // 记录最小的下标,方便更换
                }

            }
            arr[pos] = arr[i];
            arr[i] = min;

        }

    }
    ```
    
    
    -   **归并排序(稳定)**
        ![分割图](https://images2015.cnblogs.com/blog/1024555/201612/1024555-20161218163120151-452283750.png)
        -   时间复杂度: O(nlogn) O(n)为需要将待排序的序列都扫描一遍
                        而归并中的分可以认为将数组分成了完全二叉树,
                        所以深度可知为:O(logn)
        -   空间复杂度: O(n+logn) 因为需要相同的额外长度的数组,所以
                        为O(n),而又因为二叉树的性质,所以而O(logn)
        -   核心要点:   归并是指将一个数组分成若干个小的数组,对每个
                        小的数组进行排序,最后统一排序; 记得分的时候
                        退出的条件(下标一致)
        ```
             public static void mergeSort(Integer[] arr)
    {
        // 归并算法分为2个步骤: 分治法  分 + 治

        Integer[] tempArr = new Integer[arr.length];

        mergeSort(arr, 0, arr.length - 1, tempArr);

    }
    // 分代表着将数组分为直至相邻的若干个小数组 ,直至分到了最小情况(既同一下标了),所以最小的数组的长度为2[5,6]
    // 为什么这样子就可以了呢,答案在于merge中,merge会遍历数组,判断大小,按顺序写入到临时队列中

    public static void mergeSort(Integer[] arr, int left, int right, Integer[] temp)
    {
        if (left < right)
        {
            int mid = (left + right) >> 1;
            // 对左边进行分
            mergeSort(arr, left, mid, temp);
            // 对右边进行分
            mergeSort(arr, mid + 1, right, temp);
            // 对左右进行治
            merge(arr, left, mid, right, temp);
        }
    }

    // 治的逻辑::
    // 对左边,右边进行遍历,同时会进行判断,选取小的值放入到临时数组中
    // 之后再进行遍历,此时只会遍历一边
    // 最后则是将临时数组中的元素复制到元数组中
    // 关键点在于: 要建立临时的变量,代替下标去移动
    public static void merge(Integer[] arr, int left, int mid, int right, Integer[] temp)
    {
        int i = left;
        int j = mid+1;
        int k = 0;
        // 进行归并
        while (i <= mid && j <= right)
        {
            if (arr[i] < arr[j])
            {
                temp[k++] = arr[i++];
            } else
            {
                temp[k++] = arr[j++];
            }

        }
        // 对数组中剩余的进行复制
        while (i <=mid)
        {
            temp[k++] = arr[i++];
        }
        while (j <=right)
        {
            temp[k++] = arr[j++];
        }
        k = 0;

        while (left <= right)
        {

            arr[left++] = temp[k++];
        }
    }
        ```
    
    -   `WIP`堆排序
    
    
    -   `WIP`快速排序
    
    -   `WIP`快速排序的变更
    
 
    
    -   `WIP`基数排序


- `WIP` 链表
    -   `WIP` 单链表
    -   `WIP` 单向循环链表
    -   `WIP` 双链表






- `WIP` TOK 解决方案:
    -   `WIP`   全部排序
    -   `WIP`   局部淘汰法
    -   `WIP`   分治法
    -   `WIP`   hash法




- `WIP` 线程池
    


-   `WIP`   queue
    -   `WIP`   stack实现queue
    
-   `WIP`   stack
    -   `WIP`   queue实现stack







-   `WIP`   查找算法
    -   s 




-   `WIP` 树
    -   `WIP`   二叉树的创建
        -   `WIP`   先序创建
        -   `WIP`   中序创建
        -   `WIP`   后序创建
    -   `WIP`   满二叉树
    -   `WIP`   完全二叉树
    	-	`WIP`	判断是否是完全二叉树
    -	`WIP`	二叉查找树
    -   `WIP`   树的遍历
        -   `WIP`   递归先序
        -   `WIP`   递归中序
        -   `WIP`   递归后序
        -   `WIP`   非递归先序
        -   `WIP`   非递归中序
        -   `WIP`   非递归后序
        -   `WIP`   BFS
        -   `WIP`   DFS
    -   `WIP`   B tree Java 实现
    -   `WIP`   B- tree Java实现





