package org.krugerjy.laboratorio.msvanalisis.repositories;

import org.krugerjy.laboratorio.msvanalisis.models.entity.Analisis;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AnalisisRepository extends CrudRepository<Analisis, Long> {


}
