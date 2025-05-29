package ButtonActions;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;

public class ActivityManager {

	private static PriorityQueue<Activity> activities = new PriorityQueue<>();

	public static void add(Activity a) {
		activities.add(a);
		saveActivities();
	}

	public static boolean remove(Activity a){
		boolean removed = activities.remove(a);
		saveActivities();
		return removed;
	}

	public static PriorityQueue<Activity> getAll() {
		return activities;
	}

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

}
