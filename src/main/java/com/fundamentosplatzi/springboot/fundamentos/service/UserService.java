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

    @Transactional
    public void saveTransaccional(List<User> users) {

        users.stream().peek(user -> LOG.info("Usuario insertado: " + user)).forEach(userRepository::save);

    }

    public List<User> getAllUsers() {

        return userRepository.findAll();

    }


}
