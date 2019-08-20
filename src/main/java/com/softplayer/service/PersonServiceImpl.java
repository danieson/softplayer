package com.softplayer.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplayer.domain.Person;
import com.softplayer.repositories.PersonRepository;
import com.softplayer.validate.Validate;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person save(Person person) throws Exception {
		validate(person);
		person.setDataCadastro(new Date());
		Person personFound = personRepository.findByCpf(person.getCpf());
		if(personFound != null) {
			throw new Exception("CPF Já Cadastrado !!!");
		}
		return personRepository.save(person);
	}

	@Override
	public Person findByID(Long codigo) {
		return personRepository.findById(codigo).orElse(null);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public void delete(Long codigo) throws Exception {
		Person p = findByID(codigo);
		if (p == null) {
			throw new Exception("Nenhum cadastrado encontrado para a pessoa.");
		}
		personRepository.deleteById(codigo);
	}

	private void validate(Person person) throws Exception {
		if (person.getNome() == null || person.getNome().isEmpty()) {
			throw new Exception("Campo nome Obrigatório");
		}
		if (person.getEmail() != null && !person.getEmail().isEmpty()) {
			Validate.email(person.getEmail());
		}
		if (person.getDataNascimento() == null || person.getDataNascimento().isEmpty()) {
			throw new Exception("Campo nome Data nascimento");
		} else {
			Validate.validateDate(person.getDataNascimento(), true, false);
		}

		if (person.getCpf() == null || person.getCpf().isEmpty()) {
			throw new Exception("Campo nome Obrigatório");
		} else {
			Validate.cpf(person.getCpf());
		}
	}

	@Override
	public Person update(Person person) throws Exception {
		validate(person);
		person.setDataAtualizacao(new Date());
		return personRepository.save(person);

	}
	
	
}
