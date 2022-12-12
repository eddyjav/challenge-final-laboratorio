package org.krugerjy.laboratorio.msvresultados.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "resultados")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String riesgo;

//    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
//    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-5")
    private String fecha;

}
