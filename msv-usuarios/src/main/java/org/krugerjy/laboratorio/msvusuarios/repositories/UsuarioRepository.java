package org.krugerjy.laboratorio.msvusuarios.repositories;

import org.krugerjy.laboratorio.msvusuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    //query es solo apra consultas para ejecutar insert, updates se usa la notación @Modifying
    @Modifying
    @Query("delete from UsuarioAnalisis ua where ua.analisisId=?1")
    void deleteUsuarioAnalisisById(Long id);

}
