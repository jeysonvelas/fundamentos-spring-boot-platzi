package com.fundamentosplatzi.springboot.fundamentos.service;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final Log LOG = LogFactory.getLog(UserService.class);


    //Metodo Save con Transactional para que se realice el flujo de la transaccion y realice rollback en caso de que se genere inconsistencia.
    @Transactional
    public void saveTransaccional(List<User> users) {

        users.stream().peek(user -> LOG.info("Usuario insertado: " + user)).forEach(userRepository::save);

    }

    //Metodo get sencillo o estandar, para encontrar todos los usuarios insertados.

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    //Metodo save sencillo o estandar, para registrar nuevo usuarios.
    public User save(User newUser){

        return userRepository.save(newUser);

    }


    public void delete(Long id) {
        userRepository.deleteById(id);

    }


    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return  userRepository.save(user);
                        }
                ) .orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID proporcionado: " + id));
    }

}
