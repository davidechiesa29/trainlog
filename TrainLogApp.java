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

public class TrainLogApp extends Application {

	public static void main(String[] args){	
		Application.launch();	
	}

	public void start(Stage stage) {

		stage.setTitle("Train Log App");

		// Creates the background for the home page
		Image image = new Image("Images/TrainLogAppBackgroundBlurred.jpg");
		BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
			new BackgroundSize(1000, 650, true, true, false, true));		
		
		// Creates buttons on the home screen and application name
		Label appName = new Label("Train Log App");
		Button newActivity = new Button("Log Activity");
		Button viewActivities = new Button("View Activities");
		Button statistics = new Button("View Statistics");
		Button records = new Button("View Personal Records");
		Button quit = new Button("Quit");

		// Sets preferred sizes for the buttons
		newActivity.setPrefWidth(200);
		viewActivities.setPrefWidth(200);
		statistics.setPrefWidth(200);
		records.setPrefWidth(200);
		quit.setPrefWidth(200);
		newActivity.setPrefHeight(40);
		viewActivities.setPrefHeight(40);
		statistics.setPrefHeight(40);
		records.setPrefHeight(40);
		quit.setPrefHeight(40);

		appName.setStyle("-fx-font-family:Tahoma; -fx-font-weight:bold; -fx-text-fill: rgb(244, 139,129); -fx-font-size: 60px;");

		// Styles the buttons	
		newActivity.setStyle("-fx-font-family:Tahoma; -fx-font-weight: bold; -fx-color: rgb(244,139,129);");
		viewActivities.setStyle("-fx-font-family:Tahoma; -fx-font-weight: bold; -fx-color: rgb(244,139,129);");
		statistics.setStyle("-fx-font-family:Tahoma; -fx-font-weight: bold; -fx-color: rgb(244,139,129);");
		records.setStyle("-fx-font-family:Tahoma; -fx-font-weight: bold; -fx-color: rgb(244,139,129);");
		quit.setStyle("-fx-font-family:Tahoma; -fx-font-weight: bold; -fx-color: rgb(244,139,129);");
	
		// Sets margins around all of the buttons on the home screen so that they aren't stuck together
		VBox.setMargin(newActivity, new Insets(5, 5, 5, 5));
		VBox.setMargin(viewActivities, new Insets(5, 5, 5, 5));
		VBox.setMargin(statistics, new Insets(5, 5, 5, 5));
		VBox.setMargin(records, new Insets(5, 5, 5, 5));
		VBox.setMargin(quit, new Insets(5, 5, 5, 5));
		VBox.setMargin(appName, new Insets(0,0,70,0));

		// Creates the box containing all of the menu buttons and centers it
		VBox menu = new VBox(appName, newActivity, viewActivities, statistics, records, quit);
		menu.setAlignment(Pos.CENTER);

		BorderPane home = new BorderPane();
		home.setBottom(menu);
		home.setMargin(menu, new Insets(0,0,100,0));
		home.setBackground(new Background(background));
		quit.setOnAction(event -> Platform.exit());
		stage.setScene(new Scene(home, 1000, 650));
		stage.show();
	}


}

