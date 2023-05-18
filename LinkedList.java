import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<E extends Comparable<E>> implements Iterable<E>{
	
	private Node<E> head;
	private int size = 0;
	private Comparator<E> myComparator;

	public LinkedList(Comparator<E> theComparator) {
		head = null;
		myComparator = theComparator;
	}
	
	public LinkedList() {
		head = null;
		myComparator = null;
	}
	
	private int myCompare(E first, E second) {
		if(myComparator == null) {
			return first.compareTo(second);
		}
		return myComparator.compare(first, second);
	}
	
	public void add(E data) {
		Node<E> n = new Node<>(data);
		if(head == null) {
			head = n;
			size++;
			return;
		}
		Node<E> mover = head;
		while(mover.getLink() != null) {
			mover = mover.getLink();
		}
		mover.setLink(n);
		size++;
	}
	
	public void addFirst(E data) {
		Node<E> n = new Node<>(data);
		n.setLink(head);
		head = n;
		size++;
	}

	public String toString() {
		String toReturn = "";
		Node<E> mover = head;
		while(mover != null) {
			toReturn += mover.getData() + " - ";
			mover = mover.getLink();
		}
		if(size == 0) {
			toReturn = "Empty list";
		}
		return toReturn;
	}
	
	public E get(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> mover = head;
		for(int i = 0; i < index; i++) {
			mover = mover.getLink();
		}
		return mover.getData();
	}
	
	public void set(int index, E data) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> mover = head;
		for(int i = 0; i < index; i++) {
			mover = mover.getLink();
		}
		mover.setData(data);
	}
	
	public boolean remove(E data) {
		if(head == null) {
			return false;
		}
		if(head.getData().equals(data)) {
			head = head.getLink();
			size--;
			return true;
		}
		
		Node<E> mover1 = head;
		Node<E> mover2 = head.getLink();
		
		while(mover2 != null) {
			if(mover2.getData().equals(data)) {
				mover1.setLink(mover2.getLink());
				size--;
				return true;
			}else {
				mover1 = mover1.getLink();
				mover2 = mover2.getLink();
			}
		}
		return false;
	}
	
	public void addAlpha(E data) {
        Node<E> n = new Node<E>(data);
        Node<E> mover = head;
        Node<E> previous = null;
        while (mover != null && mover.getData().compareTo(data) <= 0) {
        	previous = mover;
        	mover = mover.getLink();
        }
        
        if (previous == null) {
        	head = n;
        } else {
        	previous.setLink(n);
        }
        n.setLink(mover);
        size++;
    }
    
    public boolean contains(E data) {
        Node<E> mover = head;
        while(mover != null) {
            if(mover.getData().equals(data)) {
                return true;
            }
            mover = mover.getLink();
        }
        return false;
    }
    
    public void sort() {
        head = mergeSort(head);
    }
    
    private Node<E> mergeSort(Node<E> head) {
        if (head == null || head.getLink() == null) {
            return head;
        }
        
        Node<E> middle = getMiddle(head);
        Node<E> nextOfMiddle = middle.getLink();
        middle.setLink(null);
        
        Node<E> left = mergeSort(head);
        Node<E> right = mergeSort(nextOfMiddle);
        
        Node<E> sortedList = merge(left, right);
        return sortedList;
    }
    
    private Node<E> getMiddle(Node<E> head) {
        if (head == null) {
            return head;
        }
        
        Node<E> slow = head;
        Node<E> fast = head;
        
        while (fast.getLink() != null && fast.getLink().getLink() != null) {
            slow = slow.getLink();
            fast = fast.getLink().getLink();
        }
        
        return slow;
    }
    
    private Node<E> merge(Node<E> left, Node<E> right) {
        Node<E> result = null;
        
        if (left == null) {
            return right;
        }
        
        if (right == null) {
            return left;
        }
        
        if (myCompare(left.getData(), right.getData()) <= 0) {
            result = left;
            result.setLink(merge(left.getLink(), right));
        } else {
            result = right;
            result.setLink(merge(left, right.getLink()));
        }
        
        return result;
    }

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(this);
	}
	
	public int size() {
		return this.size;
	}
	
	public void setSize(int newSize) {
		this.size = newSize;
	}
	
	public Node<E> getHead() {
		return this.head;
	}
	
	public void setHead(Node<E> newHead) {
		this.head = newHead;
	}

}