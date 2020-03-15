package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Post;
import com.uniovi.entities.User;

public interface PostsRepository extends CrudRepository<Post, Long>{

	Page<Post> findAll(Pageable pageable);
	
	@Query("SELECT r FROM Post r WHERE r.user = ?1 ORDER BY r.id ASC ")
	Page<Post> findAllByUser(Pageable pageable, User user);
}
