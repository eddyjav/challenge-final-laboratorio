package org.krugerjy.laboratorio.msvusuarios.clients;

import org.krugerjy.laboratorio.msvusuarios.models.Analisis;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msv-analisis", url = "${msv.analisis.url}")
public interface AnalisisClientRest {

    @GetMapping("/{id}")
    Analisis detail(@PathVariable Long id);

    @PostMapping("/")
    Analisis create(@RequestBody Analisis analisis);

    @GetMapping("/analisis-by-usuarios")
    List<Analisis> obtenerAnalisisPorUsuario(@RequestParam Iterable<Long> ids);



}
