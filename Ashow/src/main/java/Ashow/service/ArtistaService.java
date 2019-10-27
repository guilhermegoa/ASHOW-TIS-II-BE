package Ashow.service;

import Ashow.business.Sistema;
import Ashow.business.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("artista")
public class ArtistaService {

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAllUser() {
        return Sistema.artistaDao.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUser(@PathParam("id") String id) {
        Usuario user = Sistema.artistaDao.get(id);
        return user;
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Usuario user) {
        Sistema.artistaDao.add(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Usuario user) {
        Sistema.artistaDao.update(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        Usuario user = Sistema.artistaDao.get(id);
        Sistema.artistaDao.remove(user);
        return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
    }
}
