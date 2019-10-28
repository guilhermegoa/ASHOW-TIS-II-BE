package Ashow.interfac;

import Ashow.business.Artista;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IService {
    public List<Artista> getAll();
    public String get(String k);
    public String add(String t);
    public String update(String t);
    public Response remove(String k);
}
