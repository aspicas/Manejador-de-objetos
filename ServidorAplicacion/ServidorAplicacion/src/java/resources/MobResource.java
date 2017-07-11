/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Mob;
import services.MobService;
import xml.XMLManager;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("/objects")
@Produces(MediaType.APPLICATION_XML)
public class MobResource {
    private MobService mService = new MobService();
    
    @GET
    @Path("/{id}")
    public Mob getMob(@PathParam("id") int id) {
        Date date = new Date();
        return new Mob(id, date, "nombre", "accion");
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public void addMob(Mob mob) {
        mService.addMob(mob);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteMob(@PathParam("id") int id){
        
    }
}
