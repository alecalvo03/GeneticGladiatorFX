package characters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by Usuario on 16/05/2017.
 */
@JsonRootName("generation")
public class Generations {
    private int id;
    private boolean victory;
    private int mutations;
    private float average;

    public Generations(@JsonProperty("id")int id, @JsonProperty("victory")boolean victory,
                       @JsonProperty("mutations")int mutations, @JsonProperty("average")float average){
        this.id = id;
        this.victory = victory;
        this.mutations = mutations;
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public boolean won() {
        return victory;
    }

    public int getMutations() {
        return mutations;
    }

    public float getAverage() {
        return average;
    }
}
