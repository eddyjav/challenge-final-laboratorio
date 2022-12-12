package org.krugerjy.laboratorio.msvusuarios.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Resultado {

    private Long id;

    private String riesgo;

//    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
//    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT-5")
    private String fecha;

    @Override
    public String toString() {
        return "Resultado{" +
                "id=" + id +
                ", riesgo='" + riesgo + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
