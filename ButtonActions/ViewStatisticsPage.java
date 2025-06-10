package ButtonActions;

import Styles.*;
import java.time.*;
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
import javafx.scene.chart.*;


public class ViewStatisticsPage {

	public static void create(StackPane root){

		BorderPane statisticsPage = new BorderPane();

		Button exit = new Button("exit");
		exit.setOnAction(e -> root.getChildren().remove(statisticsPage));
		List<Button> buttons = Arrays.asList(exit);
		GeneralStyle.setButtonAnimation(buttons, false);

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();

		LineChart<String, Number> mileageChart = new LineChart<>(xAxis, yAxis);

		int currentDay = LocalDate.now().getDayOfWeek().getValue();
		
		while (currentDay != 1){
			currentDay--;
		}

		VBox statistics = new VBox(exit, mileageChart);
		statistics.setStyle("-fx-background-color: White;");

		statisticsPage.setCenter(statistics);

		root.getChildren().add(statisticsPage);


	}

}
