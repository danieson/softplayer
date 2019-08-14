package com.softplayer.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.softplayer.domain.Person;
import com.softplayer.repositories.PersonRepository;
import com.softplayer.service.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Spy
    private PersonServiceImpl personServiceImpl;
    @Mock
    private Person person;
    
    @Test
    public void testInsertWithErro() {
    	Mockito.doReturn(getPerson()).when(personServiceImpl).findByID(getPerson().getCpf());
    	
    	try {
			Person p = personServiceImpl.insert(getPerson());
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Pessoa já cadastrada.");
		}
    }
    
    @Test
    public void testDelete() {
    	Mockito.doReturn(null).when(personServiceImpl).findByID(getPerson().getCpf());
    	
    	try {
			personServiceImpl.delete(getPerson().getCpf());
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Nenhum cadastrado encontrado para a pessoa.");
		}

    }
    
    
    private Person getPerson() {
		Person person = new Person();
		person.setCpf("85259602331");
		person.setEmail("teste@gmail.com");
		person.setNacionalidade("Brasileiro");
		person.setNaturalidade("Brasiliense");
		person.setNome("teste");
		person.setSexo("M");
		
		return person;
    }
}
