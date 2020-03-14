package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.FriendRequest;
import com.uniovi.repositories.FriendRequestsRepository;

public class FriendRequestsService {

	@Autowired
	private FriendRequestsRepository friendRequestsRepository;
	
	public void addFriendRequest(FriendRequest friendRequest) {
		friendRequestsRepository.save(friendRequest);
	}
}
