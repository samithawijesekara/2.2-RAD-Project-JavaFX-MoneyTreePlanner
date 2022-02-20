package moneyTreePlanner;

//import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class IncomeSoursersController implements Initializable {

    @FXML
    private TextField txId;

    @FXML
    private TextField txSourceName;

    @FXML
    private TextField txAmount;

    @FXML
    private TextField txDescription;

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button BtnDelete;

    @FXML
    private TableView<IncomeSoursersConnectDB> tbIncomeSoursers;

    @FXML
    private TableColumn<IncomeSoursersConnectDB, Integer> colId;

    @FXML
    private TableColumn<IncomeSoursersConnectDB, String> colSourceName;

    @FXML
    private TableColumn<IncomeSoursersConnectDB, Integer> colAmount;

    @FXML
    private TableColumn<IncomeSoursersConnectDB, String> colDescription;

    @FXML
    private Button backToHomeBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showIncomeList();
    }


    @FXML
    void handleButtonAction(ActionEvent event) {
        if(event.getSource() == BtnAdd){
            insertRecord();
            try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully insert income record.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }catch(Exception ex){
                System.out.println("Error" + ex.getMessage());
            }
        }else if(event.getSource() == BtnUpdate){
            updateRecord();
            try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successfully income record.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }catch(Exception ex){
                System.out.println("Error" + ex.getMessage());
            }
        }else if(event.getSource() == BtnDelete){
            deleteRecord();
            try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Delete successfully income record.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }catch(Exception ex){
                System.out.println("Error" + ex.getMessage());
            }
        }
    }


    @FXML
    void clearFields(ActionEvent event) {
        txId.clear();
        txSourceName.clear();
        txAmount.clear();
        txDescription.clear();
    }

    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moneyTreePlanner", "root", "");
            return conn;
        }catch (Exception ex){
            System.out.println("Error" + ex.getMessage());
            return null;
        }
    }

    public ObservableList<IncomeSoursersConnectDB> getIncomeList(){
        ObservableList<IncomeSoursersConnectDB> incomeList = FXCollections.observableArrayList();
        String query = "SELECT * FROM income";
        Statement st;
        ResultSet rs;

        try{
            st = getConnection().createStatement();
            rs = st.executeQuery(query);
            IncomeSoursersConnectDB income;
            while(rs.next()){
                income = new IncomeSoursersConnectDB(rs.getInt("id"), rs.getString("sourceName"), rs.getInt("amount"), rs.getString("description"));
                incomeList.add(income);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return incomeList;
    }

    public void showIncomeList(){
        ObservableList<IncomeSoursersConnectDB> list = getIncomeList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSourceName.setCellValueFactory(new PropertyValueFactory<>("sourceName"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tbIncomeSoursers.setItems(list);
    }

    private void insertRecord(){
        String query = "INSERT INTO income VALUES ("+txId.getText()+", '"+txSourceName.getText()+"', "+txAmount.getText()+", '"+txDescription.getText()+"')";
        executeQuery(query);
        showIncomeList();
    }

    private void updateRecord(){
        String query = "UPDATE income SET sourceName = '"+txSourceName.getText()+"', amount = "+txAmount.getText()+", description = '"+txDescription.getText()+"' WHERE id = "+txId.getText()+" ";
        executeQuery(query);
        showIncomeList();
    }

    private void deleteRecord(){
        String query = "DELETE from income WHERE id = " + txId.getText()+ " ";
        executeQuery(query);
        showIncomeList();
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void handleMouseAction(MouseEvent event) {
        IncomeSoursersConnectDB incomeRecord =  tbIncomeSoursers.getSelectionModel().getSelectedItem();

        txId.setText("" +incomeRecord.getId());
        txSourceName.setText("" +incomeRecord.getSourceName());
        txAmount.setText("" +incomeRecord.getAmount());
        txDescription.setText("" +incomeRecord.getDescription());
    }

    @FXML
    public void go_toHomeScreen() throws IOException {
        Stage stage = (Stage) backToHomeBtn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("homeScreen.fxml")));
        primaryStage.setTitle("Money Tree Planner");
        primaryStage.setScene(new Scene(root,1011, 624));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("icon.png"));
        //new FadeIn(root).play();
        primaryStage.show();
    }


}
