package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Sistema;
import Ashow.business.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("artista")
public class ArtistaService {
    private Sistema sistema;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser() {
        return sistema.artistaDao.ARTISTA_DAO.getAll().toString();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUser(@PathParam("id") Integer id) {
        Usuario user = sistema.artistaDao.ARTISTA_DAO.get(id);
        return user;
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Artista user) {
        sistema.artistaDao.ARTISTA_DAO.add(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Artista user) {
        sistema.artistaDao.ARTISTA_DAO.update(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteProduct(@PathParam("id") Integer id) {
        System.out.println(sistema.artistaDao.ARTISTA_DAO.getAll());
        Artista user = sistema.artistaDao.ARTISTA_DAO.get(id);
        System.out.println();
        sistema.artistaDao.ARTISTA_DAO.remove(user);
        System.out.println();
        System.out.println(sistema.artistaDao.ARTISTA_DAO.getAll());
        System.out.println(user.toString());
        return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
    }
}
