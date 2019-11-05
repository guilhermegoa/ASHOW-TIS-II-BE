package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/artista")
public class ArtistaService{

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Artista> getAll() {
        List<Artista> a = new ArrayList<>();
        Sistema.getRepository().dao_usuarios.getAll().stream().filter(o->o instanceof Artista).forEach(o -> a.add(((Artista) o)));
        return a;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Artista get(@PathParam("id") String integer) {
        Usuario a = Sistema.getRepository().dao_usuarios.get(Integer.parseInt(integer));
        if(a instanceof Artista)
            return ((Artista) a);
        else return null;
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(Artista artista) {
        Artista artistanew = new Artista(artista.getNome(),artista.getNomeArtistico(),artista.getSenha(),artista.getEmail(),artista.getEstilo(),artista.getTipoArtista());
        System.out.println(artistanew);
        return Sistema.getRepository().dao_usuarios.add(artistanew);
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean update(Artista artista) {
        return Sistema.getRepository().dao_usuarios.update(artista);
    }

    @DELETE
    @Path("delete/{id}")
    public boolean remove(@PathParam("id") String id) {
        Usuario artista = Sistema.getRepository().dao_usuarios.get(Integer.parseInt(id));
        return Sistema.getRepository().dao_usuarios.remove(artista);
    }

}
