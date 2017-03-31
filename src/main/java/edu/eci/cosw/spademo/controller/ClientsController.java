package edu.eci.cosw.spademo.controller;

import edu2.eci.cosw.stubs.fakeclientslibrary.Client;
import edu2.eci.cosw.stubs.fakeclientslibrary.ClientServicesStub;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by oscar on 8/02/17.
 */
@RestController
@RequestMapping("/clients")
public class ClientsController {

    ClientServicesStub clientServicesStub = new ClientServicesStub();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getClientsManagement(){
        try {
            Set<Client> data = clientServicesStub.getAllClients();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No clients found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getClientByIDManagement(@PathVariable Integer id){
        try {
            Client client = clientServicesStub.getClientById(id);
            return new ResponseEntity<>(client, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No clients matches this ID", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{id}/picture", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getClientPictureManagement(@PathVariable Integer id){
        try {
            InputStream picture = clientServicesStub.getClientPicture(id);
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpg")).body(new InputStreamResource(picture));
        } catch (Exception ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
