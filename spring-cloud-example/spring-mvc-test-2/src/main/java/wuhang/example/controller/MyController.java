package wuhang.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wuhang on 2017/7/25.
 */
@RestController
public class MyController {

	Logger logger = LoggerFactory.getLogger(MyController.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/trace-2", method = {RequestMethod.GET})
	public String trace() {
		logger.info("trace-2");
		return "trace-2";
	}
	
}
