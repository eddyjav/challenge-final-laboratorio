package org.krugerjy.laboratorio.msvusuarios.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuarios_resultados")
public class UsuarioResultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resultado_id", unique = true)
    private Long resultadoId;

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof UsuarioResultado)){
            return false;
        }

        UsuarioResultado o=(UsuarioResultado) obj;
        return this.resultadoId != null && this.resultadoId.equals(o.resultadoId);

    }
}
