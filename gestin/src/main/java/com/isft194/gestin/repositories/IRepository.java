package com.isft194.gestin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IRepository <E extends Object, ID extends Serializable> extends JpaRepository<E, ID> {

}
