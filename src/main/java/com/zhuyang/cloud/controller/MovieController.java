package com.zhuyang.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zhuyang.cloud.entity.User;
import com.zhuyang.config.RibbonConfiguration;

@RestController
@RibbonClient(name = "microservice-provider", configuration = RibbonConfiguration.class)
public class MovieController {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() { // equals to RestTemplate
		// restTemplate=new RestTemplate();
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable Long id) {
		// return restTemplate.getForEntity("http://localhost:8000/service/"+id,
		// User.class).getBody();
		return restTemplate.getForEntity("http://microservice-provider/provider/service/" + id, User.class).getBody();
	}
}