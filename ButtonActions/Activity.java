package ButtonActions;

public class Activity implements Comparable<Activity> {

	public String title;
	public double distance;
	public int duration;
	public Integer heartrate;
	public String description;
	public String location;
	public String date;
	public String time;
	public String runType;
	public boolean weatherData;
	public boolean dynamicTitle;

	public Activity (String title, double distance, int duration, Integer heartrate, String description, String location, String date, String time, 
			String runType, boolean weatherData, boolean dynamicTitle) {
		this.title = title;
		this.distance = distance;
		this.duration = duration;
		this.heartrate = heartrate;
		this.description = description;
		this.location = location;
		this.date = date;
		this.time = time;
		this.runType = runType;
		this.weatherData = weatherData;
		this.dynamicTitle = dynamicTitle;
	}

	public String toString() {
		return "Title: " + title + "\nDescription: " + description;
	}

	@Override
	public int compareTo(Activity other) {

		String[] thisDate = this.date.split("-");
		String[] otherDate = other.date.split("-");
		String[] thisTime = this.time.split("-");
		String[] otherTime = other.time.split("-");

		thisDate[0] = convertMonthToInteger(thisDate[0]);
		otherDate[0] = convertMonthToInteger(otherDate[0]);
		thisTime[2] = convertTimeToInteger(thisTime[2]);
		otherTime[2] = convertTimeToInteger(otherTime[2]);

		if (Integer.parseInt(thisDate[2]) > Integer.parseInt(otherDate[2]))
			return -1;
		else if (Integer.parseInt(thisDate[2]) < Integer.parseInt(otherDate[2]))
                        return 1;
		if (Integer.parseInt(thisDate[0]) > Integer.parseInt(otherDate[0]))
			return -1;
		else if (Integer.parseInt(thisDate[0]) < Integer.parseInt(otherDate[0]))
                        return 1;
		if (Integer.parseInt(thisDate[1]) > Integer.parseInt(otherDate[1]))
			return -1;
		else if (Integer.parseInt(thisDate[1]) < Integer.parseInt(otherDate[1]))
                        return 1;
		
		if (Integer.parseInt(thisTime[2]) > Integer.parseInt(otherTime[2]))
			return -1;
		else if (Integer.parseInt(thisTime[2]) < Integer.parseInt(otherTime[2]))
                        return 1;
		if (Integer.parseInt(thisTime[0]) > Integer.parseInt(otherTime[0]))
			return -1;
		else if (Integer.parseInt(thisTime[0]) < Integer.parseInt(otherTime[0]))
                        return 1;
		if (Integer.parseInt(thisTime[1]) > Integer.parseInt(otherTime[1]))
			return -1;
		else if (Integer.parseInt(thisTime[1]) < Integer.parseInt(otherTime[1]))
                        return 1;

		return 0;
	}

	private String convertMonthToInteger(String s){
		if (s.equals("January")) return "1";
		if (s.equals("Febuary")) return "2";
		if (s.equals("March")) return "3";
		if (s.equals("April")) return "4";
		if (s.equals("May")) return "5";
		if (s.equals("June")) return "6";
		if (s.equals("July")) return "7";
		if (s.equals("August")) return "8";
		if (s.equals("September")) return "9";
		if (s.equals("October")) return "10";
		if (s.equals("November")) return "11";
		return "12";
	}

	private String convertTimeToInteger(String s){
		if (s.equals("AM")) return "0";
		return "1";
	}


}
