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

public class TrainLogApp extends Application {

	public static void main(String[] args){	
		Application.launch();	
	}

	public void start(Stage stage) {

		stage.setTitle("RunStata");
		
		BorderPane loadingScreen = new BorderPane();
		loadingScreen.setStyle("-fx-background-color: Black");
		Label appLogo = new Label("RunStata");
		appLogo.setStyle("-fx-font-family: Sans Serif; -fx-font-weight:bold; -fx-text-fill: rgb(219, 74,64); -fx-font-size: 150px;");
		VBox loadingMenu = new VBox(appLogo);
		loadingScreen.setCenter(loadingMenu);
		loadingMenu.setAlignment(Pos.CENTER);

		stage.setScene(new Scene(loadingScreen, 1000, 650));
		stage.show();
			
		FadeTransition logoFadeIn = new FadeTransition(Duration.seconds(2), loadingMenu);
		logoFadeIn.setFromValue(0);
		logoFadeIn.setToValue(1);
		logoFadeIn.play();
	
		PauseTransition logoStay = new PauseTransition(Duration.seconds(1));
		logoFadeIn.setOnFinished(e ->logoStay.play());
			
		FadeTransition logoFadeOut = new FadeTransition(Duration.seconds(2), loadingMenu);
		logoFadeOut.setFromValue(1);
		logoFadeOut.setToValue(0);
		logoStay.setOnFinished(e -> logoFadeOut.play());

		logoFadeOut.setOnFinished(e -> {
		
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
	
			setMenuButtonAnimation(newActivity);
			setMenuButtonAnimation(viewActivities);
			setMenuButtonAnimation(statistics);
			setMenuButtonAnimation(records);
			setMenuButtonAnimation(quit);
			

			// Styles the buttons
			DropShadow buttonShadow = new DropShadow();
			buttonShadow.setOffsetX(2.0);
			buttonShadow.setOffsetY(2.0);
			buttonShadow.setColor(Color.BLACK);
			buttonShadow.setRadius(2.0);
	
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

			StackPane root = new StackPane();
			
			quit.setOnAction(event -> {
				BorderPane quitVerification = new BorderPane();
				Label question = new Label("Are you sure you want to quit?");
				question.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-font-family: 'Sans Serif';");
				Button yes = new Button("Yes");
				Button no = new Button("No");
				yes.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
				no.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
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

	private void setMenuButtonAnimation(Button b) {
    		String base = "-fx-background-color: rgb(219,74,64);" +
                  //-fx-border-color: rgb(139,0,0);" +
                  //"-fx-border-width: 2px;" +
                 // "-fx-border-radius: 10px;" +
                  "-fx-background-radius: 10px;" +
                  "-fx-font-weight: bold;" +
                  "-fx-font-size: 20px;" +
                  "-fx-font-family: 'Sans Serif';" +
                  "-fx-text-fill: rgb(255,214,215);";

    		String hover = base.replace("219,74,64", "200,60,55");
    		String pressed = base.replace("219,74,64", "150,30,30");

    		b.setStyle(base); 

    		b.setOnMouseEntered(e -> b.setStyle(hover));
    		b.setOnMouseExited(e -> b.setStyle(base));
    		b.setOnMousePressed(e -> b.setStyle(pressed));
    		b.setOnMouseReleased(e -> b.setStyle(hover));
	}



}

