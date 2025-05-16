import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.application.Platform;

public class TrainLogApp extends Application {

	public static void main(String[] args){	
		Application.launch();	
	}

	public void start(Stage stage) {
		Button b = new Button("Log New Activity");
		Button quit = new Button("Quit");
		Group g = new Group(b, quit);
		quit.setOnAction(event -> Platform.exit());
		stage.setScene(new Scene(g, 1000, 650));
		stage.show();
	}


}
