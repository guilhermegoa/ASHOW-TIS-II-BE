package Ashow.interfac;

import java.util.List;

public interface IDao<T, K> {

    public T get(K k);

    public void add(T t);

    public boolean update(T t);

    public boolean remove(K k);

    public List<T> getAll();
}
