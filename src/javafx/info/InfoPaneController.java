package javafx.info;

import characters.Gladiator;
import javafx.MainFX;
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
    @FXML private Button btnRoman;
    @FXML private Button btnGreek;
    @FXML private Label lbName;
    @FXML private Label lbKind;
    @FXML private Label lbGender;
    @FXML private Label lbHStrikeDmg;
    @FXML private Label lbFStrikeDmg;
    @FXML private Label lbHStrikeRes;
    @FXML private Label lbFStrikeRes;
    @FXML private Label lbArrowRes;
    @FXML private Label lbTotalRes;
    @FXML private Label lbStamina;
    @FXML private Label lbMaxSpeed;
    @FXML private Label lbTime;
    @FXML private Label lbScore;
    @FXML private Label lbSexAttract;
    @FXML private Label lbBeauty;
    @FXML private Label lbIntelligence;



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
        lbName.setText(gladiator.getName());
        lbKind.setText(gladiator.getKind());
        lbGender.setText(gladiator.getGender());
        lbHStrikeDmg.setText(String.valueOf(gladiator.getHandStrikeDamage()));
        lbFStrikeDmg.setText(String.valueOf(gladiator.getFootStrikeDamage()));
        lbHStrikeRes.setText(String.valueOf(gladiator.getHandStrikeResistance()));
        lbFStrikeRes.setText(String.valueOf(gladiator.getFootStrikeResistance()));
        lbArrowRes.setText(String.valueOf(gladiator.getArrowResistance()));
        lbTotalRes.setText(String.valueOf(gladiator.getTotalResistance()));
        lbStamina.setText(String.valueOf(gladiator.getStamina()));
        lbMaxSpeed.setText(String.valueOf(gladiator.getMaxSpeed()));
        lbTime.setText(String.valueOf(gladiator.getTime()));
        lbScore.setText(String.valueOf(gladiator.getScore()));
        lbSexAttract.setText(String.valueOf(gladiator.getSexualAttraction()));
        lbBeauty.setText(String.valueOf(gladiator.getBeauty()));
        lbIntelligence.setText(String.valueOf(gladiator.getIntelligence()));
    }
}
