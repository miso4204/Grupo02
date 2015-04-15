 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.estampate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Listman G.A
 */
@Path("/camiseta")
@Consumes({MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_XML})
public class Camisetas{   
    @Context
    private UriInfo uriInfo;
    @POST
    public Response agregarCamiseta(Camiseta vendedor) {
        System.out.println("Agrega Camiseta");
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    public Response eliminarCamiseta(@PathParam("id") Long id)  {
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCamisetas() {
          List<Camiseta> list=new ArrayList<>();
        list.add(new Camiseta(new BigDecimal(51), "1"));
        list.add(new Camiseta(new BigDecimal(851), "2"));
        list.add(new Camiseta(new BigDecimal(831), "3"));
        list.add(new Camiseta(new BigDecimal(31), "4"));
        list.add(new Camiseta(new BigDecimal(541), "5"));
        return Response.status(200).entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getCamiseta(@PathParam("id") Long id) {
        return Response.status(200).build();
    }
}
