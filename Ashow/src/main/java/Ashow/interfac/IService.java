package Ashow.interfac;

import Ashow.business.Artista;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IService<T> {
    public List<T> getAll();
    public T get(String k);
    public boolean add(T t);
    public boolean update(T t);
    public boolean remove(String k);
}
