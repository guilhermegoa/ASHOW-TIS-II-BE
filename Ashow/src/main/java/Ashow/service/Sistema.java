package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Contratante;
import Ashow.repository.RepositoryArtista;
import Ashow.repository.RepositoryContratante;
import Ashow.repository.RepositoryUsuario;

public class Sistema {

    public static RepositoryArtista artistaDao = new RepositoryArtista();
    public static RepositoryContratante contratanteDao = new RepositoryContratante();
    public static RepositoryUsuario usuarioDao = new RepositoryUsuario();

    public static RepositoryArtista initArtista() {
        RepositoryArtista a = new RepositoryArtista();
        a.ARTISTA_DAO.add(new Artista("Matheus", "Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
        a.ARTISTA_DAO.add(new Artista("Guilherme", "GOA", "12345", "goa@email.com", "Pagodeiro", "Solo"));
        Artista x = new Artista("Arthur", "Tuba", "12345", "tuba@email.com", "Emocore", "Solo");
        a.ARTISTA_DAO.add(x);
        a.ARTISTA_DAO.remove(x);
        a.ARTISTA_DAO.add(new Artista("Daniel", "Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
        return a;
    }

    public static RepositoryContratante initContratante() {
        RepositoryContratante c = new RepositoryContratante();
        c.CONTRATANTE_DAO.add(new Contratante("Shulambs", "12345", "contratante@gmail.com"));
        c.CONTRATANTE_DAO.add(new Contratante("Tadeu", "12345", "tadeu@gmail.com"));
        return c;
    }

    public static void main(String[] args) {
        artistaDao = initArtista();
        contratanteDao = initContratante();
        artistaDao.ARTISTA_DAO.getAll().stream().forEach(a -> System.out.println(a));
    }
}
