package com.basic.reference.soft;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author joker
 * @When
 * @Description
 * @Detail
 * @date 创建时间：2019-02-01 07:01
 */
public class SoftReferenceInfo<T> extends SoftReference<T>
{

    public SoftReferenceInfo(T referent)
    {
        super(referent);
    }

    public SoftReferenceInfo(T referent, ReferenceQueue<? super T> q)
    {
        super(referent, q);
    }
}
