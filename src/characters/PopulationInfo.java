package characters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Usuario on 16/05/2017.
 */
@JsonIgnoreProperties({"id","generations"})
public class PopulationInfo {
    private int size;
    private String kind;
    private Generations[] generations;

    public PopulationInfo(@JsonProperty("size")int size, @JsonProperty("kind")String kind){
        this.size = size;
        this.kind = kind;
    }

    public int getSize() {
        return size;
    }

    public String getKind() {
        return kind;
    }

    public Generations[] getGenerations() {
        return generations;
    }

    public void setGenerations(Generations[] generations) {
        this.generations = generations;
    }
}
