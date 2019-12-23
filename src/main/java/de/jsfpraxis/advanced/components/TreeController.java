package de.jsfpraxis.advanced.components;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 * 
 * @author bernd
 *
 */
@Named
@RequestScoped
public class TreeController {
	
	private TreeNode<String> root;
	
	public TreeController() {
		//root = new TreeNode<>("1");
		
//		TreeNode<String> oneDotOne = new TreeNode<>("1.1");
//		root.addChild(oneDotOne);
//		TreeNode<String> oneDotOneDotOne = new TreeNode<>("1.1.1");
//		TreeNode<String> oneDotOneDotTwo = new TreeNode<>("1.1.2");
//		oneDotOne.addChild(oneDotOneDotOne);
//		oneDotOne.addChild(oneDotOneDotTwo);
//		TreeNode<String> oneDotTwo = new TreeNode<>("1.2");
//		root.addChild(oneDotTwo);
		
		root = TreeExample.build(1, new TreeNode<>("1"));
		System.out.println("counter: " + TreeExample.counter);
	}


	// Getter und Setter
	public TreeNode<String> getTree() {
		return root;
	}
	
	
	/**
	 * Beispielbaum mit Anzahl Sohnknoten entsprechend Baumhöhe.
	 *  
	 * 
	 * TREE_HEIGHT  Knotenanzahl
	 *      2               3
	 *      3               9           
	 *      4              33
	 *      5             153
	 *      6             873
	 *      7            5913
	 *      8           46233
	 *      9          409113
	 *            \sum_{i=1}^n i!
	 */
	static class TreeExample {
		
		private static final int TREE_HEIGHT = 7;
		private static int counter = 0;
		
		private static TreeNode<String> build(int level, TreeNode<String> node) {
			counter++;
			if (level < TREE_HEIGHT) {
				for (int i = 1; i <= level + 1; i++) {
					node.addChild(build(level + 1, new TreeNode<String>(node.getData() + "." + i)));
				}
			}
			return node;
		}
	}
}
