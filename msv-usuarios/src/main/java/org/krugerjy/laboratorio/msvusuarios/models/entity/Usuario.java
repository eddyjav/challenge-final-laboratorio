package org.krugerjy.laboratorio.msvusuarios.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.krugerjy.laboratorio.msvusuarios.models.Analisis;
import org.krugerjy.laboratorio.msvusuarios.models.Resultado;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String password;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private List<UsuarioAnalisis> usuarioAnalisisP;
    public void addUsuarioAnalisis(UsuarioAnalisis usuarioAnalisis){
        usuarioAnalisisP.add(usuarioAnalisis);
    }

    public void removeUsuarioAnalisis(UsuarioAnalisis usuarioAnalisis){
        usuarioAnalisisP.remove(usuarioAnalisis);
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private List<UsuarioResultado> usuarioResultados;
    public void addUsuarioResultado(UsuarioResultado usuarioResultado){
        usuarioResultados.add(usuarioResultado);
    }

    public void removeUsuarioResultado(UsuarioResultado usuarioResultado){
        usuarioResultados.remove(usuarioResultado);
    }


    @Transient
    private List<Analisis> analisisP;

    @Transient
    private  List<Resultado> resultados;

    public Usuario() {
        usuarioAnalisisP = new ArrayList<>();
        analisisP = new ArrayList<>();

        usuarioResultados = new ArrayList<>();
        resultados = new ArrayList<>();

    }
}
