package edu.eci.cosw.spademo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by USUARIO on 07/02/2017.
 */
@RestController
public class UsersController {
    @RequestMapping("/app/user")
    public Principal user(Principal user) {
        return user;
    }
}
