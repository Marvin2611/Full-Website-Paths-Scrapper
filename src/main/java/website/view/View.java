package website.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Create UI Controls
        Label label1 = new Label("Website URL:");
        TextField websiteURL = new TextField();

        Label label2 = new Label("Website Trunk:");
        TextField websiteTrunk = new TextField();

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String value1 = websiteURL.getText();
            String value2 = websiteTrunk.getText();
            System.out.println("Field 1: " + value1);
            System.out.println("Field 2: " + value2);

            Results result = new Results();
            result.show();
        });

        //Open a new window with the scraping
        //Results result = new Results();
        //result.show();

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(
                label1, websiteURL,
                label2, websiteTrunk,
                submitButton
        );

        // Scene & stage
        Scene scene = new Scene(layout, 300, 200);
        stage.setTitle("Simple JavaFX Form");
        stage.setScene(scene);
        stage.show();
    }
}
