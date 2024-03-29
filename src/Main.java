import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import population.PopulationController;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;
    private static BorderPane mainLayout;
    private PopulationController controller = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Genetic Gladiator");
        Main.primaryStage.setOnCloseRequest(event -> {
            if (controller!=null)
                controller.running = false;
            System.exit(0);
        });
        showMainPane();
        showLogInPane();
        //showPopulationView(0);
    }

    public void showMainPane() throws IOException {
        mainLayout = FXMLLoader.load(getClass().getResource("javafx/MainPane.fxml"));
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLogInPane() throws IOException {
        Pane logInPane = FXMLLoader.load(getClass().getResource("javafx/login/LogInPane.fxml"));
        mainLayout.setCenter(logInPane);
    }

    public void showPopulationView(int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("population/PopulationView.fxml"));
        BorderPane population = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.id = id;
        mainLayout.setCenter(population);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
