package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.FriendRequest;

public interface FriendRequestsRepository extends CrudRepository<FriendRequest, Long> {

}
