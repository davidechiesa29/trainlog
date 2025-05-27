runApp: TrainLogApp.class
	java --module-path javafx/lib --add-modules javafx.controls,javafx.fxml TrainLogApp
TrainLogApp.class: TrainLogApp.java Styles/NewActivityStyle.class ButtonActions/NewActivityPage.class ButtonActions/QuitPage.class Styles/GeneralStyle.class ButtonActions/Activity.class
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml TrainLogApp.java
ButtonActions/Activity.class: ButtonActions/Activity.java
	javac ButtonActions/Activity.java
Styles/NewActivityStyle.class: Styles/NewActivityStyle.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml Styles/NewActivityStyle.java
Styles/GeneralStyle.class: Styles/GeneralStyle.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml Styles/GeneralStyle.java
ButtonActions/QuitPage.class: ButtonActions/QuitPage.java	
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml ButtonActions/QuitPage.java
ButtonActions/NewActivityPage.class: ButtonActions/NewActivityPage.java	
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml ButtonActions/NewActivityPage.java
clean:
	rm *.class
	rm *.log
	rm Styles/*.class
	rm ButtonActions/*.class

