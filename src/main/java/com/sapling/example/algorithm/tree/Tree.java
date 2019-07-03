package com.sapling.example.algorithm.tree;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/27
 * @since v1.0
 */
public interface Tree<T extends Node> {

    void add(T node);

    void delete(T node);

}
