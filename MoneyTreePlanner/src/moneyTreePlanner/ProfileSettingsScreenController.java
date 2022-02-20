package moneyTreePlanner;

//import animatefx.animation.FadeIn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class ProfileSettingsScreenController implements Initializable {

    @FXML
    private Button backToHomeBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private PasswordField resetpass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            show_names();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void saveChangesBtn(ActionEvent event) {

        if(event.getSource() == saveBtn){
            updateRecord();
            try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }catch(Exception ex){
                System.out.println("Error" + ex.getMessage());
            }
        }
    }

    private void updateRecord(){
        String query1 = "update profiledetails set firstName = '"+fname.getText()+"', lastName = '"+lname.getText()+"',password = '"+resetpass.getText()+"'";
        executeQuery(query1);
    }

    private void executeQuery(String query1) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query1);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    private Connection getConnection() {
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moneyTreePlanner", "root", "");
            return conn;
        }catch (Exception ex){
            System.out.println("Error" + ex.getMessage());
            return null;
        }
    }


    public void show_names() throws SQLException {
        String sql = "SELECT * FROM profiledetails";
        Connection conn = getConnection();
        try{
            Statement st = conn.createStatement();
            ResultSet rt = st.executeQuery(sql);

            while(rt.next()) {
                fname.setText(rt.getString("firstName"));
                lname.setText(rt.getString("lastName"));
                resetpass.setText(rt.getString("password"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    @FXML
    public void go_toHomeScreen() throws IOException {
        Stage stage = (Stage) backToHomeBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        primaryStage.setTitle("Money Tree Planner");
        primaryStage.setScene(new Scene(root, 1011, 624));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icon.png"));
        //new FadeIn(root).play();
        primaryStage.show();

    }
}

