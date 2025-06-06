import javafx.stage.*;
import javafx.application.*;
import javafx.geometry.*; 
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.util.Duration;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.time.*;
import javafx.collections.*;
import ButtonActions.*;
import Styles.*;
import org.controlsfx.control.NotificationPane;

public class TrainLogApp extends Application {

	public static void main(String[] args){	
		Application.launch();	
	}

	public void start(Stage stage) {

		// Sets title of the window
		stage.setTitle("RunStata");

		// Retrieves previously saved activities
		ActivityManager.retrieveSavedActivities();
		
		// Creates the loading screen
		BorderPane loadingScreen = new BorderPane();

		// Sets the background of the loading screen black
		loadingScreen.setStyle("-fx-background-color: Black");

		// Creates app logo and styles it
		Label appLogo = new Label("RunStata");
		appLogo.setStyle("-fx-font-family: Sans Serif;"+
			         "-fx-font-weight:bold;" +
			         "-fx-text-fill: rgb(219, 74,64);" + 
			         "-fx-font-size: 150px;");

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

			// Creates a list of the buttons that are on the home screen
			List<Button> menuButtons = new ArrayList<>(Arrays.asList(newActivity, viewActivities, statistics, records, quit));

			// Sets preferred sizes for the buttons
			for (Button b : menuButtons) {
				b.setPrefWidth(250);
				b.setPrefHeight(60);
			}

			// Create app title and shadow
			appName.setStyle("-fx-font-family: Sans Serif; -fx-font-weight:bold; -fx-text-fill: rgb(219, 74,64); -fx-font-size: 150px;");
			DropShadow appNameShadow = new DropShadow();
			appNameShadow.setOffsetX(2.0);
			appNameShadow.setOffsetY(2.0);
			appNameShadow.setColor(Color.BLACK);
			appNameShadow.setRadius(4.0);
			appName.setEffect(appNameShadow);
	
			// Sets styling and effects for when buttons are hovered over or pressed, including shadows
			GeneralStyle.setButtonAnimation(menuButtons, true);

			// Handles events triggered when pressing the new activity button
			// Creates a new page that allows the user to upload a manual
			newActivity.setOnAction(event -> NewActivityPage.create(root));

			// Handles events triggered when pressing the view activities button
			// Creates a new page that allows the user to scroll through saved activities
			viewActivities.setOnAction(event -> ViewActivitiesPage.create(root));

			// Handles event in which quit button is pressed
			// Creates a small menu that allows user to confirm their quit request
			quit.setOnAction(event -> QuitPage.create(root));
			
			// Sets margins around all of the buttons on the home screen so that they aren't stuck together
			for (Button b : menuButtons) VBox.setMargin(b, new Insets(2));
			VBox.setMargin(appName, new Insets(50,0,70,0));

			// Creates the box containing all of the menu buttons and centers it
			VBox menu = new VBox(appName, newActivity, viewActivities, statistics, records, quit);
			
			menu.setAlignment(Pos.CENTER);

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

}
