package com.basic.algorithm.timewheel.jianzhioffer;

/**
 * @author joker
 * @When
 * @Description 在旋转数组中的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，
 * 该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * @Detail 这道题目刚开始没看懂, 以为直接排序取最小的就OK, 但是肯定没有辣么easy
 * 的确是我想的太简单了,其实这道题的前提条件是已经排序了的(排序之后再旋转,所以子数组还是排序了的)
 * 所以有以下解题方法:
 * 1 .暴力遍历,暴力遍历的方法获取最小值
 * 2 .依旧是暴力遍历,但是不同的地方在于,当发现下一个下标处(index+1)比这个下标(index)的值大的时候,就直接返回arr[index+1]
 * 因为,题目表示旋转之前是有序的,意味着是从小到大的,而旋转之后最小的那个数就跑到中间去了,所以只需要这么匹配即可
 * 3 .二分查找法,既然数组无论是旋转前还是旋转后子数组都是有序的,则可以通过二分查找来寻找值(并且注意在这个二分钟,如果是旋转的
 * left是肯定要大于right的,如1,2,3,4,5->3,4,5,1,2 3是肯定要大于2的)
 * 在二分的流程中,中间数5(下标2)大于left ,说明在[left,2]在这途中都是递增的,最小的肯定不会出现在这里(因为既然是旋转
 * 之后的数组则最小值肯定是与最大值相连的)
 * 所以需要在右边查找[2,right]查找,继续二分,若中间值小于right,他肯定不会在[middle,right]之间,因为这个时候是递增的
 * 临界退出条件为left-right=1说明相邻
 * @date 创建时间：2019-05-17 20:54
 */
public class _6_the_minnumber_of_rotatearray
{

}
