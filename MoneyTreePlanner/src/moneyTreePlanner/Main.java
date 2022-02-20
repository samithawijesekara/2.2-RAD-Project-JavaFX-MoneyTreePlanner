package moneyTreePlanner;

//import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;


public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml")); //load screen path
        primaryStage.initStyle(StageStyle.UNDECORATED); //to remove the action bar in window
        primaryStage.getIcons().add(new Image("icon.png")); //change the icon on the title bar
        primaryStage.setTitle("Money Tree Planner"); //window title
        primaryStage.setResizable(false); //to disable the screen size(maximize, minimize)

        primaryStage.setScene(new Scene(root, 875, 560)); //window width & height
        //new FadeIn(root).play(); //animation style
        primaryStage.show(); //start window showing

        /*Steps for close the programme*/
        window = primaryStage;
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
    }

    /*Confirmation box for close the programme*/
    private void closeProgram(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            window.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
