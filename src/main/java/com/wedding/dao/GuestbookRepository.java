package com.wedding.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.wedding.model.Guestbook;

//This will be AUTO IMPLEMENTED by Spring

public interface GuestbookRepository extends CrudRepository<Guestbook, Integer> {
	Set<Guestbook> findAllByOrderByDateCreatedDesc();
}
