package Styles;

import javafx.scene.control.*;
import java.util.*;

public class HomeStyle {

	public static void setMenuButtonAnimation(List<Button> buttons) {

		for (Button b : buttons) {
			// Base style for the buttons
    			String base = "-fx-background-color: rgb(219,74,64);" +
                  	"-fx-background-radius: 10px;" +
                  	"-fx-font-weight: bold;" +
                  	"-fx-font-size: 20px;" +
                  	"-fx-font-family: 'Sans Serif';" +
                  	"-fx-text-fill: rgb(255,214,215);";

			// Style for when the button is hovered over
    			String hover = base.replace("219,74,64", "200,60,55");

			// Style for when the button is pressed
    			String pressed = base.replace("219,74,64", "150,30,30");

			// Applies appropriate styles to button
    			b.setStyle(base);
    			b.setOnMouseEntered(e -> b.setStyle(hover));
    			b.setOnMouseExited(e -> b.setStyle(base));
    			b.setOnMousePressed(e -> b.setStyle(pressed));
    			b.setOnMouseReleased(e -> b.setStyle(hover));
		}
	}
}
