/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;

/**
 *
 * @author herson
 */
public class Publicacion {
    private int idRevista;
    private String titulo;
    private String descripcion;

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setPermiteComentarios(boolean permiteComentarios) {
        this.permiteComentarios = permiteComentarios;
    }

    public void setPermiteMegusta(boolean permiteMegusta) {
        this.permiteMegusta = permiteMegusta;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public boolean isPermiteComentarios() {
        return permiteComentarios;
    }

    public boolean isPermiteMegusta() {
        return permiteMegusta;
    }
    private String categoria;
    private Date fechaCreacion;
    private boolean permiteComentarios;
    private boolean permiteMegusta;

    public Publicacion(int idRevista, String titulo, String descripcion, String categoria, Date fechaCreacion, boolean permiteComentarios, boolean permiteMegusta) {
        this.idRevista = idRevista;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaCreacion = fechaCreacion;
        this.permiteComentarios = permiteComentarios;
        this.permiteMegusta = permiteMegusta;
    }

    // Getters y Setters...
}