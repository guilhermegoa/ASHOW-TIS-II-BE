package Ashow.interfac;

import Ashow.business.Artista;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IService<T> {
    public List<T> getAll();
    public Artista get(String k);
    public T add(T t);
    public String update(String t);
    public Response remove(String k);
}
