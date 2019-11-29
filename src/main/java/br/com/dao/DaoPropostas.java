package br.com.dao;

import br.com.business.Contratante;
import br.com.business.Evento;
import br.com.business.Proposta;
import br.com.repository.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DaoPropostas implements Serializable {
    List<Proposta> dados;
    File file;
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;

    public DaoPropostas(String filename) throws IOException {
        file = new File(filename);
        dados = readFromFile();
        if (dados.stream().mapToInt(Proposta::getId).count() > 0) {
            Proposta.setMaxId(dados.stream().mapToInt(Proposta::getId).max().getAsInt());
        } else Proposta.setMaxId(0);
    }

    public File getFile() {
        return file;
    }

    private List<Proposta> readFromFile() {
        dados = new ArrayList<Proposta>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            dados = (List<Proposta>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("opc 1");
            saveInFile();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("opc 2");
            e.printStackTrace();
        }
        return dados;
    }

    public boolean saveInFile() {
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(dados);
            objectOutputStream.flush();
            close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void close() throws IOException {
        if (objectOutputStream != null) {
            objectOutputStream.close();
            fileOutputStream.close();
            objectOutputStream = null;
            fileOutputStream = null;
        }
    }

    public Proposta get(int k) {
        for (Proposta t : dados)
            if (t.getId() == (k)) {
                return t;
            }
        return null;
    }

    public boolean update(int id, Proposta proposta) {
        List<Proposta> x = dados.stream().filter(o -> o.getId() == (id)).collect(Collectors.toList());
        if (!x.isEmpty()) {
            Proposta proposta2 = x.get(0);
            System.out.println("ANTES:" + proposta2);
            if (proposta.getValor() != proposta2.getValor())
                proposta2.setValor(proposta.getValor());
            if (proposta.getEmailContratante() != proposta2.getEmailContratante())
                proposta2.setEmailContratante(proposta.getEmailContratante());
            if (proposta.getEmailArtista() != proposta2.getEmailArtista())
                proposta2.setEmailArtista(proposta.getEmailArtista());
            if (proposta.getIdEvento() != proposta2.getIdEvento())
                proposta2.setIdEvento(proposta.getIdEvento());
            System.out.println("DEPOIS:" + proposta2);
            return saveInFile();
        } else return false;
    }

    public boolean add(Proposta proposta) {
        if (getAll().stream().anyMatch(a -> a.equals(proposta))) return false;
        else return dados.add(proposta) && saveInFile();
    }

    public boolean remove(Proposta proposta) {
        ListIterator<Proposta> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == (proposta.getId())) {
                iterator.remove();
                return saveInFile();
            }
        }
        return false;
    }

    public List<Proposta> getAll() {
        return readFromFile();
    }

    @Override
    protected void finalize() throws Throwable {
        saveInFile();
        super.finalize();
    }

//    public boolean attall() {
//        Repository repository = Repository.getINSTANCE();
//        repository.daoPropostas.getAll().stream().forEach(proposta -> {
//            final boolean[] achou = {false};
//            if (proposta.isArtistaAceitou() && proposta.isContratanteAceitou()) {
//                repository.daoEventos.get(proposta.getIdEvento()).getEmailArtistasPendente().forEach(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) {
//                        if (!achou[0]) {
//                            achou[0] = s.equals(proposta.getEmailArtista());
//                        }
//                    }
//                });
//                if (!achou[0])
//                    repository.daoEventos.addArtistaPendente(proposta.getIdEvento(),proposta.getEmailArtista());
//            }
//        });
//        return false;
//    }
}