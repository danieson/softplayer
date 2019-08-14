package com.softplayer.controllers.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softplayer.domain.Person;
import com.softplayer.service.PersonService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/person/v2")
public class PersonControllerServiceREST {
	
	@Autowired
	private PersonService personService;
	
	@ApiOperation(value = "View a list of available person",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> list(Model model){
        Iterable<Person> PersonList = personService.findAll();
        return PersonList;
    }
    @ApiOperation(value = "Search a person with an ID",response = Person.class)
    @RequestMapping(value = "/show/{cpf}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person showPerson(@PathVariable String cpf, Model model){
    	Person Person = personService.findByID(cpf);
        return Person;
    }

    @ApiOperation(value = "Add a person")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity savePerson(@RequestBody Person person) throws Exception{
        personService.save(person);
        return new ResponseEntity("Person saved successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Update a person")
    @RequestMapping(value = "/update/{cpf}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePerson(@PathVariable String cpf, @RequestBody Person person){
    	Person storedperson = personService.findByID(cpf);
    	storedperson.setNome(person.getNome());
    	storedperson.setSexo(person.getSexo());
    	storedperson.setEmail(person.getEmail());
    	storedperson.setNacionalidade(person.getNacionalidade());
    	storedperson.setNaturalidade(person.getNaturalidade());

        return new ResponseEntity("Person updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a Person")
    @RequestMapping(value="/delete/{cpf}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable String cpf) throws Exception{
        personService.delete(cpf);;
        return new ResponseEntity("Person deleted successfully", HttpStatus.OK);

    }


}

