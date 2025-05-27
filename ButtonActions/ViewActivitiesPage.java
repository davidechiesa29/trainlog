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
		VBox scrollableList = new VBox();
		scrollableList.setStyle("-fx-background-color: Yellow;");
		List<Label> activityList = new ArrayList<>();
		for (Activity a : ActivityManager.getAll()) {
			activityList.add(new Label(a.toString()));
		}
		for (Label l : activityList) scrollableList.getChildren().add(l);
		viewActivitiesPage.setCenter(scrollableList);
		root.getChildren().add(viewActivitiesPage);

	}
}
