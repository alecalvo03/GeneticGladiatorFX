package logic.server.com;

import characters.Generations;
import characters.Gladiator;
import characters.PopulationInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class GetFromServer {
    private static String IP = "localhost";
    private static String Puerto = "9080";

    public static Gladiator[] getGladiators(int population){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/GG/gg/gladiator/population/"+population);
        Response response = target.request().get();
        return response.readEntity(Gladiator[].class);
    }

    public static PopulationInfo getPopulationInfo(int population) throws IOException {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/GG/gg/gladiator/population/info/"+population);
        Response response = target.request().get();
        String strResponse = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(strResponse);
        JsonNode generations = node.get("generations").get("generation");
        PopulationInfo popu = mapper.treeToValue(node,PopulationInfo.class);
        Generations[] genarray = mapper.treeToValue(generations,Generations[].class);
        popu.setGenerations(genarray);
        return popu;
    }

}
