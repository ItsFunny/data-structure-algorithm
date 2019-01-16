# TODO-LIST

## [GitHub更新的较CSDN勤](https://github.com/ItsFunny/data-structure-algorithm)


---
囊括常见的排序算法,数据结构的实现,具体的代码会有Go也会有Java,至于
模块的链接应该最近是都没空了

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
              
    -  **希尔排序: 稳定**
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
    -   `WIP` LinkedList的实现
    -   `WIP` CopyOnWriteArrayList的实现
    
- `WIP` hash
    -   `WIP`   HashSet的实现
    -   `WIP`   HashMap的实现

- `WIP` TOK 解决方案:
    -   `WIP`   全部排序
    -   `WIP`   局部淘汰法
    -   `WIP`   分治法
    -   `WIP`   hash法

- `WIP` 锁
    -   `WIP` 死锁的实现
    -   'WIP' 生产者消费者的实现
        -   `WIP`   concurrent组件实现
        -   `WIP`   原生的wait/notify/notifyall实现
        -   `WIP`   lock/condition实现
    -   `WIP`   读写锁的实现
    

- `WIP` 线程池
    


-   `WIP`   queue
    -   `WIP`   原生queue的实现
    -   `WIP`   stack实现queue
    
-   `WIP`   stack
    -   `WIP`   原生stack的实现
    -   `WIP`   queue实现stack







-   `WIP`   查找算法
    -   s 




-   `WIP` 树
    -   `WIP`   二叉树的创建
        -   `FINISH`   递归先序创建  
        -   `WIP`   递归先序无返回值创建
        -   `FINISH`   非递归先序创建
        ```
        // 非递归创建普通二叉树,因为是给定的数组,所以以-1代表空,流程逻辑如下
        // 核心就是二叉树节点满的时候只有2个节点
        //  判断data==-1   true?说明要么插入右节点,要么是左右节点都没有,如果是插入右节点,则新node入队之后还需要修改方向为左
        //				   false:判断插入的是左节点还是右节点,如果插入的是右节点,则需要修改方向为左(因为二叉树只有2个节点,
        // 						  并且是先序创建:根左右) ,最后元素入队(因为可能后面的值不是-1,是要继续插入的)
        //	ps: 在流程中并不需要对stack进行判空,因为开始之前先将根节点入队了,已经确保了不会为空
        //  ps: 同时,其实二叉树是当遇到连续2个-1的时候会pop,那么n叉树则可以认为是遇到n个-1时是会出队的
        //  ps : 因此,当n叉树时,判断的情况为: if counts!=n {} else{}
        //  总结: 出队的情况: n叉树遇到n个连续的-1(子节点出栈,跳回父节点) | n叉树的节点满了(父节点出栈,子节点开始接收值)
        //       与递归创建不同,栈实现只需要一个for循环即可,也**不需要自己内部++操作**
        func (t *BinaryTree)BuildTreeWithStack(arr []int){
        	if len(arr)==0{
        		return
        	}
        	t.root=&TreeNode{data:arr[0]}
        	left:=true
        	stack := arraystack.New()
        	stack.Push(t.root)
        	for i:=1;i< len(arr);i++{
        		if arr[i]==-1{
        			if left{
        				left=false
        			}else if !stack.Empty() {
        				stack.Pop()
        			}
        		}else{
        			node:=&TreeNode{data:arr[i]}
        				temp, _ := stack.Peek()
        				if left{
        					temp.(*TreeNode).leftChild=node
        				}else{
        					temp.(*TreeNode).rightChild=node
        					left=true
        					stack.Pop()
        				}
        				stack.Push(node)
        		}
        	}
        }
        ```
    -   `WIP`   二叉树的CRUD
    
    TODO 2019-0116,以及分清意思
    
    -   `WIP`   满二叉树
        * 定义: 满二叉树是指除了最后一层外,每个节点都有2个孩子
        * 特性:
            -   层数为k,一定有2^k -1个节点
            -   第i层的节点数为2^(i-1) 
            
    -   `WIP`   完全二叉树   
        * 定义: 是一种效率很高的数据结构,除了最后一层外,其余各层
        的节点数都达到了最大值2,并且最后一层的节点都在左边,当深度为k
        的树,只有当与满二叉树的节点顺序一致的才为完全二叉树
        * 特性: 
            -   高度差为[0,1]
            -   当n个节点时:
                -   下标为i的左儿子下标为:`2*i` (当然范围要<n)
                ,右孩子的下标为2*i+1   既:左0右1,**因此构建树需要通过节点下标来构建**
                同时我们可以发现 **只有[0,n/2]有孩子节点**
        -   `WIP`   完全二叉树的另外一种遍历方式:基于节点的特性,可直接避免构造树而通过对数组进行遍历(写先序和bfs)
        -   `WIP`   完全二叉树的构建
    	-	`WIP`	判断是否是完全二叉树
    	    -   思路: 完全二叉树根据定义来判断即可 
    	        -   如果有右孩子,却没左孩子肯定不是(左:2*i,右:2*i+1)
    	        -   如果该节点不存在右孩子,则遍历该层,判断该层的后继节点是否是叶子都
    	        是节点,如果有一个不是,则不是完全二叉树(叶子节点都在左边的特性)
   
    -	`WIP`	二叉查找树
        * 定义: 左子树的值一定小于根节点的值,右子树的值一定大于等于根节点的值
   
    -   `WIP`   平衡二叉树
        * 定义: 平衡二叉树是二叉查找树的延伸,因为二叉查找树存在极端情况:如形成的是一个链表
               而平衡二叉树使得高度差最大为1
               
    -   `WIP`   B tree Java 实现
    
    -   `WIP`   B- tree Java实现
    
    -   `WIP`   树的遍历
     
        **关于树的遍历,当涉及递归的时候,内部只需要两个函数,
        如先序:只需要入队node.Left和node.Right即可,不要入node,死循环了,
        至于关于打印的先后顺序,只需要记得,参数中的node都是根节点即可**
        -   `FINISH`   递归先序
        -   `FINISH`   递归中序
        -   `FINISH`   递归后序
        -   `WIP`   非递归先序
        -   `WIP`   非递归中序
        -   `WIP`   非递归后序
        -   `FINISH`   BFS
        
        ```
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
        ```
        
        -   `FINISH`   DFS
        ```
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
        ```
        
        










