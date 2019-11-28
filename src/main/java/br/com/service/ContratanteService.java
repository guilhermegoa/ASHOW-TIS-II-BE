package br.com.service;

import br.com.business.Contratante;
import br.com.business.Evento;
import br.com.business.Usuario;
import br.com.repository.Repository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("contratante")
public class ContratanteService {

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contratante> getAll() {
        return Repository.getINSTANCE().daoContratantes.getAll();
    }

    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contratante get(@PathParam("email") String email) {
        return Repository.getINSTANCE().daoContratantes.get(email);
    }

    @GET
    @Path("{email}/eventos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getEventos(@PathParam("email") String email) {
        ArrayList e = new ArrayList();
        Repository.getINSTANCE().daoContratantes.get(email).getEventos().forEach(o -> e.add(Repository.getINSTANCE().daoEventos.get(o)));
        return e;
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(Contratante contratante) {
        System.out.println("ADD Contratante:");
        Contratante newcontratante =
                new Contratante(contratante.getNome(), contratante.getSenha(), contratante.getEmail());
        System.out.println(newcontratante);
        boolean b = Repository.getINSTANCE().daoUsuarios.add(newcontratante);
        boolean a = Repository.getINSTANCE().daoContratantes.add(newcontratante);
        System.out.println(b);
        System.out.println(a);
        return b && a;
    }

    @PUT
    @Path("update")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean update(Contratante contratante) {
        System.out.println(contratante);
        String email = contratante.getEmail();
        boolean a = Repository.getINSTANCE().daoUsuarios.update(email, contratante);
        boolean b = Repository.getINSTANCE().daoContratantes.update(email, contratante);
        System.out.println(a);
        System.out.println(b);
        return a && b;
    }

    @DELETE
    @Path("delete/{email}")
    public boolean remove(@PathParam("email") String email) {
        System.out.println("DELETE Artista:");
        List<Usuario> contratantes =
                Repository.getINSTANCE().daoUsuarios.getAll().stream()
                        .filter(a -> a.getEmail().equals(email))
                        .filter(a -> a instanceof Contratante)
                        .collect(Collectors.toList());
        if (!contratantes.isEmpty()) {
            Contratante contratante = ((Contratante) contratantes.get(0));
            System.out.println(contratante);
            if (contratante != null) {
                boolean a = Repository.getINSTANCE().daoUsuarios.remove(contratante);
                boolean b = Repository.getINSTANCE().daoContratantes.remove(contratante);
                System.out.println(a);
                System.out.println(b);
                return a && b;
            } else return false;
        } else return false;
    }

    @POST
    @Path("log")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean log(LoginUsuario lodDados) {
        System.out.println("Login contratante:");
        System.out.println(lodDados);
        Contratante c = Repository.getINSTANCE().daoContratantes.get(lodDados.getEmail());
        if (c != null) {
            boolean resp = c.isSenha(lodDados.getSenha());
            return resp;
        } else return false;
    }
}
