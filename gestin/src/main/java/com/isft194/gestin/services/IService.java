package com.isft194.gestin.services;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface IService <E extends Object, ID extends Serializable>{

    E save (E entity) throws Exception;
    E findById (ID id) throws Exception;
    List<E> findAll() throws Exception;
    E update (ID id, E entity) throws Exception;
    boolean delete(ID id) throws Exception;
}
