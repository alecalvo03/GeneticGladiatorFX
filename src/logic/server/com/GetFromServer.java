package logic.server.com;

import characters.Generations;
import characters.Gladiator;
import characters.PopulationInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper mapper = MapperSingleton.getMapper();
        JsonNode node = mapper.readTree(strResponse);
        JsonNode generations = node.get("generations").get("generation");
        PopulationInfo popu = mapper.treeToValue(node,PopulationInfo.class);
        List<Generations> genarray = new ArrayList<>(Arrays.asList(mapper.treeToValue(generations,Generations[].class)));
        popu.setGenerations(genarray);
        return popu;
    }

    public static Generations getGenerations() throws ExecutionException, InterruptedException, IOException {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/GG/gg/sync/join");
        final AsyncInvoker asyncInvoker = target.request().async();
        final Future<Response> responseFuture = asyncInvoker.get();
        final Response response = responseFuture.get();
        String strResponse = response.readEntity(String.class);
        JsonNode node = MapperSingleton.getMapper().readTree(strResponse);
        node = node.get("generation");
        return MapperSingleton.getMapper().treeToValue(node,Generations.class);
    }

}
