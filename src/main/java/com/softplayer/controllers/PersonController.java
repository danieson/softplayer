package com.softplayer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.softplayer.domain.Person;
import com.softplayer.service.PersonService;

import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
    @ApiOperation(value = "Add a person")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView savePerson(@Validated Person person, Errors errors) throws Exception{
        ModelAndView mv = new ModelAndView("CadastroPessoa");
        
        if(errors.hasErrors()) {
        	return mv;
        }
        
        personService.insert(person);
        mv.addObject("mensagem", "Pessoa salva com sucesso!!!");
        return mv;
    }
	
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(){
        List<Person> PersonList = personService.findAll();
        ModelAndView mv = new ModelAndView("PesquisaPessoas");
        mv.addObject("persons",PersonList);
        return mv;
    }

    @ApiOperation(value = "Search a person with an ID",response = Person.class)
    @RequestMapping(value = "/show/{cpf}", method= RequestMethod.GET, produces = "application/json")
    public Person showPerson(@PathVariable String cpf, Model model){
    	Person Person = personService.findByID(cpf);
        return Person;
    }



    @ApiOperation(value = "Update a person")
    @RequestMapping(value = "/update/{cpf}", method = RequestMethod.PUT)
    public ResponseEntity updatePerson(@PathVariable String cpf,  Person person){
    	Person storedperson = personService.findByID(cpf);
    	storedperson.setNome(person.getNome());
    	storedperson.setSexo(person.getSexo());
    	storedperson.setEmail(person.getEmail());
    	storedperson.setNacionalidade(person.getNacionalidade());
    	storedperson.setNaturalidade(person.getNaturalidade());

        return new ResponseEntity("Person updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Person")
    @RequestMapping(value="/delete/{cpf}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String cpf) throws Exception{
        personService.delete(cpf);;
        return new ResponseEntity("Person deleted successfully", HttpStatus.OK);

    }


}
