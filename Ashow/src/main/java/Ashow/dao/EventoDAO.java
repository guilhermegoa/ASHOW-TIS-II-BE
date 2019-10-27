package Ashow.dao;

import Ashow.interfac.IDao;
import Ashow.business.Evento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO implements IDao<Evento, String> {

    private File file;
    private FileOutputStream fos;
    private ObjectOutputStream outputFile;

    public EventoDAO(String filename) throws IOException {
        file = new File(filename);
//        if (file.exists())
//            file.delete();
        fos = new FileOutputStream(file, false);
        outputFile = new ObjectOutputStream(fos);
    }

    public void add(Evento evento) {
        try {
            outputFile.writeObject(evento);
        } catch (Exception e) {
            System.out.println("ERRO ao gravar o evento '" + evento.getEstilo() + "' no disco!");
            e.printStackTrace();
        }
    }

    public Evento get(String ID) {
        Evento evento = null;

        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                evento = (Evento) inputFile.readObject();

                if (ID.equals(evento.getNome())) {
                    return evento;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o evento '" + ID + "' do disco!");
            e.printStackTrace();
        }
        return null;
    }

    public List<Evento> getAll() {
        List<Evento> eventos = new ArrayList<Evento>();
        Evento evento = null;
        try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                evento = (Evento) inputFile.readObject();
                eventos.add(evento);
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar evento no disco!");
            e.printStackTrace();
        }
        return eventos;
    }

    public boolean update(Evento p) {
        int valido = 0;
        List<Evento> eventos = getAll();
        int index = eventos.indexOf(p);
        if (index != -1) {
            valido = 1;
            eventos.set(index, p);
        }
        saveToFile(eventos);
        return valido == 1 ? true : false;
    }

    @Override
    public boolean remove(Evento p) {
        int valido = 0;
        List<Evento> eventos = getAll();
        int index = eventos.indexOf(p);
        if (index != -1) {
            valido = 1;
            eventos.remove(index);
        }
        saveToFile(eventos);
        return valido == 1 ? true : false;
    }

    private void saveToFile(List<Evento> eventos) {
        try {
            close();
            fos = new FileOutputStream(file, false);
            outputFile = new ObjectOutputStream(fos);

            for (Evento evento : eventos) {
                outputFile.writeObject(evento);
            }
            outputFile.flush();
        } catch (Exception e) {
            System.out.println("ERRO ao gravar evento no disco!");
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        outputFile.close();
        fos.close();
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
    }

}