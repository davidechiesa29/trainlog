package Styles;

import javafx.scene.control.TextInputControl;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class NewActivityStyle {

	public static void styleNewActivityText(TextInputControl text) {
		text.setStyle("-fx-background-color: White;" +
			      "-fx-border-color: Black;");
	}

	public static void styleNewActivityDropDown(ComboBox c){
		c.setStyle("-fx-background-color: White;"+
			   "-fx-border-color: Black;");
	}

	public static String formatMonth(String month_name){
		return month_name.substring(0,1) + month_name.substring(1).toLowerCase();
	}

	public static int formatHour(int hour) {
		if (hour%12 ==0) return 12;
		return hour%12;
	}

	public static void styleNewActivityLabel(Label l){
		l.setStyle("-fx-font-family: 'Sans Serif';" +
		           "-fx-font-size: 18px;");
	}

}
