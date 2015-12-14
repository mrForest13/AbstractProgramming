package zadaniedrugie;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private T value;
	private List<Node<T>> children;
	private boolean isVisited;

	public Node(T value) {
		this.value = value;
		this.children = new ArrayList<Node<T>>();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}

	public boolean getIsVisited() {
		return isVisited;
	}

	public void setIsVisited(boolean viisVisited) {
		this.isVisited = viisVisited;
	}
	
	public Node<T> getFirstNotVisitedChildren() {
		for(Node<T> node : children) {
			if(!node.getIsVisited()) return node;
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
}
