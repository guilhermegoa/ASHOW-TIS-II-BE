package Ashow.interfac;

import Ashow.dao.Dao;

public interface IRepository<T extends UtilitarioDoDao<K>, K> {

    public Dao<T, K> inicializarDao();

//    public List<T> getList();
//
//    public T get(K k);
//
//    public Response add(T t);
//
//    public Response update(T t);
//
//    public Response remove(K k);

}
