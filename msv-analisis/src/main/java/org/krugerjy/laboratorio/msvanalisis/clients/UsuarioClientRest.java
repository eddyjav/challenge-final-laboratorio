package org.krugerjy.laboratorio.msvanalisis.clients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(name = "msv-usuarios", url = "${msv.usuarios.url}")
public interface UsuarioClientRest {


    @DeleteMapping("/delete-usuario-analisis/{id}")
    void deleteUsuarioAnalisisById(@PathVariable Long id);

}
