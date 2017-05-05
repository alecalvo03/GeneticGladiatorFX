package population;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Usuario on 29/04/2017.
 */
public class PopulationController implements Initializable{

    @FXML private LineChart<String,Float> lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String,Float> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("1",10.22f));
        series.getData().add(new XYChart.Data<>("2",25.3f));
        series.getData().add(new XYChart.Data<>("3",43.27f));
        series.getData().add(new XYChart.Data<>("4",69.69f));
        series.getData().add(new XYChart.Data<>("5",77.6f));
        series.getData().add(new XYChart.Data<>("6",87.96f));
        series.setName("Average Fitness");
        lineChart.getData().add(series);

        XYChart.Series<String,Float> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("1",23.22f));
        series1.getData().add(new XYChart.Data<>("2",30.3f));
        series1.getData().add(new XYChart.Data<>("3",53.27f));
        series1.getData().add(new XYChart.Data<>("4",79.69f));
        series1.getData().add(new XYChart.Data<>("5",82.6f));
        series1.getData().add(new XYChart.Data<>("6",91.96f));
        series1.setName("Pene Fitness");
        lineChart.getData().add(series1);

        for (final XYChart.Data data : series.getData()){
            Tooltip.install(data.getNode(),new Tooltip("Fitness: " + String.valueOf(data.getYValue())));
        }

        for (final XYChart.Data data : series1.getData()){
            Tooltip.install(data.getNode(),new Tooltip("Fitness: " + String.valueOf(data.getYValue())));
        }
    }
}
