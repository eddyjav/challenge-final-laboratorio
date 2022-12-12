package org.krugerjy.laboratorio.msvanalisis.models.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
@Table(name = "analisis")
public class Analisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double azucar;

    @NotNull
    private Double grasa;

    @NotNull
    private Double oxigeno;

    //@Transient, nos indica que el atributo no esta mapeado a la persistencia


}
