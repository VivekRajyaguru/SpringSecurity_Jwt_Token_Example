package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class Controller {

	@RequestMapping(value = "/msg", method = RequestMethod.GET)
	public String msg() {
		return "Welcome...";
	}
	
}
