package javafx.info.charts;

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
 * Created by Usuario on 20/05/2017.
 */
public class RomanChartController implements Initializable{
    private PopulationInfo populationInfo;
    public boolean running = true;

    @FXML
    private LineChart<String,Float> lineChart;

    private XYChart.Series<String,Float> averageFitness = new XYChart.Series<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populationInfo = GetFromServer.getPopulationInfo(0);
            Thread t = new Thread(()-> {
                while(running) {
                    Generations generations = null;
                    try {
                        generations = GetFromServer.getGenerations(0);
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

        addToolTip();
    }

    public void addToolTip(){
        for (final XYChart.Data data : averageFitness.getData()){
            Tooltip.install(data.getNode(),new Tooltip("Fitness: " + String.valueOf(data.getYValue())));
        }
    }

    public void updateGraph(){
        averageFitness.getData().removeAll();
        for (Generations generation : populationInfo.getGenerations()) {
            averageFitness.getData().add(new XYChart.Data<>(String.valueOf(generation.getId()),generation.getAverage()));
        }
        addToolTip();
    }
}
