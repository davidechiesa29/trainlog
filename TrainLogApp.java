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

public class TrainLogApp extends Application {

	public static void main(String[] args){	
		Application.launch();	
	}

	public void start(Stage stage) {

		stage.setTitle("RunStata");

		// Creates the background for the home page
		Image image = new Image("Images/Background5.png");
		BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, 
			new BackgroundSize(1000, 650, true, true, false, true));		
		
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

		// Styles the buttons
		DropShadow buttonShadow = new DropShadow();
		buttonShadow.setOffsetX(2.0);
		buttonShadow.setOffsetY(2.0);
		buttonShadow.setColor(Color.BLACK);
		buttonShadow.setRadius(2.0);
	
		newActivity.setStyle("-fx-shadow-highlight-color: transparent; -fx-border-width: 2px;-fx-border-radius: 5px;-fx-border-color: rgb(139,0,0); -fx-font-size: 20px; -fx-font-family:Sans Serif; -fx-color: rgb(219,74,64); -fx-text-fill: rgb(255,214,215);");
		viewActivities.setStyle("-fx-shadow-highlight-color: transparent; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-border-color: rgb(139,0,0); -fx-font-size: 20px; -fx-font-family:Sans Serif; -fx-color: rgb(219,74,64); -fx-text-fill: rgb(255,214,215);");
		statistics.setStyle("-fx-shadow-highlight-color: transparent; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-border-color: rgb(139,0,0); -fx-font-size: 20px; -fx-font-family:Sans Serif; -fx-color: rgb(219,74,64); -fx-text-fill:rgb(255,214,215);");
		records.setStyle("-fx-shadow-highlight-color: transparent; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-border-color: rgb(139,0,0); -fx-font-size: 20px; -fx-font-family:Sans Serif; -fx-color: rgb(219,74,64); -fx-text-fill:rgb(255,214,215);");
		quit.setStyle("-fx-shadow-highlight-color: transparent; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-border-color: rgb(139,0,0); -fx-font-size: 20px; -fx-font-family:Sans Serif; -fx-color: rgb(219,74,64); -fx-text-fill:rgb(255,214,215);");

		newActivity.setEffect(buttonShadow);
		viewActivities.setEffect(buttonShadow);
		statistics.setEffect(buttonShadow);
		records.setEffect(buttonShadow);
		quit.setEffect(buttonShadow);
	
		// Sets margins around all of the buttons on the home screen so that they aren't stuck together
		VBox.setMargin(newActivity, new Insets(3, 3, 3, 3));
		VBox.setMargin(viewActivities, new Insets(3, 3, 3, 3));
		VBox.setMargin(statistics, new Insets(3, 3, 3, 3));
		VBox.setMargin(records, new Insets(3, 3, 3, 3));
		VBox.setMargin(quit, new Insets(3, 3, 3, 3));
		VBox.setMargin(appName, new Insets(0,0,70,0));

		// Creates the box containing all of the menu buttons and centers it
		VBox menu = new VBox(appName, newActivity, viewActivities, statistics, records, quit);
		menu.setAlignment(Pos.CENTER);
		//menu.setStyle("-fx-background-color: rgba(0,0,0, 0.3);");

		BorderPane home = new BorderPane();
		home.setCenter(menu);
		home.setMargin(menu, new Insets(0,0,100,0));
		home.setBackground(new Background(background));
		quit.setOnAction(event -> Platform.exit());
		stage.setScene(new Scene(home, 1000, 650));
		stage.show();
	}


}

