package com.example.demo.resttemplate.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.resttemplate.model.Demo;

@RestController
public class RestTemplateController
{
	@Autowired
	RestTemplate restTemplate;

	@PostMapping("/save")
	public String post(@RequestBody Demo demo)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Demo> entity = new HttpEntity<Demo>(demo, headers);

		ResponseEntity<String> responseEntity =  restTemplate.exchange("http://localhost:9090/save", HttpMethod.POST, entity, String.class);
		return responseEntity.getBody();

	}
	
	@GetMapping("/get")
	public List<Object> findAll()
	{
		String url="http://localhost:9090/get";
		Object[] objects = restTemplate.getForObject(url, Object[].class);
		
		return Arrays.asList(objects);
	}
	
	@PutMapping("/update/{id}")
	public String update(@PathVariable("id") int id,@RequestBody Demo demo)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Demo> entity = new HttpEntity<Demo>(demo, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:9090/update/" + id, HttpMethod.PUT,entity, String.class);
		return responseEntity.getBody();
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Demo> entity = new HttpEntity<Demo>(headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:9090/delete/" + id, HttpMethod.DELETE,
				entity, String.class);
		return responseEntity.getBody();	

	}
}
