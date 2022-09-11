package com.majidmostafavi.jakartaee10prime12.core.utils;

import com.majidmostafavi.jakartaee10prime12.core.interfaces.TreeNode;
import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by majid on 11/6/16.
 */

@Dependent
public class TreesUtils implements Serializable {

    private boolean found;

    public <T extends TreeNode> List convert(List<? extends TreeNode> values) {
        List<TreeNode> roots = new ArrayList<>();
        for (TreeNode node : values) {
            if(node.getId()==4000L){
                roots.add(node);
            }
        }
        values.removeAll(roots);
        roots.forEach(root ->{
            convertToTree( root,(List<TreeNode>) values);
        });

        return roots;
    }

    /*public <T extends TreeNode> TreeNode convert(List<? extends TreeNode> values, List<? extends TreeNode> selectedValues) {
        TreeNode root = new DefaultTreeNode("root");
        List<TreeNode> treeNodes = new ArrayList<>();
        for (T value : values){
            DefaultTreeNode node = new DefaultTreeNode(value);
            if (selectedValues.contains(value)){
                node.setSelected(true);
            }
            treeNodes.add(node);
        }
        convertToTree(root, treeNodes);
        return root;
    }

    public <T extends Treeable> TreeNode convert(TreeNode treeNode,List<T> values) {
        TreeNode root = treeNode;
        treeNode.setExpanded(true);
        List<TreeNode> treeNodes = new ArrayList<>();
        values.forEach(b -> treeNodes.add(new DefaultTreeNode(b)));
        convertToTree(root, treeNodes);
        return root;
    }

    public <T extends Treeable> TreeNode convert(TreeNode treeNode,List<T> values, Predicate<T> predicate) {
        TreeNode root = treeNode;
        treeNode.setExpanded(true);
        List<TreeNode> treeNodes = new ArrayList<>();
        values.forEach(b -> treeNodes.add(new DefaultTreeNode(b)));
        convertToTree(root, treeNodes);
        return root;
    }

    public <T extends Treeable> TreeNode convert(List<T> values, Consumer<TreeNode> consumer) {
        TreeNode root = new DefaultTreeNode("root");
        List<TreeNode> treeNodes = new ArrayList<>();
        values.forEach(b -> treeNodes.add(new DefaultTreeNode(b)));
        convertToTree(root, treeNodes);
        treeNodes.forEach(consumer::accept);
        return root;
    }

    public <T extends Treeable> List<T> convert(TreeNode root) {
        List<T> values = new ArrayList<>();
        convertFromTree(root, values);
        return values;
    }

    public <T extends Treeable> TreeNode convert(TreeNode root,List<T> values,boolean selectableParent) {
        List<TreeNode> treeNodes = new ArrayList<>();
        values.forEach(b -> treeNodes.add(new DefaultTreeNode(b)));
        convertToTree(root, treeNodes, selectableParent);
        return root;
    }

    public <T extends Treeable> TreeNode convert(TreeNode root,List<T> values,List<T> selectedValues,boolean selectableParent) {
        List<TreeNode> treeNodes = new ArrayList<>();
        for (T value : values){
            DefaultTreeNode node = new DefaultTreeNode(value);
            if (selectedValues.contains(value)){
                node.setSelected(true);
            }
            treeNodes.add(node);
        }        convertToTree(root, treeNodes, selectableParent);
        return root;
    }

    public <T extends Treeable> TreeNode convert(TreeNode root,List<T> values,boolean selectableParent,boolean selectedAll) {
        List<TreeNode> treeNodes = new ArrayList<>();
        values.forEach(b -> treeNodes.add(new DefaultTreeNode(b)));
        treeNodes.forEach(t -> t.setSelected(true));
        convertToTree(root, treeNodes, selectableParent,selectedAll);
        return root;
    }

    public <T extends Treeable> TreeNode convert(List<T> values,boolean selectableParent) {
        TreeNode root = new DefaultTreeNode("root");
        List<TreeNode> treeNodes = new ArrayList<>();
        values.forEach(b -> treeNodes.add(new DefaultTreeNode(b)));
        convertToTree(root, treeNodes, selectableParent);
        return root;
    }

    private <T extends Treeable> void convertFromTree(TreeNode node, List<T> values) {
        for (TreeNode treeNode : node.getChildren()) {
            values.add((T) treeNode.getData());
            convertFromTree(treeNode, values);
        }
    }*/

    private <T extends TreeNode> void convertToTree(T root,List<TreeNode> treeNodes) {
        for (TreeNode node : treeNodes) {
            if (node.getParentId() != null  && !containsNode(treeNodes, node)) {
                root.getChildren().add(node);
            }else if (node.getParentId() != null ){
                treeNodes.stream().filter(t -> ((T) t).getId().equals(node.getParentId())).findAny().get().getChildren().add(node);
            }else {
                root.getChildren().add(node);
            }
        }
    }

    private boolean containsNode(List<TreeNode> nodeList , TreeNode treeable){
        return nodeList.parallelStream().anyMatch(treeNode -> treeNode.getId().equals(treeable.getParentId()));
    }


   /* public void remove(TreeNode treeNode, boolean recursive) {
        TreeNode parent = treeNode.getParent();
        if (parent != null) {
            parent.getChildren().remove(treeNode);
            if (recursive && parent.getChildren().isEmpty()) {
                remove(parent, true);
            }
        }
    }

    public TreeNode clone(TreeNode source) {
        TreeNode target = new DefaultTreeNode(source.getData());
        copy(source, target);
        return target;
    }

    public <T extends TreeNode>void copy(T source, T target) {
        copy(source, target, false);
    }

    public  <T extends Treeable>   void copy(TreeNode source, TreeNode target, boolean withParent) {
        if (withParent) {
            target = new DefaultTreeNode(source.getData(), target);
        }
        for (Object node : source.getChildren()) {
            TreeNode treeNode = new DefaultTreeNode(node, target);
            copy((TreeNode) node, treeNode);
        }
    }

    public <S, H> void copy(TreeNode source, TreeNode target, Function<S, H> function) {
        copy(source, target, function, false);
    }

    public <S, H> void copy(TreeNode source, TreeNode target, Function<S, H> function, boolean withParent) {
        if (withParent) {
            target = new DefaultTreeNode(function.apply((S) source.getData()), target);
        }
        for (Object node : source.getChildren()) {
            TreeNode treeNode = new DefaultTreeNode(function.apply((S) node), target);
            copy(node, treeNode, function);
        }
    }

    public <S, H> void copyNodes(TreeNode source, TreeNode target, BiFunction<S, TreeNode, H> function) {
        copyNodes(source, target, function, false);
    }

    public <S, H> void copyNodes(TreeNode source, TreeNode target, BiFunction<S, TreeNode, H> function, boolean withParent) {
        if (withParent) {
            target = new DefaultTreeNode(function.apply((S) source.getData(), target), target);
        }
        for (TreeNode node : source.getChildren()) {
            TreeNode treeNode = new DefaultTreeNode(function.apply((S) node.getData(), target), target);
            copyNodes(node, treeNode, function);
        }
    }

    public void move(TreeNode source, TreeNode target) {
        source.getParent().getChildren().remove(source);
        target.getChildren().add(source);
    }

    private <T extends TreeNode> boolean containsNode(List<T> nodeList , TreeNode node){
        return nodeList.parallelStream().anyMatch(treeNode -> treeNode.getId().equals(node.getParentId()));
    }

    public <T extends Treeable> TreeNode convertSpecial(List<T> values,List<T> expandedOrganList,T selectedObj) {
        TreeNode root = new DefaultTreeNode("root");
        List<TreeNode> treeNodes = new ArrayList<>();
        for (T value : values){
            DefaultTreeNode node = new DefaultTreeNode(value);
            if (expandedOrganList.contains(value)){
                node.setExpanded(true);
            }if (value.equals(selectedObj)){
                node.setSelected(true);
            }
            treeNodes.add(node);
        }
        convertToTree(root, treeNodes);
        return root;
    }
*/
}