import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;

public class TrainLogApp extends Application {

	public static void main(String[] args){	
		Application.launch();	
	}

	public void start(Stage stage) {

		Label appName = new Label("Train Log");
		Button newActivity = new Button("Log Activity");
		Button viewActivities = new Button("View Activities");
		Button statistics = new Button("View Statistics");
		Button records = new Button("View Personal Records");
		Button quit = new Button("Quit");
		VBox menu = new VBox(newActivity, viewActivities, statistics, records, quit);
		BorderPane home = new BorderPane();
		home.setCenter(menu);
		home.setTop(appName);
		
		quit.setOnAction(event -> Platform.exit());
		stage.setScene(new Scene(home, 1000, 650));
		stage.show();
	}


}
