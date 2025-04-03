package com.isft194.gestin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface IController<E extends Object, ID extends Serializable>
{
    ResponseEntity<?> getAll();
    ResponseEntity<?> getOne(@PathVariable ID id);
    ResponseEntity<?> create(@RequestBody E entity);
    ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity);
    ResponseEntity<?> delete(@PathVariable ID id);
}
