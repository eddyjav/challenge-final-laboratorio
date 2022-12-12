package org.krugerjy.laboratorio.msvusuarios.request;

import org.krugerjy.laboratorio.msvusuarios.models.Analisis;
import org.krugerjy.laboratorio.msvusuarios.models.Resultado;
import org.krugerjy.laboratorio.msvusuarios.models.entity.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class  CreateFichaAnalisis {

    private Riesgo riesgo;

    private String fecha;

    private Double azucar;
    private Double grasa;
    private Double oxigeno;

    public Resultado Agregarficha(Analisis analisis, Usuario usuario){

        this.azucar = analisis.getAzucar();
        this.grasa = analisis.getGrasa();
        this.oxigeno = analisis.getOxigeno();

        if(azucar > 70 && grasa > 88.5 && oxigeno < 60){
            this.riesgo = Riesgo.ALTO;
        }else if((azucar >= 50 && azucar <= 70) && (grasa >= 62.2 && grasa <=88.5) && (oxigeno >= 60 && oxigeno <=70) ){
            this.riesgo = Riesgo.MEDIO;
        }else if(azucar < 50 && grasa < 62.2 && oxigeno > 70){
            this.riesgo = Riesgo.BAJO;
        } else {
            this.riesgo= Riesgo.INDEFINIDO;
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        System.out.println(LocalDateTime.now().format(df));

        this.fecha = LocalDateTime.now().format(df);

        Resultado res = new Resultado();
        res.setFecha(this.fecha);
        res.setRiesgo(String.valueOf(this.riesgo));

        return res;

    }

}
