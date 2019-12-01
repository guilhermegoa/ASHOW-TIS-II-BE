package br.com.dao;

import br.com.business.Evento;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class DaoEventos implements Serializable {
    List<Evento> dados;
    File file;
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;

    public DaoEventos(String filename) throws IOException {
        file = new File(filename);
        dados = readFromFile();
        if (dados.stream().mapToInt(Evento::getId).count() > 0) {
            Evento.setMaxIDEventos(dados.stream().mapToInt(Evento::getId).max().getAsInt());
        } else Evento.setMaxIDEventos(0);
    }

    public File getFile() {
        return file;
    }

    private List<Evento> readFromFile() {
        dados = new ArrayList<Evento>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            dados = (List<Evento>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            saveInFile();
        } catch (IOException | ClassNotFoundException e) {
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

    public Evento get(int k) {
        for (Evento t : dados)
            if (t.getId() == (k)) {
                return t;
            }
        return null;
    }

    public boolean update(int id, Evento evento) {
        List<Evento> x = dados.stream().filter(o -> o.getId() == (id)).collect(Collectors.toList());
        if (!x.isEmpty()) {
            Evento contratante = x.get(0);
            System.out.println("ANTES:" + contratante);
            if (evento.getCapacidadeEsperada() != contratante.getCapacidadeEsperada())
                contratante.setCapacidadeEsperada(evento.getCapacidadeEsperada());
            if (evento.getQuantidadeArtistas() != contratante.getQuantidadeArtistas())
                contratante.setQuantidadeArtistas(evento.getQuantidadeArtistas());
            if (evento.getValor() != contratante.getValor()) contratante.setValor(evento.getValor());
            if (evento.getEstilo() != null) contratante.setEstilo(evento.getEstilo());
            if (evento.getNome() != null) contratante.setNome(evento.getNome());
            if (evento.isOpen() != contratante.isOpen()) contratante.setOpen(evento.isOpen());
            if (evento.getData() != null) contratante.setData(evento.getData());
            if (evento.getEndereco() != null) contratante.setEndereco(evento.getEndereco());
            System.out.println("DEPOIS:" + contratante);
            return saveInFile();
        } else return false;
    }

    public boolean add(Evento evento) {
        boolean jaECadastrado = getAll().stream().anyMatch(a -> a.getId() == (evento.getId()));
        if (jaECadastrado) return false;
        else return dados.add(evento) && saveInFile();
    }

    public boolean remove(Evento t) {
        ListIterator<Evento> iterator = dados.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == (t.getId())) {
                iterator.remove();
                return saveInFile();
            }
        }
        return false;
    }

    public List<Evento> getAll() {
        return readFromFile();
    }

    public boolean addArtistaPendente(int id, String emailArtista) {
        return get(id).addArtistaPendente(emailArtista) && saveInFile();
    }

    public boolean removerArtistaPendente(int id, String emailArtista) {
        return get(id).removerArtistaPendente(emailArtista) && saveInFile();
    }

    @Override
    protected void finalize() throws Throwable {
        saveInFile();
        super.finalize();
    }

    public boolean removerArtistaConfirmado(int id, String email) {
        return get(id).removerArtistaConfirmado(email) && saveInFile();
    }

    public boolean confirmarArtistaPendente(int id, String emailArtista) {
        return get(id).confirmarArtistaPendente(emailArtista) && saveInFile();
    }

    public boolean fecharEvento(int id) {
        get(id).setOpen(false);
        return saveInFile();
    }

    public boolean abrirEvento(int id) {
        get(id).setOpen(true    );
        return saveInFile();
    }
}
