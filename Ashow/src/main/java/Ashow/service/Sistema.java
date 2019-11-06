package Ashow.service;

import Ashow.business.Artista;
import Ashow.business.Contratante;
import Ashow.repository.Repository;

public class Sistema {

    private static Repository repository = new Repository();

    public static Repository getRepository() {
        return repository;
    }

    public static Repository init() {
        Repository a = new Repository();
        a.dao_usuarios.add(new Artista("Matheus", "Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
        a.dao_usuarios.add(new Artista("Guilherme", "GOA", "12345", "goa@email.com", "Pagodeiro", "Solo"));
        Artista x = new Artista("Arthur", "Tuba", "12345", "tuba@email.com", "Emocore", "Solo");
        a.dao_usuarios.add(x);
        a.dao_usuarios.remove(x);
        a.dao_usuarios.add(new Artista("Daniel", "Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
        a.dao_usuarios.add(new Contratante("Shulambs", "12345", "contratante@gmail.com"));
        a.dao_usuarios.add(new Contratante("Tadeu", "12345", "tadeu@gmail.com"));
        return a;
    }

    public static void main(String[] args) {
        repository = init();
        repository.dao_usuarios.getAll().stream().forEach(a -> System.out.println(a));
    }
}
