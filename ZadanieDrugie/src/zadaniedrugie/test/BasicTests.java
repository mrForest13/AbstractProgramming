package zadaniedrugie.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import zadaniedrugie.EnumeratorOrder;
import zadaniedrugie.Node;
import zadaniedrugie.Tree;

public class BasicTests {

	@Test
	public void EnumeratorValidation() throws Exception {

		Tree<Integer> subtree = new Tree<Integer>(5, EnumeratorOrder.BreadthFirstSearch);
		subtree.add(1);
		subtree.add(2);
		Tree<Integer> tree = new Tree<Integer>(7, EnumeratorOrder.BreadthFirstSearch);
		tree.add(subtree);
		tree.add(10);
		tree.add(15);
		int[] bfs = new int[] { 7, 5, 10, 15, 1, 2 };
		List<Integer> maybeBfs = tree.asList();
		Assert.assertEquals(bfs.length, maybeBfs.size());
		for (int i = 0; i < bfs.length; i++) {
			Assert.assertEquals(bfs[i], maybeBfs.get(i).intValue());
		}
		tree.setEnumeratorOrder(EnumeratorOrder.DepthFirstSearch);
		int[] dfs = new int[] { 7, 5, 1, 2, 10, 15 };
		List<Integer> maybeDfs = tree.asList();
		Assert.assertEquals(dfs.length, maybeDfs.size());
		for (int i = 0; i < dfs.length; i++) {
			Assert.assertEquals(dfs[i], maybeDfs.get(i).intValue());
		}
	}

	@Test
	public void EnumerateWithNoChildren() throws Exception {
		Tree<Integer> tree = new Tree<Integer>(7, EnumeratorOrder.DepthFirstSearch);
		Assert.assertEquals(7, tree.getRoot().getValue().intValue());
        tree.setEnumeratorOrder(EnumeratorOrder.BreadthFirstSearch); 
        Assert.assertEquals(7, tree.asList().get(0).intValue());
        Tree<Integer> subtree = new Tree<Integer>(5, EnumeratorOrder.BreadthFirstSearch);
        tree.add(subtree);
        Assert.assertEquals(5, tree.asList().get(1).intValue());
        tree.setEnumeratorOrder(EnumeratorOrder.DepthFirstSearch); 
        Assert.assertEquals(5, tree.asList().get(1).intValue());
	}
	
	@Test(expected = Exception.class)
	public void OrderPropertyValidation() throws Exception
    {
		Tree<Integer> subtree = new Tree<Integer>(5, EnumeratorOrder.DepthFirstSearch);
		subtree.add(1);
		subtree.add(2);
        Tree<Integer> tree = new Tree<Integer>(7, EnumeratorOrder.BreadthFirstSearch);
        tree.add(subtree);
        Assert.assertEquals(EnumeratorOrder.BreadthFirstSearch, subtree.getEnumeratorOrder());

        try
        {
            tree.setEnumeratorOrder(null);
        } catch (Exception e) {
        	Assert.assertEquals("Unknown order type defined.", e.getMessage());
        	throw e;
        }

        
    }
	
	//Nie by³em dokoñca pewny czy o takie pobieranie chodzi³o... Do dfs i bfs wystarczy
	@Test
	public void getChildrenValidation() throws Exception
    {
		Tree<Integer> subtree = new Tree<Integer>(5, EnumeratorOrder.BreadthFirstSearch);
		subtree.add(1);
		subtree.add(2);
		Tree<Integer> tree = new Tree<Integer>(7, EnumeratorOrder.BreadthFirstSearch);
		tree.add(subtree);
		tree.add(10);
		tree.add(15);
		
		int[] subtreeChildren = new int[] {1 , 2};
		int i = 0;
		for(Node<Integer> node : subtree.getChildren()) {
			Assert.assertEquals(node.getValue().intValue(), subtreeChildren[i++]);
		}
		
		int[] treeChildren = new int[] {5 , 10, 15};
		i = 0;
		for(Node<Integer> node : tree.getChildren()) {
			Assert.assertEquals(node.getValue().intValue(), treeChildren[i++]);
		}
		
		subtree.add(99);
		
		int[] treeSubtreeChildren = new int[] {1 , 2, 99};
		i=0;
		for(Node<Integer> node : tree.getChildren().get(0).getChildren()) {
			Assert.assertEquals(node.getValue().intValue(), treeSubtreeChildren[i++]);
		}
    }

}
