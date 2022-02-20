package moneyTreePlanner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

// import animatefx.animation.FadeIn;

public class DashboardScreenController<PieChart> implements Initializable {

    @FXML
    private Button backToHomeBtn;

    @FXML
    private Label totalIncome;

    @FXML
    private Label totalOutgoing;

    @FXML
    private Label totalSavings;

    @FXML
    private Label totalFixed;

    @FXML
    private javafx.scene.chart.PieChart pieChart;

    @FXML
    private AreaChart<?, ?> areaChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getIncome();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            getOutgoings();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            getSavings();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            getFixed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadPieChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Integer tIncome;
    Integer tOutgoings;
    Integer tSavings;
    Integer tFixed;

    public void getIncome() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT SUM(amount) FROM income")
                .executeQuery();
        if (set.next()) {
            tIncome = set.getInt(1);
            String pattern = "#,###,###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setGroupingSize(3);
            String currency = decimalFormat.format(tIncome);
            totalIncome.setText(currency+" LKR");
        }
    }

    public void getOutgoings() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT SUM(amount) FROM outgoing")
                .executeQuery();
        if (set.next()) {
            tOutgoings = set.getInt(1);
            String pattern = "#,###,###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setGroupingSize(3);
            String currency = decimalFormat.format(tOutgoings);
            totalOutgoing.setText(currency+" LKR");
        }
    }

    public void getSavings() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT SUM(amount) FROM savings")
                .executeQuery();
        if (set.next()) {
            tSavings = set.getInt(1);
            String pattern = "#,###,###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setGroupingSize(3);
            String currency = decimalFormat.format(tSavings);
            totalSavings.setText(currency+" LKR");
        }
    }

    public void getFixed() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT SUM(amount) FROM fixeddeposits")
                .executeQuery();
        if (set.next()) {
            tFixed = set.getInt(1);
            String pattern = "#,###,###";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            decimalFormat.setGroupingSize(3);
            String currency = decimalFormat.format(tFixed);
            totalFixed.setText(currency+" LKR");
        }
    }


    public void loadPieChart() throws SQLException, ClassNotFoundException {

        //Pie chart start here
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = observableArrayList(
                new javafx.scene.chart.PieChart.Data("Total Incomes", tIncome),
                new javafx.scene.chart.PieChart.Data("Total Outgoings", tOutgoings),
                new javafx.scene.chart.PieChart.Data("Total Savings", tSavings),
                new javafx.scene.chart.PieChart.Data("Total Fixed Deposits", tFixed));
        pieChart.setData(pieChartData);
        pieChart.setLabelLineLength(8);

        //XY chart start here
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Total Incomes", tIncome));
        series.getData().add(new XYChart.Data("Total Outgoings", tOutgoings));
        series.getData().add(new XYChart.Data("Total Savings", tSavings));
        series.getData().add(new XYChart.Data("Total Fixed Deposits", tFixed));
        areaChart.getData().add(series);
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
