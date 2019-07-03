package com.sapling.example.algorithm.tree;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/27
 * @since v1.0
 */
public class TreeNode<T extends Comparable> {

    /**
     * 当前节点
     */
    private Node<T> value;

    /**
     * 左节点
     */
    private TreeNode left;

    /**
     * 右节点
     */
    private TreeNode right;


    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Node<T> getValue() {
        return value;
    }

    public void setValue(Node<T> value) {
        this.value = value;
    }
}
