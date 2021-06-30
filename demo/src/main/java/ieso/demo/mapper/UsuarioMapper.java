package ieso.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ieso.demo.domain.Usuario;

@Mapper
public interface UsuarioMapper {
    @Select("select id,correo,nombres,apellidos,pass,status from usuario")
    public List<Usuario> listar();


    @Select("select CASE WHEN EXISTS(select id from usuario WHERE correo=(#{correo}) and pass=(#{pass}))THEN 1 ELSE 0 END")
    public Boolean ingresar(@Param("correo") String correo, @Param("pass") String pass);

    @Insert("insert into usuario(id, correo,nombres,apellidos,pass) values (nextval('sec_usuario'),  #{correo}, #{nombres},#{apellidos},#{pass})")
    public void insertar(
        @Param("correo") String correo,
        @Param("nombres") String nombres,
        @Param("apellidos") String apellidos ,
        @Param("pass") String pass
    );

    @Delete("delete from usuario where id=(#{id})")
    public Integer borrarUsuario(@Param("id") Integer id);

    @Update("update usuario set correo=#{correo},nombres=#{nombres},apellidos=#{apellidos},pass=#{pass} where id=#{id}")
    public Integer editarUsuario( @Param("correo") String correo,
    @Param("nombres") String nombres,
    @Param("apellidos") String apellidos ,
    @Param("pass") String pass,
    @Param("id") Integer id);

}
