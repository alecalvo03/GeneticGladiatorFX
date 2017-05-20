package javafx.info;

import characters.Gladiator;
import javafx.MainFX;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Usuario on 18/05/2017.
 */
public class InfoPaneController implements Initializable{

    @FXML private ListView<Gladiator> lvRomans;
    @FXML private ListView<Gladiator> lvGreeks;
    @FXML private TableView<Gladiator> tbvStats;
    @FXML private TableColumn<Gladiator,String> tbcSkillName;
    @FXML private TableColumn<Gladiator,Float> tbcValue;
    @FXML private Button btnRoman;
    @FXML private Button btnGreek;
    @FXML private Label lbName;

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

        FilteredList<Gladiator> skillNameList = new FilteredList<>(GladiatorLists.getRomanList());
        SortedList<Gladiator> sortedSkill = new SortedList<>(skillNameList);
        sortedSkill.comparatorProperty().bind(tbvStats.comparatorProperty());
        tbvStats.setItems(sortedSkill);
    }

    public void openRomanChart() throws IOException {
        MainFX.showRomanChart();
    }

    public void openGreekChart() throws IOException {
        MainFX.showGreekChart();
    }

    public void selectedRoman(){
        updateStats(lvRomans.getSelectionModel().getSelectedItem());
    }

    public void selectedGreek(){
        updateStats(lvGreeks.getSelectionModel().getSelectedItem());
    }

    public void updateStats(Gladiator gladiator){

    }
}
