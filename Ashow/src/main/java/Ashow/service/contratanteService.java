package Ashow.service;

import Ashow.business.Contratante;
import Ashow.business.Sistema;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("contratante")
public class contratanteService {
    private Sistema sistema;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser() {
        return Sistema.contratanteDao.CONTRATANTE_DAO.getAll().toString();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  String getUser(@PathParam("id") String id) {
       return Sistema.contratanteDao.CONTRATANTE_DAO.get(Integer.parseInt(id)).toString();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(String nome) {
        System.out.println(nome);
        Sistema.contratanteDao.CONTRATANTE_DAO.add(new Contratante(nome, "", ""));
        return Response.status(Response.Status.CREATED).build();
    }

//    @POST
//    @Path("add")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response add(Object jsonObject) {
//        System.out.println(jsonObject);
////        Contratante contratante = new Contratante("","","");
////        Sistema.contratanteDao.CONTRATANTE_DAO.add(contratante);
//        return Response.status(Response.Status.CREATED).build();
//    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Contratante contratante) {
        Sistema.contratanteDao.CONTRATANTE_DAO.update(contratante);
        return Response.ok().build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        Contratante contratante = Sistema.contratanteDao.CONTRATANTE_DAO.get(Integer.parseInt(id));
        Sistema.contratanteDao.CONTRATANTE_DAO.remove(contratante);
        return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
    }
}
