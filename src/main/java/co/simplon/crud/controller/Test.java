package co.simplon.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
	
	@GetMapping("/test/ping")
	public String ping()
	{
		return "ok";
	}

}
