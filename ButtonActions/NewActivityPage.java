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
		Button cancelButton = new Button("Cancel");
		Button saveButton = new Button("Save Activity");
		List<Button> saveCancelButtons = new ArrayList<>(Arrays.asList(cancelButton, saveButton));
		GeneralStyle.setButtonAnimation(saveCancelButtons, false);

		Label activityNameLabel = new Label("Activity Name");
		NewActivityStyle.styleNewActivityLabel(activityNameLabel);
		TextField activityNameField = new TextField("Daily Run");
		NewActivityStyle.styleNewActivityText(activityNameField);
		VBox activityNameControl = new VBox(activityNameLabel, activityNameField);

		Label activityDescriptionLabel = new Label("Activity Description");
		NewActivityStyle.styleNewActivityLabel(activityDescriptionLabel);
		TextArea activityDescriptionField = new TextArea();		
		NewActivityStyle.styleNewActivityText(activityDescriptionField);
		VBox activityDescriptionControl = new VBox(activityDescriptionLabel, activityDescriptionField);
		
		// Handles the distance label and controls	
		Label distanceLabel = new Label("Distance (mi)");
		NewActivityStyle.styleNewActivityLabel(distanceLabel);

		ComboBox distanceSelectionOnes = new ComboBox();
		distanceSelectionOnes.getSelectionModel().select(Integer.valueOf(0));
		NewActivityStyle.styleNewActivityDropDown(distanceSelectionOnes);
		ObservableList<Integer> runLengthOnes = FXCollections.observableArrayList();
		for (int i = 0; i < 101; i++) runLengthOnes.add(i);
		distanceSelectionOnes.setItems(runLengthOnes);

		ComboBox distanceSelectionDecimal = new ComboBox();
		distanceSelectionDecimal.getSelectionModel().select("00");
		NewActivityStyle.styleNewActivityDropDown(distanceSelectionDecimal);
		ObservableList<String> runLengthDecimals = FXCollections.observableArrayList();
		for (int i = 0; i<100; i++) {
			if (i < 10) runLengthDecimals.add("0"+i);
			else runLengthDecimals.add(""+i);
		}
		distanceSelectionDecimal.setItems(runLengthDecimals);				
	
		Label decimal = new Label(".");
		HBox finalDistance = new HBox(distanceSelectionOnes, decimal, distanceSelectionDecimal);
		HBox.setMargin(decimal, new Insets(3));
		decimal.setStyle(
				 "-fx-font-size: 20px;");
		
		VBox distanceControl = new VBox(distanceLabel, finalDistance);

		// Handles the duration label and controls
		Label durationLabel = new Label("Duration");
		NewActivityStyle.styleNewActivityLabel(durationLabel);

		// Hours and their input
		ComboBox hours = new ComboBox();
		hours.getSelectionModel().select(Integer.valueOf(0));
		NewActivityStyle.styleNewActivityDropDown(hours);
		Label hoursLabel = new Label("hrs");
		ObservableList<Integer> hoursDigit = FXCollections.observableArrayList();
		for (int i = 0; i < 100; i++) hoursDigit.add(i);
		hours.setItems(hoursDigit);
		HBox hoursControl = new HBox(hours, hoursLabel);
		HBox.setMargin(hoursLabel, new Insets(0,4,0,2));
		
		// minutes and their input
		ComboBox minutes = new ComboBox();
		minutes.getSelectionModel().select(Integer.valueOf(0));
		NewActivityStyle.styleNewActivityDropDown(minutes);
		Label minutesLabel = new Label("min");
		ObservableList<Integer> minDigit = FXCollections.observableArrayList();
		for (int i = 0; i < 60; i++) minDigit.add(i);
		minutes.setItems(minDigit);
		HBox minutesControl = new HBox(minutes, minutesLabel);
		HBox.setMargin(minutesLabel, new Insets(0,4,0,2));
		
		// Seconds and their input
		ComboBox seconds = new ComboBox();
		seconds.getSelectionModel().select(Integer.valueOf(0));
		NewActivityStyle.styleNewActivityDropDown(seconds);
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
		NewActivityStyle.styleNewActivityLabel(heartrateLabel);

		ComboBox bpm = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(bpm);
		Label beats = new Label("bpm");
		ObservableList<Integer> bpmDigits = FXCollections.observableArrayList();
		for (int i=50; i < 220; i++) bpmDigits.add(i);
		bpm.setItems(bpmDigits);
		HBox bpmSelection = new HBox(bpm, beats);
		HBox.setMargin(beats, new Insets(0,4,0,2));
		VBox heartrateControl = new VBox(heartrateLabel, bpmSelection);
		
		// Handles location label and controls
		Label locationLabel = new Label("Activity Location");
		NewActivityStyle.styleNewActivityLabel(locationLabel);
		TextField locationField = new TextField("No Location"); // temporary, want to make one that uses google places API
		NewActivityStyle.styleNewActivityText(locationField);
		VBox locationControl = new VBox(locationLabel, locationField);

		// Handles Date & Time label and Controls
		Label dateAndTimeLabel = new Label("Date & Time");
		NewActivityStyle.styleNewActivityLabel(dateAndTimeLabel);

		// Handles month 
		ComboBox month = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(month);
		month.getSelectionModel().select(NewActivityStyle.formatMonth(LocalDate.now().getMonth().name()));
		month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

		// Handles day of the month
		ComboBox day = new ComboBox<>();
		NewActivityStyle.styleNewActivityDropDown(day);
		day.getSelectionModel().select(Integer.valueOf(LocalDate.now().getDayOfMonth()));
		ObservableList<Integer> days = FXCollections.observableArrayList();
		for (int i =1; i<32; i++) days.add(i);
		day.setItems(days);

		// Handles upload year
		ComboBox year = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(year);
		year.getSelectionModel().select(Integer.valueOf(Year.now().getValue()));
		ObservableList<Integer> years = FXCollections.observableArrayList();
		for (int i = 2024; i <= Year.now().getValue(); i++) years.add(i);
		year.setItems(years);

		// Handles upload hour
		ComboBox currentHour = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(currentHour);
		currentHour.getSelectionModel().select(Integer.valueOf(NewActivityStyle.formatHour(LocalTime.now().getHour())));
		ObservableList<Integer> dayHours = FXCollections.observableArrayList();
		for (int i = 1; i <13; i++) dayHours.add(i);
		currentHour.setItems(dayHours);

		Label timeSeperator = new Label(":");	
		timeSeperator.setStyle(
				 "-fx-font-size: 20px;");
		
		// Handles upload minute
		ComboBox currentMin = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(currentMin);

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
		NewActivityStyle.styleNewActivityDropDown(amOrPm);
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
		NewActivityStyle.styleNewActivityLabel(runTypeLabel);
		ComboBox possibleTypes = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(possibleTypes);
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
		NewActivityStyle.styleNewActivityLabel(weatherData);
		ComboBox includeWeatherData = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(includeWeatherData);
		includeWeatherData.getItems().addAll("Include", "Do Not Include");
		includeWeatherData.getSelectionModel().select("Include");
		VBox weatherControl = new VBox(weatherData, includeWeatherData);

		// Dynamic title controls
		Label dynamicTitle = new Label("Generate Dynamic Title");
		NewActivityStyle.styleNewActivityLabel(dynamicTitle);
		ComboBox generateDynamicTitle = new ComboBox();
		NewActivityStyle.styleNewActivityDropDown(generateDynamicTitle);
		generateDynamicTitle.getItems().addAll("Yes", "No");
		generateDynamicTitle.getSelectionModel().select("No");
		VBox dynamicTitleControl = new VBox(dynamicTitle, generateDynamicTitle);

		// Combines all of the extra controls
		HBox extraControls = new HBox(locationControl, dateAndTimeControl, runTypeControl, weatherControl);
		for (Node child : extraControls.getChildren()) HBox.setMargin(child, new Insets(10)); // Adds appropriate spacing/margin between the controls	

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

		// Styles the activity menu
		activity.setStyle("-fx-background-color: White;"+
				  "-fx-padding: 20 20 20 20;" +
				  "-fx-background-radius: 20px;");
		DropShadow activityMenuShadow = new DropShadow();
		activityMenuShadow.setOffsetX(2.0);
		activityMenuShadow.setOffsetY(2.0);
		activityMenuShadow.setColor(Color.BLACK);
		activityMenuShadow.setRadius(4.0);
		activity.setEffect(activityMenuShadow);

		newActivityPage.setCenter(activity);
		activity.setAlignment(Pos.CENTER);
		newActivityPage.setStyle("-fx-background-color: rgb(219,74,64);");
		root.getChildren().add(newActivityPage);

		// Makes sure that the activity window uses up the smallest size possible
		activity.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

		// Sets action for the cancel butotn
		cancelButton.setOnAction(cancelEvent -> root.getChildren().remove(newActivityPage));

		// Sets action for the save button
		saveButton.setOnAction(saveEvent -> {
			root.getChildren().remove(newActivityPage);
		});
	}
}	
