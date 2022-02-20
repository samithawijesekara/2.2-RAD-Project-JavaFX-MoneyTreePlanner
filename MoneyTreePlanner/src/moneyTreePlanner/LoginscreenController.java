package moneyTreePlanner;


//import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;


public class LoginscreenController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private Label loginError;

    @FXML
    private Label userFirstName;

    @FXML
    private PasswordField userPassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loginError.setVisible(false);
    }

    public void getName() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT firstName FROM profiledetails")
                .executeQuery();
        if (set.next()) {
            String person = set.getString(1);
            userFirstName.setText(person);
        }
    }

    @FXML
    public void go_toHomeScreen(ActionEvent event) throws IOException {
        try{
            ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT password FROM profiledetails")
                .executeQuery();
            if (set.next()) {
            String pass = set.getString(1);

            if (userPassword.getText().equals(pass)) {
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.close();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
                primaryStage.setTitle("Money Tree Planner");
                primaryStage.setScene(new Scene(root, 1011, 624));
                primaryStage.setResizable(false);
                primaryStage.getIcons().add(new Image("icon.png"));
                //new FadeIn(root).play();
                primaryStage.show();

                /*Steps for close the programme*/
                primaryStage.setOnCloseRequest(e -> {
                    e.consume();
                    closeProgram(primaryStage);
                });
            } else{
                loginError.setVisible(true);
            }}
        }catch(Exception e){
            System.out.println("You can't log this time" + e);
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

    @FXML
    void help(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("If you are a new user make sure to use your password as \"123456\". Ones you sign in you can go to the profile settings and change your personal details." + "\n\n" + "Thank you.");
        alert.show();
    }


}