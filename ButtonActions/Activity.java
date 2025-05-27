package ButtonActions;

public class Activity {

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

}
