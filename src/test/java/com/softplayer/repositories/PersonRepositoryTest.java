package com.softplayer.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.softplayer.config.RepositoryConfig;
import com.softplayer.domain.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfig.class})
public class PersonRepositoryTest {
	
	private static final String CPF = "47235244607";
	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void testCrudRepository() {
		Person person = new Person();
		person.setCPF(CPF);
		person.setEmail("teste@gmail.com");
		person.setNacionalidade("Brasileiro");
		person.setNaturalidade("Brasiliense");
		person.setNome("teste");
		person.setSexo("M");
		
		Person personSave = personRepository.save(person);
		assertNotNull(personSave);
		
		Person personFindId = personRepository.findById(CPF).orElse(null);
		assertNotNull(personFindId);
		
		List<Person> personFindAll = personRepository.findAll();
		int count = 0;
		for (Person person2 : personFindAll) {
			count++;
		}
		assertEquals(count, 1);
		
		personRepository.deleteById(CPF);
		
		personFindId = personRepository.findById(CPF).orElse(null);
		assertNull(personFindId);
	}

}
