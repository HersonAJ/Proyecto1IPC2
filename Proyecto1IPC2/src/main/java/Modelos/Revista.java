/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author herson
 */
public class Revista {

    private int idRevista;
    private String titulo;
    private String descripcion;
    private Date fechaCreacion;
    private Usuario autor;
    private List<Etiqueta> etiquetas;
    // private Categoria categoria;  // Si se va a usar más adelante
    private String categoria;
    private double costoPorDia;
    private int cantidadMeGusta;
    private List<Comentario> comentarios;
    private boolean permiteComentarios;
    private boolean permiteSuscripciones;
    private byte[] archivoPdf;  // Añadimos archivoPdf como byte[] para manejar el PDF
    
    private boolean permiteMeGusta;

    // Constructor por defecto
    public Revista() {
    }

    // Constructor completo
    public Revista(int idRevista, String titulo, String descripcion, Date fechaCreacion, Usuario autor, List<Etiqueta> etiquetas, double costoPorDia, int cantidadMeGusta, List<Comentario> comentarios, boolean permiteComentarios, boolean permiteSuscripciones, boolean permiteMeGusta, byte[] archivoPdf) {
        this.idRevista = idRevista;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.etiquetas = etiquetas;
        this.costoPorDia = costoPorDia;
        this.cantidadMeGusta = cantidadMeGusta;
        this.comentarios = comentarios;
        this.permiteComentarios = permiteComentarios;
        this.permiteSuscripciones = permiteSuscripciones;
        this.permiteMeGusta = permiteMeGusta;
        this.archivoPdf = archivoPdf;  // El archivo PDF almacenado como byte[]
    }

    // Métodos Getters y Setters
    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public double getCostoPorDia() {
        return costoPorDia;
    }

    public void setCostoPorDia(double costoPorDia) {
        this.costoPorDia = costoPorDia;
    }

    public int getCantidadMeGusta() {
        return cantidadMeGusta;
    }

    public void setCantidadMeGusta(int cantidadMeGusta) {
        this.cantidadMeGusta = cantidadMeGusta;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }
    
    //


    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    public boolean isPermiteMeGusta(){
        return permiteMeGusta;
    }
    public void setPermiteMeGusta(boolean permiteMeGusta){
        this.permiteMeGusta = permiteMeGusta;
    }

    public boolean isPermiteComentarios() {
        return permiteComentarios;
    }

    public void setPermiteComentarios(boolean permiteComentarios) {
        this.permiteComentarios = permiteComentarios;
    }

    public boolean isPermiteSuscripciones() {
        return permiteSuscripciones;
    }

    public void setPermiteSuscripciones(boolean permiteSuscripciones) {
        this.permiteSuscripciones = permiteSuscripciones;
    }

    public byte[] getArchivoPdf() {
        return archivoPdf;
    }

    public void setArchivoPdf(byte[] archivoPdf) {
        this.archivoPdf = archivoPdf;
    }

    // Métodos para gestionar comentarios y "me gusta"
    public void agregarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    public void agregarMeGusta() {
        this.cantidadMeGusta++;
    }

    public void bloquearComentarios() {
        this.permiteComentarios = false;
    }

    public void desbloquearComentarios() {
        this.permiteComentarios = true;
    }

    public void bloquearSuscripciones() {
        this.permiteSuscripciones = false;
    }

    public void desbloquearSuscripciones() {
        this.permiteSuscripciones = true;
    }
/*
    // Método para generar reportes de suscripciones
    public Reporte generarReporteSuscripciones(Date desde, Date hasta) {
        // Implementación pendiente
        return null;
    }
*/

}

