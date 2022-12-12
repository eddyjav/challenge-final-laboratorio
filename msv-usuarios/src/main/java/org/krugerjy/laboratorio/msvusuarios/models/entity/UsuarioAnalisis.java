package org.krugerjy.laboratorio.msvusuarios.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuarios_analisis")
public class UsuarioAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "analisis_id", unique = true) //unique = true
    private Long analisisId;

    @Override
    public boolean equals(Object obj) {
       if(this == obj){
           return true;
       }
       if(!(obj instanceof UsuarioAnalisis)){
           return false;
       }

       UsuarioAnalisis o = (UsuarioAnalisis) obj;
       return this.analisisId != null && this.analisisId.equals(o.analisisId);
    }
}
