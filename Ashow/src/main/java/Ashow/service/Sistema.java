package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Contratante;
import Ashow.repository.RepositoryArtista;
import Ashow.repository.RepositoryContratante;

public class Sistema {

    public static RepositoryArtista artistaDao = new RepositoryArtista();
    public static RepositoryContratante contratanteDao = new RepositoryContratante();

    public static RepositoryArtista initArtista() {
        RepositoryArtista a = new RepositoryArtista();
        a.ARTISTA_DAO.add(new Artista("Matheus", "Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
        a.ARTISTA_DAO.add(new Artista("Guilherme", "GOA", "12345", "goa@email.com", "Pagodeiro", "Solo"));
        a.ARTISTA_DAO.add(new Artista("Arthur", "Tuba", "12345", "tuba@email.com", "Emocore", "Solo"));
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
    }
}
