package de.jsfpraxis.advanced.components;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class TreeNodeTest {

	@Test
	public void testUniqueness() {
		String foo = new String("foo");
		String fooToo = new String("foo"); // different reference, but equals
		TreeNode<String> root = new TreeNode<>(foo);
		root.addChild(new TreeNode<>(fooToo));
		Assert.assertTrue(root.getChildren().size() == 1);
	}

	
	@Test
	public void testLeaf() {
		TreeNode<String> root = new TreeNode<>("foo");
		Assert.assertTrue(root.isLeaf());
	}

	
	@Test
	public void testRoot() {
		TreeNode<String> root = new TreeNode<String>("foo");
		Assert.assertTrue(root.isRoot());
	}


	@Test
	public void testWrongRoot() {
		TreeNode<String> root = new TreeNode<>("foo");
		TreeNode<String> bar = new TreeNode<>("bar");
		root.addChild(bar);
		Assert.assertFalse(bar.isRoot());
	}

	
	@Test
	public void treeDepthTwo() {
		TreeNode<String> root = new TreeNode<>("root");
		Set<String> strings = Set.of("foo", "bar", "baz");
		for (String string : strings) {
			root.addChild(new TreeNode<>(string));	
		}
		Set<String> stringsFromTree = root.getChildren().stream().map(TreeNode::getData).collect(Collectors.toSet());
		Assert.assertTrue(strings.equals(stringsFromTree));
	}

	
}
