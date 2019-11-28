package br.com.service;

import br.com.business.Evento;
import br.com.repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------");
        List e = Repository.getINSTANCE().daoArtistas.get("test123@email.com").getEventos();
        System.out.println(e);
        List l = Repository.getINSTANCE().daoEventos.getAll().stream().filter(o -> e.get(0).equals(o.getId())).collect(Collectors.toList());
        System.out.println(l);
        System.out.println("--------------------------------------------");
    }
}
