package ieso.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ieso.demo.domain.Producto;

@Mapper
public interface ProductoMapper {
    
        @Select("select p.id, p.nombre,p.presentacion,p.status,p.cantidad,c.nombre as categoria, p.precio from producto as p, categoria as c where p.categoria_id = c.id;")
        public List<Producto> listar();
    
        @Insert("insert into producto (id,nombre,presentacion,cantidad,categoria_id,precio) values (nextval('sec_producto'), #{nombre},#{presentacion},#{cantidad},#{categoria_id}, #{precio})")
        public void insertarProducto(@Param("nombre") String nombre,
        @Param("presentacion") String presentacion, 
        @Param("cantidad") Integer cantidad, 
        @Param("categoria_id") Integer categoria_id,
        @Param("precio") Float precio) ;

        @Delete("delete from producto where id=(#{id})")
        public Integer borrarProducto(@Param("id") Integer id);
    
        @Update("update producto set nombre=#{nombre},presentacion=#{presentacion},cantidad=#{cantidad},categoria_id=#{categoria_id},precio=#{precio} where id=#{id}")
        public Integer editarProducto( @Param("nombre") String nombre,
        @Param("presentacion") String presentacion, 
        @Param("cantidad") Integer cantidad, 
        @Param("categoria_id") Integer categoria_id, 
        @Param("precio") Float precio, 
        @Param("id") Integer id);    
    
} 