package ButtonActions;

import java.util.*;

public class ActivityManager {

	private static PriorityQueue<Activity> activities = new PriorityQueue<>();

	public static void add(Activity a) {
		activities.add(a);
	}

	public static boolean remove(Activity a){
		return activities.remove(a);
	}

	public static PriorityQueue<Activity> getAll() {
		return activities;
	}

}
