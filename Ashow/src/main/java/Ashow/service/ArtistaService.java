package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("artista")
public class ArtistaService {

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Artista> getAll() {
        List<Artista> a = Sistema.getRepository().dao_artistas.getAll();
        return a;
    }

    @GET
    @Path("{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public Artista get(@PathParam("email") String email) {
        Usuario a = Sistema.getRepository().dao_usuarios.get(email);
        if (a instanceof Artista)
            return ((Artista) a);
        else return null;
    }

    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean add(Artista artista) {
        System.out.println("ADD Artista:");
        Artista artistanew = new Artista(artista.getNome(), artista.getNomeArtistico(), artista.getSenha(), artista.getEmail(), artista.getEstilo(), artista.getTipoArtista());
        System.out.println(artistanew);
        boolean b = Sistema.getRepository().dao_usuarios.add(artistanew);
        boolean a = Sistema.getRepository().dao_artistas.add(artistanew);
        System.out.println(a);
        System.out.println(b);
        return b && a;
    }

    @POST
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean update(Artista artista) {
        System.out.println(artista);
        String email = artista.getEmail();
        boolean a = Sistema.getRepository().dao_usuarios.update(email,artista);
        boolean b = Sistema.getRepository().dao_artistas.update(email,artista);
        System.out.println(a);
        System.out.println(b);
        return a && b;
    }

    @DELETE
    @Path("delete/{email}")
    public boolean remove(@PathParam("email") String email) {
        System.out.println("DELETE Artista:");
        List<Usuario> artistas = Sistema.getRepository().dao_usuarios.getAll().stream().filter(a -> a.getEmail().equals(email)).filter(a -> a instanceof Artista).collect(Collectors.toList());
        if (!artistas.isEmpty()) {
            Artista artista = ((Artista) artistas.get(0));
            System.out.println(artista);
            if (artista != null) {
                boolean a = Sistema.getRepository().dao_usuarios.remove(artista);
                boolean b = Sistema.getRepository().dao_artistas.remove(artista);
                System.out.println(a);
                System.out.println(b);
                return a && b;
            } else return false;
        } else return false;
    }

}
