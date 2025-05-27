package ButtonActions;

import java.util.*;

public class ActivityManager {

	private static List<Activity> activities = new ArrayList<>();

	public static void add(Activity a) {
		activities.add(a);
	}

	public static Activity get(int index){
		return activities.get(index);
	}

	public static List<Activity> getAll() {
		return activities;
	}

}
