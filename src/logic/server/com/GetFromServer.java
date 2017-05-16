package logic.server.com;

import characters.Gladiator;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class GetFromServer {
    private static String IP = "localhost";
    private static String Puerto = "9080";

    /*public static SingleLinkedList<Food> getFoodLists (String path) throws IOException, ClassNotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"+ IP +":"+Puerto);
        Response response = target.path("ChefServerProject/rest/inventory/"+path).request(MediaType.APPLICATION_JSON).get();
        String responsii = response.readEntity(String.class);
        SingleLinkedList<Food> list = JsonImp.getFoodList(responsii);
        return list;
    }

    public static Message getChatMsg(String nick) throws ExecutionException, InterruptedException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/ChefServerProject/rest/chat/"+nick);
        final AsyncInvoker asyncInvoker = target.request().async();
        final Future<Response> responseFuture = asyncInvoker.get();
        System.out.println("Request is being processed asynchronously.");
        final Response response = responseFuture.get();
        String responsii = response.readEntity(String.class);
        System.out.println("Response received." + responsii);
        String[] split = responsii.split("/");
        if (split.length > 1) {
            String from = responsii.split("/")[0];
            String msg = responsii.split("/")[1];
            Message message = new Message(from, "", msg);
            return message;
        } return null;
    }

    public static SingleLinkedList<MenuItem> getMenuItemLists (String path) throws IOException, ClassNotFoundException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"+ IP +":"+Puerto);
        Response response = target.path("ChefServerProject/rest/restaurant/menu/"+path).request(MediaType.APPLICATION_JSON).get();
        String responsii = response.readEntity(String.class);
        SingleLinkedList<MenuItem> list = JsonImp.getMenuList(responsii);
        return list;
    }

    public static SingleLinkedList<Order> getAllOrders () throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/ChefServerProject/rest/orders/getall");
        Response response = target.request(MediaType.TEXT_PLAIN).get();
        String strResponse = response.readEntity(String.class);
        SingleLinkedList<Order> list = ParseToOrderList.parse(strResponse);
        return list;
    }

    public static String[] requestOrder(String ID) throws IOException {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/ChefServerProject/rest/orders/request/"+ID);
        Response response = target.request().get();
        String strOrder = response.readEntity(String.class);
        Order order = OrderHandler.parser(JsonImp.nodeThis(strOrder));
        for (int i = 0; i < order.getItemsInfo().getSize(); i++){
            System.out.println(order.getItemsInfo().getDataAt(i).getChefID());
            if (order.getItemsInfo().getDataAt(i).getChefID().equals("null")){
                target = client.target("http://"+ IP +":"+Puerto+"/ChefServerProject/rest/selection/"
                + order.getOrderID() + "/"
                + order.getItemsInfo().getDataAt(i).getItemID() + "/"
                + ID);
                Response taken = target.request().get();
                System.out.println(taken.getStatus());
                if (taken.getStatus() == 200){
                    order.getItemsInfo().getDataAt(i).setChefID(ID);
                    for (Order order1 : OrderList.getOrderList()){
                        if (order1.getOrderID().equals(order.getOrderID())){
                            order1.getItemsInfo().getDataAt(i).setChefID(ID);
                            return new String[] {order.getOrderID(),order.getItemsInfo().getDataAt(i).getItemID()};
                        }
                    }
                }
            } else {
                System.out.println("No entré a "+order.getItemsInfo().getDataAt(i)+" porque el chef es "+order.getItemsInfo().getDataAt(i).getChefID());
            }
        }
        return null;
    }

    public static Order incomingOrder(String id) throws ExecutionException, InterruptedException, IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/ChefServerProject/rest/manager/incomingorder/"+id);
        final AsyncInvoker asyncInvoker = target.request().async();
        final Future<Response> responseFuture = asyncInvoker.get();
        final Response response = responseFuture.get();
        String responsii = response.readEntity(String.class);
        if (responsii.equals("reload"))return null;
        Order order = OrderHandler.parser(JsonImp.nodeThis(responsii));
        return order;
    }*/

    public static Gladiator[] getGladiaor(int population){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/GG/gg/gladiator/population/"+population);
        Response response = target.request().get();
        return response.readEntity(Gladiator[].class);
    }

    /*public static void login() throws IOException, URISyntaxException {
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/ChefServerProject/rest/chef/login");
        Response response = target.request().get();
        String link = response.readEntity(String.class);
        Browser.openDefaultBrowser(link);
    }*/
    public static String login(String ID){
        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        WebTarget target = client.target("http://"+ IP +":"+Puerto+"/ChefServerProject/rest/chef/login/"+ID);
        Response response = target.request().get();
        return response.readEntity(String.class);
    }

}
