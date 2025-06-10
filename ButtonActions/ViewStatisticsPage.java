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
import java.time.format.*;


public class ViewStatisticsPage {

	public static void create(StackPane root){

		BorderPane statisticsPage = new BorderPane();

		Button exit = new Button("Return to Main Menu");
		exit.setOnAction(e -> root.getChildren().remove(statisticsPage));
		List<Button> buttons = Arrays.asList(exit);
		GeneralStyle.setButtonAnimation(buttons, false);

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Miles");

		AreaChart<String, Number> mileageChart = new AreaChart<>(xAxis, yAxis);

		LocalDate currentDay = LocalDate.now();
		while (currentDay.getDayOfWeek() != DayOfWeek.MONDAY) {
			currentDay = currentDay.minusDays(1);
		}

		double[] weeklyMileageData = new double[12];
		String[] weeklyMileageLabels = new String[12];

		for (int i = 0; i < weeklyMileageData.length; i++){
			double mileage = 0;
			PriorityQueue<Activity> activities = new PriorityQueue<>(ActivityManager.getAll());
			LocalDate oldDay = currentDay.minusWeeks(11-i);
			LocalDate startOfWeek = LocalDate.from(oldDay);
			LocalDate endOfWeek = oldDay.plusDays(6);
			weeklyMileageLabels[i] = startOfWeek.toString().split("-")[1] + "/" + startOfWeek.toString().split("-")[2];

			// Iterates through the list of activities to find the mileage for one specific week
			while (!activities.isEmpty()){

				// Dequeues an activity and finds its date
				Activity a = activities.poll();
				LocalDate activityDate = LocalDate.parse(Activity.convertDate(a.date));

				// Loop breaks if the activity dequeued happened before the start of the current week
				if (activityDate.isBefore(startOfWeek)) break;

				// Checks if the activity is within the specified week's range
				if ((activityDate.isAfter(startOfWeek) || activityDate.isEqual(startOfWeek)) && 
				 (activityDate.isBefore(endOfWeek) || activityDate.isEqual(endOfWeek)))
					// Adds the activity's distance to the mileage for the week
					mileage += a.distance;
			}
			// Stores the week's mileage before iterating forward to check the next week's mileage
			weeklyMileageData[i] = mileage;
			
		}

		XYChart.Series series = new XYChart.Series();
		for (int i = 0; i < weeklyMileageData.length; i++) {
			series.getData().add(new XYChart.Data(weeklyMileageLabels[i], weeklyMileageData[i]));
			//series.getData().add(new XYChart.Data((i+1)+"", weeklyMileageData[i]));
		}

		mileageChart.getData().add(series);
		mileageChart.setMinWidth(600);
		mileageChart.setMaxHeight(250);
		mileageChart.setTitle("Weekly Mileage");
		mileageChart.setLegendVisible(false);


		VBox statistics = new VBox(exit, mileageChart);
		VBox.setMargin(mileageChart, new Insets(20));
		VBox.setMargin(exit, new Insets(20));

		statistics.setAlignment(Pos.CENTER);
		statistics.setStyle("-fx-background-color: White;");
		statistics.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

		statisticsPage.setCenter(statistics);
		statisticsPage.setStyle("-fx-background-color: rgb(219,74,64);");

		root.getChildren().add(statisticsPage);


	}

}
