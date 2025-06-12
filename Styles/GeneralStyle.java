package Styles;

import javafx.scene.effect.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.scene.image.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class GeneralStyle {

	public static void setButtonAnimation(List<Button> buttons, boolean shadow, int font_size) {
		
		// Base style for the buttons
    		String base = "-fx-background-color: rgb(219,74,64);" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: " + font_size + "px;" +
                "-fx-font-family: 'Sans-serif';" +
                "-fx-text-fill: rgb(255,214,215);";

		// Style for when the button is hovered over
    		String hover = base.replace("219,74,64", "200,60,55");

		// Style for when the button is pressed
    		String pressed = base.replace("219,74,64", "150,30,30");

		for (Button b : buttons) {
			// Applies appropriate styles to button
    			b.setStyle(base);

			// Adds shadow if requested
			if (shadow) b.setEffect(new DropShadow(2.0,2.0,2.0,Color.BLACK)); 

			// Adds animations for hovering and pressing on the button
    			b.setOnMouseEntered(e -> b.setStyle(hover));
    			b.setOnMouseExited(e -> b.setStyle(base));
    			b.setOnMousePressed(e -> b.setStyle(pressed));
    			b.setOnMouseReleased(e -> b.setStyle(hover));
		}
	}

	public static void setBackgroundImage(Pane root, String imageToUse) {

		// Adds background to the page
		Image image = new Image(imageToUse);
                BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1000, 650, true, true, false, true));
		root.setBackground(new Background(background));

	}

}
