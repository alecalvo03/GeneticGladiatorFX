package logic.server.com;

import characters.Generations;
import characters.Gladiator;
import characters.PopulationInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.MainFX;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetFromServer {
    private static String IP = MainFX.ip;
    private static String Puerto = MainFX.puerto;

    public static Gladiator[] getGladiators(int population){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/GG/gg/gladiator/population/"+population+"/0");
        Response response = target.request().get();
        return response.readEntity(Gladiator[].class);
    }

    public static PopulationInfo getPopulationInfo(int population) throws IOException {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/GG/gg/gladiator/population/info/"+population);
        Response response = target.request().get();
        String strResponse = response.readEntity(String.class);
        ObjectMapper mapper = MapperSingleton.getMapper();
        JsonNode node = mapper.readTree(strResponse);
        JsonNode generations = node.get("generations");
        PopulationInfo popu = mapper.treeToValue(node,PopulationInfo.class);
        if (generations.toString().length() == 2){
            popu.setGenerations(new ArrayList<>());
        } else {
            generations = generations.get("generation");
            List<Generations> genarray;
            if (generations.isArray()) {
                genarray = new ArrayList<>(Arrays.asList(mapper.treeToValue(generations, Generations[].class)));
            } else {
                genarray = new ArrayList<>(Arrays.asList(mapper.treeToValue(generations, Generations.class)));
            }
            popu.setGenerations(genarray);
        }
        return popu;
    }

    public static Generations getGenerations(int i) throws ExecutionException, InterruptedException, IOException {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/GG/gg/sync/join/"+i);
        final AsyncInvoker asyncInvoker = target.request().async();
        final Future<Response> responseFuture = asyncInvoker.get();
        final Response response = responseFuture.get();
        String strResponse = response.readEntity(String.class);
        JsonNode node = MapperSingleton.getMapper().readTree(strResponse);
        node = node.get("generation");
        Generations generations = MapperSingleton.getMapper().treeToValue(node,Generations.class);
        System.out.println(generations.getAverage());
        return generations;
    }

}
