package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;

public interface PostsRepository extends CrudRepository<Publicacion, Long>{

	Page<Publicacion> findAll(Pageable pageable);
	
	@Query("SELECT r FROM Publicacion r WHERE r.user = ?1 ORDER BY r.id ASC ")
	Page<Publicacion> findAllByUser(Pageable pageable, User user);
}
