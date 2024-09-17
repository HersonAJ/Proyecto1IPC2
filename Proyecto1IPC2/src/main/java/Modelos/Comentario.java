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
public class Comentario {
    
    private int idComentario;
    private String contenido;
    private Usuario usuario;
    private Revista revista;
    private Date fecha;

    public Comentario() {
    }

    public Comentario(int idComentario, String contenido, Usuario usuario, Revista revista, Date fecha) {
        this.idComentario = idComentario;
        this.contenido = contenido;
        this.usuario = usuario;
        this.revista = revista;
        this.fecha = fecha;
    }
    
    void editarComentario(String nuevoComentario){
        
    }
    
    boolean esAutor(Usuario usuario){
        return true;//eliminar esto solo se puso por el return 
    }
    
    void eliminarComentario(){
        
    }
    
}
