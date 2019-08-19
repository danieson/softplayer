package com.softplayer.controller;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softplayer.controllers.swagger.PersonControllerServiceREST;
import com.softplayer.domain.Person;
import com.softplayer.service.PersonServiceImpl;

@RunWith(SpringRunner.class)
public class PersonControllerServiceRESTTeste {

	@InjectMocks
	private PersonControllerServiceREST controller;

	@Mock
	private PersonServiceImpl serviceController;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void add() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/person/v2/add").content(asJsonString(new Person()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void list() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/person/v2/list").content(asJsonString(new Person()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/person/v2/delete/123").content(asJsonString(new Person()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void update() throws Exception {
		when(serviceController.findByID(Matchers.anyLong())).thenReturn(getPerson());

		mockMvc.perform(MockMvcRequestBuilders.put("/person/v2/update/456").content(asJsonString(getPerson()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void findByID() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/person/v2/show/456").content(asJsonString(new Person()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

	private Person getPerson() {
		Person person = new Person();
		person.setCpf("05907233087");
		person.setEmail("teste@gmail.com");
		person.setDataNascimento("21/05/1989");
		person.setNacionalidade("Brasileiro");
		person.setNaturalidade("Brasiliense");
		person.setNome("teste");
		person.setSexo("M");
		person.setDataAtualizacao(new Date());
		person.setDataCadastro(new Date());

		return person;
	}
}
