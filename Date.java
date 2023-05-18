import java.io.Serializable;
import java.util.Objects;

public class Date implements Comparable<Date>, Serializable {
	private static final long serialVersionUID = 1L;
    
	private Month month;
	private int day;
	private int year;
	
	
	
	public Date(Month month, int day, int year) {
		if (!Month.isValidDay(month, day) || year < 0) {
			throw new IllegalArgumentException("Invalid date!");
		}
		this.month = month;
		this.day = day;
		this.year = year;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		if (!Month.isValidDay(month, this.day)) {
			throw new IllegalArgumentException("Invalid month!");
		}
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if (!Month.isValidDay(this.month, day)) {
			throw new IllegalArgumentException("Invalid day!");
		}
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year < 0) {
			throw new IllegalArgumentException("Invalid year!");
		}
		this.year = year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, month, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		return day == other.day && month == other.month && year == other.year;
	}
	
	@Override
	public String toString() {
		return month.getName() + " " + day + ", " + year;
	}

	@Override
	public int compareTo(Date o) {
		int result = Integer.compare(this.year, o.getYear());

        if (result == 0) {
            result = this.month.compareTo(o.getMonth());
        }

        if (result == 0) {
            result = Integer.compare(this.day, o.getDay());
        }

        return result;
	}

}
