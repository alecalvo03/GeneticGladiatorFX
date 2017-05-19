package javafx.login;

import javafx.MainFX;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Usuario on 18/05/2017.
 */
public class LogInController {

    @FXML private TextField tfIP;
    @FXML private TextField tfPuerto;

    public void connectEvent() throws IOException {
        MainFX.ip = tfIP.getText();
        MainFX.puerto = tfPuerto.getText();
        MainFX.showInfoPane();
    }

}
