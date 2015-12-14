package zadaniedrugie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Tree<T> {

	private Node<T> root;
	private EnumeratorOrder enumeratorOrder;

	public Tree(T value, EnumeratorOrder enumeratorOrder) throws Exception {
		this.root = new Node<T>(value);
		this.setEnumeratorOrder(enumeratorOrder);
	}

	public void add(T value) {
		this.root.getChildren().add(new Node<T>(value));
	}

	public void add(Tree<T> tree) throws Exception {
		this.root.getChildren().add(tree.root);
		tree.setEnumeratorOrder(this.enumeratorOrder);
	}

	public List<T> asList() {
		if (enumeratorOrder == EnumeratorOrder.BreadthFirstSearch)
			return bfs();
		else
			return dfs();
	}

	private List<T> bfs() {

		List<T> result = new ArrayList<T>();
		Queue<Node<T>> queue = new LinkedList<>();
		queue.add(this.root);

		while (!queue.isEmpty()) {
			Node<T> temp = queue.remove();
			result.add(temp.getValue());

			for (Node<T> node : temp.getChildren()) {
				queue.add(node);
			}
		}

		return result;
	}

	private List<T> dfs() {

		List<T> result = new ArrayList<T>();
		Stack<Node<T>> stack = new Stack<>();
		this.root.setIsVisited(true);
		stack.push(this.root);
		result.add(root.getValue());

		while (!stack.isEmpty()) {
			Node<T> temp = stack.peek().getFirstNotVisitedChildren();

			if (temp == null) {
				stack.pop();
			} else {
				temp.setIsVisited(true);
				stack.push(temp);
				result.add(temp.getValue());
			}
		}

		return result;
	}

	public EnumeratorOrder getEnumeratorOrder() {
		return enumeratorOrder;
	}

	public void setEnumeratorOrder(EnumeratorOrder enumeratorOrder) throws Exception {
		if (!EnumeratorOrder.BreadthFirstSearch.equals(enumeratorOrder)
				&& !EnumeratorOrder.DepthFirstSearch.equals(enumeratorOrder))
			throw new Exception("Unknown order type defined.");
		else
			this.enumeratorOrder = enumeratorOrder;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

	public List<Node<T>> getChildren() {
		return root.getChildren();
	}

}
