package moneyTreePlanner;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmationBox {

    static boolean answer;

    public static boolean display (String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(150);
        Label label = new Label();
        label.setText(message);

        Button yButton = new Button("Yes");
        Button nButton = new Button("No");

        yButton.setOnAction(e ->{
            answer = true;
            window.close();
        });
        nButton.setOnAction(e ->{
            answer = false;
            window.close();
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(label,yButton, nButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}
