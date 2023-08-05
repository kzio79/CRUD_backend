package com.backend.crud.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crud.service.UserService;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String home() {
		return "이제 시작!!!";
	}
}
