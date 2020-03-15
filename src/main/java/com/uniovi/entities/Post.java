package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(name = "publicacion")
public class Post {

	@Id
	@GeneratedValue
	private long id;
	
	private String titulo;
	private String texto;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private Date date;
	
	public Post() {
		
	}
	
	public Post(String titulo, String texto, User user) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.user = user;
		this.date = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", user=" + user + ", date=" + date + "]";
	}
	
	

}