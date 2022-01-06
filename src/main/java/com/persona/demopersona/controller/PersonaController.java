package com.persona.demopersona.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.persona.demopersona.model.Persona;
import com.persona.demopersona.repository.PersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class PersonaController {    

    @Autowired
    public PersonaRepository perRepo;

    @GetMapping(value = "/listar")
    public ResponseEntity<List<Persona>>findAll(){
        List<Persona>personas = (List<Persona>) perRepo.findAll();
        return new ResponseEntity<List<Persona>>(personas,HttpStatus.OK);
    }

    /**
     * Update person
     * @param p
     * @return
     */
    @PutMapping(value = "/actualizarPersona")
    public ResponseEntity<Persona> actualizarPersona(@RequestBody Persona p){
        Persona aux = this.perRepo.save(p);
        return new ResponseEntity<Persona>(aux,HttpStatus.OK);
    }

    /**
     * Insert new person
     * @param p
     * @return
     */
    @PostMapping(value = "/insertarPersona")
    public ResponseEntity<Persona>crearPersona(@RequestBody Persona p){
        Persona aux = this.perRepo.save(p);
        return new ResponseEntity<Persona>(aux,HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePersona/{id}")
    public ResponseEntity<Persona>eliminarPersona(@PathParam("id")int id){
        
        Persona existePersona = this.perRepo.findById(id);

        if(existePersona!=null){
            this.perRepo.delete(existePersona);
        }

        return new ResponseEntity<Persona>(existePersona, HttpStatus.OK);
    }
    
}
