package ieso.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;


import ieso.demo.domain.Categoria;

@Mapper
public interface CategoriaMapper {
    @Select("select id,nombre,status from categoria")
    public List<Categoria> listar();

    @Insert("insert into categoria (id,nombre) values (nextval('sec_categoria'), #{nombre})")
    public void insertar(@Param("nombre") String nombre);

    @Delete("delete from categoria where id=(#{id})")
    public Integer borrarCategoria(@Param("id") Integer id);

    @Update("update categoria set nombre=#{nombre} where id=#{id}")
    public Integer editarCategoria(@Param("id") Integer id, @Param("nombre") String nombre);

}