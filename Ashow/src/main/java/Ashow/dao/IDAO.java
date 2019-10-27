package Ashow.dao;

import java.util.List;

public interface IDAO<T, K> {

    public T get(K chave);

    public void add(T p);

    public boolean update(T p);

    public boolean remove(T p);

    public List<T> getAll();
}