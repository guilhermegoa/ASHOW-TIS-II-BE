//package Ashow.service;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import java.util.List;
//
//public class contratanteService {
//    @Path("contratante")
//    public class BemDeConsumoService {
//
//        @GET
//        @Path("all")
//        @Produces(MediaType.APPLICATION_JSON)
//        public List<BemDeConsumo> getAllProducts() {
//            return Estoque.bemDeConsumoDao.getAll();
//        }
//
//        @GET
//        @Path("{id}")
//        @Produces(MediaType.APPLICATION_JSON)
//        public BemDeConsumo getProduto(@PathParam("id") String id) {
//            BemDeConsumo produto = Estoque.bemDeConsumoDao.get(id);
//            return produto;
//        }
//
//        @POST
//        @Path("add")
//        @Consumes(MediaType.APPLICATION_JSON)
//        public Response addProduct(BemDeConsumo produto) {
//            Estoque.bemDeConsumoDao.add(produto);
//            return Response.status(Response.Status.CREATED).build();
//        }
//
//        @PUT
//        @Path("update")
//        @Consumes(MediaType.APPLICATION_JSON)
//        public Response updateProduct(BemDeConsumo produto) {
//            Estoque.bemDeConsumoDao.update(produto);
//            return Response.ok().build();
//        }
//
//        @DELETE
//        @Path("delete/{id}")
//        public Response deleteProduct(@PathParam("id") String id) {
//            BemDeConsumo produto = Estoque.bemDeConsumoDao.get(id);
//            Estoque.bemDeConsumoDao.remove(produto);
//            return Response.status(202).entity("Produto " + id + " removido com sucesso.").build();
//        }
//    }
//}
