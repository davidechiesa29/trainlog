package ButtonActions;
import java.net.http.*;
import java.net.URI;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
	public String locationData;

	/**
	 * Stores all of the necessary information for the activity
	 */
	public Activity (String title, double distance, int duration, Integer heartrate, String description, String location, String date, String time, 
			String runType, boolean weatherData, boolean dynamicTitle) {
		this.distance = distance;
		this.duration = duration;
		this.heartrate = heartrate;
		this.description = description;
		this.location = parseDisplayedLocation(location);
		this.date = date;
		this.time = time;
		this.runType = runType;
		this.weatherData = weatherData;
		this.dynamicTitle = dynamicTitle;
		this.title = title;
		this.locationData = location;

		if (weatherData) {
			if (!this.description.trim().equals("")){
				this.description += "\n\n";
			}
			this.description += generateActivityWeather(this);	
		}

		// Replaces the previous title with an AI-generated one if requested by the user
		if (this.dynamicTitle) this.title = generateActivityTitle(this);
	}

	/**
	 * Compare-to method for sorting activities according to how recently the activity was completed
	 */
	@Override
	public int compareTo(Activity other) {

		// Parses the date and time of the activities
		String[] thisDate = this.date.split("-");
		String[] otherDate = other.date.split("-");
		String[] thisTime = this.time.split("-");
		String[] otherTime = other.time.split("-");

		// Converts months into an integer 
		thisDate[0] = convertMonthToInteger(thisDate[0]);
		otherDate[0] = convertMonthToInteger(otherDate[0]);

		// Converts 'AM' into 0 and 'PM' to 1
		thisTime[2] = convertTimeToInteger(thisTime[2]);
		otherTime[2] = convertTimeToInteger(otherTime[2]);

		// Compares the date
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
		
		// Compares the time if the activities have the same date
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

	/**
	 * Returns integer value corresponding to each month
	 */
	private static String convertMonthToInteger(String s){
		if (s.equals("January")) return "01";
		if (s.equals("Febuary")) return "02";
		if (s.equals("March")) return "03";
		if (s.equals("April")) return "04";
		if (s.equals("May")) return "05";
		if (s.equals("June")) return "06";
		if (s.equals("July")) return "07";
		if (s.equals("August")) return "08";
		if (s.equals("September")) return "09";
		if (s.equals("October")) return "10";
		if (s.equals("November")) return "11";
		return "12";
	}

	/**
	 * Returns integer value corresponding to 'AM' or 'PM'
	 */
	private static String convertTimeToInteger(String s){
		if (s.equals("AM")) return "0";
		return "1";
	}

	private static String parseDisplayedLocation(String location){
		
		if (location.equals("No Location")) return location;
		String[] parsed = location.split(",");
		return parsed[0] + ", " + parsed[1];
	}

	/**
	 * Creates an AI-Generated title for an activity, getting inspiration from the activity time, heart-rate, distance, duration, and run-type
	 */
	public static String generateActivityTitle(Activity a) {

		
		// Stores API key to access openAI API
		String apiKey = "sk-proj-kizqVyvb1ualfWfFHgdERP8EvOn8REaKtKDw-04ZvPHMon5b5zP5uuPugx9afz" +
			"_jjemtWvYky_T3BlbkFJ6gNiZJ_20TGKt38nebeAxbocBeAdFrz5k6RPMLuHhn0lL9a9LYCTkzM8Un55hafOqELQ4TfZgA";

		// Generates the body of the request that will be sent to chatGPT in JSON format
		String body = """
		{
			"model": "gpt-4o-mini",
			"messages": [
				{
					"role":"user",
					"content": "placeholder"
				}
			]
		}""";

		body = body.replace("placeholder",generatePrompt(a)); 

		// Formally creates the reqeust, specifying the API key 
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.openai.com/v1/chat/completions")).header("Content-Type","application/json").header("Authorization","Bearer " + apiKey).POST(HttpRequest.BodyPublishers.ofString(body)).build();
		
		try {
			// Attempts to get a response from open AI, and then parses the content out of the response and returns it
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			Gson parsed = new Gson();
		        Output output =  parsed.fromJson(response.body(),new TypeToken<Output>(){});
			return output.choices.get(0).message.content;
			

		} catch (Exception e){

			// Catches potential errors that may be caused by connected to the API
			System.out.println("Error fetching dynamically generated title, resorting to default title name");
		}

		// Returns default activity title if dynamic title generation failed
		return "Daily Run";

	}

	/**
	 * Creates the specific prompt which will be fed to chatGPT
	 */
	private static String generatePrompt(Activity activity) {

		String prompt = "Generate a unique and creative title for a running activity." +
        	" Use the following information to inspire the title, but avoid repeating phrases like 'Chasing' or 'Whispers'. " +
        	"Do not directly mention distances, dates, times, or locations. Avoid clichés. Be original, surprising, or vivid." +
        	" You can draw from emotions, colors, moods, music, nature, or the vibe of the run." +
        	" Limit the title to a maximum of 40 characters and do not use quotation marks around it." +
        	" Distance: " + activity.distance +
        	", Duration: " + activity.duration +
        	", Run Type: " + activity.runType +
        	", Time: " + activity.time;

		if (activity.heartrate != null)
			prompt += ", HR: " + activity.heartrate;
		return prompt;
	}


	/**
	 * Inner classes used for parsing the content out of the Json format and directly into an object
	 */
	class Output {

		ArrayList<Choice> choices;

		class Choice {

			Message message;

			class Message {

				String content;
			}
		}

	}

	private static String generateActivityWeather(Activity activity) {
		
		// Returns no weather data if there is no activity location
		if (activity.location.equals("No Location")) return "";

		String apiKey = "";

		// Gets the activity date along with the current date
		String date = convertDate(activity.date);
		LocalDate activityDate = LocalDate.parse(date);
		LocalDate currentDate = LocalDate.now();

		// Returns no weather data if the activity date is in the future
		if (ChronoUnit.DAYS.between(currentDate, activityDate) >= 1) return "";

		// Gets coordinates of activity location
		String[] parsed = activity.locationData.split(",");
		String latitude = parsed[3];
		String longitude = parsed[4];

		// Handles case in which historical weather data needs to be accessed
		if (ChronoUnit.DAYS.between(currentDate, activityDate) < -1) {	
		
			// Creates the key	
			apiKey = "https://archive-api.open-meteo.com/v1/archive?latitude=" + latitude + "&longitude=" + longitude + 
			"&start_date=" + date + "&end_date=" + date + 
			"&hourly=temperature_2m,relative_humidity_2m,apparent_temperature&temperature_unit=fahrenheit";
		}
		
		// Handles case in which modern weather data needs to be accessed
		else {

			// Creates the key
			apiKey = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +
				"&hourly=temperature_2m,relative_humidity_2m,apparent_temperature&" + 
				"temperature_unit=fahrenheit&start_date=" + date + "&end_date=" + date;
		}

		// Creates HTTP request to send to the api using the created key
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiKey)).GET().build();

		String weatherInfo = "";

		// Calculates the hour of the activity in military time
		int activityHour = Integer.parseInt(activity.time.split("-")[0]);
		if (activity.time.split("-")[2].equals("AM") && activityHour == 12)
			activityHour = 0;
		else if (activity.time.split("-")[2].equals("PM") && activityHour != 12)
			activityHour += 12;
		if (Integer.parseInt(activity.time.split("-")[1]) > 29) activityHour = (activityHour + 1)%24;

		// Attempts to retrieve response from the API
		try {
	       		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			Gson gson = new Gson();
		        Weather weatherOutput =  gson.fromJson(response.body(),new TypeToken<Weather>(){});
			return "Temp: " + Math.round(weatherOutput.hourly.temperature_2m[activityHour]) + " °F , Humidity: " +
				weatherOutput.hourly.relative_humidity_2m[activityHour] + "% , Feels Like: " +
				Math.round(weatherOutput.hourly.apparent_temperature[activityHour]) + "°F";

		} catch (Exception e) {
			System.out.println("Error caught. Unable to retrieve weather data.");
		}

		return weatherInfo;	
	}

	public static String convertDate(String date) {

		String[] parsed = date.split("-");
		parsed[0] = convertMonthToInteger(parsed[0]);

		if (Integer.valueOf(parsed[1]) < 10) parsed[1] = "0" + parsed[1];

		return parsed[2] + "-" + parsed[0] + "-" + parsed[1];

	}


	class Weather {

		HourlyWeather hourly;

		class HourlyWeather {
		
			float[] temperature_2m;
			int[] relative_humidity_2m;
			float[] apparent_temperature;	
		}
	
	}


}
