package javafx.info;

import characters.Gladiator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.server.com.GetFromServer;

import java.util.Collections;

/**
 * Created by Usuario on 18/05/2017.
 */
public class GladiatorLists {

    private static ObservableList<Gladiator> romanList = FXCollections.observableArrayList();
    private static ObservableList<Gladiator> greekList = FXCollections.observableArrayList();

    public static void setLists(){
        Gladiator[] romans = GetFromServer.getGladiators(0);
        Gladiator[] greeks = GetFromServer.getGladiators(1);
        Collections.addAll(romanList, romans);
        Collections.addAll(greekList, greeks);
    }

    public static ObservableList<Gladiator> getRomanList() {
        return romanList;
    }

    public static ObservableList<Gladiator> getGreekList() {
        return greekList;
    }
}
