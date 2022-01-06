package com.persona.demopersona.repository;

import java.time.LocalDate;
import java.util.List;

import com.persona.demopersona.model.Persona;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends PagingAndSortingRepository<Persona,Integer>{
    
    Persona findById(int id);

    List<Persona> findAllByFechaInicioGreaterThanEqualAndFechaFinLessThanEqual(
        @DateTimeFormat(iso = ISO.DATE) @Param("fechaInicio")LocalDate fechaInicio,
        @DateTimeFormat(iso = ISO.DATE) @Param("fechaFin")LocalDate fechaFin);
    
}
