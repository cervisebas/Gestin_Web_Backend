package com.isft194.gestin.services;

import com.isft194.gestin.repositories.IRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class ServiceIMPL <E extends Object, ID extends Serializable> implements IService<E, ID>{

    protected final IRepository<E,ID> iRepository;

    protected ServiceIMPL(IRepository<E, ID> iRepository) {
        this.iRepository = iRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception{
        try{
            List<E> entities = iRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception{
        return iRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Entidad con ID" + id + "no encontrada"));
        /*try{
            Optional<E> entityOptional = iRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }*/
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception{
        try{
            entity = iRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la entidad: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception{
        try{
            Optional<E> entityOptional = iRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = iRepository.save(entity);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception{
        try{
            if(iRepository.existsById(id)){
                iRepository.deleteById(id);
                return true;
            }else {
                throw new EntityNotFoundException("Entidad con ID " + id + " no encontrada para eliminacion");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al querer eliminar la entidad: " + e.getMessage());
        }
    }
}
