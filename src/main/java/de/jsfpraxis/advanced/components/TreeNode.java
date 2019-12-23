package de.jsfpraxis.advanced.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
/**
  * Einfacher Baumknoten.
  * 
  * @author bernd
  *
  * @param <T> Typ der Knotendaten
  */
public class TreeNode<T> implements Serializable {

	private static final long serialVersionUID = -5203868889548118621L;
	
	private T data;
	private TreeNode<T> parent;
	private List<TreeNode<T>> children;


	public TreeNode(T data) {
		parent = null;
		this.data = data;
		children = new ArrayList<TreeNode<T>>();				
	}

	public void addChild(TreeNode<T> child) {
		child.setParent(this);
		children.add(child);
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}
	
	public boolean isRoot() {
		return parent == null;
	}
	
	
	// Getter and Setter
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	
	public TreeNode<T> getParent() {
		return parent;
	}
	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	
	public List<TreeNode<T>> getChildren() {
		return children;
	}

}
