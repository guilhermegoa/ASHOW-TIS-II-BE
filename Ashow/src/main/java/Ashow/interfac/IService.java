package Ashow.interfac;

import Ashow.business.Artista;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IService {
    public String getAll();
    public String get(String k);
    public String add(String t);
    public String update(String t);
    public Response remove(String k);
}
