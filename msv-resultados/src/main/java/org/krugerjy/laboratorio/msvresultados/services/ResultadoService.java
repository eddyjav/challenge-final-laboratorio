package org.krugerjy.laboratorio.msvresultados.services;

import org.krugerjy.laboratorio.msvresultados.models.entity.Resultado;

import java.util.List;
import java.util.Optional;

public interface ResultadoService {
    List<Resultado> listar();
    Optional<Resultado> porId(Long id);
    Resultado save(Resultado resultado);
    void delete(Long id);

    List<Resultado> listByIds(Iterable<Long> ids);

}
