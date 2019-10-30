package Ashow.interfac;

public interface UtilitarioDoDao<K> {
    public K getID();
    default public boolean isID(K k) {
        return k.equals(getID());
    }
    public String getEmail();

}
