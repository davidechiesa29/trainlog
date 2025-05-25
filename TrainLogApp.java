import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos; 
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.time.*;
import javafx.collections.*;

public class TrainLogApp extends Application {

	public static void main(String[] args){	
		Application.launch();	
	}

	public void start(Stage stage) {

		// Sets title of the window
		stage.setTitle("RunStata");
		
		// Creates the loading screen
		BorderPane loadingScreen = new BorderPane();

		// Sets the background of the loading screen black
		loadingScreen.setStyle("-fx-background-color: Black");

		// Creates app logo and styles it
		Label appLogo = new Label("RunStata");
		appLogo.setStyle("-fx-font-family: Sans Serif; -fx-font-weight:bold; -fx-text-fill: rgb(219, 74,64); -fx-font-size: 150px;");

		VBox loadingMenu = new VBox(appLogo);
		loadingScreen.setCenter(loadingMenu);
		loadingMenu.setAlignment(Pos.CENTER);

		stage.setScene(new Scene(loadingScreen, 1000, 650));
		stage.show();
			
		// Causes the logo to fade in for 2 seconds
		FadeTransition logoFadeIn = new FadeTransition(Duration.seconds(2), loadingMenu);
		logoFadeIn.setFromValue(0);
		logoFadeIn.setToValue(1);
		logoFadeIn.play();
	
		// Pauses the logo in the center of the screen for oen second
		PauseTransition logoStay = new PauseTransition(Duration.seconds(1));
		logoFadeIn.setOnFinished(e ->logoStay.play());
			
		// Makes the logo fade out for two seconds
		FadeTransition logoFadeOut = new FadeTransition(Duration.seconds(2), loadingMenu);
		logoFadeOut.setFromValue(1);
		logoFadeOut.setToValue(0);
		logoStay.setOnFinished(e -> logoFadeOut.play());

		// Handles events after the logo fades out
		logoFadeOut.setOnFinished(e -> {
	
			StackPane root = new StackPane();			
	
			// Creates the background for the home page
			Image image = new Image("Images/Background5.png");
			BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1000, 650, true, true, false, true));		
			// Creates buttons on the home screen and application name
			Label appName = new Label("RunStata");
			Button newActivity = new Button("Log Activity");
			Button viewActivities = new Button("View Activities");
			Button statistics = new Button("View Statistics");
			Button records = new Button("Personal Records");
			Button quit = new Button("Quit");

			// Sets preferred sizes for the buttons
			newActivity.setPrefWidth(250);
			viewActivities.setPrefWidth(250);
			statistics.setPrefWidth(250);
			records.setPrefWidth(250);
			quit.setPrefWidth(250);
			newActivity.setPrefHeight(60);
			viewActivities.setPrefHeight(60);
			statistics.setPrefHeight(60);
			records.setPrefHeight(60);
			quit.setPrefHeight(60);

			// Create app title and shadow
			appName.setStyle("-fx-font-family: Sans Serif; -fx-font-weight:bold; -fx-text-fill: rgb(219, 74,64); -fx-font-size: 150px;");
			DropShadow appNameShadow = new DropShadow();
			appNameShadow.setOffsetX(2.0);
			appNameShadow.setOffsetY(2.0);
			appNameShadow.setColor(Color.BLACK);
			appNameShadow.setRadius(4.0);
			appName.setEffect(appNameShadow);
	
			// Sets styling and effects for when buttons are hovered over or pressed
			setMenuButtonAnimation(newActivity);
			setMenuButtonAnimation(viewActivities);
			setMenuButtonAnimation(statistics);
			setMenuButtonAnimation(records);
			setMenuButtonAnimation(quit);

			newActivity.setOnAction(event -> {
				BorderPane newActivityPage = new BorderPane();
				Button cancelButton = new Button("Cancel");
				setMenuButtonAnimation(cancelButton);
				Button saveButton = new Button("Save Activity");
				setMenuButtonAnimation(saveButton);

				Label activityNameLabel = new Label("Activity Name");
				styleNewActivityLabel(activityNameLabel);
				TextField activityNameField = new TextField("Daily Run");
				styleNewActivityText(activityNameField);
				VBox activityNameControl = new VBox(activityNameLabel, activityNameField);

				Label activityDescriptionLabel = new Label("Activity Description");
				styleNewActivityLabel(activityDescriptionLabel);
				TextArea activityDescriptionField = new TextArea();		
				styleNewActivityText(activityDescriptionField);
				VBox activityDescriptionControl = new VBox(activityDescriptionLabel, activityDescriptionField);
				
				// Handles the distance label and controls	
				Label distanceLabel = new Label("Distance (mi)");
				styleNewActivityLabel(distanceLabel);

				ComboBox distanceSelectionOnes = new ComboBox();
				distanceSelectionOnes.getSelectionModel().select(Integer.valueOf(0));
				styleNewActivityDropDown(distanceSelectionOnes);
				ObservableList<Integer> runLengthOnes = FXCollections.observableArrayList();
				for (int i = 0; i < 101; i++) runLengthOnes.add(i);
				distanceSelectionOnes.setItems(runLengthOnes);

				ComboBox distanceSelectionDecimal = new ComboBox();
				distanceSelectionDecimal.getSelectionModel().select("00");
				styleNewActivityDropDown(distanceSelectionDecimal);
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
				styleNewActivityLabel(durationLabel);

				// Hours and their input
				ComboBox hours = new ComboBox();
				hours.getSelectionModel().select(Integer.valueOf(0));
				styleNewActivityDropDown(hours);
				Label hoursLabel = new Label("hrs");
				ObservableList<Integer> hoursDigit = FXCollections.observableArrayList();
				for (int i = 0; i < 100; i++) hoursDigit.add(i);
				hours.setItems(hoursDigit);
				HBox hoursControl = new HBox(hours, hoursLabel);
				HBox.setMargin(hoursLabel, new Insets(0,4,0,2));
				
				// minutes and their input
				ComboBox minutes = new ComboBox();
				minutes.getSelectionModel().select(Integer.valueOf(0));
				styleNewActivityDropDown(minutes);
				Label minutesLabel = new Label("min");
				ObservableList<Integer> minDigit = FXCollections.observableArrayList();
				for (int i = 0; i < 60; i++) minDigit.add(i);
				minutes.setItems(minDigit);
				HBox minutesControl = new HBox(minutes, minutesLabel);
				HBox.setMargin(minutesLabel, new Insets(0,4,0,2));
			
				// Seconds and their input
				ComboBox seconds = new ComboBox();
				seconds.getSelectionModel().select(Integer.valueOf(0));
				styleNewActivityDropDown(seconds);
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
				styleNewActivityLabel(heartrateLabel);

				ComboBox bpm = new ComboBox();
				styleNewActivityDropDown(bpm);
				Label beats = new Label("bpm");
				ObservableList<Integer> bpmDigits = FXCollections.observableArrayList();
				for (int i=50; i < 220; i++) bpmDigits.add(i);
				bpm.setItems(bpmDigits);
				HBox bpmSelection = new HBox(bpm, beats);
				HBox.setMargin(beats, new Insets(0,4,0,2));
				VBox heartrateControl = new VBox(heartrateLabel, bpmSelection);
		
				// Handles location label and controls
				Label locationLabel = new Label("Activity Location");
				styleNewActivityLabel(locationLabel);
				TextField locationField = new TextField("No Location"); // temporary, want to make one that uses google places API
				styleNewActivityText(locationField);
				VBox locationControl = new VBox(locationLabel, locationField);

				// Handles Date & Time label and Controls
				Label dateAndTimeLabel = new Label("Date & Time");
				styleNewActivityLabel(dateAndTimeLabel);

				// Handles month 
				ComboBox month = new ComboBox();
				styleNewActivityDropDown(month);
				month.getSelectionModel().select(formatMonth(LocalDate.now().getMonth().name()));
				month.getItems().addAll("January","February","March","April","May","June","July","August","September","October","November","December");

				// Handles day of the month
				ComboBox day = new ComboBox<>();
				styleNewActivityDropDown(day);
				day.getSelectionModel().select(Integer.valueOf(LocalDate.now().getDayOfMonth()));
				ObservableList<Integer> days = FXCollections.observableArrayList();
				for (int i =1; i<32; i++) days.add(i);
				day.setItems(days);

				// Handles upload year
				ComboBox year = new ComboBox();
				styleNewActivityDropDown(year);
				year.getSelectionModel().select(Integer.valueOf(Year.now().getValue()));
				ObservableList<Integer> years = FXCollections.observableArrayList();
				for (int i = 2024; i <= Year.now().getValue(); i++) years.add(i);
				year.setItems(years);

				// Handles upload hour
				ComboBox currentHour = new ComboBox();
				styleNewActivityDropDown(currentHour);
				currentHour.getSelectionModel().select(Integer.valueOf(formatHour(LocalTime.now().getHour())));
				ObservableList<Integer> dayHours = FXCollections.observableArrayList();
				for (int i = 1; i <13; i++) dayHours.add(i);
				currentHour.setItems(dayHours);

				Label timeSeperator = new Label(":");	
				timeSeperator.setStyle(
						 "-fx-font-size: 20px;");
				
				// Handles upload minute
				ComboBox currentMin = new ComboBox();
				styleNewActivityDropDown(currentMin);

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
				styleNewActivityDropDown(amOrPm);
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
				styleNewActivityLabel(runTypeLabel);
				ComboBox possibleTypes = new ComboBox();
				styleNewActivityDropDown(possibleTypes);
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
				styleNewActivityLabel(weatherData);
				ComboBox includeWeatherData = new ComboBox();
				styleNewActivityDropDown(includeWeatherData);
				includeWeatherData.getItems().addAll("Include", "Do Not Include");
				includeWeatherData.getSelectionModel().select("Include");
				VBox weatherControl = new VBox(weatherData, includeWeatherData);

				// Dynamic title controls
				Label dynamicTitle = new Label("Generate Dynamic Title");
				styleNewActivityLabel(dynamicTitle);
				ComboBox generateDynamicTitle = new ComboBox();
				styleNewActivityDropDown(generateDynamicTitle);
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
			});
			

			// Creates a shadow that will be used for the buttons
			DropShadow buttonShadow = new DropShadow();
			buttonShadow.setOffsetX(2.0);
			buttonShadow.setOffsetY(2.0);
			buttonShadow.setColor(Color.BLACK);
			buttonShadow.setRadius(2.0);
		
			// Applies the previously created button shadow to the buttons	
			newActivity.setEffect(buttonShadow);
			viewActivities.setEffect(buttonShadow);
			statistics.setEffect(buttonShadow);
			records.setEffect(buttonShadow);
			quit.setEffect(buttonShadow);
	
			// Sets margins around all of the buttons on the home screen so that they aren't stuck together
			VBox.setMargin(newActivity, new Insets(2, 2, 2, 2));
			VBox.setMargin(viewActivities, new Insets(2, 2, 2, 2));
			VBox.setMargin(statistics, new Insets(2, 2, 2, 2));
			VBox.setMargin(records, new Insets(2, 2, 2, 2));
			VBox.setMargin(quit, new Insets(2, 2, 2, 2));
			VBox.setMargin(appName, new Insets(50,0,70,0));

			// Creates the box containing all of the menu buttons and centers it
			VBox menu = new VBox(appName, newActivity, viewActivities, statistics, records, quit);
			
			menu.setAlignment(Pos.CENTER);

			// Handles event in which quit button is pressed
			quit.setOnAction(event -> {
				BorderPane quitVerification = new BorderPane();
				Label question = new Label("Are you sure you want to quit?");
				question.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-font-family: 'Sans Serif';");
				Button yes = new Button("Yes");
				Button no = new Button("No");
				// yes.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
				setMenuButtonAnimation(yes);
				// no.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
				setMenuButtonAnimation(no);
				yes.setPrefWidth(100);
				yes.setPrefHeight(50);
				no.setPrefWidth(100);
				no.setPrefHeight(50);
				HBox options = new HBox(yes, no);
				HBox.setMargin(yes, new Insets(10,10,10,10));
				HBox.setMargin(no, new Insets(10,10,10,10));
				VBox quitOptions = new VBox(question, options);
				quitOptions.setAlignment(Pos.CENTER);
				VBox.setMargin(question, new Insets(10,10,10,10));
				options.setAlignment(Pos.CENTER);
				quitOptions.setStyle("-fx-background-color: White; -fx-background-radius: 10px;");
				
                                DropShadow quitMenuShadow = new DropShadow();
                                quitMenuShadow.setOffsetX(2.0);
                                quitMenuShadow.setOffsetY(2.0);
                                quitMenuShadow.setColor(Color.BLACK);
                                quitMenuShadow.setRadius(4.0);
                                quitOptions.setEffect(quitMenuShadow);

				quitOptions.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
				quitVerification.setCenter(quitOptions);
				root.getChildren().add(quitVerification);
				no.setOnAction(returnEvent -> root.getChildren().remove(quitVerification));
				yes.setOnAction(quitEvent -> Platform.exit());
			});

			BorderPane home = new BorderPane();
			home.setCenter(menu);
			home.setMargin(menu, new Insets(0,0,100,0));
			home.setBackground(new Background(background));

			root.getChildren().add(home);
			stage.setScene(new Scene(root, 1000, 650));

			// Allows the home menu to fade in rather than appearing abruptly
			FadeTransition homeScreenAppear = new FadeTransition(Duration.seconds(3), menu);
			homeScreenAppear.setFromValue(0);
			homeScreenAppear.setToValue(1);
			homeScreenAppear.play();
		});
		

	}

	private void styleNewActivityText(TextInputControl text) {
		text.setStyle("-fx-background-color: White;" +
			      "-fx-border-color: Black;");
	}

	private void styleNewActivityDropDown(ComboBox c){
		c.setStyle("-fx-background-color: White;"+
			   "-fx-border-color: Black;");
	}

	private void setMenuButtonAnimation(Button b) {

		// Base style for the buttons
    		String base = "-fx-background-color: rgb(219,74,64);" +
                  //-fx-border-color: rgb(139,0,0);" +
                  //"-fx-border-width: 2px;" +
                 // "-fx-border-radius: 10px;" +
                  "-fx-background-radius: 10px;" +
                  "-fx-font-weight: bold;" +
                  "-fx-font-size: 20px;" +
                  "-fx-font-family: 'Sans Serif';" +
                  "-fx-text-fill: rgb(255,214,215);";

		// Style for when the button is hovered over
    		String hover = base.replace("219,74,64", "200,60,55");

		// Style for when the button is pressed
    		String pressed = base.replace("219,74,64", "150,30,30");

		// Applies appropriate styles to button
    		b.setStyle(base);
    		b.setOnMouseEntered(e -> b.setStyle(hover));
    		b.setOnMouseExited(e -> b.setStyle(base));
    		b.setOnMousePressed(e -> b.setStyle(pressed));
    		b.setOnMouseReleased(e -> b.setStyle(hover));
	}
	
	private String formatMonth(String month_name){
		return month_name.substring(0,1) + month_name.substring(1).toLowerCase();
	}	

	private int formatHour(int hour) {
		if (hour%12 ==0) return 12;
		return hour%12;
	}

	private void styleNewActivityLabel(Label l){
		l.setStyle("-fx-font-family: 'Sans Serif';" +
		           "-fx-font-size: 18px;");
	}

}

