package Ashow.service;

import Ashow.business.Contratante;
import Ashow.business.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;


@Path("contratante")
public class ContratanteService {// implements IService<Contratante> {

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getAll() {
        return Sistema.getRepository().dao_usuarios.getAll().stream().filter(o->o instanceof Contratante).collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario get(@PathParam("id") String id) {
       return Sistema.getRepository().dao_usuarios.get(Integer.parseInt(id));
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(Contratante contratante) {
        System.out.println(contratante);
        return Sistema.getRepository().dao_usuarios.add(contratante);
    }
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Contratante contratante) {
        Sistema.getRepository().dao_usuarios.update(contratante);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response remove(@PathParam("id") String id) {
        Usuario contratante = Sistema.getRepository().dao_usuarios.get(Integer.parseInt(id));
        Sistema.getRepository().dao_usuarios.remove(contratante);
        return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
    }
}
