package Ashow.interfac;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IService<T, K> {
    public List<T> getAll();
    public T get(K k);
    public Response insert(T t);
    public Response update(T t);
    public Response remove(K k);
}
