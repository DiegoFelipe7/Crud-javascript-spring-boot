package com.sofka.Crudjavascript.controller;

import com.sofka.Crudjavascript.models.UserModel;
import com.sofka.Crudjavascript.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> obtenerUsuarios() {
        return userService.obtenerUsuarios();
    }

    @PostMapping()
    public UserModel guardarUsuario(@RequestBody UserModel usuario) {
        return this.userService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.userService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UserModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad) {
        return this.userService.obtenerPorPrioridad(prioridad);
    }
    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.userService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }
    @GetMapping("/querys")
    public ArrayList<UserModel> obtenerUsuarioPorEmail(@RequestParam("email") String email){
        return this.userService.obtenerPorEmail(email);
    }

    @DeleteMapping(path = "/correo/{email}")
    public String deleteEmail(@PathVariable("email") String email){
        ArrayList<UserModel> usuarioModels = this.userService.obtenerPorEmail(email);
        boolean ok = this.userService.eliminarEmail(usuarioModels.get(0).getId());
        if (ok) {
            return "Se eliminó el usuario " + email;
        } else {
            return "No pudo eliminar el usuario con id"+email;
        }

    }



}
