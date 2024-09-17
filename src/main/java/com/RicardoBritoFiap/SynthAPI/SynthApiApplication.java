package com.RicardoBritoFiap.SynthAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
@Controller
public class SynthApiApplication {

	@ResponseBody
	@GetMapping
	public String teste(){
		return "Sucesso";
	}
	public static void main(String[] args) {
		SpringApplication.run(SynthApiApplication.class, args);
	}
}
