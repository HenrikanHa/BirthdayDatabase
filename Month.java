public enum Month {

	JANUARY(31, "January", 1), 
	FEBRUARY(28, "February", 2), 
	MARCH(31, "March", 3), 
	APRIL(30, "April", 4), 
	MAY(31, "May", 5), 
	JUNE(30, "June", 6), 
	JULY(31, "July", 7), 
	AUGUST(31, "August", 8), 
	SEPTEMBER(30, "September", 9), 
	OCTOBER(31, "October", 10), 
	NOVEMBER(31, "November", 11), 
	DECEMBER(31, "December", 12);
	
	private int day;
	private String name;
	private int num;
	
	Month(int theDay, String theName, int theNum){
		day = theDay;
		name = theName;
		num = theNum;
	}
	
	public int getDay() {
		return day;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNum() {
		return num;
	}

	// Static method to determine whether the given day
	// is valid for the given Month
	public static boolean isValidDay(Month theMonth, int theDay) {
		return theDay > 0 && theDay <= theMonth.day;
	}

	
}
