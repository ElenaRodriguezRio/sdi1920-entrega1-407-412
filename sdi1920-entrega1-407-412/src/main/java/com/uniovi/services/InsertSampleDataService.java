package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {
		//Usuario normal (ROLE_STANDARDUSER)
		User user1 = new User("pedro99@uniovi.es", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		//Usuario admin (ROLE_ADMIN)
		User user2 = new User("admin@email.com", "Marta", "Almonte");
		user2.setPassword("admin");
		user2.setRole(rolesService.getRoles()[1]);

		
		usersService.addUser(user1);
		usersService.addUser(user2);
	}
}
