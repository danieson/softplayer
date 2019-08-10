package com.softplayer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softplayer.domain.Pessoa;


@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @RequestMapping(value = "/teste", method= RequestMethod.GET, produces = "application/json")
    public Pessoa teste(){
    	Pessoa t = new Pessoa();
    	t.setCPF("8976574534");
    	t.setNaturalidade("Brasileiro");
    	t.setEmail("teste@gmail.com");
    	t.setNaturalidade("Brasiliense");
    	t.setNome("teste");
    	t.setSexo("m");
        return t;
    }
}
