package com.softplayer.service;

import java.util.List;

import com.softplayer.domain.Person;

public interface PersonService {
	
	Person save(Person pessoa) throws Exception;
	
	Person update(Person person) throws Exception;
	
	Person findByID(Long codigo);
	
	List<Person> findAll();
	
	void delete(Long codigo) throws Exception;

}
