package com.example.springbootredis.resource;

import com.example.springbootredis.model.Hash;
import com.example.springbootredis.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hash")
public class EmployeeResource {

	@Autowired
	HashService hashService;

	@GetMapping
	public Hash getHash(@RequestParam final String string) {
		return hashService.findByString(string);
	}

	@PostMapping
	public Hash saveHash(@RequestParam String string) {
		return hashService.save(string);
	}

}
