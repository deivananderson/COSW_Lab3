package edu.eci.cosw.spademo.controller;

import edu.eci.cosw.spademo.model.Tarea;
import edu.eci.cosw.spademo.model.TareasServices;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by USUARIO on 07/02/2017.
 */

@Service
public class StudTareas implements TareasServices {

    private List<Tarea> tareas;

    private StudTareas(){
        tareas=new LinkedList<Tarea>();
        Tarea tarea1 = new Tarea("Llamado tarea 1",1);
        Tarea tarea2 = new Tarea("Llamado tarea 2",2);
        Tarea tarea3 = new Tarea("Llamado tarea 3",3);
        Tarea tarea4 = new Tarea("Llamado tarea 4",4);
        Tarea tarea5 = new Tarea("Llamado tarea 5",5);
        agregarTarea(tarea1);
        agregarTarea(tarea2);
        agregarTarea(tarea3);
        agregarTarea(tarea4);
        agregarTarea(tarea5);
    }

    @Override
    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    @Override
    public List<Tarea> getTareas() {
        return tareas;
    }
}
