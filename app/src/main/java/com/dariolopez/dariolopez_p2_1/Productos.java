package com.dariolopez.dariolopez_p2_1;

public class Productos {

    String id, codigo, nombre, precio,descripcion;

    public Productos(/*String id,*/String codigo, String nombre, String precio, String descripcion) {
  //      this.id=id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }
/*
    public String getId() {
        return id;
    }
*/
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
