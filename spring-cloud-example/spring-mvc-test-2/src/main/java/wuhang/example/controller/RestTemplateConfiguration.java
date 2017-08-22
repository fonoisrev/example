package wuhang.example.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wuhang on 2017/7/25.
 */
@Configuration
public class RestTemplateConfiguration {
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
