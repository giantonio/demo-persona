package com.persona.demopersona;

import java.time.LocalDate;

import com.persona.demopersona.model.Persona;
import com.persona.demopersona.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoPersonaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoPersonaApplication.class, args);
	}

	@Autowired
	private PersonaRepository persoRepo;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Persona p1 = new Persona(0, "Persona1", "Apellidos1", 22, "Calle 1, Nº 1", 16.000, LocalDate.of(2022, 01, 01), LocalDate.of(2023, 01, 01));
		Persona p2 = new Persona(1, "Persona2", "Apellidos2", 23, "Calle 2, Nº 2", 16.500, LocalDate.of(2022, 01, 02), LocalDate.of(2024, 01, 04));
		Persona p3 = new Persona(2, "Persona3", "Apellidos3", 24, "Calle 3, Nº 3", 17.000, LocalDate.of(2022, 01, 03), LocalDate.of(2024, 01, 04));
		Persona p4 = new Persona(3, "Persona4", "Apellidos4", 22, "Calle 4, Nº 4", 17.500, LocalDate.of(2022, 01, 04), LocalDate.of(2025, 01, 07));

		this.persoRepo.save(p1);
		this.persoRepo.save(p2);
		this.persoRepo.save(p3);
		this.persoRepo.save(p4);
		
	}

}
