package ieso.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ieso.demo.domain.Ventas;
import ieso.demo.mapper.VentasMapper;

@RestController
@RequestMapping("/ventas")
public class VentasController {
    
    @Autowired
    private VentasMapper ventasMapper;

    @GetMapping("/lista1")
    public List<Ventas> listarfecha(
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @RequestParam(required = true)Date fecha) {
        
        return  ventasMapper.lista1(fecha);      
    }

    @GetMapping("/lista2")
    public List<Ventas> listardosfechas(
        @DateTimeFormat(pattern = "yyyy-MM-dd")    
    @RequestParam(required = true)Date fecha,
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    @RequestParam(required = true)Date fechal){
            return ventasMapper.lista2(fecha,fechal);
    }

    @PostMapping("/nuevo")
    public void insertarUsuarios(@RequestParam(required = true)  String productos,
     @RequestParam(required = true)  Integer cantidadtotal, 
     @RequestParam(required = true)  Integer descuento,
     @RequestParam(required = true)  Double precio,
     @RequestParam(required = true)  Date fecha){
     ventasMapper.insertar(productos,cantidadtotal,descuento,precio,fecha);
    }
}
