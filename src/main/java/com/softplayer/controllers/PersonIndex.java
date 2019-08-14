package com.softplayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softplayer.domain.Person;

@Controller
@RequestMapping("/")
public class PersonIndex {
	
    @RequestMapping()
    String index(){
        return "redirect:/person/v1/list";
    }
    
    @RequestMapping("cadastro")
    ModelAndView cadastro(){
    	ModelAndView mv = new ModelAndView("CadastroPessoa");
    	mv.addObject("person", new Person());
        return mv;
    }

}
