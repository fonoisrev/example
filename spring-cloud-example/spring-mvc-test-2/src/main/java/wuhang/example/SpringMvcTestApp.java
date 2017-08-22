package wuhang.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by wuhang on 2017/7/25.
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringMvcTestApp {
	
	public static void main(String[] args){
		SpringApplication.run(SpringMvcTestApp.class, args);
	}
}
