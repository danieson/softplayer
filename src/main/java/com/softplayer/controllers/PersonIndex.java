package com.softplayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PersonIndex {
	
    @RequestMapping()
    String index(){
        return "redirect:/person/v1/list";
    }
    
    @RequestMapping("cadastro")
    String cadastro(){
        return "CadastroPessoa";
    }

}
