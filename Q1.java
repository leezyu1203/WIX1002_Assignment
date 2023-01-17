import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class Q1 extends Application {
 
    @Override 
    public void start(Stage stage) {
        stage.setTitle("Line Chart for Number of Jobs Created");
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        //creating the chart
        final LineChart<String, Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Number of Jobs Created");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Number of Jobs Created");
        
        series1.getData().add(new XYChart.Data("June", 2191));
        series1.getData().add(new XYChart.Data("July", 1303));
        series1.getData().add(new XYChart.Data("August", 1179));
        series1.getData().add(new XYChart.Data("September", 1253));
        series1.getData().add(new XYChart.Data("October", 1777));
        series1.getData().add(new XYChart.Data("November", 900));
        series1.getData().add(new XYChart.Data("December", 551));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Number of Jobs Ended");
        series2.getData().add(new XYChart.Data("June", 1893));
        series2.getData().add(new XYChart.Data("July", 1139));
        series2.getData().add(new XYChart.Data("August", 1186));
        series2.getData().add(new XYChart.Data("September", 1153));
        series2.getData().add(new XYChart.Data("October", 1564));
        series2.getData().add(new XYChart.Data("November", 1092));
        series2.getData().add(new XYChart.Data("December", 452));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Number of Jobs Causing Error");
        series3.getData().add(new XYChart.Data("June", 38));
        series3.getData().add(new XYChart.Data("July", 0));
        series3.getData().add(new XYChart.Data("August", 56));
        series3.getData().add(new XYChart.Data("September", 9));
        series3.getData().add(new XYChart.Data("October", 5));
        series3.getData().add(new XYChart.Data("November", 35));
        series3.getData().add(new XYChart.Data("December", 0));
        
        Scene scene  = new Scene(lineChart,800,600);       
        lineChart.getData().addAll(series1, series2, series3);
        
        
    
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}