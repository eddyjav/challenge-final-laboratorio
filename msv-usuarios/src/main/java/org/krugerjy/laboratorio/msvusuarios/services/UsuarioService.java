package org.krugerjy.laboratorio.msvusuarios.services;

import org.krugerjy.laboratorio.msvusuarios.models.Analisis;
import org.krugerjy.laboratorio.msvusuarios.models.Resultado;
import org.krugerjy.laboratorio.msvusuarios.models.entity.Usuario;



import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    void delete(Long id);

    Optional<Usuario> byIdWithAnalisis(Long id);
    Optional<Usuario> byIdWithResultados(Long id);

    void deleteUsuarioAnalisisById(Long id);

    Optional<Usuario> findByEmail(String email);


   //METODOS remotodos RELACIONA al CLIENTE HTTP
    Optional<Analisis> asignarAnalisis(Analisis analisis, Long analisisId);
    Optional<Analisis> crearAnalisis(Analisis analisis, Long analisisId);
    Optional<Analisis> eliminarAnalisis(Analisis analisis, Long analisisId);

    Optional<Resultado> asignarResultado(Resultado resultado, Long resultadoId);

}
