package ieso.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ieso.demo.domain.Usuario;
import ieso.demo.mapper.UsuarioMapper;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioMapper usuarioMapper;
    

    @GetMapping("/todos")
    public List<Usuario> listarTodos(){
            return usuarioMapper.listar();
    }

    @GetMapping("/ingresar")
    public  boolean ingresar(@RequestParam(required = true) String correo,
    @RequestParam(required = true) String pass) {
        return  usuarioMapper.ingresar(correo, pass);       
    }
    
    @GetMapping("/nuevo")
    public List<Usuario> insertarUsuarios(@RequestParam(required = true)  String correo,
     @RequestParam(required = true)  String nombres, 
     @RequestParam(required = true)  String apellidos,
     @RequestParam(required = true)  String pass){
    usuarioMapper.insertar(correo, nombres, apellidos,pass);
    return listarTodos();
    }

    @GetMapping("/borrar")
    public List<Usuario> borrarUsuario(@RequestParam(required = true) Integer id) {
        usuarioMapper.borrarUsuario(id);
        return listarTodos();
    }

    @GetMapping("/editar")
    public List<Usuario> editarUsuario(@RequestParam(required = true)  String correo,
    @RequestParam(required = true)  String nombres, 
    @RequestParam(required = true)  String apellidos,
    @RequestParam(required = true)  String pass,
    @RequestParam(required = true) Integer id){
    usuarioMapper.editarUsuario(correo, nombres, apellidos,pass,id);
        return listarTodos();
    }
 
}