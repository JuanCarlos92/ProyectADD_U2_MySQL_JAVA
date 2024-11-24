package org.example.dto;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int categoriaId;
    private String categoriaNombre;

    public Producto(int id, String nombre, double precio, int categoriaId, String categoriaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
    }

    public Producto(String nombre, double precio, int categoriaId) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }
    public Producto(int id, String nombre, double precio, int categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    @Override
    public String toString() {
        return "ID: "+ id +", Nombre: "+ nombre +", Precio: "+ precio +", CategoriaID: "+ categoriaId +", CategoriaNombre: " + categoriaNombre;

    }
}
//                +
//                "id=" + id +
//                ", nombre='" + nombre + '\'' +
//                ", precio=" + precio +
//                ", categoriaId=" + categoriaId +
//                ", categoriaNombre='" + categoriaNombre + '\'' +
//                '}';

