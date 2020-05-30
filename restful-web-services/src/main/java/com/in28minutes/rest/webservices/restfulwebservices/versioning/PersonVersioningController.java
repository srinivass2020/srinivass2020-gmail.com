package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	@GetMapping(value="v1/person")			//URI Versioning : Twitter (Pollutes the URI)
	public PersonV1 uriV1() {
		return new PersonV1("Srinivas Chilukuri");
	}
	
	@GetMapping(value="v2/person")			
	public PersonV2 uriV2() {
		return new PersonV2(new Name("Srinivas","Chilukuri"));
	}
	
	@GetMapping(value="/person/param",params="version=1")			//Request Param Versioning : Amazon (Pollutes the URI)
	public PersonV1 paramV1() {
		return new PersonV1("Srinivas Chilukuri");
	}
	
	@GetMapping(value="/person/param",params="version=2")			
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Srinivas","Chilukuri"));
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=1")		//Header Versioning : Microsfot	(caching can not be done,Misuse of HTTP Headers)
	public PersonV1 headerV1() {
		return new PersonV1("Srinivas Chilukuri");
	}
	
	@GetMapping(value="/person/header",headers="X-API-VERSION=2")	
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Srinivas","Chilukuri"));
	}	
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json") //Media Type/Content Negoitation/accept header Versioning,Misuse of HTTP Headers --Github 
	public PersonV1 producesV1() {														//caching can not be done	
		return new PersonV1("Srinivas Chilukuri");
	}
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Srinivas","Chilukuri"));
	}		

}
