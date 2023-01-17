import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class Q2 extends Application {
    final static String cpu_epyc = "cpu_epyc";
    final static String cpu_opteron = "cpu_opteron";
    final static String gpu_k10 = "gpu_k10";
    final static String gpu_k40c = "gpu_k40c";
    final static String gpu_titan = "gpu_titan";
    final static String gpu_v100s = "gpu_v100s";
 
    @Override 
    public void start(Stage stage) {
        stage.setTitle("Bar Chart for Number of jobs by partitions");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Number of Jobs By Partitions");
        xAxis.setLabel("Month");       
        yAxis.setLabel("Value");
 
        XYChart.Series series = new XYChart.Series();
        series.setName("Number of jobs by partitions");       
        series.getData().add(new XYChart.Data(cpu_epyc, 2756));
        series.getData().add(new XYChart.Data(cpu_opteron, 4509));
        series.getData().add(new XYChart.Data(gpu_k10, 428));
        series.getData().add(new XYChart.Data(gpu_k40c, 295));
        series.getData().add(new XYChart.Data(gpu_titan, 640));      
        series.getData().add(new XYChart.Data(gpu_v100s, 588));
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}

