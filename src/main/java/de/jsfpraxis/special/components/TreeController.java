package de.jsfpraxis.special.components;

import java.util.logging.Logger;

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
	
	private static final Logger logger = Logger.getLogger(TreeController.class.getCanonicalName());
	
	private TreeNode<String> root;
	
	public TreeController() {
		root = TreeExample.build();
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
		
		private static final int TREE_HEIGHT = 6;
		private static int counter;
		
		private static TreeNode<String> build() {
			counter = 0;
			TreeNode<String> tree = build(1, new TreeNode<>("1"));
			logger.info("built tree with " + counter + " nodes");
			return tree;
		}
		
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
