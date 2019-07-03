package com.sapling.example.algorithm.tree;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/27
 * @since v1.0
 */
public class Node<T extends Comparable> {

    T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
