package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Person;
import com.example.service.DemoService;

@RestController
public class CacheController {

	@Autowired
	DemoService demoService;
	
	@RequestMapping("/put")
	public Person put(Person person) {
		return demoService.save(person);
	}
	
	@RequestMapping("/able")
	public Person cacheable(Person person) {
		return demoService.findOne(person);
	}
	
	@RequestMapping("/evit")
	public String evit(Long id) {
		demoService.remove(id);
		return "OK";
	}
}
