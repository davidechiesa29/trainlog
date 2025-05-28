package ButtonActions;

import java.util.*;

public class ActivityManager {

	private static List<Activity> activities = new LinkedList<>();

	public static void add(Activity a) {
		activities.add(0,a);
		Collections.sort(activities);
	}

	public static Activity get(int index){
		return activities.get(index);
	}

	public static boolean remove(Activity a){
		return activities.remove(a);
	}

	public static List<Activity> getAll() {
		return activities;
	}

}
