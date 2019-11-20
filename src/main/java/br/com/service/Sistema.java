package br.com.service;

import br.com.business.Contratante;
import br.com.business.Endereco;
import br.com.business.Evento;
import br.com.repository.Repository;

import java.time.LocalDateTime;

public class Sistema {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------");
        System.out.println(Repository.getINSTANCE().daoContratantes.get("ruhtraamaral@hotmail.com"));
        System.out.println("--------------------------------------------");
    }
}
