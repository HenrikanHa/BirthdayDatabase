import java.io.Serializable;
import java.util.Objects;

public class Name implements Comparable<Name>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String first;
	private String middle;
	private String last;
	
	public Name(String first, String middle, String last) {
		this.first = first;
		this.middle = middle;
		this.last = last;
	}
	
	public Name(String first, String last) {
		this(first, "", last);
	}
	
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, last, middle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		return Objects.equals(first, other.first) && Objects.equals(last, other.last)
				&& Objects.equals(middle, other.middle);
	}
	
	@Override
	public String toString() {
		if (middle.isEmpty()) {
			return first + " " + last;
		} else {
			return first + " " + middle + " " + last;
		}
	}

	@Override
	public int compareTo(Name o) {
		int lastNameCompare = this.getLast().toLowerCase().compareTo(o.getLast().toLowerCase());
		if (lastNameCompare != 0) {
			return lastNameCompare;
		}
		int firstNameCompare = this.getFirst().toLowerCase().compareTo(o.getFirst().toLowerCase());
		if (firstNameCompare != 0) {
			return firstNameCompare;
		}
		return this.getMiddle().toLowerCase().compareTo(o.getMiddle().toLowerCase());
	}

}
