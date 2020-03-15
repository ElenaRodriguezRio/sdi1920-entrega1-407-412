package com.uniovi.services;

import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Post;
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
	
	public Page<Post> getMarks(Pageable pageable) {
		Page<Post> posts = postsRepository.findAll(pageable);
		return posts;
	}
	

	public void addPost(Post post) {
		postsRepository.save(post);
	}
	
	public Page<Post> getPostsForUser(Pageable pageable, User user) {
		Page<Post> posts = new PageImpl<Post>(new LinkedList<Post>());
		posts = postsRepository.findAllByUser(pageable, user);
		return posts;
	}
}
