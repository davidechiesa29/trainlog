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
import javafx.scene.text.*;

public class ViewActivitiesPage {

	private static StackPane rootReference;
	private static VBox viewActivitiesPage;

	public static void create(StackPane root, Filter filter) {
		
		rootReference = root;

		// Creates the page itself
		viewActivitiesPage = new VBox();

		// Adds background to the page
		GeneralStyle.setBackgroundImage(viewActivitiesPage, "Images/map_art.png");

		// Creates options to either return to the main menu or filter the activities
		Button escape = new Button("Return to Main Menu");
		Button filterButton = new Button("Filter");

		// Styles the filter and main menu buttons and adds their actions when pressed
		List<Button> buttons = Arrays.asList(escape, filterButton);
		GeneralStyle.setButtonAnimation(buttons, false,20);
		escape.setOnAction(e -> root.getChildren().remove(viewActivitiesPage));
		filterButton.setOnAction(e -> createFilterMenu());

		// Combines the menu and filter buttons into an HBox and styles the box
		HBox menuOptions = new HBox(escape, filterButton);
		HBox.setMargin(escape, new Insets(25));
		HBox.setMargin(filterButton, new Insets(25));
		menuOptions.setAlignment(Pos.CENTER);
		menuOptions.setMaxWidth(800);
		//menuOptions.setStyle("-fx-background-color:rgb(245,245,245);");
		menuOptions.setStyle("-fx-background-color: transparent;");

		// Creates label to be displayed when no activities are logged
		Label noActivities = new Label("No activities have currently been logged.");
		noActivities.setStyle("-fx-text-fill: rgb(219,74,64); -fx-font-weight: Bold; -fx-font-family: 'Sans-serif'; -fx-font-size: 24px;");
		VBox.setMargin(noActivities, new Insets(20));

		// Creates the box containing the activities that have been logged
		VBox scrollableList = new VBox();
		//scrollableList.setStyle("-fx-background-color: rgb(245,245,245);");
		scrollableList.setStyle("-fx-background-color: transparent;");
		scrollableList.setAlignment(Pos.CENTER);

		// Adds each activity to the box of activities
		PriorityQueue<Activity> activities = new PriorityQueue<>(ActivityManager.getAll());
		if (activities.isEmpty())
			scrollableList.getChildren().add(noActivities);
		else { 
			while (!activities.isEmpty())
				scrollableList.getChildren().add(createViewableActivity(activities.poll()));
		}

		// Wraps the vertical box containing the activities in a scroll pane so that it is scrollable, and styles it
		ScrollPane scrollBox = new ScrollPane(scrollableList);
		scrollBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollBox.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollBox.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		scrollBox.setFitToWidth(true);
		scrollBox.getStyleClass().clear();

		// Wraps the options and the scroll pane into one vertical box and styles it
		VBox scrollBoxAndOptions = new VBox(menuOptions, scrollBox);
		scrollBoxAndOptions.setAlignment(Pos.TOP_CENTER);
		scrollBoxAndOptions.setMaxWidth(800);
		//scrollBoxAndOptions.setStyle("-fx-background-color: rgb(245,245,245);");
		scrollBoxAndOptions.setStyle("-fx-background-color: transparent;");
		scrollBoxAndOptions.setEffect(new DropShadow(1.0,1.0,1.0,Color.GRAY));
		VBox.setVgrow(scrollBoxAndOptions, Priority.ALWAYS);

		// Sets the vertical box at the top center of the page
		viewActivitiesPage.getChildren().add(scrollBoxAndOptions);
		viewActivitiesPage.setAlignment(Pos.TOP_CENTER);

		root.getChildren().add(viewActivitiesPage);

	}


	private static VBox createViewableActivity(Activity activity) {


		// Creates the buttons for checking the activity details or deleting a specific activity
		Button edit_activity = new Button("Edit");
		Button activity_details = new Button("Details");
		List<Button> buttons = Arrays.asList(edit_activity, activity_details);
		GeneralStyle.setButtonAnimation(buttons, false, 12);

		// Creates the label containing the date and time (and possibly location) of the activity
		String dateTimeLocation = parseActivityDate(activity.date) + " @ " + parseActivityTime(activity.time);

		if (!activity.location.equals("No Location")) dateTimeLocation = activity.location + " - " + dateTimeLocation;
		Label dateTimeLocationLabel = new Label(dateTimeLocation);
		dateTimeLocationLabel.setAlignment(Pos.CENTER);
		dateTimeLocationLabel.setStyle("-fx-font-family: 'Sans-serif';");

		// Sets the action for deleting an activity
		edit_activity.setOnAction(e -> {
			ActivityManager.remove(activity);
			rootReference.getChildren().remove(viewActivitiesPage);
			create(rootReference, null); 
		});

		// Combines the delete and details button, and time/date label into one box
		BorderPane options = new BorderPane();
		options.setLeft(activity_details);
		options.setRight(edit_activity);
		options.setCenter(dateTimeLocationLabel);

		// Stores and displays activity name
		Label activity_name = new Label(activity.title);
		activity_name.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-weight: Bold; -fx-font-size: 24px;");

		// Stores and displays activity description 
		Text activity_description = new Text();
		activity_description.setStyle("-fx-font-family: 'Sans-serif'; -fx-font-size: 14px;");
		activity_description.setText(activity.description);
		activity_description.setWrappingWidth(550);
		VBox.setMargin(activity_description, new Insets(10,0,10,0));

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
		Image mapArt = new Image("/Images/map_art.png", 562, 384, false, true);
		ImageView map = new ImageView();
		map.setImage(mapArt);
		

		VBox activityBox = new VBox(options, activity_name);
		if (!activity.description.trim().equals("")) activityBox.getChildren().add(activity_description);
		activityBox.getChildren().add(quickStats);
		// Adds default map art
		//		activityBox.getChildren().add(map);

		activityBox.setStyle("-fx-background-color: White;"+
				     "-fx-background-radius: 10px;");
		activityBox.setEffect(new DropShadow(1,1,1,Color.GRAY));
		activityBox.setPadding(new Insets(20));
		activityBox.setPrefHeight(Region.USE_PREF_SIZE);
		activityBox.setPrefWidth(600);
		activityBox.setMaxWidth(600);
		activityBox.setAlignment(Pos.TOP_CENTER);
		activityBox.setSpacing(10);
		VBox.setMargin(activityBox, new Insets(25));
		VBox.setMargin(options, new Insets(0,0,10,0));
		return activityBox;
	}

	private static void createFilterMenu() {
	
		Label options = new Label("Filter Options");
	
		// Creates aave and cancel buttons for the new filter 
		Button save = new Button("Save");
		Button cancel = new Button("Cancel");
		BorderPane saveOrCancelBox = new BorderPane();
		saveOrCancelBox.setLeft(cancel);
		saveOrCancelBox.setRight(save);
		

		// Creates the filter options
		Label runTypeFilterLabel = new Label("Run Type");
		ComboBox<String> typeOption = new ComboBox<>();
		typeOption.getItems().addAll("Recovery","Workout","Long Run", "Race");
	
		Label distanceFilterLabel = new Label("Distance");

		Label timeFilterLabel = new Label("Time");

		Label dateFilterLabel = new Label("Date");	

		VBox filterMenu = new VBox(options, typeOption, saveOrCancelBox);
		filterMenu.setStyle("-fx-background-color: White;");
		filterMenu.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		save.setOnAction(e -> rootReference.getChildren().remove(filterMenu));
		cancel.setOnAction(e ->rootReference.getChildren().remove(filterMenu));
		filterMenu.setAlignment(Pos.CENTER);

		rootReference.getChildren().add(filterMenu);
		
	}

	/**
	 * Calculates and formats the activity pace
	 */
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

		formattedPace += " /mi";
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

	// Defines a filter for loading the activities
	class Filter {

		String runType; 
		String distance;
		String time;
		String date;

		public Filter(String runType, String distance, String time, String date) {
			this.runType = runType;
			this.distance = distance;
			this.time = time;
			this.date = date;
		}


	}

}
