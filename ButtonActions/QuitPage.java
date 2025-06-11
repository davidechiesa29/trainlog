package ButtonActions;

import java.util.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.effect.*;
import javafx.scene.paint.*;
import javafx.application.*;
import Styles.*;


public class QuitPage {

	public static void create(StackPane root) {
		BorderPane quitVerification = new BorderPane();
		Label question = new Label("Are you sure you want to quit?");
		question.setStyle("-fx-font-weight: bold;" +
				"-fx-font-size: 20px;"+
				"-fx-font-family: 'Sans Serif';");
		Button yes = new Button("Yes");
		Button no = new Button("No");
		List<Button> quitButtons = Arrays.asList(yes, no);

		// Styles the buttons so that they correspond with the rest of the app, with the appropriate colors (but w/o shadow)
		GeneralStyle.setButtonAnimation(quitButtons, false, 20);

		// Completes more specialized styling of the quit buttons, modifying size and margins
		for (Button b : quitButtons) {
			b.setPrefWidth(100);
			b.setPrefHeight(50);
			HBox.setMargin(b, new Insets(10));
		}

		// Creates the horizontal box containing the two buttons next to each other
		HBox options = new HBox(yes, no);

		// Nests horizontal box in a vertical box with question above it
		VBox quitOptions = new VBox(question, options);

		// Centers quit menu, adds margins, and styles appropriately
		quitOptions.setAlignment(Pos.CENTER);
		VBox.setMargin(question, new Insets(10));
		options.setAlignment(Pos.CENTER);
		quitOptions.setStyle("-fx-background-color: White; -fx-background-radius: 10px;");

		// Creates and adds shadow to the quit menu
                quitOptions.setEffect(new DropShadow(4.0,2.0,2.0, Color.BLACK));

		// Makes the quit verification menu use up the smallest size possible
	        quitOptions.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

		// Centers the menu on the borderpane and adds it to the root (the stack pane), making it appear
		quitVerification.setCenter(quitOptions);
		root.getChildren().add(quitVerification);

		// Adds appropriate event responses, quitting the app if yes is click or reverting to previous menu if not
		no.setOnAction(returnEvent -> root.getChildren().remove(quitVerification));
		yes.setOnAction(quitEvent -> Platform.exit());
	}

}
