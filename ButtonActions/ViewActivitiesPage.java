package ButtonActions;

import Styles.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.geometry.*;
import java.util.*;
import java.time.*;
import javafx.collections.*;
import javafx.scene.layout.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import java.text.*;
import javafx.scene.image.*;

public class ViewActivitiesPage {

	private static StackPane rootReference;
	private static BorderPane viewActivitiesPage;

	public static void create(StackPane root) {
		
		rootReference = root;

		// Creates the page itself
		viewActivitiesPage = new BorderPane();
		viewActivitiesPage.setStyle("-fx-background-color: rgb(219,74,64);");

		Button escape = new Button("Return to Main Menu");
		List<Button> buttons = Arrays.asList(escape);
		GeneralStyle.setButtonAnimation(buttons, false);
		escape.setOnAction(e -> root.getChildren().remove(viewActivitiesPage));

		Label noActivities = new Label("No activities have currently been logged.");
		noActivities.setStyle("-fx-text-fill: rgb(219,74,64); -fx-font-weight: Bold; -fx-font-family: 'Sans-serif'; -fx-font-size: 24px;");
		VBox.setMargin(noActivities, new Insets(20));

		VBox scrollableList = new VBox(escape);
		VBox.setMargin(escape, new Insets(20));
		scrollableList.setStyle("-fx-background-color: rgb(245,245,245);");
		scrollableList.setAlignment(Pos.CENTER);

		PriorityQueue<Activity> activities = new PriorityQueue<>(ActivityManager.getAll());
		if (activities.isEmpty())
			scrollableList.getChildren().add(noActivities);
		else { 
			while (!activities.isEmpty())
				scrollableList.getChildren().add(createViewableActivity(activities.poll()));
		}

		ScrollPane scrollBox = new ScrollPane(scrollableList);
		scrollBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		scrollBox.setFitToWidth(true);

		viewActivitiesPage.setCenter(scrollBox);
		scrollableList.setPrefWidth(650);
		scrollableList.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		root.getChildren().add(viewActivitiesPage);

	}


	private static VBox createViewableActivity(Activity activity) {


		// Creates the buttons for checking the activity details or deleting a specific activity
		Button delete_activity = new Button("Delete");
		Button activity_details = new Button("Details");
		//List<Button> buttons = Arrays.asList(delete_activity, activity_details);
		//GeneralStyle.setButtonAnimation(buttons, false);

		
		// Creates teh label containing the location of the activity 
		Label activity_location = new Label(activity.location);


		// Creates the label containing the date and time of the activity
		Label activity_date_time = new Label(parseActivityDate(activity.date) + " @ " + parseActivityTime(activity.time));

		VBox locationAndDate = new VBox();
		if (!activity_location.getText().equals("No Location")) locationAndDate.getChildren().add(activity_location);
		locationAndDate.getChildren().add(activity_date_time);
		locationAndDate.setAlignment(Pos.CENTER);

		// Sets the action for deleting an activity
		delete_activity.setOnAction(e -> {
			ActivityManager.remove(activity);
			rootReference.getChildren().remove(viewActivitiesPage);
			create(rootReference); 
		});

		// Combines the delete and details button, and time/date label into one box
		BorderPane options = new BorderPane();
		options.setLeft(activity_details);
		options.setRight(delete_activity);
		options.setCenter(locationAndDate);

		// Stores and displays activity name
		Label activity_name = new Label(activity.title);
		activity_name.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-weight: Bold; -fx-font-size: 24px;");

		// Stores and displays activity description 
		Label activity_description = new Label();
		activity_description.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 14px;");
		activity_description.setWrapText(true);
		activity_description.setText(activity.description);
		VBox.setMargin(activity_description, new Insets(10));

		// Stores and displays activity distance
		Label activity_distance = new Label(parseActivityDistance(activity.distance));
		activity_distance.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold;");
		Label distance = new Label("Distance");
		VBox displayDistance = new VBox(distance, activity_distance);
		displayDistance.setSpacing(2);

		// Stores and displays activity duration 
		Label activity_duration = new Label(parseActivityDuration(activity.duration));
		activity_duration.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold;");
		Label duration = new Label("Duration");
		VBox displayDuration = new VBox(duration, activity_duration);
		displayDuration.setSpacing(2);

		// Stores and displays activity pace
		VBox displayPace = generateActivityPace(activity.distance, activity.duration);
		displayPace.setSpacing(2);

		// Stores and displays activity heart-rate
		Label activity_hr = new Label();
		if (activity.heartrate != null)  activity_hr.setText(""+activity.heartrate);
		else activity_hr.setText("");
		Label hr = new Label("HR");
		activity_hr.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold;");
		VBox displayHR = new VBox(hr, activity_hr);
		displayHR.setSpacing(2);	

		// Stores and displays run type
		Label activity_type = new Label(activity.runType);
		activity_type.setStyle(generateRunTypeStyle(activity.runType));
		Label type = new Label("Type");
		VBox displayType = new VBox(type, activity_type);
		displayType.setSpacing(2);

		HBox quickStats = new HBox(displayDistance, displayDuration);
		if (!(activity.duration == 0 || activity.distance ==0)) quickStats.getChildren().add(displayPace);
		if (activity.heartrate != null) quickStats.getChildren().add(displayHR);
		if (!activity.runType.equals("Unspecified")) quickStats.getChildren().add(displayType);

		quickStats.setSpacing(25);
		Image mapArt = new Image("/Images/default_map.png", 562, 384, false, true);
		ImageView map = new ImageView();
		map.setImage(mapArt);
		

		VBox activityBox = new VBox(options, activity_name);
		if (!activity.description.trim().equals("")) activityBox.getChildren().add(activity_description);
		activityBox.getChildren().add(quickStats);
		// Adds default map art
		//		activityBox.getChildren().add(map);

		activityBox.setStyle("-fx-background-color: White;"+
				     "-fx-background-radius: 20px;");
		activityBox.setEffect(new DropShadow(4,2,2,Color.BLACK));
		activityBox.setPadding(new Insets(20));
		activityBox.setPrefHeight(Region.USE_PREF_SIZE);
		activityBox.setPrefWidth(600);
		activityBox.setAlignment(Pos.TOP_CENTER);
		activityBox.setSpacing(10);
		VBox.setMargin(activityBox, new Insets(25));
		return activityBox;
	}

	private static VBox generateActivityPace(double distance, int duration) {
		if (distance == 0 || duration == 0) return new VBox();
		int secondsPerMile = Math.round((float)(duration/distance));
		String formattedPace = "";

		if (secondsPerMile/3600 > 0){
			formattedPace += (secondsPerMile/3600) + ":";
			secondsPerMile = secondsPerMile%3600;
		}
		if (secondsPerMile/60 > 0) {
			formattedPace += (secondsPerMile/60) + ":";
			secondsPerMile = secondsPerMile%60;
		}
		if (secondsPerMile == 0)
			formattedPace += "00";
		else if (secondsPerMile < 10) 
			formattedPace += "0" + secondsPerMile;
		else 
			formattedPace += "" + secondsPerMile;

		formattedPace += "/mi";
		Label paceLabel = new Label("Pace");
		Label activity_pace = new Label(formattedPace);
		activity_pace.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold;");
		return new VBox(paceLabel, activity_pace);

	}

	private static String generateRunTypeStyle(String s) {
		String base = ("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold; -fx-text-fill: Black;");
		if (s.equals("Recovery")) base = base.replace("Black", "rgb(0,153,204)");
		else if (s.equals("Race")) base = base.replace("Black", "rgb(219, 74, 64)");
		else if (s.equals("Workout")) base = base.replace("Black", "rgb(265, 165, 0)");
		else if (s.equals("Long Run")) base = base.replace("Black", "rgb(0, 153, 0)");
		return base;
	}

	private static String parseActivityDistance(double distance) {
		DecimalFormat d = new DecimalFormat("#.##");
		return d.format(distance) + " mi";	
	}

	private static String parseActivityDate(String date){
		String[] dateSplit = date.split("-");
		return dateSplit[0] + " " + dateSplit[1] + ", " + dateSplit[2];
	}

	private static String parseActivityTime(String time) {
		String[] timeSplit = time.split("-");
		return timeSplit[0] + ":" + timeSplit[1] + " " + timeSplit[2];
	}

	private static String parseActivityDuration(int duration) {
		int hours = duration/3600;
		duration = duration%3600;
		int minutes = duration/60;
		duration = duration%60;
		int seconds = duration;
		String formattedDuration = "";
		if (hours != 0) formattedDuration += hours + "hr ";
		if (minutes != 0) formattedDuration += minutes + "m ";
		formattedDuration += seconds + "s";
		return formattedDuration;
	}

}
