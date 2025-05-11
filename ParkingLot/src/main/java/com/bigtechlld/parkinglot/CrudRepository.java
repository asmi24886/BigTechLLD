package com.bigtechlld.parkinglot;

public interface CrudRepository<T, ID> {
    public T save(T t);
    public T remove(ID id);
}
