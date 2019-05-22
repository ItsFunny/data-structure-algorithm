package com.basic.algorithm.timewheel.jianzhioffer;

/**
 * @author joker
 * @When
 * @Description 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * @Detail 最暴力的方法, 直接遍历的方式
 * 当exponent=0 的时候为1  F(0)=1
 * 当exponent=1 的时候为1 * base  F(1)=F(0)*Base
 * 当exponent=2 的时候未 F(2)=F(1)*Base
 * 所以当exponent=n 的时候为 F(n)=F(n-1)*Base
 * @date 创建时间：2019-05-22 20:28
 */
public class _12_double_exponent
{
    public static double Power(double base, int exponent)
    {
        if (exponent == 0)
        {
            return 1.0;
        } else
        {
            return Power(base, exponent - 1) * base;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(Power(2, 2));
    }

}
