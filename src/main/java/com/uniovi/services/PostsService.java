package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;
import com.uniovi.repositories.PostsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

	@Autowired
	private PostsRepository postsRepository;
	
	public Page<Publicacion> getMarks(Pageable pageable) {
		Page<Publicacion> posts = postsRepository.findAll(pageable);
		return posts;
	}
	

	public void addPost(Publicacion post) {
		postsRepository.save(post);
	}
	
	public Page<Publicacion> getPostsForUser(Pageable pageable, User user) {
		Page<Publicacion> posts = new PageImpl<Publicacion>(new LinkedList<Publicacion>());
		posts = postsRepository.findAllByUser(pageable, user);
		return posts;
	}
}
