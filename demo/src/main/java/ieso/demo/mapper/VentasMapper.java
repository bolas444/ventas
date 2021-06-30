package ieso.demo.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ieso.demo.domain.Ventas;

@Mapper
public interface VentasMapper {

    @Select("select id,productos,cantidadtotal,descuento,precio,fecha from ventas WHERE fecha=(#{fecha})")
    public List<Ventas>  lista1(
        @Param("fecha") Date fecha ) ;

    @Select("select * from ventas where fecha between #{fecha} and #{fechal}")
    public List<Ventas>  lista2(  
        @Param("fecha") Date fecha, 
        @Param("fechal") Date fechal);


    @Insert("insert into ventas(id,productos,cantidadtotal,descuento,precio,fecha) values (nextval('sec_ventas'),#{productos},#{cantidadtotal},#{descuento},#{precio},#{fecha})")
    public void insertar(
        @Param("productos") String productos, 
        @Param("cantidadtotal") Integer cantidadtotal,
        @Param("descuento") Integer descuento ,
        @Param("precio") Double precio,
        @Param("fecha") Date fecha
    );
}
 