# TODO-LIST

## [GitHub更新的较CSDN勤](https://github.com/ItsFunny/data-structure-algorithm)


---
囊括常见的排序算法,数据结构的实现,具体的代码会有Go也会有Java,至于
模块的链接应该最近是都没空了

---


排序算法
- 稳定与不稳定的意思: 稳定算法与不稳定算法并不是指复杂度是否稳定,而是指前后的位置是否发生了变化,如A原先在B的前面,
当我们经过一系列操作之后A到了B后面,则这个算法就是不稳定的
- 不稳定算法: 快希选堆

- 可能会搞混的记忆点:
    -   插入排序: 有序中插入   (插入既插队)
    -   选择排序: 遍历使得顺序存储(从头到尾暴力匹配)

- 


| 排序方法| 时间复杂度|空间复杂度|
| -------|--------|--------|
| 插入排序(稳定)  | O(n^2)      |  O(1)     |
| 冒泡排序(稳定) | O(n^2)      |   O(1)     |
| 归并排序(稳定)  | O(nlogn)   |  O(n+logn) |
| 快速排序(不稳定)|             |            |
| 希尔排序(不稳定)|O(n^2)      |   O(1)      |
| 选择排序(不稳定)  |O(n^2)      |  O(1)      |
| 堆排序(不稳定)  | O(nlogn)   |  O(1)      |

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
   - `FINISH`**冒泡排序(稳定)**
        -   时间复杂度: O(n^2) 2层for循环
                        一个O(n)用于遍历查找,一个用于匹配
        -   空间复杂度: O(1) 只在原先的数组中操作
        -   核心要点: 
    ```
     就是2层for循环,对前后进行比较
     public static void popSort(Integer[] arr)
     {
         //  冒泡排序就是暴力遍历比较
         //  如果后者小于则直接进行更换即可
         for (int i = 0; i < arr.length; i++)
         {
             for (int j = i + 1; j < arr.length; j++)
             {
                 if (arr[j] < arr[i])
                 {
                     int temp = arr[i];
                     arr[i] = arr[j];
                     arr[j] = temp;
                 }
    
             }
         }
     }
    ```
     
   - `FINISH`**归并排序(稳定)**
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
    - `WIP` **快速排序(不稳定)**
        -   `WIP` 普通快速排序
        -   `WIP` 变更版快速排序(三值排序)
              
    - `FINISH`**希尔排序:不稳定**
    **在原先简单排序的基础,对原先数组通过stride步长分成多块,对每块做简单插入**
        -   时间复杂度: O(n^2) 
    ```
        // 希尔排序是直接插入算法的优化:
        // 将一个数组分成多块,对每块进行插入排序
        // 直接插入排序其实就是步长为1的希尔排序
    ```
    - `FINISH`**简单选择排序(不稳定)**
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
    
    - `FINISH`**选择排序(不稳定)**:每个数都与剩下的所有数比较大小,
    从而选取出最大或者最小的值
        - 时间复杂度: O(n^2),一层O(n)用于起始遍历,另外一层O(n)
        用于遍历剩下的所有数来比较大小
        - 空间复杂度:O(1),不需要额外申请空间
        - 核心实现:核心就是一个数与剩下的所有数比较大小
        ```
        public static void simpleSelectionSort(Integer[] arr)
        {
            // 简单选择排序的核心就是0号放的是最小的元素,和1号放的是次小的元素,意味着需要暴力遍历
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
           
    - `FINISH`**堆排序(不稳定算法)**
        -   时间复杂度: **O(nlogn)** ,i层有2^(i-1)个节点,而因为每次都要
        进行比较,更换之后子树也要比较,所以时间为:2^(i-1)*(k-i)
        i代表第几层,k代表高度(既这层节点需要比较的次数),提取常量则时间复杂度为
        O(n); 然后重新建堆,重新建堆的方式是:从尾到头,循环n-1次(>0即可),每次基于
        二叉树的特性为logn,所以为nlogn-logn ,所以总共为O(nlogn)
        -   空间复杂度: O(1),不需要额外的申请空间
        -   核心要点: 建堆->排序(再建堆的过程),并且都是从后往前,建堆是中间开始,排序是与0号元素交换位置再建堆,并且建堆过程中当节点更换之后还得将子节点也重新建堆,左孩子下标为2**index+1 ,右孩子下标为2**index+2
        -   实现: 
        
        ```
        // 堆排序:
        // 分为2个步骤: 建堆,排序 (其实排序就是重新建堆的过程,因为构建的是堆,只需要将首位移到最后,剩余的继续建堆即可)
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
        
        ```
        
        - `FINISH`  非递归实现:
        
        ```
        // 非递归排序的思路与递归排序的思路是一样的;
        // 选取左右孩子的最大值,然后对交换位置的孩子作为根节点的树继续调整树
        public void buildHeapWithOutRecursion(Integer[] arr, Integer rootIndex, Integer limitIndex)
        {
            Integer temp=arr[rootIndex];
            for(Integer i=(rootIndex<<1)+1;i<limitIndex;i=(rootIndex<<1)+1)
            {
                // 右孩子就是+1所处的位置
                // 选取左右孩子的最大值与根节点进行比较
                if (i+1<=limitIndex&&arr[i+1]>arr[i])
                {
                    i++;
                }
                // 这里可能会有疑问,为什么是不变的temp值去遍历比较底下的叶子节点的值:
                // 因为当我们不满足条件的时候(既根节点与孩子节点更换之后),此时孩子节点的root值就是temp了
                //
                //     5                    7
                //   3   7   -->         3      5    对   5  进行重新建树
                //  2 1 0 6           2    1  0  6       0 6
                //
                if (temp>=arr[i])
                {
                    break;
                }
                // 否则就是孩子节点的值更加大,则需要更换位置,将孩子节点提到root节点上,
                // 然后对子节点所处的进行再建树(rootIndex=i 就使得这个孩子节点变成了根节点),对应上面的就是递归
                arr[rootIndex]=arr[i];
                rootIndex=i;
            }
            // 上面是直接复制的,最先的rootIndex节点的值就丢失了,因而我们需要将其补回,这里其实有点像插入排序
            arr[rootIndex]=temp;
        }    
        ```
        
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
    
    -   `FINISH`   满二叉树
        * 定义: 满二叉树是指除了最后一层外,每个节点都有2个孩子
        * 特性:
            -   层数为k,一定有2^k -1个节点
            -   第i层的节点数为2^(i-1) 
            
    -   `FINISH`   完全二叉树   
        * 定义: 是一种效率很高的数据结构,除了最后一层外,其余各层
        的节点数都达到了最大值2,并且最后一层的节点都在左边,当深度为k
        的树,只有当与满二叉树的节点顺序一致的才为完全二叉树
        * 特性: 
            -   高度差满足:[0,1] (因为顺序是按照满二叉树的顺序走的)
            -   当n个节点时:
                -   下标为i的左儿子下标为:`2*i` (当然范围要<n)
                ,右孩子的下标为2*i+1   既:左0右1,**因此构建树需要通过节点下标来构建**
                同时我们可以发现 **只有[0,n/2]有孩子节点**
        -   `FINISH`   完全二叉树的另外一种遍历方式:基于节点的特性,可直接避免构造树而通过对数组进行遍历(写先序和bfs)
        
        **注意点也是相同的,就是基于数组的特殊性,从1开始(从0开始会栈溢出),真正操作要减去1**
                 
        ```
            public void inIteratorByArray(Integer[] arr, Integer index, List<Integer> resultList)
            {
                if (index <= arr.length)
                {
                    // ROOT
                    resultList.add(arr[index - 1]);
                    // LEFT
                    this.inIteratorByArray(arr, 2 * index, resultList);
                    // RIGHT
                    this.inIteratorByArray(arr, 2 * index + 1, resultList);
                }
            }
        ```
        -   `FINISH`   完全二叉树的构建
        
        ```
          // 构建一颗完全二叉树
          // 构件完全二叉树的时候我们需要判断,左孩子节点 2*i+1 是否超过长度  <length,右孩子是否超过长度:2*i+2<length
          // 同时,当左孩子不存在的时候,右孩子就没必要判断了
          public void buildCompleteBinaryTree(Integer[] arr)
          {
      
              List<TreeNode> nodeList = new ArrayList<>();
              this.root = new TreeNode(arr[0]);
              nodeList.add(this.root);
              for (int i = 1; i < arr.length; i++)
              {
                  nodeList.add(new TreeNode(arr[i]));
              }
              Integer length = arr.length >> 1;
              for (int i = 0; i <= length; i++)
              {
                  if (i * 2 + 1 < arr.length)
                  {
                     nodeList.get(i).setLeftChild(nodeList.get(i * 2 + 1));
                     if (i * 2 + 2 < arr.length)
                     {
                         nodeList.get(i).setRightChild(nodeList.get(i * 2 + 2));
                     }
                   }
         
              }
          }
          
        ```
        
    	-	`FINISH`	判断是否是完全二叉树
    	    -   思路: 完全二叉树根据定义来判断即可 
    	        -   如果有右孩子,却没左孩子肯定不是(左:2*i+1,右:2*i+2)
    	        -   如果该节点不存在右孩子,则遍历该层,判断该层的后继节点是否是叶子都
    	        是节点,如果有一个不是,则不是完全二叉树(叶子节点都在左边的特性)
    	        
    	```
    	// 采取标志位的方式:
        // 如果一棵树只有左节点,则标志位为true,代表不完整
        // 若后续的树不完整(意味着left+right|left|right)则不是完全二叉树,既一旦有节点就不是完全二叉树
        // 非二叉树的条件:
        //  1. 当存在右孩子,左孩子却为空
        //  2. 当存在左孩子,右孩子为空,而同层的后续节点有孩子(既可以认为是之前的节点是不完整的)
        // 注意点的话只需要注意下退出条件以及元素入队的条件即可
        public boolean validIfCompleteTree()
        {
            if (this.root == null)
            {
                return false;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            boolean previousCompleted = true;
            queue.add(this.root);
            for (TreeNode temp = queue.pop(); temp != null; temp = queue.pop())
            {
                // 1.对第一种条件的判断 和对第二种条件的判断
                if (temp.leftChild == null && temp.rightChild != null ||
                        !previousCompleted && (temp.leftChild != null || temp.rightChild != null))
                {
                    return false;
                } else
                {
    
                    // left=null &right=null
                    // left!=null & right=null || left!=null & right!=null
                    // previousCompleted
                    if (temp.leftChild != null)
                    {
                        queue.add(temp.leftChild);
                    }
                    if (temp.rightChild != null)
                    {
                        previousCompleted = true;
                        queue.add(temp.rightChild);
                    } else
                    {
                        previousCompleted = false;
                    }
                }
                if (queue.isEmpty())
                {
                    break;
                }
            }
            return true;
        }
    	```     
   
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
        
        










