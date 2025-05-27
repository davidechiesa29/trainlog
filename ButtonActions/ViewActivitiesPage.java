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

public class ViewActivitiesPage {

	public static void create(StackPane root) {
		
		BorderPane viewActivitiesPage = new BorderPane();
		viewActivitiesPage.setStyle("-fx-background-color: rgb(219,74,64);");

		Button escape = new Button("Return");
		List<Button> buttons = Arrays.asList(escape);
		GeneralStyle.setButtonAnimation(buttons, false);
		escape.setOnAction(e -> root.getChildren().remove(viewActivitiesPage));

		VBox scrollableList = new VBox(escape);
		scrollableList.setStyle("-fx-background-color: White;");
		scrollableList.setAlignment(Pos.CENTER);

		List<Label> activities = new ArrayList<>();
		for (Activity a : ActivityManager.getAll()) {
			activities.add(new Label(a.toString()));
			createViewableActivity(a);
		}
		for (Label l : activities) scrollableList.getChildren().add(l);
		viewActivitiesPage.setCenter(scrollableList);
		scrollableList.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		root.getChildren().add(viewActivitiesPage);

	}


	public static VBox createViewableActivity(Activity activity) {
		return null;
	}
}
