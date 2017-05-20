package javafx;

/**
 * Created by Usuario on 18/05/2017.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.info.GladiatorLists;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import population.PopulationController;

import java.io.IOException;

public class MainFX extends Application {

    public static String ip = null;
    public static String puerto = null;

    private static Stage primaryStage;
    private static BorderPane mainLayout;
    private static PopulationController controller = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainFX.primaryStage = primaryStage;
        MainFX.primaryStage.setTitle("Genetic Gladiator");
        MainFX.primaryStage.setOnCloseRequest(event -> {
            if (controller!=null)
                controller.running = false;
            System.exit(0);
        });
        showMainPane();
        showLogInPane();
        //showPopulationView(0);
    }

    public void showMainPane() throws IOException {
        mainLayout = FXMLLoader.load(getClass().getResource("MainPane.fxml"));
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLogInPane() throws IOException {
        Pane logInPane = FXMLLoader.load(getClass().getResource("login/LogInPane.fxml"));
        mainLayout.setCenter(logInPane);
    }

    public static void showInfoPane() throws IOException {
        GladiatorLists.setLists();
        BorderPane infoPane = FXMLLoader.load(MainFX.class.getResource("info/InfoPane.fxml"));
        mainLayout.setCenter(infoPane);
    }

    public static void showPopulationChart(int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("../population/PopulationView.fxml"));
        BorderPane population = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.id = id;
        Stage chartStage = new Stage();
        if (id == 0) {
            chartStage.setTitle("Roman Chart");
        } else {
            chartStage.setTitle("Greek Chart");
        }
        chartStage.initOwner(primaryStage);
        Scene scene = new Scene(population);
        chartStage.setScene(scene);
        chartStage.show();
        //mainLayout.setCenter(population);
    }

    public static void showRomanChart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("info/charts/RomanChart.fxml"));
        BorderPane population = fxmlLoader.load();
        Stage chartStage = new Stage();
        chartStage.setTitle("Roman Chart");
        chartStage.initOwner(primaryStage);
        Scene scene = new Scene(population);
        chartStage.setScene(scene);
        chartStage.show();
    }

    public static void showGreekChart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("info/charts/GreekChart.fxml"));
        BorderPane population = fxmlLoader.load();
        Stage chartStage = new Stage();
        chartStage.setTitle("Greek Chart");
        chartStage.initOwner(primaryStage);
        Scene scene = new Scene(population);
        chartStage.setScene(scene);
        chartStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

