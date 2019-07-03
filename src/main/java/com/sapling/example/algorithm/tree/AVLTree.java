package com.sapling.example.algorithm.tree;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/27
 * @since v1.0
 */
public class AVLTree<T extends Comparable> extends BiSortTree {

    TreeNode<T> root;

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public void addNode(Node node) {
        // 根节点大于当前节点的时候，则是
        if (root.getValue().getValue().compareTo(node.getValue()) > 0) {

        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
    }
}
