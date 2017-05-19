package javafx.info;

import characters.Gladiator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Usuario on 18/05/2017.
 */
public class InfoPaneController implements Initializable{

    @FXML private ListView<Gladiator> lvRomans;
    @FXML private ListView<Gladiator> lvGreeks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvRomans.setItems(GladiatorLists.getRomanList());
        lvRomans.setCellFactory(new Callback<ListView<Gladiator>, ListCell<Gladiator>>() {
            @Override
            public ListCell<Gladiator> call(ListView<Gladiator> param) {
                return new ListCell<Gladiator>(){
                    @Override
                    protected void updateItem(Gladiator item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        lvGreeks.setItems(GladiatorLists.getGreekList());
        lvGreeks.setCellFactory(lvRomans.getCellFactory());
    }
}
