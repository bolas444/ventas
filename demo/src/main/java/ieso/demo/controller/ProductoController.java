package ieso.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ieso.demo.domain.Producto;
import ieso.demo.mapper.ProductoMapper;

@RestController
@RequestMapping("/producto")
public class ProductoController {
        
    @Autowired 
    private ProductoMapper productoMapper;
    
    @GetMapping("/todos")
    public List<Producto> listarTodos(){
            return productoMapper.listar();
    }
    
    @PostMapping("/nuevo")
    public List<Producto> insertarProducto(@RequestParam(required = true)  String nombre,
    @RequestParam(required = true)  String presentacion,
    @RequestParam(required = true)  Integer cantidad,
    @RequestParam(required = true)  Integer categoria_id,
    @RequestParam(required = true)  Float precio){
        productoMapper.insertarProducto(nombre, presentacion, cantidad, categoria_id, precio);
        return listarTodos();
    }

    @GetMapping("/borrar")
    public List<Producto> borrarProducto(@RequestParam(required = true) Integer id) {
        productoMapper.borrarProducto(id);
        return listarTodos();
    }

    @GetMapping("/editar")
    public List<Producto> editarProducto(@RequestParam(required = true) String nombre,
    @RequestParam(required = true)  String presentacion,
    @RequestParam(required = true)  Integer cantidad,
    @RequestParam(required = true)  Integer categoria_id,
    @RequestParam(required = true)  Float precio, 
    @RequestParam(required = true) Integer id){
        productoMapper.editarProducto(nombre,presentacion, cantidad,categoria_id, precio, id);
        return listarTodos();
    }
}
