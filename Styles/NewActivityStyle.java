package Styles;

import javafx.scene.control.TextInputControl;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.paint.*;
import javafx.scene.layout.*;
import java.util.*;

public class NewActivityStyle {

	public static void styleNewActivityText(TextInputControl text) {
		text.setStyle("-fx-background-color: White;" +
			      "-fx-border-color: Black;");
	}

	public static void styleNewActivityDropDown(List<ComboBox> dropdowns){
		for (ComboBox d : dropdowns)
		d.setStyle("-fx-background-color: White;"+
			   "-fx-border-color: Black;");
	}

	public static String formatMonth(String month_name){
		return month_name.substring(0,1) + month_name.substring(1).toLowerCase();
	}

	public static int formatHour(int hour) {
		if (hour%12 ==0) return 12;
		return hour%12;
	}

	public static void styleNewActivityLabel(List<Label> labels){
		for (Label l : labels)
		l.setStyle("-fx-font-family: 'Sans Serif';" +
		           "-fx-font-size: 18px;");
	}

	public static void styleNewActivityMenuBackground(VBox activityMenu) {

		// Styles the activity menu background, adds shadow
		activityMenu.setStyle("-fx-background-color: White;"+
				  "-fx-padding: 20 20 20 20;" +
				  "-fx-background-radius: 20px;");
		DropShadow activityMenuShadow = new DropShadow();
		activityMenuShadow.setOffsetX(2.0);
		activityMenuShadow.setOffsetY(2.0);
		activityMenuShadow.setColor(Color.BLACK);
		activityMenuShadow.setRadius(4.0);
		activityMenu.setEffect(activityMenuShadow);
	}

}
