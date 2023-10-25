package com.features.generics;


public class GeneralDAO<T extends SchoolMember> {

    void save(T entity){

    }

    T get(Long id) {
        return (T) new Object();
    }
}
