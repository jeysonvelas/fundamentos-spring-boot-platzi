package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {


	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	@Autowired
	private UserService userService;
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
		//saveUser();
		//findUsers();
		saveWithTransaction();
	}


	public void findUsers(){

		//LOGGER.info("Ususario encontrado: " +	userRepository.findByUserEmail("jeysonvelasquez.v@gmail.com").orElseThrow(() -> new RuntimeException("No se encontro usuario")));

		//userRepository.findAndSort("j" , Sort.by("birthDate").ascending()).stream().forEach(user -> LOGGER.info("Usuarios encontrados: " + user));

		//userRepository.findByAll(new User()).stream().forEach(user -> LOGGER.info("Usuarios encontrados: " + user.getName() ));

		//uso de Query Methods
		//userRepository.findByName("jeyson").stream().forEach(user -> LOGGER.info("Usuarios encontrado: " + user));
		//userRepository.findByNameLikeOrderByNameAsc("%j%").stream().forEach(user -> LOGGER.info("Usuarios encontrados: " + user.getName()));

		//Uso de JPQL con named parameter
		//LOGGER.info("El Ususario encontrado a partir del named Parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(1986, 8, 5),"jeysonvelasquez.v@gmail.com").orElseThrow(() -> new RuntimeException("No se encontro el usuario")));

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

	public void saveWithTransaction(){

		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);

		userService.saveTransaccional(users);

		userService.getAllUsers().stream().forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional: " + user));

	}

}
