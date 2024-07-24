package com.tech.cloud.config.insurance.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RefreshScope
public class SpringCloudConfigInsuranceClientApplication {
	
	@Value("${insurance.provider.url}")
	private String url;
	
	
	@Autowired
	@Lazy
	private RestTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigInsuranceClientApplication.class, args);
	}
	
	@GetMapping("/getPlans")
	public List<String> getPlans(){
		List<String> result = template.getForObject(url, List.class);
		return result;
	}
	
	@Bean
	public RestTemplate getRestTemaplate() {
		return new RestTemplate();
	}

}
