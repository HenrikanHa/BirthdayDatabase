public class Node<E> {

	private E data;
	private Node<E> link;
	
	public Node(E data) {
		this.data = data;
		this.link = null;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getLink() {
		return link;
	}

	public void setLink(Node<E> link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "Node: " + data;
	}
	
}
