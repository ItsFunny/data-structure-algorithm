package com.basic.algorithm.timewheel.jianzhioffer;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * @author joker
 * @When
 * @Description 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * @Detail 整数转换为二进制的方式为: 一个数不停的除以2
 * 这题是我盲目了,其实压根就不需要我自己将其转换为二进制数,完全用&, <<,>>这种即可
 * @date 创建时间：2019-05-20 22:37
 */
public class _11_number_of_1
{
    public static int NumberOf1(int n)
    {
        int res = 0;
        int count = 0;
        if (n < 0)
        {
            count++;
            n *= -1;
        }
        while (n != 0)
        {
            res = n % 2;
            n = n / 2;
            if (res == 1)
            {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args)
    {
        System.out.println(_11_number_of_1.NumberOf1(3));
    }

}
