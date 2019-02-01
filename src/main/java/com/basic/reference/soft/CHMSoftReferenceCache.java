package com.basic.reference.soft;

import com.basic.reference.ClearStrategy;
import com.basic.reference.ObjectCreateStrategy;

import java.lang.ref.Reference;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author joker
 * @When
 * @Description
 * @Detail
 * @date 创建时间：2019-02-01 06:42
 */
public class CHMSoftReferenceCache<K, V> extends AbstractCHMSoftReferenchCache<K, V>
{
    private ClearStrategy<V> DEFAULT_CHM_CLEAR_STRATEGY = (queue) ->
    {
        Reference<V> poll = (Reference<V>) queue.poll();
        while (null != poll)
        {
            V v = poll.get();
            this.dataMap.remove(v);
        }
    };


    public CHMSoftReferenceCache(ClearStrategy<V> clearStrategy, ObjectCreateStrategy<V> objectCreateStrategy)
    {
        super(clearStrategy, objectCreateStrategy);
    }

    public CHMSoftReferenceCache(ObjectCreateStrategy<V> objectCreateStrategy)
    {
        super(objectCreateStrategy);
        this.clearStrategy = DEFAULT_CHM_CLEAR_STRATEGY;
    }
}
