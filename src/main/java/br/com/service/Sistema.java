package br.com.service;

import br.com.business.Evento;
import br.com.repository.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class Sistema {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------");
        System.out.println(Repository.getINSTANCE().daoArtistas.get("a@a").removeEvento(Repository.getINSTANCE().daoEventos.get(1)));
        System.out.println("--------------------------------------------");
    }
}
