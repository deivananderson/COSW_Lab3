package edu.eci.cosw.spademo.controller;

import edu.eci.cosw.spademo.model.Tarea;
import edu.eci.cosw.spademo.model.TareasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * Created by USUARIO on 06/02/2017.
 */

@RestController
public class TareasController {

    @Autowired
    TareasServices services;

    @RequestMapping(value = "/tareas", method = RequestMethod.GET)
    public List<Tarea> getTareasPendientes() {
        return services.getTareas();
    }

    @RequestMapping(value = "/tareas/agregar", method = RequestMethod.POST)
    public ResponseEntity<Tarea> addTarea(@RequestBody Tarea tarea) {
        System.out.println(tarea.getDescripcion() + " " + tarea.getPrioridad());
        services.agregarTarea(tarea);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
