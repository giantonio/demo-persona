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

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileOutputStream;

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

    @GetMapping(value = "/ver")
    public void exportarExcel(){
        
        //Creacion
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        
        List<Persona>listar = (List<Persona>) perRepo.findAll();

        workbook=new XSSFWorkbook();
        sheet=workbook.createSheet("Alumnos");

        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(1);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        String CAMPOS_CABECERA []={"NOMBRE","APELLIDOS","EDAD","DIRECCION","SALARIO","FECHAINICIO","FECHAFIN"};

        for(int i=0;i<CAMPOS_CABECERA.length;i++){
            Cell headerCell = header.createCell(i+1);
            headerCell.setCellValue(CAMPOS_CABECERA[i]);
            headerCell.setCellStyle(headerStyle);
        }

        /*Cell headerCell = header.createCell(1);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Age");
        headerCell.setCellStyle(headerStyle);*/

        int r=2;
        
        for(int i=0;i<listar.size();i++){

            Row row = sheet.createRow(r);

            Cell cell = row.createCell(1);
            cell.setCellValue(listar.get(i).getNombre());

            cell = row.createCell(2);
            cell.setCellValue(listar.get(i).getApellidos());

            cell = row.createCell(3);
            cell.setCellValue(listar.get(i).getEdad());

            cell = row.createCell(4);
            cell.setCellValue(listar.get(i).getDireccion());

            cell = row.createCell(5);
            cell.setCellValue(listar.get(i).getSalario());

            cell = row.createCell(6);
            cell.setCellValue(listar.get(i).getFechaInicio());
            System.out.print("finicio"+listar.get(i).getFechaInicio());

            cell = row.createCell(6);
            cell.setCellValue(listar.get(i).getFechaFin());
            //cell.setCellStyle(style);
            r++;

        }

        /*CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);       

        Cell cell = row.createCell(0);
        cell.setCellValue("John Smith");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(20);
        cell.setCellStyle(style);*/

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String filelocation  = path.substring(0, path.length()-1)+"temp.xlsx";

        
        try{
            FileOutputStream salida= new FileOutputStream(filelocation);
            workbook.write(salida);
            workbook.close();
        }catch(Exception e){
            System.out.println("Error en el excel " + e.getCause());
        }                

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
