package com.fundamentosplatzi.springboot.fundamentos.controller;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private GetUser getUser;
    @Autowired
    private CreateUser createUser;
    @Autowired
    private DeleteUser deleteUser;
    @Autowired
    private UpdateUser updateUser;

    //Servicio Rest para obtener una Lista
    @GetMapping("/")
    List<User> get(){

    return getUser.getAll();

    }

    //Servicio Rest para guardar un nuevo usuario
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void createUsers(@RequestBody User newUser){
    createUser.save(newUser);

    }

    //Servicio Rest para Actualizar un usuario
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateServiceUser(@RequestBody User newUser, @PathVariable Long id){
        updateUser.update(newUser,id);


    }

    //Servicio Rest para eliminar usuarios
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        deleteUser.remove(id);

    }

}
