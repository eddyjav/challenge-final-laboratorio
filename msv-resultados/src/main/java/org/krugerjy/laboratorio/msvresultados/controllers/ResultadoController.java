package org.krugerjy.laboratorio.msvresultados.controllers;

import org.krugerjy.laboratorio.msvresultados.models.entity.Resultado;
import org.krugerjy.laboratorio.msvresultados.services.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
public class ResultadoController {

    @Autowired
    private ResultadoService service;


    @GetMapping
    public List<Resultado> list(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        System.out.println(LocalDateTime.now().format(df));
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<Resultado> resultadoOptional = service.porId(id);
        if(resultadoOptional.isPresent()){
            return  ResponseEntity.ok(resultadoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Resultado resultado){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(resultado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody Resultado resultado, @PathVariable Long id){
        Optional<Resultado> o = service.porId(id);
        if(o.isPresent()){
            Resultado resultadoDb = o.get();
            resultadoDb.setRiesgo(resultado.getRiesgo());
            resultadoDb.setFecha(resultado.getFecha());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(resultadoDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Resultado> o = service.porId(id);
        if(o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/resultados-by-usuarios")
    public ResponseEntity<?> obtenerResultadosPorUsuarios(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listByIds(ids));
    }

}
