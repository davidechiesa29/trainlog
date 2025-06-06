package ButtonActions;

import org.controlsfx.control.Notifications;
import java.util.*;
import javafx.geometry.Pos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;

public class ActivityManager {

	// Used for directly storing activities, with activities sorted by how recently they occurred
	private static PriorityQueue<Activity> activities = new PriorityQueue<>();

	/**
	 * Adds the specified activity and saves it 
	 */
	public static void add(Activity a) {
		activities.add(a);
		savedActivityNotification();
		saveActivities();
	}

	/**
	 * Removes the specified activity and saves the new list of activities
	 */
	public static boolean remove(Activity a){
		boolean removed = activities.remove(a);
		saveActivities();
		return removed;
	}

	/**
	 * Returns the entire list of activities
	 */
	public static PriorityQueue<Activity> getAll() {
		return activities;
	}

	/**
	 * Intializes the priority queue by loading data stored in a Json file onto it
	 */ 
	public static void retrieveSavedActivities() {
		try {
			Gson gson = new Gson();
			BufferedReader reader = new BufferedReader(new FileReader("ButtonActions/activities.json"));
		        activities = gson.fromJson(reader, new TypeToken<PriorityQueue<Activity>>(){});
			reader.close();	
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("Failed to load activities");
		}	
	}

	/**
	 * Saves the user's activities by writing them to a Json file 
	 */ 
	public static void saveActivities() {
		List<Activity> saved = new ArrayList<>(activities);
		Gson gson = new Gson();
		String json = gson.toJson(activities);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("ButtonActions/activities.json"));
			writer.write(json);
			writer.close();

		} catch (Exception e) {
			System.out.println("Failed to save activities");
		}	

	}

	public static void savedActivityNotification(){
		Notifications.create().title("Your activity has been saved").text("You can now view your activity").position(Pos.TOP_RIGHT).show();
		
	}

}
