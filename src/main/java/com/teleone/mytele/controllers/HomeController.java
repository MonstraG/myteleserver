package com.teleone.mytele.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/*")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<String> home() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

}
