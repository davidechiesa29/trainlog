runApp: TrainLogApp.class
	java --module-path javafx/lib --add-modules javafx.controls,javafx.fxml TrainLogApp
TrainLogApp.class: TrainLogApp.java Styles/NewActivityStyle.class ButtonActions/NewActivityPage.class ButtonActions/QuitPage.class Styles/GeneralStyle.class ButtonActions/Activity.class ButtonActions/ActivityManager.class ButtonActions/ViewActivitiesPage.class
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml TrainLogApp.java
ButtonActions/Activity.class: ButtonActions/Activity.java
	javac ButtonActions/Activity.java
ButtonActions/ActivityManager.class: ButtonActions/ActivityManager.java
	javac ButtonActions/ActivityManager.java
Styles/NewActivityStyle.class: Styles/NewActivityStyle.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml Styles/NewActivityStyle.java
Styles/GeneralStyle.class: Styles/GeneralStyle.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml Styles/GeneralStyle.java
ButtonActions/QuitPage.class: ButtonActions/QuitPage.java	
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml ButtonActions/QuitPage.java
ButtonActions/NewActivityPage.class: ButtonActions/NewActivityPage.java	
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml ButtonActions/NewActivityPage.java
ButtonActions/ViewActivitiesPage.class: ButtonActions/ViewActivitiesPage.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml ButtonActions/ViewActivitiesPage.java

clean:
	rm -f *.class
	rm -f *.log
	rm -f Styles/*.class
	rm -f ButtonActions/*.class

