package Ashow.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import Ashow.business.Artista;
import Ashow.business.Sistema;
import Ashow.business.Usuario;

public class artistaService {
    @Path("artista")
    public class BemDeConsumoService {

        @GET
        @Path("all")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Usuario> getAllUser() {
        	List<Usuario> usersaux = Sistema.artistaDao.getAll();
        	List<Usuario> users = null;
        	
        	for(Usuario user : usersaux) {
        		users.addAll((Collection<? extends Usuario>) user.toJson());
        	}
        	
            return users;
        }

        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public JSONObject getProduto(@PathParam("id") String id) {
            Usuario user = Sistema.artistaDao.get(id);
            return user.toJson();
        }

        @POST
        @Path("add")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addUser(Artista user) {
        	Sistema.artistaDao.add(user);
            return Response.status(Status.CREATED).build();
        }

        @PUT
        @Path("update")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateUser(Artista user) {
        	Sistema.artistaDao.update(user);
            return Response.ok().build();
        }

        @DELETE
        @Path("delete/{id}")
        public Response deleteUser(@PathParam("id") String id) {
            Usuario user = Sistema.artistaDao.get(id);
            Sistema.artistaDao.remove(user);
            return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
        }
    }
}
