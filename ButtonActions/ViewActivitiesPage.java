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

		viewActivitiesPage = new BorderPane();
		viewActivitiesPage.setStyle("-fx-background-color: rgb(219,74,64);");

		Button escape = new Button("Return to Main Menu");
		List<Button> buttons = Arrays.asList(escape);
		GeneralStyle.setButtonAnimation(buttons, false);
		escape.setOnAction(e -> root.getChildren().remove(viewActivitiesPage));

		VBox scrollableList = new VBox(escape);
		VBox.setMargin(escape, new Insets(20));
		scrollableList.setStyle("-fx-background-color: rgb(245,245,245);");
		scrollableList.setAlignment(Pos.CENTER);

		for (Activity a : ActivityManager.getAll()) {
			scrollableList.getChildren().add(createViewableActivity(a));
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

		Button delete_activity = new Button("Delete");
		Label activity_details = new Label("Details");
		Label activity_date_time = new Label(parseActivityDate(activity.date) + " @ " + parseActivityTime(activity.time));

		delete_activity.setOnAction(e -> {
			ActivityManager.remove(activity);
			rootReference.getChildren().remove(viewActivitiesPage);
			create(rootReference); 
		});

		BorderPane options = new BorderPane();
		options.setLeft(activity_details);
		options.setRight(delete_activity);
		options.setCenter(activity_date_time);
		//VBox.setMargin(options, new Insets(10));

		Label activity_name = new Label(activity.title);
		activity_name.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-weight: Bold; -fx-font-size: 24px;");

		Label activity_description = new Label(activity.description);
		activity_description.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 14px;");

		Label activity_distance = new Label(parseActivityDistance(activity.distance));
		Label activity_duration = new Label(parseActivityDuration(activity.duration));
		activity_distance.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold;");
		activity_duration.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold;");
		Label activity_pace = new Label(generateActivityPace(activity.distance, activity.duration));
		activity_pace.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 20px; -fx-font-weight: Bold;");

		HBox quickStats = new HBox(activity_distance, activity_duration, activity_pace);
		quickStats.setSpacing(25);
		Image mapArt = new Image("/Images/default_map.png", 562, 384, false, true);
		ImageView map = new ImageView();
		map.setImage(mapArt);
		

		VBox activityBox = new VBox(options, activity_name, activity_description, quickStats);
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

	private static String generateActivityPace(double distance, int duration) {
		if (distance == 0 || duration == 0) return "";
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

		return formattedPace += "/mi";

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
