package org.krugerjy.laboratorio.msvusuarios.controllers;

import feign.FeignException;
import org.krugerjy.laboratorio.msvusuarios.models.Analisis;
import org.krugerjy.laboratorio.msvusuarios.models.Resultado;
import org.krugerjy.laboratorio.msvusuarios.models.entity.Usuario;
import org.krugerjy.laboratorio.msvusuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public List<Usuario> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.byIdWithAnalisis(id);//service.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result){

        if(result.hasErrors()){
            return validar(result);
        }

        if(!usuario.getEmail().isEmpty() && service.findByEmail(usuario.getEmail()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "El email ingresado ya esta registrado con otro usuario"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){

            if(result.hasErrors()){
                    return validar(result);
                }

        Optional<Usuario> o = service.findById(id);
        if(o.isPresent()){
            Usuario usuarioDb = o.get();
            if(!usuario.getEmail().isEmpty() && !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) && service.findByEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("mensaje", "El email ingresado ya esta registrado con otro usuario"));
            }
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuario> o = service.findById(id);
        if(o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/assign-analisis/{usuarioId}")
    public ResponseEntity<?> assignAnalisis(@RequestBody Analisis analisis, @PathVariable Long usuarioId){
        Optional<Analisis> o;
        try{
            o = service.asignarAnalisis(analisis, usuarioId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No existe el analisis por el id o no se logro la comunicación"+ e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/assign-resultado/{usuarioId}")
    public ResponseEntity<?> assignResultado(@RequestBody Resultado resultado, @PathVariable Long usuarioId){
        Optional<Resultado> o;
        try{
            o = service.asignarResultado(resultado, usuarioId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No existe el analisis por el id o no se logro la comunicación"+ e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create-analisis/{usuarioId}")
    public ResponseEntity<?> createAnalisis(@RequestBody Analisis analisis, @PathVariable Long usuarioId){
        Optional<Analisis> o;
        try{
            o = service.crearAnalisis(analisis, usuarioId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No se puedo crear el analisis o no se logro la comunicación"+ e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-analisis/{usuarioId}")
    public ResponseEntity<?> deleteAnalisis(@RequestBody Analisis analisis, @PathVariable Long usuarioId){
        Optional<Analisis> o;
        try{
            o = service.eliminarAnalisis(analisis, usuarioId);
        } catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("mensaje","No existe el analisis por el id o no se logro la comunicación"+ e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-usuario-analisis/{id}")
    public ResponseEntity<?> deleteUsuarioAnalisisById(@PathVariable Long id){
        service.deleteUsuarioAnalisisById(id);
        return ResponseEntity.noContent().build();
    }


    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+ err.getField()+" "+ err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
