package com.persona.demopersona.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; 

    private String nombre;
    private String apellidos;
    private int edad;
    private String direccion;
    private double salario;            
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    
}
