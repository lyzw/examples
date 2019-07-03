package com.sapling.common.tools.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * description:
 *
 * @author wei.zhou
 * @date 2019/6/21 10:17
 */
public class MapWrapper<T extends Map, K, V> {

    private T owner;

    public MapWrapper(T owner) {
        this.owner = owner;
    }

    public MapWrapper put(K key,V value){
        owner.put(key,value);
        return this;
    }

    public T get(){
        return owner;
    }
}
