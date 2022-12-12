package org.krugerjy.laboratorio.msvresultados.services;

import org.krugerjy.laboratorio.msvresultados.models.entity.Resultado;
import org.krugerjy.laboratorio.msvresultados.repositories.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoServiceImpl implements ResultadoService {

    @Autowired
    private ResultadoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Resultado> listar() {
        return (List<Resultado>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Resultado> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Resultado save(Resultado resultado) {
        return repository.save(resultado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Resultado> listByIds(Iterable<Long> ids) {
        return (List<Resultado>) repository.findAllById(ids);
    }
}
