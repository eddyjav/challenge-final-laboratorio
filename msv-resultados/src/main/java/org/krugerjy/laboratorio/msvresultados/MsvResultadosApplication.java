package org.krugerjy.laboratorio.msvresultados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvResultadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvResultadosApplication.class, args);
	}

}
