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
		GeneralStyle.setBackgroundImage((Pane)statisticsPage, "Images/map_art.png");

		Button exit = new Button("Return to Main Menu");
		exit.setOnAction(e -> root.getChildren().remove(statisticsPage));
		List<Button> buttons = Arrays.asList(exit);
		GeneralStyle.setButtonAnimation(buttons, false,20);

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

		Label percentage = createPercentageLabel((float)weeklyMileageData[10], (float)weeklyMileageData[11]);
		Label changeTitle = new Label("Change from Last Week");
		changeTitle.setStyle("-fx-font-weight: Bold; -fx-font-size: 20px; -fx-font-family: 'Sans-serif';");
		VBox mileageIncreaseBox = new VBox(changeTitle, percentage);

		Label longestRunLabel = new Label("Longest Run This Week");
		longestRunLabel.setStyle("-fx-font-weight: Bold; -fx-font-size: 20px; -fx-font-family: 'Sans-serif';");
		Label longestRun = findLongestRun();
		VBox longestRunBox = new VBox(longestRunLabel, longestRun);

		Label totalYearlyMileageLabel = new Label("Year to Date");
		totalYearlyMileageLabel.setStyle("-fx-font-weight: Bold; -fx-font-size: 20px; -fx-font-family: 'Sans-serif';");
		Label yearlyMileage = findYearlyMileage();
		VBox yearlyMileageBox = new VBox(totalYearlyMileageLabel, yearlyMileage);
	

		HBox tileInfo = new HBox(mileageIncreaseBox, longestRunBox, yearlyMileageBox);
		tileInfo.setSpacing(20);
		tileInfo.setAlignment(Pos.CENTER);


		VBox statistics = new VBox(exit, mileageChart, tileInfo);
		VBox.setMargin(mileageChart, new Insets(20));
		VBox.setMargin(exit, new Insets(20));
		VBox.setMargin(tileInfo, new Insets(20));

		statistics.setAlignment(Pos.CENTER);
		statistics.setStyle("-fx-background-color: White;");
		statistics.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

		statisticsPage.setCenter(statistics);

		root.getChildren().add(statisticsPage);


	}

	private static Label createPercentageLabel(float first, float second) {
		Label l = new Label();
		int percentage = 0;

		if (first > second) {
		       	if (second != 0)	
			percentage = Math.round(((first-second)/second)*100);
			l.setText("↓ " + percentage + "%");
			l.setStyle("-fx-text-fill: rgb(219,74,64);" +
				   "-fx-font-weight: Bold;" +
				   "-fx-font-size: 24px;" +
				   "-fx-font-family: 'Sans-serif';");
		}
		else if (second > first) {
			if (first != 0)
			percentage = Math.round(((second-first)/first)*100);
			l.setText("↑ " + percentage + "% ");
			l.setStyle("-fx-text-fill: Green;" +
				   "-fx-font-weight: Bold;" +
				   "-fx-font-size: 24px;");

		}
		else {
			l.setText("-- % ");
			l.setStyle("-fx-text-fill: Gray;" +
				   "-fx-font-weight: Bold;" +
				   "-fx-font-size: 24px;");
			
		}
		l.setPadding(new Insets(5, 5, 5, 5));
		return l;
	}

	private static Label findLongestRun(){
		
		PriorityQueue<Activity> activities = new PriorityQueue<>(ActivityManager.getAll());
		
		LocalDate startOfWeek = LocalDate.now();
		while (startOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
			startOfWeek = startOfWeek.minusDays(1);
		}

		List<Double> distances = new ArrayList<>();
		
		while (!activities.isEmpty()){
			Activity a = activities.poll();
			LocalDate activityDate = LocalDate.parse(Activity.convertDate(a.date));
			if (activityDate.isBefore(startOfWeek)) break;
			else distances.add(a.distance);
			
		}

		Collections.sort(distances);
		
		String longest = "--";
		if (distances.size() > 0){
			longest = ""+distances.get(distances.size()-1);
		}

		Label l = new Label(""+longest+ " mi");
		l.setStyle("-fx-text-fill: Green;"+
			   "-fx-font-weight: Bold;"+
			   "-fx-font-size: 24px;");

		l.setPadding(new Insets(5, 5, 5, 5));
		return l;
	}

	private static Label findYearlyMileage(){
	
		Label l = new Label("");	
		double mileage = 0;
		PriorityQueue<Activity> activities = new PriorityQueue<>(ActivityManager.getAll());

		LocalDate start = LocalDate.parse((LocalDate.now().getYear()-1)+"-12-31");

		while (!activities.isEmpty()) {
			Activity a = activities.poll();
			LocalDate activityDate = LocalDate.parse(Activity.convertDate(a.date));
			if (activityDate.isAfter(start))
				mileage += a.distance;
			else
				break;
		}
		l.setText(mileage + " mi");
		l.setStyle("-fx-text-fill: Gray;"+
			   "-fx-font-weight: Bold;"+
			   "-fx-font-size: 24px");
		return l;
	}	

}
