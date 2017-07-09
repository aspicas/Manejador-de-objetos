package services;

import common.Mob;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by gbsojo on 7/9/17.
 */
@Path("/objects")
@Produces(MediaType.APPLICATION_XML)
public class MobServices {

    @GET
    @Path("/{id}")
    public Mob getObject(@PathParam("id") int id){
        Date date = new Date();
        Mob m = new Mob(id, date, "hola", "saludar");
        return m;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Mob createObject(Mob mob){
        return mob;
    }

    @DELETE
    @Path("/{id}")
    public Mob deleteObject(@PathParam("id") int id){
        return null;
    }

}
