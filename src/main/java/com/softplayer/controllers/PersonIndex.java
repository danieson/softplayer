package com.softplayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PersonIndex {
	
    @RequestMapping(method = RequestMethod.GET)
    String index(){
        return "index";
    }

}
