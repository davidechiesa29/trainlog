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

	public static void create(StackPane root) {
		
		BorderPane viewActivitiesPage = new BorderPane();
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
		viewActivitiesPage.setCenter(scrollBox);
		scrollBox.setFitToWidth(true);
		scrollableList.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		root.getChildren().add(viewActivitiesPage);

	}


	private static VBox createViewableActivity(Activity activity) {

		Label edit_activity = new Label("Edit");
		Label activity_details = new Label("Details");
		Label activity_date = new Label(activity.date);
		BorderPane options = new BorderPane();
		options.setLeft(activity_details);
		options.setRight(edit_activity);
		options.setCenter(activity_date);
		VBox.setMargin(options, new Insets(10));

		Label activity_name = new Label(activity.title);
		activity_name.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-weight: Bold; -fx-font-size: 24px;");

		Label activity_description = new Label(activity.description);
		activity_description.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 14px;");

		Label activity_distance = new Label(parseActivityDistance(activity.distance));
		Label activity_duration = new Label(parseActivityDuration(activity.duration));
		Label activity_pace = new Label("0");

		HBox quickStats = new HBox(activity_distance, activity_duration, activity_pace);
		Image mapArt = new Image("/Images/default_map.png", 562, 384, false, true);
		ImageView map = new ImageView();
		map.setImage(mapArt);
		

		VBox activityBox = new VBox(options, activity_name, activity_description, quickStats);
		activityBox.setStyle("-fx-background-color: White;"+
				     "-fx-background-radius: 20px;");
		activityBox.setEffect(new DropShadow(4,2,2,Color.BLACK));
		activityBox.setPadding(new Insets(20));
		activityBox.setPrefHeight(300);
		activityBox.setPrefWidth(600);
		activity_name.setAlignment(Pos.CENTER);
		VBox.setMargin(activityBox, new Insets(40));
		return activityBox;
	}

	private static String parseActivityDistance(double distance) {
		DecimalFormat d = new DecimalFormat("#.##");
		return d.format(distance);	
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
