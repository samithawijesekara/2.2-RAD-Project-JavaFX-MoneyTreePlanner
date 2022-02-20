package moneyTreePlanner;

//import animatefx.animation.FadeIn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashscreenController implements Initializable {

    @FXML
    private AnchorPane splashScreen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread{

        @Override
        public void run(){
            try{
                Thread.sleep(7000);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;

                        try{
                           root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScreen.fxml")));
                        }catch(IOException ex){
                            Logger.getLogger(SplashscreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }


                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setResizable(false); //to disable the screen size(maximize, minimize)
                        stage.show();

                        //new FadeIn(root).play();

                        stage.getIcons().add(new Image("icon.png")); //change the icon on the title bar
                        stage.setTitle("Sign in to Money Tree Planner"); //window title

                        splashScreen.getScene().getWindow().hide(); //hide the splashscreen after sleep time

                        /*Steps for close the programme*/
                        stage.setOnCloseRequest(e -> {
                            e.consume();
                            closeProgram(stage);
                        });
                    }

                });

            }catch (InterruptedException ex){
                Logger.getLogger(SplashscreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*Confirmation box for close the programme*/
    private void closeProgram(Stage window){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            window.close();
        }
    }

}
