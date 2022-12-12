package org.krugerjy.laboratorio.msvanalisis.controllers;

import org.krugerjy.laboratorio.msvanalisis.models.entity.Analisis;
import org.krugerjy.laboratorio.msvanalisis.services.AnalisisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class AnalisisController {

    @Autowired
    private AnalisisService service;

    @GetMapping("/")
    public ResponseEntity<List<Analisis>> list(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detil(@PathVariable Long id){
        //service.findById(id);
        Optional<Analisis> o = service.findById(id);//findByIdWithUsers(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Analisis analisis, BindingResult result){
            if(result.hasErrors()){
                return validar(result);
            }
        Analisis analisisDb = service.save(analisis);
        return ResponseEntity.status(HttpStatus.CREATED).body(analisisDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Analisis analisis, BindingResult result , @PathVariable Long id){
            if(result.hasErrors()){
                return validar(result);
            }
        Optional<Analisis> o = service.findById(id);
        if(o.isPresent()){
            Analisis analisisDb = o.get();
            analisisDb.setGrasa(analisis.getGrasa());
            analisisDb.setGrasa(analisis.getGrasa());
            analisisDb.setGrasa(analisis.getGrasa());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(analisisDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Analisis> o = service.findById(id);
        if(o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/analisis-by-usuarios")
    public ResponseEntity<?> obtenerAnalisisPorUsuario(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listByIds(ids));
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+ err.getField()+" "+ err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }


}
