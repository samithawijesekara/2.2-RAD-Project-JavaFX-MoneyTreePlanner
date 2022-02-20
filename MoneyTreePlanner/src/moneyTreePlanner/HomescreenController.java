package moneyTreePlanner;

//import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomescreenController implements Initializable {

    @FXML
    private Button signOutBtn;

    @FXML
    private Button myIncome;

    @FXML
    private Button myOutgoing;

    @FXML
    private Button mySavings;

    @FXML
    private Button myFixedDeposits;

    @FXML
    private Button myDashboard;

    @FXML
    private Button myProfileSettings;

    @FXML
    private Label welcomeName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getName() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT firstName FROM profiledetails")
                .executeQuery();
        if (set.next()) {
            String person = set.getString(1);
            welcomeName.setText(person);
        }
    }

    @FXML
    public void sign_out() throws IOException {
        Stage stage = (Stage) signOutBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        primaryStage.setTitle("Sign in to Money Tree Planner");
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
    }

    @FXML
    public void my_Income() throws IOException {
        Stage stage = (Stage) myIncome.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("incomeSoursers.fxml"));
        primaryStage.setTitle("My Income Sources");
        primaryStage.setScene(new Scene(root, 1024, 675));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icon.png"));
       // new FadeIn(root).play();
        primaryStage.show();

        /*Steps for close the programme*/
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
    }

    @FXML
    public void my_Outgoing() throws IOException {
        Stage stage = (Stage) myOutgoing.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OutgoingSoursers.fxml"));
        primaryStage.setTitle("My Outgoing Sources");
        primaryStage.setScene(new Scene(root, 1024, 675));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icon.png"));
        //new FadeIn(root).play();
        primaryStage.show();

        /*Steps for close the programme*/
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
    }

    @FXML
    public void my_Savings() throws IOException {
        Stage stage = (Stage) mySavings.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("SavingsAccounts.fxml"));
        primaryStage.setTitle("My Saving Accounts");
        primaryStage.setScene(new Scene(root, 1024, 675));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icon.png"));
        //new FadeIn(root).play();
        primaryStage.show();

        /*Steps for close the programme*/
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
    }

    @FXML
    public void my_FixedDeposits() throws IOException {
        Stage stage = (Stage) myFixedDeposits.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FixedDeposits.fxml"));
        primaryStage.setTitle("My Fixed Deposits");
        primaryStage.setScene(new Scene(root, 1024, 675));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icon.png"));
       // new FadeIn(root).play();
        primaryStage.show();

        /*Steps for close the programme*/
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(primaryStage);
        });
    }

    @FXML
    public void my_Dashboard(ActionEvent event) throws IOException {
        Stage stage = (Stage) myDashboard.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dashboardScreen.fxml"));
        primaryStage.setTitle("My Dashboard");
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
    }


    @FXML
    public void my_ProfileSettings() throws IOException {
        Stage stage = (Stage) myProfileSettings.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("profileSettingsScreen.fxml"));
        primaryStage.setTitle("My Profile Settings");
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
