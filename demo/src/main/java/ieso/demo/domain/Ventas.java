package ieso.demo.domain;

import java.util.Date;

import javax.websocket.Decoder.Text;

/**
 * Ventas
 */
public class Ventas {
    private Integer id;
    private String productos;
    private Integer cantidadtotal;
    private Integer descuento;
    private Double precio;
    private Date fecha;
    private Date fechal;

    private String fecha1;

    public Integer getId() {
        return id;
    }
    public Date getFechal() {
        return fechal;
    }
    public void setFechal(Date fechal) {
        this.fechal = fechal;
    }
    public String getFecha1() {
        return fecha1;
    }
    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProductos() {
        return productos;
    }
    public void setProductos(String productos) {
        this.productos = productos;
    }
    public Integer getCantidadtotal() {
        return cantidadtotal;
    }
    public void setCantidadtotal(Integer cantidadtotal) {
        this.cantidadtotal = cantidadtotal;
    }
    public Integer getDescuento() {
        return descuento;
    }
    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
   
}
