package Ashow.service;

import Ashow.business.Contratante;
import Ashow.business.Usuario;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("contratante")
public class ContratanteService {

  @GET
  @Path("all")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Contratante> getAll() {
    return Sistema.getRepository().daoContratantes.getAll();
  }

  @GET
  @Path("{email}")
  @Produces(MediaType.APPLICATION_JSON)
  public Contratante get(@PathParam("email") String email) {
    Usuario a = Sistema.getRepository().daoUsuarios.get(email);
    if (a instanceof Contratante) return ((Contratante) a);
    else return null;
  }

  @POST
  @Path("add")
  @Consumes(MediaType.APPLICATION_JSON)
  public boolean add(Contratante contratante) {
    System.out.println("ADD Contratante:");
    Contratante newcontratante =
        new Contratante(contratante.getNome(), contratante.getSenha(), contratante.getEmail());
    System.out.println(newcontratante);
    boolean b = Sistema.getRepository().daoUsuarios.add(newcontratante);
    boolean a = Sistema.getRepository().daoContratantes.add(newcontratante);
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
    boolean a = Sistema.getRepository().daoUsuarios.update(email, contratante);
    boolean b = Sistema.getRepository().daoContratantes.update(email, contratante);
    System.out.println(a);
    System.out.println(b);
    return a && b;
  }

  @DELETE
  @Path("delete/{email}")
  public boolean remove(@PathParam("email") String email) {
    System.out.println("DELETE Artista:");
    List<Usuario> contratantes =
        Sistema.getRepository().daoUsuarios.getAll().stream()
            .filter(a -> a.getEmail().equals(email))
            .filter(a -> a instanceof Contratante)
            .collect(Collectors.toList());
    if (!contratantes.isEmpty()) {
      Contratante contratante = ((Contratante) contratantes.get(0));
      System.out.println(contratante);
      if (contratante != null) {
        boolean a = Sistema.getRepository().daoUsuarios.remove(contratante);
        boolean b = Sistema.getRepository().daoContratantes.remove(contratante);
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
    Contratante c = Sistema.getRepository().daoContratantes.get(lodDados.getEmail());
    if (c != null) {
      boolean resp = c.isSenha(lodDados.getSenha());
      return resp;
    } else return false;
  }
}
