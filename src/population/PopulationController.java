package population;

import characters.Generations;
import characters.PopulationInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import logic.server.com.GetFromServer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

/**
 * Created by Usuario on 29/04/2017.
 */
public class PopulationController implements Initializable{

    private PopulationInfo populationInfo;
    public int id;
    public boolean running = true;

    @FXML private LineChart<String,Float> lineChart;

    private XYChart.Series<String,Float> averageFitness = new XYChart.Series<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populationInfo = GetFromServer.getPopulationInfo(id);
            Thread t = new Thread(()-> {
                while(running) {
                    Generations generations = null;
                    try {
                        generations = GetFromServer.getGenerations();
                        System.out.println(generations.getAverage());
                        populationInfo.getGenerations().add(generations);
                        Platform.runLater(this::updateGraph);
                    } catch (ExecutionException | InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateGraph();

        averageFitness.setName("Average Fitness");
        lineChart.getData().add(averageFitness);

        XYChart.Series<String,Float> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>("1",23.22f));
        series1.getData().add(new XYChart.Data<>("2",30.3f));
        series1.getData().add(new XYChart.Data<>("3",53.27f));
        series1.getData().add(new XYChart.Data<>("4",79.69f));
        series1.getData().add(new XYChart.Data<>("5",82.6f));
        series1.getData().add(new XYChart.Data<>("6",91.96f));
        series1.setName("Pene Fitness");
        lineChart.getData().add(series1);

        for (final XYChart.Data data : averageFitness.getData()){
            Tooltip.install(data.getNode(),new Tooltip("Fitness: " + String.valueOf(data.getYValue())));
        }

        for (final XYChart.Data data : series1.getData()){
            Tooltip.install(data.getNode(),new Tooltip("Fitness: " + String.valueOf(data.getYValue())));
        }
    }

    public void updateGraph(){
        averageFitness.getData().removeAll();
        for (Generations generation : populationInfo.getGenerations()) {
            averageFitness.getData().add(new XYChart.Data<>(String.valueOf(generation.getId()),generation.getAverage()));
        }
    }
}
