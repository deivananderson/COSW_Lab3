package edu.eci.cosw.spademo.model;

/**
 * Created by USUARIO on 06/02/2017.
 */

public class Tarea {

    private String descripcion;
    private Integer prioridad;

    public Tarea() {
    }

    public Tarea(String descripcion,Integer prioridad){
        this.descripcion= descripcion;
        this.prioridad=prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

}
