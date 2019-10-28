package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Sistema;
import Ashow.interfac.IService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/artista")
public class ArtistaService implements IService <Artista>{
    private Sistema sistema;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<Artista> getAll() {
        return sistema.artistaDao.ARTISTA_DAO.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Artista get(@PathParam("id") String integer) {
        return sistema.artistaDao.ARTISTA_DAO.get(Integer.parseInt(integer));
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response add(Artista artista) {
        return null;
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response update(Artista artista) {
        return null;
    }

    @DELETE
    @Path("delete/{id}")
    @Override
    public Response remove(@PathParam("id") String id) {
        Artista artista = Sistema.artistaDao.ARTISTA_DAO.get(Integer.parseInt(id));
        Sistema.artistaDao.ARTISTA_DAO.remove(artista);
        return Response.status(202).entity("Artista " + id + " removido com sucesso.").build();
    }

}
