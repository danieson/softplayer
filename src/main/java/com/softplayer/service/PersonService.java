package com.softplayer.service;

import java.util.List;

import com.softplayer.domain.Person;

public interface PersonService {
	
	Person save(Person pessoa) throws Exception;
	
	Person findByID(String cpf);
	
	List<Person> findAll();
	
	void delete(String cpf) throws Exception;

}
