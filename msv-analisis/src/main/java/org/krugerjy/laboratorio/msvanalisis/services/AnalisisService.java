package org.krugerjy.laboratorio.msvanalisis.services;


import org.krugerjy.laboratorio.msvanalisis.models.entity.Analisis;

import java.util.List;
import java.util.Optional;

public interface AnalisisService {

    List<Analisis> findAll();
    Optional<Analisis> findById(Long id);
    Analisis save(Analisis analisis);
    void delete(Long id);

    List<Analisis> listByIds(Iterable<Long> ids);



}
