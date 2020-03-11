package com.uniovi.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);
	
	@Query("SELECT r FROM User r WHERE ( (LOWER(r.name) LIKE LOWER('%'+?1+'%') OR LOWER(r.lastName) LIKE LOWER('%'+?1+'%') OR LOWER(r.email) LIKE LOWER('%'+?1+'%')) AND (r.role='ROLE_STANDARDUSER' AND r.id<>?2) )")
	Page<User> searchByEmailNameAndLastName(String searchText, Long userId, Pageable pageable);
	
	Page<User> findAll(Pageable pageable);
	
	@Query("SELECT r FROM User r WHERE (r.role='ROLE_STANDARDUSER' AND r.id<>?1)")
	Page<User> searchStandardUsersButAuthenticated(Long userId, Pageable pageable);
	
	
}
