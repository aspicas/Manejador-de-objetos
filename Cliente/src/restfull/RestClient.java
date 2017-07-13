/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restfull;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import static javax.ws.rs.client.ClientBuilder.newClient;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import model.Mob;
import model.Registro;

/**
 * Jersey REST client generated for REST resource:MobResource [/objects]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author david
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;

    public RestClient() {
        client = newClient();
        webTarget = client.target(Registro.BASE_URI).path("objects");
    }

//    public <T> T getMob(Class<T> responseType, String id) throws ClientErrorException {
//        WebTarget resource = webTarget;
//        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
//        return resource.get(responseType);
//    }

    public void addMob(Mob mob) throws ClientErrorException {
        webTarget.request(MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(mob, MediaType.APPLICATION_XML));
    }

    public void deleteMob(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
