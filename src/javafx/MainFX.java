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
import logic.server.com.GetFromServer;
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
        GladiatorLists.setLists(GetFromServer.getGladiators(0),GetFromServer.getGladiators(1));
        BorderPane infoPane = FXMLLoader.load(MainFX.class.getResource("info/InfoPane.fxml"));
        mainLayout.setCenter(infoPane);
    }

    public static void showPopulationChart(int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFX.class.getResource("../population/PopulationView.fxml"));
        BorderPane population = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.id = id;
        mainLayout.setCenter(population);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

