package br.com.service;

import br.com.business.Artista;
import br.com.business.Contratante;
import br.com.business.Endereco;
import br.com.business.Evento;
import br.com.repository.Repository;

import java.time.LocalDateTime;

public class Sistema {

  private static Repository repository = new Repository();

  public static Repository getRepository() {
    return repository;
  }

  public static Repository init() {
    Repository a = new Repository();
    a.daoUsuarios.add(new Artista("Matheus", "Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
    a.daoUsuarios.add(
        new Artista("Guilherme", "GOA", "12345", "goa@email.com", "Pagodeiro", "Solo"));
    Artista x = new Artista("Arthur", "Tuba", "12345", "tuba@email.com", "Emocore", "Solo");
    a.daoUsuarios.add(x);
    a.daoUsuarios.remove(x);
    a.daoUsuarios.add(new Artista("Daniel", "Thetheu", "12345", "theu@email.com", "DJ", "Solo"));
    a.daoUsuarios.add(new Contratante("Shulambs", "12345", "contratante@gmail.com"));
    a.daoUsuarios.add(new Contratante("Tadeu", "12345", "tadeu@gmail.com"));
    return a;
  }

  public static void main(String[] args) {
    initEventos();
    repository.daoUsuarios.getAll().stream().forEach(a -> System.out.println(a));
  }

  private static void initEventos() {
    repository.daoEventos.add(
        new Evento(
            "Festinha da Helen",
            2,
            300,
            "KPOP",
            LocalDateTime.of(2019, 11, 15, 20, 30),
            new Endereco("32020200", "Rua X", "300", "Camargos", "Belo Horizonte", "MG", "casa"),
            2));
  }
}
