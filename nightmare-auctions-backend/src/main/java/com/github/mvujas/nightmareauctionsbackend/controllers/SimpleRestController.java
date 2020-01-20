package com.github.mvujas.nightmareauctionsbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.url}")
public class SimpleRestController {

	@GetMapping("greeting")
	public String greet() {
		return "Hello World!";
	}

}
