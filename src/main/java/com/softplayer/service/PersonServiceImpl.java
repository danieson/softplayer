package com.softplayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplayer.domain.Person;
import com.softplayer.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person insert(Person pessoa) throws Exception {
		Person p = findByID(pessoa.getCPF());
		if(p != null) {
			throw new Exception("Pessoa já cadastrada.");
		}
		
		return personRepository.save(pessoa);
		
	}

	@Override
	public Person findByID(String cpf) {
		return personRepository.findById(cpf).orElse(null);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public void delete(String cpf) throws Exception {
		Person p = findByID(cpf);
		if(p == null) {
			throw new Exception("Nenhum cadastrado encontrado para a pessoa.");
		}
		personRepository.deleteById(cpf);
		
	}

}
