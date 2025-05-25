runApp: TrainLogApp.class
	java --module-path javafx/lib --add-modules javafx.controls,javafx.fxml TrainLogApp
TrainLogApp.class: TrainLogApp.java Styles/NewActivityStyle.java ButtonActions/NewActivityPage.java ButtonActions/QuitPage.java Styles/HomeStyle.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml *.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml Styles/*.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml ButtonActions/*.java
clean:
	rm *.class
	rm *.log
	rm Styles/*.class
	rm ButtonActions/*.class

