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
    ResponseEntity<User> newUser(@RequestBody User newUser){

        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);

    }

    //Servicio Rest para Actualizar un usuario
    @PutMapping("/{id}")
    ResponseEntity<User> updateServiceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);


    }

    //Servicio Rest para eliminar usuarios
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
