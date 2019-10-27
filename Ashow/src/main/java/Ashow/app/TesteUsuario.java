package Ashow.app;

import Ashow.business.Artista;
import Ashow.business.Avaliacao;
import Ashow.business.Contratante;
import Ashow.business.Endereco;
import Ashow.business.Evento;
import Ashow.business.Usuario;
import Ashow.dao.IDAO;
import Ashow.dao.UsuarioDAO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

////////////////////////////////////////////
// NAO SEI SE VAMOS USAR ESSA CLASSE  /////
//////////////////////////////////////////

public class TesteUsuario {
    public static void main(String args[]) throws IOException {

    	//teste de inserção do usuário contratante
        IDAO<Usuario, String> contratanteDAO = new UsuarioDAO("contratante.bin");
        
        Endereco e = new Endereco("3201998", "Tereza", "13", "Tropical", "Contagem", "MG", "Próximo a uma padaria");
        
        Evento evento = new Evento("Testando", 120, 1200.00, "Rock", LocalDateTime.now(), e);
        Avaliacao avaliacao = new Avaliacao("Muito Bom!", 5, evento);
        
        Contratante contratante1 = new Contratante("Helen","1234","whu@united.br");
        contratante1.criarEvento(evento);
        contratante1.addAvaliacao(avaliacao);

        contratanteDAO.add(contratante1);
        contratanteDAO.add(new Contratante("Guilherme","1234","gui@gui.br"));
        contratanteDAO.add(new Contratante("Arthur","1234","aaa@aaa.br"));
        contratanteDAO.add(new Contratante("Daniel","1234","ddd@ddd.br"));
        contratanteDAO.add(new Contratante("Matheus","1234","ffm@ffm.br"));
        contratanteDAO.add(new Contratante("Gulio","1234","gugu@gugu.br"));
        
        System.out.println("<<<<<<<\n" + contratanteDAO.get("gui@gui.br") + "\n\n>>>>>>>\n");
        contratanteDAO.update(new Contratante("GOA","4321","gui@gui.br"));
        contratanteDAO.remove(new Contratante("Gulio","1234","gugu@gugu.br"));

        List<Usuario> usuarios = contratanteDAO.getAll();

        for (Usuario usuario: usuarios) {
            System.out.println(usuario);
            System.out.println("\nEventos: \n" + usuario.verEventos());
            System.out.println("\nAvaliações: \n" + usuario.verAvaliacoes());
            System.out.println("=====");
        }

        System.out.println("---------------------------------------------");

        //teste de inserção do usuário artista
        IDAO<Usuario, String> artistaDAO = new UsuarioDAO("artista.bin");
        artistaDAO.add(new Artista("João", "Nevasca", "ABCD", "joao@neves.br", "Sertanejo", "Solo"));

        List<Usuario> artistas = artistaDAO.getAll();

        for (Usuario usuario: artistas) {
            System.out.println(usuario);
            System.out.println("\nEventos: \n" + usuario.verEventos());
            System.out.println("=====");
        }
    }
}

