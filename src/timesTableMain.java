import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class timesTableMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Times Table Visualization");

        //Initialize Variables
        int drawSize = 750;
        int optSize = 50;

        //Box Initializtion
        BorderPane root = new BorderPane();

        Pane drawing = new Pane();
        VBox options = new VBox(10);
        HBox pointInput = new HBox(8);
        HBox valInput = new HBox(8);


        //Text box input for setting points
        Label pointLabel = new Label("Number of Points: ");
        TextField pointsAmt = new TextField();
        Button submitP = new Button("Submit");

        //Slider and update controls for setting times table value;
        Slider tableVal = new Slider(2,360,2);
        tableVal.setShowTickMarks(true);
        tableVal.setShowTickLabels(true);
        tableVal.setBlockIncrement(0.1f);
        Label valLabel = new Label("Times Table Value: ");
        Label value = new Label(Double.toString(tableVal.getValue()));

        tableVal.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
                  value.textProperty().setValue(
                       String.format("%.1f", newValue.doubleValue()));
              }
        });


        //Animation control
        Button animCtrl = new Button("Play/Pause");

        //Add elements to fields for BorderPane
        pointInput.getChildren().addAll(pointLabel,pointsAmt, submitP);
        valInput.getChildren().addAll(valLabel, value);

        options.getChildren().addAll(valInput,tableVal,pointInput,animCtrl);

        drawing.setStyle("-fx-background-color: Black");
        drawing.setMinWidth(drawSize);
        drawing.setMinHeight(drawSize);

        options.setMinHeight(optSize);

        timesTable generator = new timesTable();

        generator.createCircle(drawing, 11);

        root.setCenter(drawing);
        root.setTop(options);

        Scene scene = new Scene(root, (drawSize + optSize), (drawSize + optSize));
        primaryStage.setScene(scene);
        primaryStage.show();
        generator.start();

    }
}