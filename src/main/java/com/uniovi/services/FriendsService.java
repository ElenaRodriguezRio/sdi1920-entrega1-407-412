package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Friend;
import com.uniovi.repositories.FriendsRepository;

public class FriendsService {

	@Autowired
	private FriendsRepository friendsRepository;
	
	public void addFriend(Friend friend) {
		friendsRepository.save(friend);
	}
}
