package ieso.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ieso.demo.domain.Categoria;

import ieso.demo.mapper.CategoriaMapper;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired 
    private CategoriaMapper categoriaMapper;
    
    @GetMapping("/todos")
    public List<Categoria> listarTodos(){
            return categoriaMapper.listar();
    }
    
    @PostMapping("/nuevo")
    public List<Categoria> insertarCategoria(@RequestParam(required = true)  String nombre){
        categoriaMapper.insertar(nombre);
        return listarTodos();
    }

    @GetMapping("/borrar")
    public List<Categoria> borrarCategoria(@RequestParam(required = true) Integer id) {
        categoriaMapper.borrarCategoria(id);
        return listarTodos();
    }

    @GetMapping("/editar")
    public List<Categoria> editarCategoria(@RequestParam(required = false) Integer id,
    @RequestParam(required = true) String nombre){
    categoriaMapper.editarCategoria(id,nombre);
        return listarTodos();
    }
}