package ButtonActions;

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
		question.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-font-family: 'Sans Serif';");
		Button yes = new Button("Yes");
		Button no = new Button("No");
		// yes.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		HomeStyle.setMenuButtonAnimation(yes);
		// no.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
		HomeStyle.setMenuButtonAnimation(no);
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
	}

}
