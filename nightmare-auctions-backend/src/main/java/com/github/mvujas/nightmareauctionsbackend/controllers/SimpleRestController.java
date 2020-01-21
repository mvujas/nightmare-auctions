package com.github.mvujas.nightmareauctionsbackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class SimpleMessage {
	String name = "pera";
	String message = "Zdravo svete!";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

@RestController
@RequestMapping("${api.url}")
public class SimpleRestController {

	@GetMapping("greeting")
	public String greet() {
		return "Hello World!";
	}
	
	@GetMapping("message")
	public SimpleMessage message() {
		return new SimpleMessage();
	}

}
