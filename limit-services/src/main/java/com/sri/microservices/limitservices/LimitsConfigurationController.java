package com.sri.microservices.limitservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.microservices.limitservice.bean.LimitConfiguraion;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguraion retrieveLimitsFromConfiguration() {
		return new LimitConfiguraion(configuration.getMaximum() ,configuration.getMinimum());
	}

}
