package wuhang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wuhang.file.FileMatcher;
import wuhang.validate.FileValidateService;

import java.io.File;

@SpringBootApplication
@Controller
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	FileMatcher fileMatcher;

	@Autowired
	FileValidateService fileValidateService;

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	@ResponseBody
	public String doValiate(@RequestParam("file") String fileName) {
		long start = System.currentTimeMillis();

		File[] files = fileMatcher.matchFiles(fileName);
		if (ObjectUtils.isEmpty(files)) {
			return "No match files!";
		}

		String toReturn = fileValidateService.validate(fileName, files);

		long end = System.currentTimeMillis();
		System.out.println("总耗时" + (end - start) + "ms");
		return toReturn;

	}

	@Bean("myThreadPool")
	public ThreadPoolTaskScheduler threadPoolExecutor(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(1);
		return threadPoolTaskScheduler;
	}
}
