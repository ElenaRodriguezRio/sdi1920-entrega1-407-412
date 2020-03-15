package com.uniovi.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Publicacion;
import com.uniovi.entities.User;
import com.uniovi.services.PostsService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.CreatePostValidator;

@Controller
public class PostsController {

	@Autowired
	private PostsService postsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CreatePostValidator createPostValidator;

	@RequestMapping("/publicacion/list")
	public String getList(Model model, Pageable pageable, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Page<Publicacion> posts = new PageImpl<Publicacion>(new LinkedList<Publicacion>());
		posts = postsService.getPostsForUser(pageable, user);
		
		model.addAttribute("postsList", posts.getContent());
		model.addAttribute("page", posts);
		
		return "publicacion/list";
	}
	
	@RequestMapping(value = "/publicacion/add", method = RequestMethod.POST)
	public String setPost(@Validated Publicacion publicacion, BindingResult result, Model model, Principal principal) {
		createPostValidator.validate(publicacion, result);
		if (result.hasErrors()) {
			return "publicacion/add";
		}
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		publicacion.setUser(user);
		publicacion.setDate(new Date());
		postsService.addPost(publicacion);
		return "redirect:/publicacion/list";
	}
	
	@RequestMapping(value = "/publicacion/add", method = RequestMethod.GET)
	public String getPost(Model model) {
		model.addAttribute("publicacion", new Publicacion());
		return "publicacion/add";
	}
}
