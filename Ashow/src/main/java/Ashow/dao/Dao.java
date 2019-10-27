//package Ashow.dao;
//
//import Ashow.interfac.IDao;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Dao<T> implements IDao<T,String> {
//
//    private File file;
//    private FileOutputStream fos;
//    private ObjectOutputStream oos;
//    private List<T> info;
//
//    public Dao(String filename) throws IOException {
//        file = new File(filename);
//        info = readFile();
//    }
//
//    @Override
//    public T get(String s) {
//        return null;
//    }
//
//    @Override
//    public void add(T t) {
//
//    }
//
//    @Override
//    public boolean update(T t) {
//        return false;
//    }
//
//    @Override
//    public boolean remove(String s) {
//        return false;
//    }
//
//    @Override
//    public List<T> getAll() {
//        return readFile();
//    }
//
//    private List<T> readFile(){
//        info = new ArrayList<T>();
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            ObjectInputStream oos = new ObjectInputStream(fis);
//
//            while (fis.available() > 0 ) {
//                T t = (T) oos.readObject();
//                info.add(t);
//            }
//            return info;
//        } catch (Exception e) {
//            System.out.println("Erro ao gravar!");
//            e.printStackTrace();
//        }
//        return info;
//
//    }
//
//    private boolean saveInFile() {
//        try {
//            close();
//            fos = new FileOutputStream(file, false);
//            oos = new ObjectOutputStream(fos);
//
//            for (T t : info) {
//                oos.writeObject(t);
//            }
//            oos.flush();
//            return true;
//        } catch (Exception e) {
//            System.out.println("Erro ao gravar no disco!");
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private void close() throws IOException {
//        if (oos != null) {
//            oos.close();
//            fos.close();
//            oos = null;
//            fos = null;
//        }
//    }
//}
