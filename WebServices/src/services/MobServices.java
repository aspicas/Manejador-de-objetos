package services;

import common.Mob;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by gbsojo on 7/9/17.
 */
@Path("/objects")
@Produces(MediaType.APPLICATION_XML)
public class MobServices {

    @POST
    public Mob createObject(Mob mob){
        return mob;
    }

    @DELETE
    @Path("/{id}")
    public Mob deleteObject(@PathParam("id") int id){
        return null;
    }

    @GET
    @Path("/{id}")
    public Mob getObject(@PathParam("id") int id){
        return null;
    }

}
