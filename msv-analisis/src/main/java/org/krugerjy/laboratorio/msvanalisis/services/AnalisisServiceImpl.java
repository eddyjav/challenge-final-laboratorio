package org.krugerjy.laboratorio.msvanalisis.services;

import org.krugerjy.laboratorio.msvanalisis.clients.UsuarioClientRest;
import org.krugerjy.laboratorio.msvanalisis.models.entity.Analisis;
import org.krugerjy.laboratorio.msvanalisis.repositories.AnalisisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalisisServiceImpl implements AnalisisService{

    @Autowired
    private AnalisisRepository repository;

    @Autowired
    private UsuarioClientRest client;


    @Override
    @Transactional(readOnly = true)
    public List<Analisis> findAll() {
        return (List<Analisis>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Analisis> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Analisis save(Analisis analisis) {
        return repository.save(analisis);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        client.deleteUsuarioAnalisisById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Analisis> listByIds(Iterable<Long> ids) {
        return (List<Analisis>) repository.findAllById(ids);
    }

    //FEIGN
    //Antes de implemetar estos metodos se debe configurar el cliente http con FEIGN



}
