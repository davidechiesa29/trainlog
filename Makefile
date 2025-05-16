runApp: TrainLogApp.class
	java --module-path javafx/lib --add-modules javafx.controls,javafx.fxml TrainLogApp
TrainLogApp.class: TrainLogApp.java
	javac --module-path javafx/lib --add-modules javafx.controls,javafx.fxml TrainLogApp.java
clean:
	rm *.class

