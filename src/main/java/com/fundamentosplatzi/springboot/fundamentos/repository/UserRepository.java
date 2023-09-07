package com.fundamentosplatzi.springboot.fundamentos.repository;

import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u From User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("Select u From User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    @Query("Select u From User u")
    List<User> findByAll(User user);


}
