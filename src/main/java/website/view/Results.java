package website.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Results {

    public void show() {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"
        );

        ListView<String> listView = new ListView<>(items);

        Button saveButton = new Button("Save Data");
        saveButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setOnAction(e -> {
            System.out.println("Saving data:");
            items.forEach(System.out::println);
        });

        VBox root = new VBox(10, listView, saveButton);
        root.setPadding(new Insets(10));
        VBox.setVgrow(listView, Priority.ALWAYS);

        Stage stage = new Stage();
        stage.setTitle("List Window");
        stage.setScene(new Scene(root, 300, 400));
        stage.show();
    }
}
