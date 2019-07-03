package com.sapling.example.algorithm.tree;

/**
 * 二叉树
 *
 * @author weizhou
 * @version v1.0
 * @date 2019/3/27
 * @since v1.0
 */
public class BiTree<T extends Node> implements Tree<T>{

    /** 当前的数据  **/
    private T value;

    /** 左节点 **/
    private BiTree<T> left;

    /** 右节点 **/
    private BiTree<T> right;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BiTree<T> getLeft() {
        return left;
    }

    public void setLeft(BiTree<T> left) {
        this.left = left;
    }

    public BiTree<T> getRight() {
        return right;
    }

    public void setRight(BiTree<T> right) {
        this.right = right;
    }

    @Override
    public void add(T node) {
        //根节点大于当前节点
        if (value.getValue().compareTo(node.getValue())>0){

        }
    }

    @Override
    public void delete(T node) {

    }

    public void addLeft(){

    }
}
