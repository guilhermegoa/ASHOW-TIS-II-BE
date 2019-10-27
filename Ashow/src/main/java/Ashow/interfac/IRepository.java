package Ashow.interfac;

import Ashow.dao.UsuarioDAO;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IRepository<T, K> {

    public Dao inicializarDao();

    public List<T> getList();

    public T find(K k);

    public Response add(T t);

    public Response update(T t);

    public Response remove(K k);

}
