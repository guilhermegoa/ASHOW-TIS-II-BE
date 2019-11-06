package Ashow.service;

import Ashow.business.Contratante;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("contratante")
public class contratanteService {// implements IService<Contratante> {
    private Sistema sistema;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contratante> getAll() {
        return Sistema.contratanteDao.CONTRATANTE_DAO.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Contratante get(@PathParam("id") String id) {
       return Sistema.contratanteDao.CONTRATANTE_DAO.get(Integer.parseInt(id));
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean add(Contratante contratante) {
        System.out.println(contratante);
        return Sistema.contratanteDao.CONTRATANTE_DAO.add(contratante);
    }
    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Contratante contratante) {
        Sistema.contratanteDao.CONTRATANTE_DAO.update(contratante);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response remove(@PathParam("id") String id) {
        Contratante contratante = Sistema.contratanteDao.CONTRATANTE_DAO.get(Integer.parseInt(id));
        Sistema.contratanteDao.CONTRATANTE_DAO.remove(contratante);
        return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
    }
}
