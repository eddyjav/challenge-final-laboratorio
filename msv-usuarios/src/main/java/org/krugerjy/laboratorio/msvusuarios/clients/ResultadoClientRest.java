package org.krugerjy.laboratorio.msvusuarios.clients;

import org.krugerjy.laboratorio.msvusuarios.models.Resultado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msv-resultados", url = "${msv.resultados.url}")
public interface ResultadoClientRest {

    @GetMapping("/{id}")
    Resultado detail(@PathVariable Long id);

    @PostMapping("/")
    Resultado create(@RequestBody Resultado resultado);

    @GetMapping("/resultados-by-usuarios")
    List<Resultado> obtenerResultadosPorUsuarios(@RequestParam Iterable<Long> ids);


}
