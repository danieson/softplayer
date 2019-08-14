package com.softplayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplayer.domain.Person;
import com.softplayer.repositories.PersonRepository;
import com.softplayer.validate.Validate;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person save(Person person) throws Exception {
		Person p = findByID(person.getCpf());
		validate(person);
		if(p != null) {
			throw new Exception("Pessoa já cadastrada.");
		}
		
		return personRepository.save(person);
		
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
	
	private void validate(Person person) throws Exception {
		if(person.getNome() == null || person.getNome().isEmpty()) {
			throw new Exception("Campo nome Obrigatório");
		}
		if(person.getEmail() == null || person.getEmail().isEmpty()) {
			Validate.email(person.getEmail());
		}
		if(person.getDataNascimento() == null || person.getDataNascimento().isEmpty()) {
			throw new Exception("Campo nome Data nascimento");
		}
		
		if(person.getCpf() == null || person.getCpf() .isEmpty()) {
			throw new Exception("Campo nome Obrigatório");
		}else {
			Validate.cpf(person.getCpf());
		}
	}

}
