package Ashow.interfac;

import Ashow.business.Artista;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IService<T> {
    public List<Artista> getAll();
    public T get(String k);
    public boolean add(T t);
    public Response update(T t);
    public Response remove(String k);
}
