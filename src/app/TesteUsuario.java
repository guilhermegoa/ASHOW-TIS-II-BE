package app;

import business.Contratante;
import business.Usuario;
import dao.IDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.util.List;

public class TesteUsuario {
    public static void main(String args[]) throws IOException {

        IDAO<Usuario, String> contratanteDAO = new UsuarioDAO("contratante.bin");

        contratanteDAO.add(new Contratante("Guilherme","1234","gui@gui.br"));
        contratanteDAO.add(new Contratante("Arthur","1234","aaa@aaa.br"));
        contratanteDAO.add(new Contratante("Daniel","1234","ddd@ddd.br"));
        contratanteDAO.add(new Contratante("Matheus","1234","ffm@ffm.br"));
        contratanteDAO.add(new Contratante("Gulio","1234","gugu@gugu.br"));


        contratanteDAO.update(new Contratante("Guilherme","4321","gui@gui.br.com"));
        contratanteDAO.remove(new Contratante("Gulio","1234","gugu@gugu.br"));

        List<Usuario> usuarios = contratanteDAO.getAll();

        for (Usuario usuario: usuarios) {
            System.out.println(usuario);
        }

        System.out.println("---------------------------------------------");
//
//        DAO<Produto, String>  bemDuravelDAO =
//                new ProdutoDAO("bemduravel.bin");
//        bemDuravelDAO.add(new BemDuravel("Notebook", 2440.00F, 20, LocalDateTime.now(), 12));
//        bemDuravelDAO.add(new BemDuravel("Televisao", 1200.00F, 10, LocalDateTime.now(), 24));
//        bemDuravelDAO.add(new BemDuravel("Geladeira", 4000.00F, 10, LocalDateTime.now(), 36));
//
//        List<Produto> bensDuraveis = bemDuravelDAO.getAll();
//
//        for (Produto bemDuravel: bensDuraveis) {
//            System.out.println(bemDuravel);
//        }
//
//        System.out.println(bemDuravelDAO.get("Televisao"));


    }
}
