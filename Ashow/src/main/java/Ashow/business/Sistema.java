package Ashow.business;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import Ashow.dao.UsuarioDAO;

public class Sistema {

    public static final UsuarioDAO artistaDao = initArtista();
    public static final UsuarioDAO contratanteDao = initContratante();

    public static UsuarioDAO initArtista() {
        UsuarioDAO artistaDao = null;
        try {
            artistaDao = new UsuarioDAO("artista.bin");
            artistaDao.add(new Artista("João", "Nevasca", "ABCD", "joao@neves.br", "Sertanejo", "Solo"));
        } catch (IOException e) {
        }
        return artistaDao;
    }

    public static UsuarioDAO initContratante() {
        UsuarioDAO contratanteDao = null;
        try {
            contratanteDao = new UsuarioDAO("contratante.bin");
            Endereco e = new Endereco("3201998", "Tereza", "13", "Tropical", "Contagem", "MG", "Próximo a uma padaria");

            Evento evento = new Evento("Testando", 120, 1200.00, "Rock", LocalDateTime.now(), e);
            Avaliacao avaliacao = new Avaliacao("Muito Bom!", 5, evento);

            Contratante contratante1 = new Contratante("Helen", "1234", "whu@united.br");
            contratante1.criarEvento(evento);
            contratante1.addAvaliacao(avaliacao);

            contratanteDao.add(contratante1);
            contratanteDao.add(new Contratante("Guilherme", "1234", "gui@gui.br"));
            contratanteDao.add(new Contratante("Arthur", "1234", "aaa@aaa.br"));
            contratanteDao.add(new Contratante("Daniel", "1234", "ddd@ddd.br"));
            contratanteDao.add(new Contratante("Matheus", "1234", "ffm@ffm.br"));
            contratanteDao.add(new Contratante("Gulio", "1234", "gugu@gugu.br"));
        } catch (IOException e) {
        }
        return contratanteDao;
    }

    public static int count() {
        int total = 0;
        List<Usuario> artistas = artistaDao.getAll();
        for (Usuario user : artistas)
            if (user != null)
                total++;
        List<Usuario> contratantes = contratanteDao.getAll();
        for (Usuario user : contratantes)
            if (user != null)
                total++;
        return total;
    }

    public Sistema() {

        UsuarioDAO artistaDao = initArtista();
        UsuarioDAO contratanteDao = initContratante();
    }
}
