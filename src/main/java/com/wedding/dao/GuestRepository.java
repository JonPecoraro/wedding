package com.wedding.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.wedding.model.Guest;

//This will be AUTO IMPLEMENTED by Spring

public interface GuestRepository extends CrudRepository<Guest, Integer> {
	Set<Guest> findByFirstName(String firstName);
	Set<Guest> findByLastName(String lastName);
}
