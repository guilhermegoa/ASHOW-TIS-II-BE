package Ashow.service;

import Ashow.business.Artista;
import Ashow.service.Sistema;
import Ashow.interfac.IService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/artista")
public class ArtistaService implements IService<Artista>{
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
    public boolean add(Artista artista) {
        Artista artistanew = new Artista(artista.getNome(),artista.getNomeArtistico(),artista.getSenha(),artista.getEmail(),artista.getEstilo(),artista.getTipoArtista());
        System.out.println(artistanew);
        return Sistema.artistaDao.ARTISTA_DAO.add(artistanew);
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public boolean update(Artista artista) {
        return Sistema.artistaDao.ARTISTA_DAO.update(artista);
    }

    @DELETE
    @Path("delete/{id}")
    @Override
    public boolean remove(@PathParam("id") String id) {
        Artista artista = Sistema.artistaDao.ARTISTA_DAO.get(Integer.parseInt(id));
        return Sistema.artistaDao.ARTISTA_DAO.remove(artista);
    }

}
