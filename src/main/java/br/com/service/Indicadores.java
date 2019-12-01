package br.com.service;

import br.com.business.Evento;
import br.com.repository.Repository;

import java.util.stream.Stream;

public class Indicadores {
    private double perctInsatisfacao;
    private double perctDesistencia;
    private double mediaPropostasMensal;
    private double mediaEventosCriadosMensal;
    private double mediaArtistaContratadosMensal;
    private double propocaoArtistasContratantes;
    private double mediaDeValorPorContrato;
    private Repository repository = Repository.getINSTANCE();

    private Indicadores() {
        propocaoArtistasContratantes = ((double) repository.daoArtistas.getAll().size()) / ((double) repository.daoContratantes.getAll().size());


        final int[] qntArtistas = {0};
        if (repository.daoEventos.getAll().size()>0) {
            repository.daoEventos.getAll().stream().filter(o -> (o.getEmailArtistasConfirmados().size() > 0) && !o.isOpen()).forEach(o -> qntArtistas[0] += o.getEmailArtistasConfirmados().size());
            mediaDeValorPorContrato = repository.daoEventos.getAll().stream().filter(o -> (o.getEmailArtistasConfirmados().size() > 0) && !o.isOpen()).mapToDouble(Evento::getValor).sum()/ ((double) qntArtistas[0]);
        } else mediaDeValorPorContrato = 0;

//        mediaEventosCriadosMensal = repository.daoEventos.getAll().stream().map(Evento::getDataCriacao).sorted().
    }

    public static Indicadores getInstance() {
        return new Indicadores();
    }

    public double getPropocaoArtistasContratantes() {
        return propocaoArtistasContratantes;
    }

    public static void main(String[] args) {
        System.out.println("Indicadores.getInstance().propocaoArtistasContratantes = " + Indicadores.getInstance().propocaoArtistasContratantes);
        System.out.println("Indicadores.getInstance().mediaDeValorPorContrato = " + Indicadores.getInstance().mediaDeValorPorContrato);
    }
}
