package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringControl {
	
	
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		//Dynamic Filtering Starts
		SomeBean someBean = new SomeBean("Value1","Value2","Value3");
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider  filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filterProvider);
		// Dynamic Filtering End
		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBeans() {
		List<SomeBean> someBeanList = Arrays.asList(new SomeBean("Value11","Value22","Value33"),new SomeBean("Value1","Value2","Value3")); 
		//Dynamic Filtering Starts
		MappingJacksonValue mapping = new MappingJacksonValue(someBeanList);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider  filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filterProvider);
		// Dynamic Filtering End		
		return mapping;
	}
	
}
