package edu.eci.cosw.spademo.services;
import edu.eci.cosw.spademo.model.Tarea;
import edu.eci.cosw.spademo.model.TareasServices;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by USUARIO on 07/02/2017.
 */


public class Tareas implements TareasServices{
    private static final List<Tarea> tareas = new LinkedList<Tarea>();

    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public List<Tarea> getTareas(){
        return tareas;
    }
}
