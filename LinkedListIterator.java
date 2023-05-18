import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<E extends Comparable<E>> implements Iterator<E> {

	private Node<E> next;
	private Node<E> current;
    private Node<E> previous;
    private LinkedList<E> list;
    private int size;
	
	public LinkedListIterator(LinkedList<E> list) {
		this.list = list;
		this.next = list.getHead();
		this.size = list.size();
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public E next() {
		if (!hasNext()) {
            throw new NoSuchElementException();
        }
		if (current != null) {
			previous = current;
		}
		current = next;
		next = next.getLink();
        return current.getData();
	}
	
	@Override
    public void remove() {
        if (current == null) {
        	throw new IllegalStateException();
        }
        if (previous == null) {
        	this.list.setHead(next);
        } else {
            previous.setLink(current.getLink());
        }
        current = null;
        this.size--;
        this.list.setSize(this.size);
    }
}
