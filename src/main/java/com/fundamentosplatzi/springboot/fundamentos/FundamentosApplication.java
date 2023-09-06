package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {


	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	@Autowired
	@Qualifier("componentTwoImplement")
	private ComponentDependency componentDependency;

	@Autowired
	private MyBean myBean;

	@Autowired
	UserRepository userRepository;




	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//componentDependency.saludar();
		//myBean.print();
		saveUser();
		findEmail();
	}


	public void findEmail(){

		LOGGER.info("Ususario encontrado: " +
				userRepository.findByUserEmail("jeysonvelasquez.v@gmail.com").orElseThrow(() -> new RuntimeException("No se encontro usuario")));

		userRepository.findAndSort("j" , Sort.by("birthDate").ascending()).stream().forEach(user -> LOGGER.info("Usuarios encontrados: " + user));

		//userRepository.findByAll(new User()).stream().forEach(user -> LOGGER.info("Usuarios encontrados: " + user ));
		userRepository.findByAll(new User(), Sort.by("name").ascending()).stream().forEach(user -> LOGGER.info("Usuarios encontrados: " + user));
	}


	public void saveUser(){

		User user1 = new User("jeyson", "jeysonvelasquez.v@gmail.com", LocalDate.of(1986, 8, 5));
		User user2 = new User("jose", "jose@gmail.com", LocalDate.of(1990, 6, 11));
		User user3 = new User("jackson", "jackson@gmail.com", LocalDate.of(1994, 10, 8));
		User user4 = new User("josue", "josue@gmail.com", LocalDate.of(1988, 11, 20));
		User user5 = new User("jarol", "jarol@gmail.com", LocalDate.of(1999, 6, 25));
		User user6 = new User("javier", "javier@gmail.com", LocalDate.of(2000, 5, 30));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6);
		list.stream().forEach(userRepository::save);

	}


}
