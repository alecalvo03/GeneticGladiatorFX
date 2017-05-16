import characters.Gladiator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.server.com.GetFromServer;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    private static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Genetic Gladiator");
        Gladiator[] gladiators = GetFromServer.getGladiaor(0);
        System.out.println(gladiators[1]);
        showMainPane();
        showPopulationView();
    }

    public void showMainPane() throws IOException {
        mainLayout = FXMLLoader.load(getClass().getResource("MainPane.fxml"));
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showPopulationView() throws IOException {
        BorderPane population = FXMLLoader.load(getClass().getResource("population/PopulationView.fxml"));
        mainLayout.setCenter(population);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
