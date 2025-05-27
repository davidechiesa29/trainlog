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

public class NewActivityPage {
	
	public static void create(StackPane root) {

		BorderPane newActivityPage = new BorderPane();

		// Creates the save and cancel buttons for the activity
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save Activity");

		// Styles save and cancel buttons (without shadow)
		List<Button> saveCancelButtons = new ArrayList<>(Arrays.asList(cancelButton, saveButton));
		GeneralStyle.setButtonAnimation(saveCancelButtons, false);

		// Creates the controls for entering the activity name 
		Label activityNameLabel = new Label("Activity Name");
		TextField activityNameField = new TextField("Daily Run");

		// Styles input field for the activity name
		NewActivityStyle.styleNewActivityText(activityNameField);

		// Combines label and input field for activity name into vertical box
		VBox activityNameControl = new VBox(activityNameLabel, activityNameField);

		// Creates the controls for entering the activity description
		Label activityDescriptionLabel = new Label("Activity Description");
		TextArea activityDescriptionField = new TextArea();	
		
		// Styles the input area for the activity description	
		NewActivityStyle.styleNewActivityText(activityDescriptionField);

		// Combines the label and input area for the activity description into vertical box
		VBox activityDescriptionControl = new VBox(activityDescriptionLabel, activityDescriptionField);
		
		// Handles the distance label and controls	
		Label distanceLabel = new Label("Distance (mi)");

		// Creates the drop down menu to enter the amount of integer mileage
		ComboBox<Integer> distanceSelectionOnes = new ComboBox<>();
		distanceSelectionOnes.getSelectionModel().select(Integer.valueOf(0));
		ObservableList<Integer> runLengthOnes = FXCollections.observableArrayList();
		for (int i = 0; i < 101; i++) runLengthOnes.add(i);
		distanceSelectionOnes.setItems(runLengthOnes);

		// Creates the drop down menu to enter the amount of decimal mileage
		ComboBox<String> distanceSelectionDecimal = new ComboBox<>();
		distanceSelectionDecimal.getSelectionModel().select("00");
		ObservableList<String> runLengthDecimals = FXCollections.observableArrayList();
		for (int i = 0; i<100; i++) {
			if (i < 10) runLengthDecimals.add("0"+i);
			else runLengthDecimals.add(""+i);
		}
		distanceSelectionDecimal.setItems(runLengthDecimals);				
	
		// Creates the decimal that seperates the two dropdowns for the distance
		Label decimal = new Label(".");
		HBox finalDistance = new HBox(distanceSelectionOnes, decimal, distanceSelectionDecimal);
		HBox.setMargin(decimal, new Insets(3));
		decimal.setStyle(
				 "-fx-font-size: 20px;");
		
		VBox distanceControl = new VBox(distanceLabel, finalDistance);

		// Handles the duration label and controls
		Label durationLabel = new Label("Duration");

		// Hours and their input
		ComboBox<Integer> hours = new ComboBox<>();
		hours.getSelectionModel().select(Integer.valueOf(0));
		Label hoursLabel = new Label("hrs");
		ObservableList<Integer> hoursDigit = FXCollections.observableArrayList();
		for (int i = 0; i < 100; i++) hoursDigit.add(i);
		hours.setItems(hoursDigit);
		HBox hoursControl = new HBox(hours, hoursLabel);
		HBox.setMargin(hoursLabel, new Insets(0,4,0,2));
		
		// minutes and their input
		ComboBox<Integer> minutes = new ComboBox<>();
		minutes.getSelectionModel().select(Integer.valueOf(0));
		Label minutesLabel = new Label("min");
		ObservableList<Integer> minDigit = FXCollections.observableArrayList();
		for (int i = 0; i < 60; i++) minDigit.add(i);
		minutes.setItems(minDigit);
		HBox minutesControl = new HBox(minutes, minutesLabel);
		HBox.setMargin(minutesLabel, new Insets(0,4,0,2));
		
		// Seconds and their input
		ComboBox<Integer> seconds = new ComboBox<>();
		seconds.getSelectionModel().select(Integer.valueOf(0));
		Label secondsLabel = new Label("s");
		ObservableList<Integer> secondsDigit = FXCollections.observableArrayList();
		for (int i =0; i < 60; i++) secondsDigit.add(i);
		seconds.setItems(secondsDigit);
		HBox secondsControl = new HBox(seconds, secondsLabel);	
		HBox.setMargin(secondsLabel, new Insets(0,4,0,2));
	
		// Combines seconds, minutes, and hours into one box
		HBox durationSelection = new HBox(hoursControl, minutesControl, secondsControl);
		VBox durationControl = new VBox(durationLabel, durationSelection);


		// Handles heart-rate label and controls
		Label heartrateLabel = new Label("Heart-rate");
		ComboBox<Integer> bpm = new ComboBox<>();
		Label beats = new Label("bpm");
		ObservableList<Integer> bpmDigits = FXCollections.observableArrayList();
		for (int i=50;i < 221; i++) bpmDigits.add(i);
		bpm.setItems(bpmDigits);
		HBox bpmSelection = new HBox(bpm, beats);
		HBox.setMargin(beats, new Insets(0,4,0,2));
		VBox heartrateControl = new VBox(heartrateLabel, bpmSelection);
		
		// Handles location label and controls
		Label locationLabel = new Label("Activity Location");
		TextField locationField = new TextField("No Location"); // temporary, want to make one that uses google places API
		NewActivityStyle.styleNewActivityText(locationField);
		VBox locationControl = new VBox(locationLabel, locationField);

		// Handles Date & Time label and Controls
		Label dateAndTimeLabel = new Label("Date & Time");

		// Handles month 
		ComboBox<String> month = new ComboBox<>();
		month.getSelectionModel().select(NewActivityStyle.formatMonth(LocalDate.now().getMonth().name()));
		month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

		// Handles day of the month
		ComboBox<Integer> day = new ComboBox<>();
		day.getSelectionModel().select(Integer.valueOf(LocalDate.now().getDayOfMonth()));
		ObservableList<Integer> days = FXCollections.observableArrayList();
		for (int i =1; i<32; i++) days.add(i);
		day.setItems(days);

		// Handles upload year
		ComboBox<Integer> year = new ComboBox<>();
		year.getSelectionModel().select(Integer.valueOf(Year.now().getValue()));
		ObservableList<Integer> years = FXCollections.observableArrayList();
		for (int i = 2024; i <= Year.now().getValue(); i++) years.add(i);
		year.setItems(years);

		// Handles upload hour
		ComboBox<Integer> currentHour = new ComboBox<>();
		currentHour.getSelectionModel().select(Integer.valueOf(NewActivityStyle.formatHour(LocalTime.now().getHour())));
		ObservableList<Integer> dayHours = FXCollections.observableArrayList();
		for (int i = 1; i <13; i++) dayHours.add(i);
		currentHour.setItems(dayHours);

		Label timeSeperator = new Label(":");	
		timeSeperator.setStyle(
				 "-fx-font-size: 20px;");
		
		// Handles upload minute
		ComboBox<String> currentMin = new ComboBox<>();

		// Parses the minute
		int minute = Integer.valueOf(LocalTime.now().getMinute());
		String parsedMinute;
		if (minute < 10) parsedMinute = "0" + minute;
		else parsedMinute = "" + minute;

		currentMin.getSelectionModel().select(parsedMinute);
		ObservableList<String> dayMinutes = FXCollections.observableArrayList();
		for (int i = 0; i<60; i++) {
			if (i < 10) dayMinutes.add("0" + i);
			else dayMinutes.add(""+i);
		}
		currentMin.setItems(dayMinutes);

		// Handles upload PM or AM
		ComboBox amOrPm = new ComboBox();
		boolean AM = true;
		if (LocalTime.now().getHour() >= 12) AM = false;
		if (AM) amOrPm.getSelectionModel().select("AM");
		else amOrPm.getSelectionModel().select("PM");
		amOrPm.getItems().addAll("AM","PM");
		
		HBox timeSelection = new HBox(currentHour, timeSeperator, currentMin, amOrPm);
		HBox.setMargin(currentMin, new Insets(0,2,0,0));
		HBox.setMargin(timeSeperator, new Insets(2));
		HBox dateSelection = new HBox(month,day,year);
		HBox.setMargin(month, new Insets(0,2,0,0));
		HBox.setMargin(day, new Insets(0,2,0,0));
		VBox dateAndTimeControl = new VBox(dateAndTimeLabel, dateSelection, timeSelection);
		VBox.setMargin(timeSelection, new Insets(5,0,0,0));
		

		// Handles run type label and controls
		Label runTypeLabel = new Label("Run Type");
		ComboBox<String> possibleTypes = new ComboBox<>();
		possibleTypes.getSelectionModel().select("Unspecified");
		ObservableList<String> types = FXCollections.observableArrayList();
		types.add("Unspecified");
		types.add("Recovery Run");
		types.add("Long Run");
		types.add("Workout");
		types.add("Race");
		possibleTypes.setItems(types);
		VBox runTypeControl = new VBox(runTypeLabel, possibleTypes);

		// Handles weather data controls
		Label weatherData = new Label("Weather Data");
		ComboBox<String> includeWeatherData = new ComboBox<>();
		includeWeatherData.getItems().addAll("Include", "Do Not Include");
		includeWeatherData.getSelectionModel().select("Include");
		VBox weatherControl = new VBox(weatherData, includeWeatherData);

		// Dynamic title controls
		Label dynamicTitle = new Label("Generate Dynamic Title");
		ComboBox<String> generateDynamicTitle = new ComboBox<>();
		generateDynamicTitle.getItems().addAll("Yes", "No");
		generateDynamicTitle.getSelectionModel().select("No");
		VBox dynamicTitleControl = new VBox(dynamicTitle, generateDynamicTitle);

		// Styles all the drop downs and labels
		List<Label> labels = Arrays.asList(activityNameLabel, activityDescriptionLabel, distanceLabel, durationLabel, heartrateLabel,
			       	locationLabel, dateAndTimeLabel, runTypeLabel, weatherData, dynamicTitle);
	       	NewActivityStyle.styleNewActivityLabel(labels);	
		List<ComboBox> dropdowns = Arrays.asList(distanceSelectionOnes, distanceSelectionDecimal, hours, minutes, seconds, bpm, month, day, year, 
				currentHour, currentMin, amOrPm, possibleTypes, includeWeatherData, generateDynamicTitle);
		NewActivityStyle.styleNewActivityDropDown(dropdowns);

		// Combines all of the extra controls (location, date/time, run type, and weather) into one horizontal box
		HBox extraControls = new HBox(locationControl, dateAndTimeControl, runTypeControl, weatherControl);
		// Adds appropriate spacing to each control within the previous horizontal box
		for (Node child : extraControls.getChildren()) HBox.setMargin(child, new Insets(10)); 	

		// Creates a border pane to position the cancel and save buttons at the left and right hands of the new activity menu
		BorderPane cancelOrSave = new BorderPane();
		cancelOrSave.setLeft(cancelButton);
		cancelOrSave.setRight(saveButton);				

		HBox quickData = new HBox(distanceControl, durationControl, heartrateControl);
		for (Node child : quickData.getChildren()) HBox.setMargin(child, new Insets(0,10,0,10)); // Adds appropriate spacing/margin between the controls
												      
		VBox activity = new VBox(activityNameControl,
					quickData, activityDescriptionControl,
					extraControls, dynamicTitleControl, cancelOrSave);

		// Adds appropriate spacing for controls in the activity
		VBox.setMargin(activityDescriptionControl, new Insets(10, 10, 0, 10));					
		VBox.setMargin(activityNameControl, new Insets(10));
		VBox.setMargin(dynamicTitleControl, new Insets(0,0, 20, 10));
		VBox.setMargin(cancelOrSave, new Insets(10));

		// Styles the activity menu background, adds shadow
		NewActivityStyle.styleNewActivityMenuBackground(activity);

		newActivityPage.setCenter(activity);
		activity.setAlignment(Pos.CENTER);
		newActivityPage.setStyle("-fx-background-color: rgb(219,74,64);");
		root.getChildren().add(newActivityPage);

		// Makes sure that the activity window uses up the smallest size possible
		activity.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

		// Sets action for the cancel butotn
		cancelButton.setOnAction(cancel -> handleCancelButtonAction(root, newActivityPage));

		// Sets action for the save button
		saveButton.setOnAction(save -> {

			// Saves all of the values entered
			String name = activityNameField.getText();
			String description = activityDescriptionField.getText();
			double distance = (distanceSelectionOnes.getValue().doubleValue()) + ((double)(Integer.parseInt(distanceSelectionDecimal.getValue())))/100;
			int duration = (hours.getValue())*3600 + (minutes.getValue())*60 + seconds.getValue();
			Integer heartrate = bpm.getValue();
			String location = locationField.getText();
			String date = month.getValue() + "-" + day.getValue() + "-" + year.getValue();
			String time = currentHour.getValue() + "-" + currentMin.getValue() + "-" + amOrPm.getValue();
			String runType = possibleTypes.getValue();
			boolean incWeatherInfo = false;
			if (includeWeatherData.getValue().equals("Include")) incWeatherInfo = true;
			boolean incDynamicTitle = false;
			if (generateDynamicTitle.getValue().equals("Yes")) incDynamicTitle = true;

			// Creates a new activity with the stored information
			Activity savedActivity = new Activity(name, distance, duration, heartrate, description,
				location, date, time, runType, incWeatherInfo, incDynamicTitle);
			// Saves the activity in a list of activities
			ActivityManager.add(savedActivity);

			// Removes the new activity page from the screen
			root.getChildren().remove(newActivityPage);
					
		});
	}

	private static void handleCancelButtonAction(StackPane root, BorderPane newActivityPage) {

		root.getChildren().remove(newActivityPage);
	}

}	
