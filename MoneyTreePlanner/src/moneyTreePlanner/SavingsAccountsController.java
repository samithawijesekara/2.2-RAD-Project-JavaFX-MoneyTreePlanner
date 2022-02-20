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
        import java.util.ResourceBundle;

public class SavingsAccountsController implements Initializable {

    @FXML
    private TextField txId;

    @FXML
    private TextField txBankName;

    @FXML
    private TextField txAccountNo;

    @FXML
    private TextField txAmount;

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button BtnDelete;

    @FXML
    private TableView<SavingsAccountsConnectDB> tbBankAccounts;

    @FXML
    private TableColumn<SavingsAccountsConnectDB, Integer> colId;

    @FXML
    private TableColumn<SavingsAccountsConnectDB, String> colBankName;

    @FXML
    private TableColumn<SavingsAccountsConnectDB, Integer> colAccountNo;

    @FXML
    private TableColumn<SavingsAccountsConnectDB, Integer> colAmount;

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
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully insert saving record.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }catch(Exception ex){
                System.out.println("Error" + ex.getMessage());
            }
        }else if(event.getSource() == BtnUpdate){
            updateRecord();
            try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successfully saving record.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }catch(Exception ex){
                System.out.println("Error" + ex.getMessage());
            }
        }else if(event.getSource() == BtnDelete){
            deleteRecord();
            try{
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Delete successfully saving record.", ButtonType.OK);
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
        txBankName.clear();
        txAccountNo.clear();
        txAmount.clear();
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

    public ObservableList<SavingsAccountsConnectDB> getIncomeList(){
        ObservableList<SavingsAccountsConnectDB> incomeList = FXCollections.observableArrayList();
        String query = "SELECT * FROM savings";
        Statement st;
        ResultSet rs;

        try{
            st = getConnection().createStatement();
            rs = st.executeQuery(query);
            SavingsAccountsConnectDB income;
            while(rs.next()){
                income = new SavingsAccountsConnectDB(rs.getInt("id"), rs.getString("bankName"), rs.getInt("accountNo"), rs.getInt("amount"));
                incomeList.add(income);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return incomeList;
    }

    public void showIncomeList(){
        ObservableList<SavingsAccountsConnectDB> list = getIncomeList();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBankName.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        colAccountNo.setCellValueFactory(new PropertyValueFactory<>("accountNo"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tbBankAccounts.setItems(list);
    }

    private void insertRecord(){
        String query = "INSERT INTO savings VALUES ("+txId.getText()+", '"+txBankName.getText()+"', "+txAccountNo.getText()+", "+txAmount.getText()+")";
        executeQuery(query);
        showIncomeList();
    }

    private void updateRecord(){
        String query = "UPDATE savings SET bankName = '"+txBankName.getText()+"', accountNo = "+txAccountNo.getText()+", amount = "+txAmount.getText()+" WHERE id = "+txId.getText()+" ";
        executeQuery(query);
        showIncomeList();
    }

    private void deleteRecord(){
        String query = "DELETE from savings WHERE id = " + txId.getText()+ " ";
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
        SavingsAccountsConnectDB incomeRecord =  tbBankAccounts.getSelectionModel().getSelectedItem();

        txId.setText("" +incomeRecord.getId());
        txBankName.setText("" +incomeRecord.getBankName());
        txAccountNo.setText("" +incomeRecord.getAccountNo());
        txAmount.setText("" +incomeRecord.getAmount());
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
       // new FadeIn(root).play();
        primaryStage.show();
    }
}
