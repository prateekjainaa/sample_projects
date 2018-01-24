package com.pjain.service.impl;

import com.pjain.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String greetings(String name) {
		return "Hello! " + name;
	}

}
