说明
---
* [Github上更新的较这里勤](https://github.com/ItsFunny/data-structure-algorithm/tree/master/src/main/source)

* 会包括常见的数据结构,这里应该只会说明一个大概,具体的源码一步一步会单独的抽出一篇文章来写的,不过最近没啥时间,可能会慢点,`不过之前的时候整理过一部分`
* 现在还是浅层的,要深层的话必须详细的看源码一步一步来,但是现在时间不是很充裕,暂且如此


---

## HashMap源码剖析

-   HashMap源码
    -	hashMap实现了Map,Cloneable,Serializable接口,继承了抽象Map类
    -   hashMap线程不安全,允许key,value都为null,遍历的时候是无序的
        -   线程不安全体现于:
            -   `数据丢失`: 存在2个hashCode相等的对象(对象不同),同时定位到并且得知bucket的下标为空,并且都赋值,也就使得`后者的数据会覆盖前者的数据`,或者是next连接赋值的时候
            -   `死循环`:这个问题在8中已经被解决了,在7中因为会发生链表反转,因而会导致死循环,而再8中`通过2个链表,lo和hi将原先的链表分为2个部分,通过与原先的长度做&运算判断是否需要移动`
            -   如何得知线程不安全:通过`modCount`,hashMap有一个`fail-fast策略`,**在迭代的时候modCount会赋值给迭代器的exceptModCount中,迭代过程中modCount会变化,因而会导致不相同,因此就会抛出异常**
    -   底层解决hash冲突的方法是链地址法,既数组加链表的形式来解决hash冲突,当链表长度大于8的时候会把链表转成红黑树，链表长度低于6，会把红黑树转回链表
        -   hashMap负载因子的作用:
            -   负载因子的计算为: 扩容的阈值/容量
        -   减少hash冲突:hash冲突与两方面有关:hash算法和负载因子,原因在于,`在好的hash算法,当负载因子过小的时候也会导致大量的hash碰撞,此外,再大的负载因子当hash算法很差的时候也会导致大量冲突`
            -   采用不可变性作为key,如String,Integer;为什么? 因为这些键当key的值所确定的时候hashCode就不会发生变更了,而如果我们想用自定义的作为key的时候,复写hashCode方法必须选取变化少,并且独一无二的值
        -   8中的hash算法:`hashCode低16位与高16位做异或运算`,然后与`容量做与运算`即可获取下标
            -   `为什么低16位与高16位做异或`:这个问题衍生出如下子问题:
                -   为什么要移位:
                    -   弄清需要先明白:
                        -   ">>" 和">>>"的区别:前者是有符号的右移动,后者是无符号的右移动,既前者-3,2还是会为负数,正数,而后者则统一为正数
                        -   然后反证法,若没有移16位做与运算,则若有111....000000,和100111....0000 当直接与15做与运算的时候会发现index永远为0,所以我们肯定是需要移位的
                -   为什么是16位:
                    -   同样反证法,如果移动[1-16)位,我们可以发现高位上的特征会发现没有利用,试想一下就可以明白,移到8位高位上[23,31]下标的值还是处于高位,并没有利用
                    -   再反正法,如果移动[17,31)位,这个更加浅显,我们hashCode大部分值都丢失了(wtf),因而绝对不可取
                    -   所以,总结:移动16位的原因在于:`充分利用高16位的特征与低16位的特征,从而得到一个重复率小的值`
                -   为什么是异或
                    -   什么是异或: `异或是指`相同的为0,不同的则为1
                    -   为什么不用& 或者是| : 
                        -   & 只有都为1的时候才会为1,因而更加`靠近于0`
                        -   | 有一个1就是为1,更加靠近于`1`
                        -   `两者都过于有倾向性`,`而异或^使得高16位与低16位的特性充分发挥`
            -   `为什么用&替代%`:原因无它,%是除法运算,而除法运算是现代计算机很慢的
            -   `为什么HashMap的容量肯定是2的n次幂,为什么要-1`:
                -   这个问题可以这么理解:
                    -   2的n次幂有什么特定:
                        -   在计算机中,数据都是0,1;而当这个值都为1的时候,只要本身输入的hashCode合理,则很少冲突
                    -   2的n次幂能带来什么好处
                        -   二进制都为1并且通过与运算使得hashCode充分分散
                            -   具体的例子,容量为10: 
                                -   1010 & 101010100101001001000 结果：1000 = 8 
                                -   1010 & 101000101101001001001 结果：1000 = 8 
                        -   扩容resize的时候也是以2的n次幂为基础的,也就使得,原先的元素`要么在原位置,要么在移动2的n次幂处`
                            -   原理如图: a为扩容前,b为扩容后,扩容是扩大当前容量的2倍(既左移1位),则我们可以根据图发现,hash2扩容后只需要移动原先容量位置即可
                            -   ![](https://img-blog.csdnimg.cn/20190215195712222.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NvZGVyX0pva2Vy,size_16,color_FFFFFF,t_70)
                            -   具体示例图: ![](https://img-blog.csdnimg.cn/20190215195859591.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0NvZGVyX0pva2Vy,size_16,color_FFFFFF,t_70),**通过与原先的容量进行&运算判断是否==0,为0则不需要移动**
               -    总结: `2的n次幂,使得当做与运算的时候,2^n-1 在计算机中都是0,1;只要本身hashCode合理,就会分散均匀`,并且,`使得元素扩容的时候要么不移动,要么移动原先容量的长度即可`
    -   hashmap达到threshold的时候,会发生扩容,且扩容前后hash桶的长度肯定为2的次方,移动元素因为2^n的特性,要么不移动,要么移动一次(距离为之前容量)即可
    -   hashmap的hash函数是通过hashCode与hashCode的高16位做异或运算得到的
    -   hashmap内部充斥着位运算来提升效率
    
-   ConcurrentHashMap源码:
    -   chm是线程安全的类,不允许key,value为null
    -   sizeCtl是一个同步的共享变量,用于同步线程,如果这个值<0,表明正在被某个线程初始化或者扩容,则会通过thread.yield让出cpu时间,否则会自旋尝试设值,初始化的时候会进行double check 防止覆盖put线程的值
    -   查询操作与HashMap类似,都是先获取hash,然后与运算获取index,然后通过判断hashCode是否<0判断是否是红黑树,是则红黑树取值,否则链表遍历取值
    -   put操作:chm初始化是在put的时候才会初始化的,然后通过hash函数和size-1做与运算获取下标,判断下标对应的bucket是否为空,为空则直接通过cas设置值,否则会判断是否正在扩容,如果正在扩容,则会帮助扩容,否则锁住然后添加值,锁住的同时为了并发安全会双重校验,当然内部会检测是rbt还是链表,当插入完毕的时候会判断binCount是否插入成功,然后判断是否超过了阈值threshold,如果超过则会转化为rbt
    -   在1.7中是通过分段锁的形式,既内部是segment数组,每个segment都是一个单独的锁,在1.8则是采用cas+红黑树的形式,当然锁也是存在的,但是颗粒度还是比7小的
    -   `对比7的优化:`
        -   插入: 在7中插入是头插法,而在8中链表是尾插法
        -   扩容: 在7中是遍历旧的数组,然后遍历重新计算hash然后填入到新的拉链中,而在8中,则是通过lo和hi2个链表,lo代表原先的,hi中的索引为原先的index+原先的容量值,因为充斥着位运算,所以8中移动元素要么在原位置,要么仅需要移动2次幂(也就是原索引+原先容量的位置即可)即可,而如何判断是lo串还是hi串,则仅需要通过索引和原先容量做位运算判断即可,同时也因为如此避免了线程不安全的死循环问题
---

##  ThreadPool源码剖析
- 仅将现阶段的笔记先敲上,具体的源码后序再补
-   ThreadPool源码
    -   线程池的作用:
        -   降低性能消耗,提高响应速度:当应用需要频繁创建线程,而线程的创建是需要消耗额外的资源的
        -   线程集中管理,可以对线程池中的状态和数量进行集中管理
    -   参数
        -   `corePoolSize`: 核心线程池的数目,既一直存活着的线程数
        -   `maximunPoolSize`: 最大线程数目,当核心线程池满了,然后无法将任务添加到缓存队列的时候会将任务用额外的线程创建并执行
        -   `keepAliveTime`: 线程存活时间,这个参数只有当corePoolSize满的时候才会起作用
        -   `timeUnit`: 时间类别,与keepAliveTime一起构成线程存活时间的约束
        -   `workQueue`: 阻塞队列,有如下几种:
            -   `ArrayBlockingQueue` : 有界阻塞队列:
            -   `SynchronousuQueue`: 没有容量的阻塞队列,一次添加必须等待一次获取
            -   `LinkedBlockingQueue`: 无界阻塞队列
            -   关于LinkedBlockingQueue和ArrayBlockingQueue的区别:
                -   `底层结构不同`:LinkedBlockingQueue内部是链表,而ArrayBlockingQueue的内部是数组
                -   `锁不同`: linkedBlockingQueue读写是分开的2把锁putLock和takeLock,而arrayBlockingQueue是公用一把锁
                -   `初始化不同`:LinkedBlockingQueue初始化时不需要指定长度,默认是Integer.MAX_VALUE而ArrayBlockingQueue初始化的时候需要指定长度
        -   `threadFactory`: 线程工厂,用于统一创建具有某种特征的线程
        -   `rejectHandler`: 拒绝策略,有如下几种策略
            -   `AbortPolicy`: 直接抛出异常策略
            -   `CallerRunsPolicy`: 既这个线程直接运行这个任务(当然线程未处于shut down状态)
            -   `DiscardOldestPolicy`: 抛弃策略,抛弃任务等待队列中等待最久的那个任务(既末尾任务),然后将这个任务插入到队尾中
            -   `DiscardPolicy`: 抛弃策略,既什么都不管,直接丢弃
            -   
        -   一些重要的参数:
            -   `ctl`:控制着整个线程池的运行,利用高3位代表线程池的状态,低29位代表线程池的数目
                -   各个状态的含义:
                     ![](https://upload-images.jianshu.io/upload_images/10996982-7930025d00335c83.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)
                    -   running: 代表运行状态,是初始化的时候的状态
                    -   shutdown: running->调用shutdown->进入shutdown状态,此时线程池不接受新的任务,但是能处理已经添加的任务
                    -   stop: (running|shutdown)->调用shutdownNow()->进入stop状态,`不接受新的任务,并且也不再执行已经添加的任务,同时会中断正在执行的任务`
                    -   tidying:当线程池内线程数为0且队列中任务数为0时shutdown->tidying ; 当线程池内线程数为0时:stop->tidying
                    -   terminated: 当处于tidying 执行完termiated之后就会处于terminated状态
        - 线程池的执行流程:
        ![](https://upload-images.jianshu.io/upload_images/10996982-058bb93678e38f61.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)
        尝试创建核心线程->核心线程已满->添加任务到缓存队列中->如果缓存队列也满了->创建非核心线层->如果无法创建非核心线程->执行拒绝策略
     
---

## HashSet源码

-  HashSet源码:
    -   HashSet实现了set,cloneable,serializable接口,继承了AbstractSet抽象类
    -   hashSet的本质其实还是HashMap,内部的是通过HashMap+ static final类型的object来实现的,这个object对象是充当为value,其他充当为key,这样通过hashMap就能判断之前是否存在值了
    
---

##  TreeMap源码

-   TreeMap源码
    -   有一个Comparator成员变量,每当插入一个值的时候都会通过这个去进行匹配
    -   TreeMap的底层是完全二叉树,当插入值的时候除了常规的校验,就是正常的二叉树的遍历匹配,然后插入


---

## TreeSet源码

-  TreeSet源码
    -   TreeSet的底层与HashSet类似,但是不同在于TreeSet的map选型是TreeMap,TreeMap的底层是完全二叉树

---

## ArrayList和LinkdList的区别及其源码

-   ArrayList和LinkedList的区别及其源码
    -   ArrayList的底层是数组,而LinkedList的底层是链表
    -   ArrayList默认的数组长度为10,每次插入之前都会先判断是否需要扩容,当扩容时候调用的函数是System.arrayCopyOf
    -   ArrayList适合于读多,插入删除少的场景,因为插入删除很可能会导致数组的copy,而linkedList非常适合于插入或者删除

---



## 线程安全的集合:

-   CopyOnWriteArrayList: 适用于读多写少的场景,`底层是数组`,读的时候不会加锁,写的时候会加全局锁
-   CopyOnWriteArraySet: `底层是CopyOnWriteArrayList`,如何去重呢?插入之前会先通过indexOf判断值是否存在,当真正插入加锁的时候,又会判断是否存在值(既双重检测)

    
---


## volatile的作用及其原理
-   作用:
    -   `内存可见性`: 线程共享进程的内存,每个线程都会拷贝一份内存,但是当被volatile修饰的时候并不会拷贝,因而每次都是从主内存中读取或者写入
    -   `防止重排序`:Java的`haapens-before`原则,既既如锁的unlock必定在lock之后,所以volaitile的写必定是在读之前(每次被线程访问的时候都是从主内存中读取值,当值变化时又会强制刷新到主内存中,这样每个线程获取到的都是最新的值)
    -   `原子性`: 每个操作都是原子性的
    

## Synchronized和lock的区别:
-   Synchronized的是虚拟机层面的而lock是代码层面的
-   `锁的释放`: synchronized会自动释放,而lock需要我们手动释放
-   `锁的获取`: synchronized只能硬性获取,而lock可以尝试获取或者等待获取一段时间
-   `锁的状态`: synchronized无法判断锁的状态,而lock是可以判断的
-   `锁的类别`: synchronized的锁可重入,不可中断,非公平,而lock可冲入,可判断,可中断,并且可公平和非公平(`lock默认是非公平锁`)


##   Synchronized原理: 
*   `mark world是虚拟机中对象对象头的信息` 
-   对于同步代码块而言:synchronized是基于jvm的,为什么说基于jvm的呢,因为当加载到内存中的时候,`每个对象都会有一个monitor对象,而每次试图获取锁都会先判断内部的objdef的monitor是否为0,为0的话可以获取monitor`,然后退出的时候-1,moniterexit并且如果当异常发生的时候也会调用moniterexit,并且可重入,重入的时候会+1
-   对于synchronized方法而言:`不是通过字节码实现同步的,而是通过方法常量池中的方法表结构中的一个标志ACC_SYNCHRONIZED`来判断是否是同步方法,如果设置了则执行线程先获取,然后释放monitor
* synchronized中相关的锁的结构:
* 锁一般分为
    -   `乐观锁`: 顾名思义,撤销都是结束的时候判断,并且一般都是通过cas来实现的(`偏向锁,自旋锁以及轻量锁都是乐观锁`)
    -   `悲观锁`: 既先执行加锁(`重量级锁是悲观锁`)
    -   `偏向锁`:是针对加锁操作的优化,因为大多数情况下都是同一个线程获取到了锁,所以引入偏向锁的概念,在这种锁的情况下,Mark Word的结构也会变成偏向锁结构,当这个线程再次获取的时候就不会做任何同步的优化效果 **(先通过判断对象的偏向状态,如果有则表明存在竞争,如果原先的已经挂了,则变为无锁状态,然后重新偏向,若原先的没挂,则先检测原先的是否还需要用,要用则升级会轻量级锁,否则将对象回复称无锁,然后重新偏向)**
    -   `轻量级锁`: 偏向锁代表只有一个线程竞争,而再来一个任务之后偏向锁就会升级为轻量锁,当偏向锁升级到轻量级锁的时候,Mark World的结构也会变成轻量锁的结构,
适合于**线程交替执行的场景** (**判断这个对象是否被持有(因为会传递线程的地址),如果被持有则会自旋,当超过一定次数,或者又有新的来参与竞争则会升级为重量级锁**)
    -   `自旋锁`: 既while死循环一段时间,**适用于那些锁时间非常短的代码**,避免内核态到用户态的消耗
    -   `锁使用情景的总结:`
        -   偏向锁: 只有一个线程进入临界区
        -   轻量级锁: 多个线程**交替**进入临界区
        -   重量级锁: 多个线程同时进入临界区
-   `synchronized中获取锁的具体流程总结`:
    1. 检测MarkWord里面是不是当前线程的ID，如果是，表示当前线程处于偏向锁 
    2. 如果不是，则使用CAS将当前线程的ID替换Mard Word，如果成功则表示当前线程获得偏向锁，置偏向标志位1 
    3. 如果失败，则说明发生竞争，撤销偏向锁，进而升级为轻量级锁。 
    4. 当前线程使用CAS将对象头的Mark Word替换为锁记录指针，如果成功，当前线程获得锁 
    5. 如果失败，表示其他线程竞争锁，当前线程便尝试使用自旋来获取锁。 
    6. 如果自旋成功则依然处于轻量级状态。 
    7. 如果自旋失败，则升级为重量级锁
-   `代码中的锁优化`: 
    -   减少锁的时间: 既synchronized包裹的代码块越少越好
    -   减小锁的粒度:思想是将物理git上的锁拆分成多个锁,如1.7中的chm,每个segment都是一个锁
    -   使用cas+volatile: **如果同步的操作非常快,时间短暂(既上下文切换的时间比逻辑时间要长),并且线程竞争并不激烈**


##   ReentrantLock的原理:(待补)

-   ReentrantLock的实现是通过将任务委托给Sync,而Sync继承了aqs,aqs内部通过state变量来控制同步状态,当s'tate=0时代表没有任何线程占有锁,而当为1的时候线程会进入同步队列中,通过Node实现线程的FIFO,又会通过ConditionObject构建等待队列,当Condition调用wait的时候会进入等待队列而signal则会进入同步队列竞争锁